import com.jongsoft.lang.API;
import com.jongsoft.lang.Collections;
import com.jongsoft.lang.collection.Sequence;
import com.jongsoft.lang.collection.tuple.Pair;

public class Day8Exercise1 implements Exercise {
    private String[] lines;

    @Override
    public void runOnData(String dataString) {
        lines = dataString.split(NEW_LINE_MATCHER);
    }

    @Override
    public String execute() {
        var counted = Collections.List(lines)
                .map(line -> API.<String, Pair<String, String>>Tuple(line.split("\\|")))
                .map(tuple -> API.Tuple(
                        new Decoder(splitToNumbers(tuple.getFirst()).toJava()),
                        splitToNumbers(tuple.getSecond())))
                .map(tuple -> tuple
                        .getSecond()
                        .map(tuple.getFirst()::countMatch1or4or7or8)
                        .sum()
                        .get())
                .sum()
                .get();

        return String.valueOf(counted.intValue());
    }

    @Override
    public String day() {
        return "day-8";
    }

    private Sequence<CodedEntry> splitToNumbers(String token) {
        return Collections.List(token.trim().split(" "))
                .map(String::trim)
                .map(CodedEntry::new);
    }
}
