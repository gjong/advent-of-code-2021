import java.util.Arrays;
import java.util.List;

public class Day7Exercise1 implements Exercise {

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
        var median = crabPositions.get(crabPositions.size() / 2);

        var totalFuelUsed = crabPositions.stream()
                .map(position -> Math.abs(position - median))
                .mapToInt(number -> number)
                .sum();

        return String.valueOf(totalFuelUsed);
    }

    @Override
    public String day() {
        return "day-7";
    }
}
