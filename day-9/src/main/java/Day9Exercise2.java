import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

import static util.InputProcessing.*;

public class Day9Exercise2 implements Exercise {

    private int[][] grid;
    private boolean[][] visited;
    private int rowCount;
    private int columnCount;

    record Point(int row, int column) {}

    @Override
    public void runOnData(String dataString) {
        grid = gridToArray(convertToLines(dataString)
                .stream()
                .map(line -> convertToIntegers(line.toCharArray()))
                .toList());

        rowCount = grid.length;
        columnCount = grid[0].length;
        visited = new boolean[rowCount][columnCount];
    }

    @Override
    public String execute() {
        var lowestPoints = new ArrayList<Point>();
        for (var row = 0; row < rowCount; row++) {
            for (var column = 0; column < columnCount; column++) {
                if (isLowestOfNeighbours(row, column)) {
                    lowestPoints.add(new Point(row, column));
                }
            }
        }

        var basin = lowestPoints.stream()
                .map(point -> expandBasin(point.row, point.column))
                .sorted(Integer::compareTo)
                .collect(Collectors.toList());
        Collections.reverse(basin);

        return String.valueOf(basin.get(0) * basin.get(1) * basin.get(2));
    }

    private int expandBasin(int row, int column) {
        if (row < 0 || column < 0 || row >= rowCount || column >= columnCount || grid[row][column] == 9 || visited[row][column]) {
            return 0;
        }
        visited[row][column] = true;

        var basinSize = 1; // current point
        basinSize += expandBasin(row + 1, column);
        basinSize += expandBasin(row - 1, column);
        basinSize += expandBasin(row, column + 1);
        basinSize += expandBasin(row, column - 1);
        return basinSize;
    }

    @Override
    public String day() {
        return "day-9";
    }

    private boolean isLowestOfNeighbours(int row, int column) {
        var value = grid[row][column];

        return (row == 0 || grid[row - 1][column] > value)
                && (column == 0 || grid[row][column - 1] > value)
                && (column == (columnCount - 1) || grid[row][column + 1] > value)
                && (row == (rowCount - 1) || grid[row + 1][column] > value);
    }
}
