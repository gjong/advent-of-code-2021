public interface Day9Utils {
    static boolean isLowestOfNeighbours(int[][] grid, int row, int column) {
        var value = grid[row][column];

        return (row == 0 || grid[row - 1][column] > value)
                && (column == 0 || grid[row][column - 1] > value)
                && (column == (grid[0].length - 1) || grid[row][column + 1] > value)
                && (row == (grid.length - 1) || grid[row + 1][column] > value);
    }
}
