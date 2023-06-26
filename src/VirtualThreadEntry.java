import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class VirtualThreadEntry {
    public static void main(String[] args) throws InterruptedException {

        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) { // create a new virtual thread executor
            IntStream.range(0, 10_000) // create a stream of integers from 0 to 9999
                    .forEach(i -> executor.submit(() -> { // submit a virtual thread for each integer
                        System.out.println("Hello from virtual thread " + i);
                        return i;
                    }));
        }
    }
}
