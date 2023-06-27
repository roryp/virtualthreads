import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.atomic.AtomicInteger;

public class VirtualThreadTest {

    public static void main(String[] args) {
        AtomicInteger threadCount = new AtomicInteger(0);

        Instant start = Instant.now();
        while (Duration.between(start, Instant.now()).getSeconds() < 1) {
            Thread.startVirtualThread(() -> {
                threadCount.incrementAndGet();
            });
        }

        System.out.println("Number of threads created in 1 second: " + threadCount.get());
    }
}
