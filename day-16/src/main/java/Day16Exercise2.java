import util.InputProcessing;

import java.util.List;

public class Day16Exercise2 implements Exercise {

    private List<String> hexStrings;

    @Override
    public void runOnData(String dataString) {
        hexStrings = InputProcessing.convertToLines(dataString);
    }

    @Override
    public String execute() {
        var instructions = hexStrings.stream()
                .map(HexDecoder::parseOperations)
                .toList();

        var computedValue = instructions.get(0).getLiteralValue();

        return String.valueOf(computedValue);
    }

    @Override
    public String day() {
        return "day-16";
    }
}
