import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.lang.Integer.parseInt;

public class Day3Exercise2 implements Exercise {
    private final Logger logger = LoggerFactory.getLogger("Day-3-Exercise-2");

    private String[] input;
    private int amountOfBits;

    @Override
    public void runOnData(String inputData) {
        this.input = inputData.split(NEW_LINE_MATCHER);
        this.amountOfBits = this.input[0].length();
    }

    @Override
    public String execute() {
        var oxygenCandidates = computeBitString(true);
        var co2Candidates = computeBitString(false);

        var multiplication = parseInt(oxygenCandidates, 2) * parseInt(co2Candidates, 2);

        return String.valueOf(multiplication);
    }

    @Override
    public String day() {
        return "day-3";
    }

    private String computeBitString(boolean mostCounted) {
        logger.debug("Computing the bit for most counted bit {} in position.", mostCounted);
        var candidates = Arrays.stream(input).toList();

        for (var counter = 0; counter < amountOfBits && candidates.size() > 1; counter++) {
            candidates = removeIrrelevantBits(mostCounted, candidates, counter);
        }

        return candidates.get(0);
    }

    private List<String> removeIrrelevantBits(boolean mostCounted, List<String> candidates, int index) {
        var counts = candidates.stream()
                .map(bitString -> bitString.charAt(index))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        var lessOnesThanZero = counts.get('1') < counts.get('0');
        char filterForBit = lessOnesThanZero
                ? (mostCounted ? '1' : '0')
                : (mostCounted ? '0' : '1');

        return candidates.stream()
                .filter(candidate -> candidate.charAt(index) != filterForBit)
                .toList();
    }
}
