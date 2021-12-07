import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Day7Exercise2 implements Exercise {

    private List<Integer> crabPositions;

    @Override
    public void runOnData(String dataString) {
        crabPositions = Arrays.stream(dataString.split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .sorted(Integer::compareTo)
                .toList();
    }

    @Override
    public String execute() {
        var average = crabPositions.stream()
                .mapToInt(i -> i)
                .average()
                .getAsDouble();

        return String.valueOf(Math.min(
                computeFuel((int) Math.floor(average)),
                computeFuel((int) Math.ceil(average))));
    }

    private int computeFuel(int position) {
        return crabPositions.stream()
                .map(crabPos -> Math.abs(crabPos - position))
                .mapToInt(number -> number)
                .map(steps -> IntStream.rangeClosed(1, steps).sum())
                .sum();
    }

    @Override
    public String day() {
        return "day-7";
    }
}
