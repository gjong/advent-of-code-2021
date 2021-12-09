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
                if (Day9Utils.isLowestOfNeighbours(grid, row, column)) {
                    sumLowers += grid[row][column] + 1;
                }
            }
        }
        return String.valueOf(sumLowers);
    }

    @Override
    public String day() {
        return "day-9";
    }
}
