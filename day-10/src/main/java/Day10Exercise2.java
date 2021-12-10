import util.InputProcessing;

import java.util.List;

public class Day10Exercise2 implements Exercise {

    private List<String> dataLines;

    @Override
    public void runOnData(String dataString) {
        dataLines = InputProcessing.convertToLines(dataString);
    }

    @Override
    public String execute() {
        var autocompleteCost = dataLines.stream()
                .map(LineParser::new)
                .filter(LineParser::isValid)
                .map(LineParser::computeAutocomplete)
                .map(this::computeScore)
                .sorted()
                .toList();

        var middle = autocompleteCost.size() / 2;

        return String.valueOf(autocompleteCost.get(middle));
    }

    private long computeScore(List<Brackets> closingBrackets) {
        var score = 0L;
        for (var bracket : closingBrackets) {
            score = score * 5 + bracket.matchValue;
        }
        return score;
    }

    @Override
    public String day() {
        return "day-10";
    }
}
