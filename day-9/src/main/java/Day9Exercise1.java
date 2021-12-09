import java.util.List;

import static util.InputProcessing.*;

public class Day9Exercise1 implements Exercise {

    private int[][] grid;
    private int rowCount;
    private int columnCount;

    @Override
    public void runOnData(String dataString) {
        grid = gridToArray(convertToLines(dataString)
                .stream()
                .map(line -> convertToIntegers(line.toCharArray()))
                .toList());

        rowCount = grid.length;
        columnCount = grid[0].length;
    }

    @Override
    public String execute() {
        var sumLowers = 0;
        for (var row = 0; row < rowCount; row++) {
            for (var column = 0; column < columnCount; column++) {
                if (isLowestOfNeighbours(row, column)) {
                    sumLowers += grid[row][column] + 1;
                }
            }
        }
        return String.valueOf(sumLowers);
    }

    private boolean isLowestOfNeighbours(int row, int column) {
        var value = grid[row][column];

        return (row == 0 || grid[row - 1][column] > value)
                && (column == 0 || grid[row][column - 1] > value)
                && (column == (columnCount - 1) || grid[row][column + 1] > value)
                && (row == (rowCount - 1) || grid[row + 1][column] > value);
    }

    @Override
    public String day() {
        return "day-9";
    }
}
