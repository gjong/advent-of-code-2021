import com.jongsoft.lang.Collections;

public class Day11Exercise2 implements Exercise {

    private OctopusGrid board;

    @Override
    public void runOnData(String dataString) {
        var lines = Collections.List(dataString.split(NEW_LINE_MATCHER));
        board = new OctopusGrid(lines.head().length(), lines.size());
        board.populate(lines);
    }

    @Override
    public String execute() {
        for (int step = 0; step < 1000; step++) {
            board.nextRound();

            if (board.haveAllFlashed()) {
                return String.valueOf(step + 1);
            }
        }
        return "FAILED";
    }

    @Override
    public String day() {
        return "day-11";
    }
}
