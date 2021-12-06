import java.util.Arrays;

import static java.lang.Integer.parseInt;

public class Day6Exercise2 implements Exercise {
    private final int TOTAL_DAYS = 256;

    private long[] fishOnDayStart;

    @Override
    public void runOnData(String dataString) {
        fishOnDayStart = new long[Simulator.MAX_REPRODUCTION_AGE];

        Arrays.stream(dataString.split(","))
                .forEach(startAmount -> fishOnDayStart[parseInt(startAmount.trim())]++);
    }

    @Override
    public String execute() {
        var fishCount = new Simulator(TOTAL_DAYS)
                .simulateWithStart(fishOnDayStart);
        return String.valueOf(fishCount);
    }

    @Override
    public String day() {
        return "day-6";
    }
}
