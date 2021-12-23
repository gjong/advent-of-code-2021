import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static util.InputProcessing.convertToLines;

public class Day18Exercise2 implements Exercise {
    private static final Logger LOGGER = LoggerFactory.getLogger("Exercise");

    List<String> snailNumbers;

    @Override
    public void runOnData(String dataString) {
        snailNumbers = convertToLines(dataString);
    }

    @Override
    public String execute() {
        var maxMagnitude = Long.MIN_VALUE;
        for (var index = 0; index < snailNumbers.size(); index++) {
            for (var computeIndex = 0; computeIndex < snailNumbers.size(); computeIndex++) {
                if (computeIndex == index)
                    continue;
                var addedSnail = SnailNumber.generateSnailNumber(snailNumbers.get(index))
                                .add(SnailNumber.generateSnailNumber(snailNumbers.get(computeIndex)));

                addedSnail.reduce();
                var magnitude = addedSnail.magnitude();
                if (magnitude > maxMagnitude) {
                    LOGGER.debug("Addition for {} and {}", snailNumbers.get(index), snailNumbers.get(computeIndex));
                    LOGGER.debug("Result {},{} = {} with output [{}]", index, computeIndex, addedSnail.magnitude(), addedSnail);
                    maxMagnitude = magnitude;
                }
            }
        }
        return String.valueOf(maxMagnitude);
    }

    @Override
    public String day() {
        return "day-18";
    }
}
