import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

public class Day1Exercise2 implements Exercise {
    private String[] input;

    @Override
    public void runOnData(String inputData) {
        this.input = inputData.split(System.lineSeparator());
    }

    @Override
    public String execute() {
        var numbers = Arrays.stream(input)
                .filter(value -> !value.trim().isBlank())
                .mapToInt(Integer::parseInt)
                .toArray();

        var window = new SlidingWindow(3);
        int lastWindowSum = 0;
        int totalIncreased = 0;
        for (int number : numbers) {
            window.push(number);

            if (window.fullStack() && lastWindowSum < window.sum()) {
                totalIncreased++;
            }
            lastWindowSum = window.sum();
        }

        return String.valueOf(totalIncreased);
    }

    @Override
    public String day() {
        return "day-1";
    }
}
