package dm.dev.ekartest.service;

import dm.dev.ekartest.dto.ChangeThreadsDTO;
import dm.dev.ekartest.model.ChangeBaseEvent;
import dm.dev.ekartest.repository.ThreadEventRepository;
import dm.dev.ekartest.thread.ConsumerRunnable;
import dm.dev.ekartest.thread.ProducerRunnable;
import dm.dev.ekartest.thread.SystemCounter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ThreadManagerService {
    private final ThreadEventRepository threadEventRepository;

    private final ExecutorService producerExecutor;
    private final ExecutorService consumerExecutor;
    private final SystemCounter systemCounter;

    public ThreadManagerService(ThreadEventRepository threadEventRepository, SystemCounter systemCounter) {
        this.systemCounter = systemCounter;
        this.threadEventRepository = threadEventRepository;
        this.producerExecutor = Executors.newCachedThreadPool();
        this.consumerExecutor = Executors.newCachedThreadPool();
    }

    public void addThreads(ChangeThreadsDTO changeThreadsDto) {
        threadEventRepository.save(new ChangeBaseEvent(
                changeThreadsDto.getConsumersCount(),
                changeThreadsDto.getProducersCount()));

        IntStream
                .range(0, changeThreadsDto.getConsumersCount())
                .forEach(v-> consumerExecutor.submit(new ConsumerRunnable(systemCounter)));

        IntStream
                .range(0, changeThreadsDto.getProducersCount())
                .forEach(v-> producerExecutor.submit(new ProducerRunnable(systemCounter)));
    }
}
