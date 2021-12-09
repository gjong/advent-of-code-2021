package grid;

import java.util.ArrayList;
import java.util.List;

public record Vector(Point start, Point end) {

    public List<Point> toLine() {
        var direction = new Point(
                start.x() > end.x() ? -1 : start.x() < end.x() ? 1 : 0,
                start.y() > end.y() ? -1 : start.y() < end.y() ? 1 : 0);

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

    public boolean isHorizontal() {
        return start.x() != end.x() && start.y() == end.y();
    }

    public boolean isVertical() {
        return start.x() == end.x() && start.y() != end.y();
    }

    /**
     * Is a line in a 45 degree angle.
     */
    public boolean isDiagonal() {
        return !isVertical() && !isHorizontal()
                && Math.abs(end.x() - start.x()) == Math.abs(end.y() - start.y());
    }

    /**
     * True if either {@link #isHorizontal()} or {@link #isVertical()} or {@link #isDiagonal()}
     * is true.
     */
    public boolean isValid() {
        return isHorizontal() || isVertical() || isDiagonal();
    }
}
