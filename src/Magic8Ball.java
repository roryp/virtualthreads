import java.util.Random;

public class Magic8Ball {
    void main() throws InterruptedException {
        var answers = new String[] {
                "It is certain.",
                "It is decidedly so.",
                "Without a doubt.",
                "Yes - definitely.",
                "You may rely on it."
        };

        var random = new Random();
        var answer = answers[random.nextInt(answers.length)];

        Thread.startVirtualThread(() -> System.out.println("The magic 8 ball says: " + answer))
                .join();
    }
}