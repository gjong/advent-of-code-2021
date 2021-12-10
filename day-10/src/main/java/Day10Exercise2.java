import com.jongsoft.lang.Collections;
import com.jongsoft.lang.collection.Sequence;
import util.InputProcessing;

public class Day10Exercise2 implements Exercise {

    private Sequence<String> dataLines;

    @Override
    public void runOnData(String dataString) {
        dataLines = Collections.List(InputProcessing.convertToLines(dataString));
    }

    @Override
    public String execute() {
        var autocompleteCost = dataLines
                .map(LineParser::new)
                .filter(LineParser::isValid)
                .map(parser -> parser
                        .computeAutocomplete()
                        .foldLeft(0, (accumulator, bracket) -> accumulator * 5 + bracket.matchValue))
                .sorted();

        var middle = autocompleteCost.size() / 2;

        return String.valueOf(autocompleteCost.get(middle));
    }

    @Override
    public String day() {
        return "day-10";
    }
}
