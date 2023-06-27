import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.atomic.AtomicInteger;

public class VirtualThreadSpeedTest {

    public static void main(String[] args) {
        AtomicInteger threadCount = new AtomicInteger(0);

        Instant start = Instant.now();
        while (threadCount.get() <= 1_000_000) {
            Thread.startVirtualThread(() -> {
                threadCount.incrementAndGet();
            });
        }

        System.out.println("Number of threads: " +
            threadCount.get());        
        System.out.println("Time taken: " +
            Duration.between(start, Instant.now()).toMillis() +
            " ms");    
        }
}