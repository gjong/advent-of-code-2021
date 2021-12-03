import java.util.Arrays;

public class Day3Exercise1 implements Exercise {
    private String[] input;

    @Override
    public void runOnData(String inputData) {
        this.input = inputData.split(NEW_LINE_MATCHER);
    }

    @Override
    public String execute() {
        var gammaRate = 0;
        var epsilonRate = 0;
        var bitMatrix = rotateBitMatrix();

        for (var bitPos = bitMatrix.length - 1; bitPos >= 0; bitPos--) {
            var numberOnes = countOnes(bitMatrix[bitPos]);
            var pusher = bitMatrix.length - 1 - bitPos;

            if (numberOnes > (bitMatrix[bitPos].length - numberOnes)) {
                gammaRate += (1L << pusher);
            } else {
                epsilonRate += (1L << pusher);
            }
        }

        return String.valueOf(gammaRate * epsilonRate);
    }

    private Character[][] rotateBitMatrix() {
        var bitMatrix = new Character[input[0].length()][input.length];
        for (var line = 0; line < input.length; line++) {
            for (int pos = 0; pos < input[line].length(); pos++) {
                bitMatrix[pos][line] = input[line].charAt(pos);
            }
        }
        return bitMatrix;
    }

    private long countOnes(Character[] bits) {
        return Arrays.stream(bits)
                .filter(bit -> bit == '1')
                .count();
    }

    @Override
    public String day() {
        return "day-3";
    }
}
