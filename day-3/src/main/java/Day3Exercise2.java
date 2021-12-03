import java.util.Arrays;
import java.util.stream.Collectors;

public class Day3Exercise2 implements Exercise {
    private String[] input;

    @Override
    public void runOnData(String inputData) {
        this.input = inputData.split(System.lineSeparator());
    }

    @Override
    public String execute() {
        var oxygenCandidates = computeBitString(true);
        var co2Candidates = computeBitString(false);

        var multiplication = computeFromBits(oxygenCandidates) * computeFromBits(co2Candidates);

        return String.valueOf(multiplication);
    }

    @Override
    public String day() {
        return "day-3";
    }

    private String computeBitString(boolean mostCounted) {
        var candidates = Arrays.stream(input).collect(Collectors.toList());

        var totalBits = input[0].length();
        for (var counter = 0; counter < totalBits; counter++) {
            if (candidates.size() == 1) {
                break;
            }

            var index = counter;
            var counts = candidates.stream()
                    .map(bitString -> bitString.charAt(index))
                    .collect(Collectors.groupingBy(ch -> ch == '1'));

            var countOnes = counts.get(true).size();
            var countZeros = counts.get(false).size();
            if (countOnes > countZeros) {
                candidates.removeIf(candidate -> candidate.charAt(index) == (mostCounted ? '0' : '1'));
            } else if (countZeros > countOnes) {
                candidates.removeIf(candidate -> candidate.charAt(index) == (mostCounted ? '1' : '0'));
            } else {
                candidates.removeIf(candidate -> candidate.charAt(index) == (mostCounted ? '0' : '1'));
            }
        }

        return candidates.get(0);
    }

    private int computeFromBits(String bits) {
        var number = 0;

        var numberOfBits = bits.length() - 1;
        for (int counter = numberOfBits; counter >= 0; counter--) {
            if (bits.charAt(counter) == '1') {
                number += 1L << (numberOfBits - counter);
            }
        }
        return number;
    }
}
