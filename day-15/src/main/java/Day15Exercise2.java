import com.jongsoft.lang.Collections;
import grid.Grid;
import grid.Point;

import java.util.HashMap;
import java.util.Map;

import static java.lang.Integer.parseInt;

public class Day15Exercise2 implements Exercise {
    private static class VirtualGrid extends Grid<PathFinder.Node> {
        final Map<Point, PathFinder.Node> computed = new HashMap<>();

        final int virtualWidth;
        final int virtualHeight;

        public VirtualGrid(int width, int height, int expanded) {
            super(width, height);
            this.virtualHeight = height * expanded;
            this.virtualWidth = width * expanded;
        }

        @Override
        public int width() {
            return virtualWidth;
        }

        @Override
        public int height() {
            return virtualHeight;
        }

        @Override
        public PathFinder.Node at(int x, int y) {
            return computed.computeIfAbsent(new Point(x, y), point -> {
                var sourceX = x % super.width();
                var sourceY = y % super.height();
                var sourceValue = super.at(sourceX, sourceY);

                var xCorrection = x / super.width();
                var yCorrection = y / super.height();
                var correctedValue = sourceValue.getWeight() + xCorrection + yCorrection;
                if (correctedValue > 9) {
                    correctedValue -= 9;
                }

                return new PathFinder.Node(new Point(x, y), correctedValue);
            });
        }
    }

    private PathFinder pathFinder;

    @Override
    public void runOnData(String dataString) {
        var lines = Collections.List(dataString.split(NEW_LINE_MATCHER));

        var baseGrid = new VirtualGrid(lines.size(), lines.get(0).length(), 5);
        baseGrid.populate(lines, (point, number) -> new PathFinder.Node(point, parseInt(number)));

        pathFinder = new PathFinder(baseGrid);
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
