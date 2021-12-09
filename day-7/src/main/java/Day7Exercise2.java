import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static util.InputProcessing.convertToIntStream;
import static util.InputProcessing.convertToIntegers;

public class Day7Exercise2 implements Exercise {

    private List<Integer> crabPositions;

    @Override
    public void runOnData(String dataString) {
        crabPositions = convertToIntegers(List.of(dataString.split(",")));
        crabPositions.sort(Integer::compareTo);
    }

    @Override
    public String execute() {
        var average = convertToIntStream(crabPositions)
                .average()
                .getAsDouble();

        return String.valueOf(Math.min(
                computeFuel((int) Math.floor(average)),
                computeFuel((int) Math.ceil(average))));
    }

    private int computeFuel(int position) {
        return convertToIntStream(crabPositions)
                .map(crabPos -> Math.abs(crabPos - position))
                .map(steps -> IntStream.rangeClosed(1, steps).sum())
                .sum();
    }

    @Override
    public String day() {
        return "day-7";
    }
}
