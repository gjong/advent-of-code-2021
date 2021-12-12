import com.jongsoft.lang.Collections;

public class Day11Exercise1 implements Exercise {

    private OctopusGrid board;

    @Override
    public void runOnData(String dataString) {
        var lines = Collections.List(dataString.split(NEW_LINE_MATCHER));
        board = new OctopusGrid(lines.head().length(), lines.size());
        board.populate(lines);
    }

    @Override
    public String execute() {
        for (int step = 0; step < 100; step++) {
            board.nextRound();
        }
        return String.valueOf(board.getTotalFlashes());
    }

    @Override
    public String day() {
        return "day-11";
    }
}
