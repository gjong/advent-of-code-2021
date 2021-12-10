import com.jongsoft.lang.Collections;
import com.jongsoft.lang.collection.Sequence;

public class Day7Exercise1 implements Exercise {

    private Sequence<Integer> crabPositions;

    @Override
    public void runOnData(String dataString) {
        crabPositions = Collections.List(dataString.split(","))
                .map(Integer::parseInt)
                .sorted();
    }

    @Override
    public String execute() {
        var median = (int) crabPositions.median();

        var totalFuelUsed = crabPositions
                .map(position -> Math.abs(position - median))
                .sum()
                .get()
                .intValue();

        return String.valueOf(totalFuelUsed);
    }

    @Override
    public String day() {
        return "day-7";
    }
}
