import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

public class Day8Exercise1 implements Exercise {
    private String[] lines;

    @Override
    public void runOnData(String dataString) {
        lines = dataString.split(NEW_LINE_MATCHER);
    }

    @Override
    public String execute() {
        var counted = 0;
        for (var line : lines) {
            var split = line.split("\\|");

            var decoder = new Decoder(splitToNumbers(split[0]));
            decoder.alignment();
            counted += splitToNumbers(split[1]).stream()
                    .map(decoder::countMatch1or4or7or8)
                    .mapToInt(i -> i)
                    .sum();
        }

        return String.valueOf(counted);
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
