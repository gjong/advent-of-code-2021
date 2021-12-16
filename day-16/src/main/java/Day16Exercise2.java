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
                .map(EncodedOperation::parseOperations)
                .toList();

        var computedValue = computeValue(instructions.get(0));

        return String.valueOf(computedValue);
    }

    private long computeValue(EncodedOperation encodedOperation) {
        var subProcessor = encodedOperation.getSubOperations()
                .stream()
                .map(this::computeValue);

        return switch (encodedOperation.getType()) {
            case 0 -> subProcessor.reduce(0L, Long::sum);
            case 1 -> subProcessor.reduce(1L, (value, acc) -> value * acc);
            case 2 -> subProcessor.min(Long::compareTo).orElse(0L);
            case 3 -> subProcessor.max(Long::compareTo).orElse(0L);
            case 4 -> encodedOperation.getLiteralValue();
            case 5 -> computeValue(encodedOperation.getSubOperations().get(0)) > computeValue(encodedOperation.getSubOperations().get(1))
                        ? 1 : 0;
            case 6 -> computeValue(encodedOperation.getSubOperations().get(0)) < computeValue(encodedOperation.getSubOperations().get(1))
                        ? 1 : 0;
            case 7 -> computeValue(encodedOperation.getSubOperations().get(0)) == computeValue(encodedOperation.getSubOperations().get(1))
                        ? 1 : 0;
            default -> throw new IllegalStateException("Unsupported type " + encodedOperation.getType());
        };
    }

    @Override
    public String day() {
        return "day-16";
    }
}
