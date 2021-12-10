import util.InputProcessing;

import java.util.List;

public class Day10Exercise1 implements Exercise {

    private List<String> dataLines;

    @Override
    public void runOnData(String dataString) {
        dataLines = InputProcessing.convertToLines(dataString);
    }

    @Override
    public String execute() {
        var totalCost = dataLines.stream()
                .map(LineParser::new)
                .filter(LineParser::isInValid)
                .map(LineParser::getInvalidOn)
                .mapToInt(b -> b.mismatchValue)
                .sum();
        return String.valueOf(totalCost);
    }

    @Override
    public String day() {
        return "day-10";
    }
}
