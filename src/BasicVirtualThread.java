import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class BasicVirtualThread {
    public static void main(String[] args) throws InterruptedException {

        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) { // create a new virtual thread executor
            IntStream.range(0, 20_000) // create a stream of integers from 0 to 19,999
                    .forEach(i -> executor.submit(() -> { // submit a virtual thread for each integer
                        System.out.println("Hello from virtual thread " + i); // print a message from the virtual thread
                        return i;
                    }));
        }
    }
}