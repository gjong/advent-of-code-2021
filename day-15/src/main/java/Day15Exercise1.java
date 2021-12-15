import com.jongsoft.lang.Collections;
import grid.Grid;

import static java.lang.Integer.parseInt;

public class Day15Exercise1 implements Exercise {

    private PathFinder pathFinder;

    @Override
    public void runOnData(String dataString) {
        var lines = Collections.List(dataString.split(NEW_LINE_MATCHER));
        var costGrid = new Grid<PathFinder.Node>(lines.head().length(), lines.size());
        costGrid.populate(lines, (point, number) -> new PathFinder.Node(point, parseInt(number)));

        this.pathFinder = new PathFinder(costGrid);
    }

    @Override
    public String execute() {
        return String.valueOf(pathFinder.solve());
    }

    @Override
    public String day() {
        return "day-15";
    }
}
