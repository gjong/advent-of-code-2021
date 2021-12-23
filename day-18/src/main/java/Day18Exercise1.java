import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import static util.InputProcessing.convertToLines;

public class Day18Exercise1 implements Exercise {
    private static final Logger LOGGER = LoggerFactory.getLogger("Exercise");

    private List<String> linePairs;

    @Override
    public void runOnData(String dataString) {
        linePairs = convertToLines(dataString);
    }

    @Override
    public String execute() {
        SnailNumber snailNumber = null;
        for (var line : linePairs) {
            if (snailNumber == null) {
                snailNumber = SnailNumber.generateSnailNumber(line);
            } else {
                snailNumber = snailNumber.add(SnailNumber.generateSnailNumber(line));
            }

            snailNumber.reduce();
        }

        LOGGER.debug("Output of snail fish: {}", snailNumber);

        return String.valueOf(snailNumber.magnitude());
    }

    @Override
    public String day() {
        return "day-18";
    }
}
