import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import static java.lang.Integer.parseInt;

public record Vector(Point start, Point end) {
    private static final Pattern vectorPattern = Pattern.compile("([0-9]+),([0-9]+) -> ([0-9]+),([0-9]+)");

    List<Point> toLine() {
        var direction = direction();

        var x = start.x();
        var y = start.y();

        var points = new ArrayList<Point>();
        while (x != end.x() || y != end.y()) {
            points.add(new Point(x, y));
            x += direction.x();
            y += direction.y();
        }
        points.add(end);

        return points;
    }

    Point direction() {
         return new Point(
                start.x() > end.x() ? -1 : start.x() < end.x() ? 1 : 0,
                start.y() > end.y() ? -1 : start.y() < end.y() ? 1 : 0);
    }

    boolean isHorizontal() {
        return start.x() != end.x() && start.y() == end.y();
    }

    boolean isVertical() {
        return start.x() == end.x() && start.y() != end.y();
    }

    boolean isDiagonal() {
        return !isVertical() && !isHorizontal()
                && Math.abs(end.x() - start.x()) == Math.abs(end.y() - start.y());
    }

    boolean isValid() {
        return isHorizontal() || isVertical() || isDiagonal();
    }

    public static Vector toVector(String line) {
        var match = vectorPattern.matcher(line);
        if (match.matches()) {
            var start = new Point(parseInt(match.group(1)), parseInt(match.group(2)));
            var end = new Point(parseInt(match.group(3)), parseInt(match.group(4)));

            return new Vector(start, end);
        }

        throw new IllegalArgumentException("Line does not match regex: " + line);
    }
}
