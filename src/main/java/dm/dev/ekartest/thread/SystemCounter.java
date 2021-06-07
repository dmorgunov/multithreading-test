package dm.dev.ekartest.thread;

import dm.dev.ekartest.model.FinishThreadsExecutionEvent;
import dm.dev.ekartest.model.SetNewValueEvent;
import dm.dev.ekartest.repository.ThreadEventRepository;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SystemCounter {

    private final AtomicInteger counter;
    private final AtomicBoolean stopped;
    private final ThreadEventRepository threadEventRepository;

    public SystemCounter(@Value("${count.startValue}") int startValue,
                         ThreadEventRepository threadEventRepository) {
        this.counter = new AtomicInteger(startValue);
        this.stopped = new AtomicBoolean(false);
        this.threadEventRepository = threadEventRepository;
    }

    public void setNewValue(int newValue) {
        synchronized (this){
            this.counter.set(newValue);
            stopped.set(false);
        }

        saveNewValueEvent(newValue);
    }

    public synchronized int increment() throws StopSignal {
        throwSignalIfStopped();
        int newValue = counter.incrementAndGet();
        if (newValue == 100){
            stopped.set(true);
            saveStopTimeEvent();
        }
        return newValue;
    }

    public synchronized int decrement() throws StopSignal {
        throwSignalIfStopped();
        int newValue = counter.decrementAndGet();
        if (newValue == 0){
            stopped.set(true);
            saveStopTimeEvent();
        }
        return newValue;
    }

    private void throwSignalIfStopped() throws StopSignal {
        if (stopped.get()) {
            throw new StopSignal();
        }
    }

    public static class StopSignal extends Exception {}

    private void saveNewValueEvent(int newValue) {
        threadEventRepository.save(new SetNewValueEvent(newValue));
    }

    private void saveStopTimeEvent() {
        threadEventRepository.save(new FinishThreadsExecutionEvent());
    }

}
