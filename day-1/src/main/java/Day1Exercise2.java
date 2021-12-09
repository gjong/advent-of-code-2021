import java.util.List;

import static util.InputProcessing.convertToIntegers;
import static util.InputProcessing.convertToLines;

public class Day1Exercise2 implements Exercise {
    private List<Integer> input;

    @Override
    public void runOnData(String inputData) {
        this.input = convertToIntegers(
                convertToLines(inputData));
    }

    @Override
    public String execute() {
        var increases = 0;
        for (var idx = 4; idx < input.size(); idx++) {
            if (input.get(idx - 4) < input.get(idx)) {
                increases++;
            }
        }

        return String.valueOf(increases - 1);
    }

    @Override
    public String day() {
        return "day-1";
    }
}
