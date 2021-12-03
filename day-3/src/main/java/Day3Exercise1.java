import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day3Exercise1 implements Exercise {
    private String[] input;

    @Override
    public void runOnData(String inputData) {
        this.input = inputData.split(System.lineSeparator());
    }

    @Override
    public String execute() {
        var mask = IntStream.range(0, input[0].length())
                .mapToObj(i -> new BitMask())
                .collect(Collectors.toList());
        for (String rawInstruction : input) {
            for (int pos = 0; pos < rawInstruction.length(); pos++) {
                mask.get(pos)
                        .push(rawInstruction.charAt(pos));
            }
        }

        var gammaRate = 0;
        var epsilonRate = 0;
        var startPosition = mask.size() - 1;
        for (int counter = startPosition; counter >= 0; counter--) {
            var pusher = startPosition - counter;

            if (mask.get(counter).bit(true)) {
                gammaRate += (1L << pusher);
            }

            if (mask.get(counter).bit(false)) {
                epsilonRate += (1L << pusher);
            }
        }

        return String.valueOf(gammaRate * epsilonRate);
    }

    @Override
    public String day() {
        return "day-3";
    }
}
