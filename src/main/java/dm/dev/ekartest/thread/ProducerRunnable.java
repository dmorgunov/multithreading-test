package dm.dev.ekartest.thread;

import java.util.concurrent.TimeUnit;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class ProducerRunnable implements Runnable {

    private final SystemCounter counter;

    @Getter
    @Setter
    private volatile boolean work = true;

    @Override
    public void run() {
        log.info("Started Producer Thread: {}", Thread.currentThread().getName());
        while (isWork()) {
            try {
                TimeUnit.SECONDS.sleep(1);
                int value = counter.increment();
                log.info("Thread name: {} got new value {}", Thread.currentThread().getName(), value);
            } catch (SystemCounter.StopSignal | InterruptedException stopSignal) {
                log.info("Producer Got stop signal: {}", Thread.currentThread().getName());
                break;
            }
        }
        log.info("Stopped Producer Thread: {}", Thread.currentThread().getName());
    }
}
