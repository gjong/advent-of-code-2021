import java.util.List;

import static util.InputProcessing.convertToIntStream;
import static util.InputProcessing.convertToIntegers;

public class Day7Exercise1 implements Exercise {

    private List<Integer> crabPositions;

    @Override
    public void runOnData(String dataString) {
        crabPositions = convertToIntegers(List.of(dataString.split(",")));
        crabPositions.sort(Integer::compareTo);
    }

    @Override
    public String execute() {
        var median = crabPositions.get(crabPositions.size() / 2);

        var totalFuelUsed = convertToIntStream(crabPositions)
                .map(position -> Math.abs(position - median))
                .sum();

        return String.valueOf(totalFuelUsed);
    }

    @Override
    public String day() {
        return "day-7";
    }
}
