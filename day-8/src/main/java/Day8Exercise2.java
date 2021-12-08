import java.util.Arrays;
import java.util.List;

public class Day8Exercise2 implements Exercise {
    private String[] lines;

    @Override
    public void runOnData(String dataString) {
        lines = dataString.split(NEW_LINE_MATCHER);
    }

    @Override
    public String execute() {
        var sum = 0;
        for (var line : lines) {
            var split = line.split("\\|");

            var decoder = new Decoder(splitToNumbers(split[0]));
            decoder.alignment();
            sum += decoder.decode(splitToNumbers(split[1]));
        }

        return String.valueOf(sum);
    }

    @Override
    public String day() {
        return "day-8";
    }

    private List<CodedEntry> splitToNumbers(String token) {
        return Arrays.stream(token.trim().split(" "))
                .map(String::trim)
                .map(CodedEntry::new)
                .toList();
    }
}
