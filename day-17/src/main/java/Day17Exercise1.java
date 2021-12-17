import grid.Point;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Pattern;

import static java.lang.Integer.parseInt;

public class Day17Exercise1 implements Exercise {
    private final Logger LOGGER = LoggerFactory.getLogger("Exercise");
    private final Pattern AREA_PATTERN = Pattern.compile(".*x=([\\-\\d]+)..([\\-\\d]+), y=([\\-\\d]+)..([\\-\\d]+).*");

    private TargetArea targetArea;

    @Override
    public void runOnData(String dataString) {
        var matcher = AREA_PATTERN.matcher(dataString.trim());
        if (matcher.matches()) {
            targetArea = new TargetArea(
                    new Point(
                            parseInt(matcher.group(1)),
                            parseInt(matcher.group(3))),
                    new Point(
                            parseInt(matcher.group(2)),
                            parseInt(matcher.group(4))
                    ));
        }
    }

    @Override
    public String execute() {
        var highestY = 0;

        var dx = 1;
        var dy = 1;
        while (dy < 200) { // random limit to avoid running forever
            var probe = new Probe(new Point(dx, dy));
            while (notOvershot(probe.getPosition())) {
                probe.move();
                if (targetArea.isHit(probe.getPosition()) && highestY < probe.getHighestY()) {
                    highestY = probe.getHighestY();
                    break;
                }
            }

            if (dx < targetArea.rightBottom().x()) {
                dx++;
            } else {
                dx = 0;
                dy++;
            }
        }

        return String.valueOf(highestY);
    }

    private boolean notOvershot(Point position) {
        var lowestY = Math.min(targetArea.leftTop().y(), targetArea.rightBottom().y());
        return position.x() <= targetArea.rightBottom().x() && position.y() >= lowestY;
    }

    @Override
    public String day() {
        return "day-17";
    }
}
