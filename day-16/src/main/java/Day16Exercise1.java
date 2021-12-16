import util.InputProcessing;

import java.util.List;

public class Day16Exercise1 implements Exercise {

    private List<String> hexStrings;

    @Override
    public void runOnData(String dataString) {
        hexStrings = InputProcessing.convertToLines(dataString);
    }

    @Override
    public String execute() {
        var instructions = hexStrings.stream()
                .map(EncodedOperation::parseOperations)
                .toList();

        var cumulativeVersion = 0;
        for (var instruction : instructions) {
            cumulativeVersion += computeVersion(instruction);
        }

        return String.valueOf(cumulativeVersion);
    }

    private int computeVersion(EncodedOperation operation) {
        int summedVersion = operation.getVersion();

        for (var child : operation.getSubOperations()) {
            summedVersion += computeVersion(child);
        }

        return summedVersion;
    }

    @Override
    public String day() {
        return "day-16";
    }
}
