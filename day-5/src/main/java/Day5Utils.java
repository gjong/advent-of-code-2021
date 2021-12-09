import grid.Point;
import grid.Vector;

import java.util.regex.Pattern;

import static java.lang.Integer.parseInt;

public interface Day5Utils {
    Pattern vectorPattern = Pattern.compile("([0-9]+),([0-9]+) -> ([0-9]+),([0-9]+)");

    static Vector toVector(String line) {
        var match = vectorPattern.matcher(line);
        if (match.matches()) {
            var start = new Point(parseInt(match.group(1)), parseInt(match.group(2)));
            var end = new Point(parseInt(match.group(3)), parseInt(match.group(4)));

            return new Vector(start, end);
        }

        throw new IllegalArgumentException("Line does not match regex: " + line);
    }
}
