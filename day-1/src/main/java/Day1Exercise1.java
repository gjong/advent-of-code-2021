import util.InputProcessing;

import java.util.List;

public class Day1Exercise1 implements Exercise {
    private List<Integer> input;

    @Override
    public void runOnData(String inputFile) {
        var lines = InputProcessing.convertToLines(inputFile);
        input = InputProcessing.convertToIntegers(lines);
    }

    @Override
    public String execute() {
        int totalIncreased = 0;
        for (int counter = 1; counter < input.size(); counter++) {
            if (input.get(counter - 1) < input.get(counter)) {
                totalIncreased++;
            }
        }

        return String.valueOf(totalIncreased);
    }

    @Override
    public String day() {
        return "day-1";
    }
}
