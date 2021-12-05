import java.util.Arrays;
import java.util.List;

public class Day5Exercise2 implements Exercise {

    private List<Vector> vectors;

    @Override
    public void runOnData(String dataString) {
        vectors = Arrays.stream(dataString.split(NEW_LINE_MATCHER))
                .map(Vector::toVector)
                .filter(Vector::isValid)
                .toList();
    }

    @Override
    public String execute() {
        var grid = new Grid(1000, 1000);

        var totalCount = 0;
        var drawnGrid = grid.computeGrid(vectors);
        for (var x = 0; x < drawnGrid.length; x++) {
            for (var y = 0; y < drawnGrid[x].length; y++) {
                if (drawnGrid[x][y] > 1) {
                    totalCount++;
                }
            }
        }

        return String.valueOf(totalCount);
    }

    @Override
    public String day() {
        return "day-5";
    }
}
