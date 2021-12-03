import java.util.Arrays;

public class Day1Exercise1 implements Exercise {
    private String[] input;

    @Override
    public void runOnData(String inputFile) {
        this.input = inputFile.split(NEW_LINE_MATCHER);
    }

    @Override
    public String execute() {
        var lines = Arrays.stream(input)
                .filter(value -> !value.trim().isBlank())
                .mapToInt(Integer::parseInt)
                .toArray();

        int totalIncreased = 0;
        int previousValue = lines[0];
        for (int counter = 1; counter < lines.length; counter++) {
            var curValue = lines[counter];

            if (previousValue < curValue) {
                totalIncreased++;
            }
            previousValue = curValue;
        }

        return String.valueOf(totalIncreased);
    }

    @Override
    public String day() {
        return "day-1";
    }
}
