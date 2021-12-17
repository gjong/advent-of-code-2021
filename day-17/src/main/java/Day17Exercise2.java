import grid.Point;

import java.util.regex.Pattern;

import static java.lang.Integer.parseInt;

public class Day17Exercise2 implements Exercise {
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
        var hittingTrajectories = 0;

        var dx = 1;
        var dy = Math.min(targetArea.leftTop().y(), targetArea.rightBottom().y());
        while (dy < 200) { // random limit to avoid running forever
            var probe = new Probe(new Point(dx, dy));
            while (notOvershot(probe.getPosition())) {
                probe.move();
                if (targetArea.isHit(probe.getPosition())) {
                    hittingTrajectories++;
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

        return String.valueOf(hittingTrajectories);
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
