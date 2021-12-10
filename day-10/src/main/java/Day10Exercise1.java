import com.jongsoft.lang.Collections;
import com.jongsoft.lang.collection.Sequence;
import util.InputProcessing;

public class Day10Exercise1 implements Exercise {

    private Sequence<String> dataLines;

    @Override
    public void runOnData(String dataString) {
        dataLines =  Collections.List(InputProcessing.convertToLines(dataString));
    }

    @Override
    public String execute() {
        var totalCost = dataLines
                .map(LineParser::new)
                .filter(LineParser::isInValid)
                .map(parser -> parser.getInvalidOn().mismatchValue)
                .sum()
                .map(Double::intValue)
                .get();
        return String.valueOf(totalCost);
    }

    @Override
    public String day() {
        return "day-10";
    }
}
