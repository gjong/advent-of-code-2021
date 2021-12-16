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
                var actualWidth = super.width();
                var actualHeight = super.height();
                var sourceX = x % actualWidth;
                var sourceY = y % actualHeight;
                var sourceValue = super.at(sourceX, sourceY);

                var xCorrection = x / actualWidth;
                var yCorrection = y / actualHeight;
                var correctedValue = sourceValue.getWeight() + xCorrection + yCorrection;
                if (correctedValue > 9) {
                    correctedValue -= 9;
                }

                return new PathFinder.Node(correctedValue);
            });
        }
    }

    private PathFinder pathFinder;

    @Override
    public void runOnData(String dataString) {
        var lines = Collections.List(dataString.split(NEW_LINE_MATCHER));

        var baseGrid = new VirtualGrid(lines.size(), lines.get(0).length(), 5);
        baseGrid.populate(lines, (point, number) -> new PathFinder.Node(parseInt(number)));

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
