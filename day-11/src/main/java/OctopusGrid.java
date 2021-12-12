import com.jongsoft.lang.collection.Sequence;
import grid.Point;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

import static java.lang.Integer.parseInt;

class OctopusGrid {

    private final int[][] grid;
    private final long gridSize;

    private long flashes;
    private long flashesThisRound;

    public OctopusGrid(int width, int height) {
        grid = new int[width][height];
        gridSize = (long) width * height;
    }

    public void populate(Sequence<String> lines) {
        for (var index = 0; index < lines.size(); index++) {
            var trimmed = lines.get(index).trim();
            for (var position = 0; position < trimmed.length(); position++) {
                grid[index][position] = parseInt(String.valueOf(trimmed.charAt(position)));
            }
        }
    }

    public void nextRound() {
        flashesThisRound = 0;
        executeOnGrid(point -> incrementAndVerify(point.x(), point.y()));
        executeOnGrid(point -> resetOnFlashed(point.x(), point.y()));

        flashes += flashesThisRound;
    }

    public long getTotalFlashes() {
        return flashes;
    }

    public boolean haveAllFlashed() {
        return flashesThisRound == gridSize;
    }

    private void executeOnGrid(Consumer<Point> positionConsumer) {
        for (var row = 0; row < grid.length; row++) {
            for (var column = 0; column < grid[0].length; column++) {
                positionConsumer.accept(new Point(row, column));
            }
        }
    }

    private void resetOnFlashed(int row, int column) {
        if (grid[row][column] > 9) {
            grid[row][column] = 0;
        }
    }

    private void incrementAndVerify(int row, int column) {
        if (row < 0 || column < 0 || row >= grid.length || column >= grid[0].length) {
            // out of bounds, do nothing
            return;
        }

        grid[row][column]++;

        if (grid[row][column] == 10) {
            flashesThisRound++;
            incrementAndVerify(row - 1, column - 1); //up left
            incrementAndVerify(row - 1, column + 1); //up right
            incrementAndVerify(row - 1, column); //up
            incrementAndVerify(row, column + 1); // right
            incrementAndVerify(row, column - 1); // left
            incrementAndVerify(row + 1, column); // down
            incrementAndVerify(row + 1, column + 1); // down right
            incrementAndVerify(row + 1, column - 1); // down left
        }
    }

}
