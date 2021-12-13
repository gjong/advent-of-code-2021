public class Matrix {

    boolean[][] grid;

    private Matrix(boolean[][] grid) {
        this.grid = grid;
    }

    public Matrix(int width, int height) {
        grid = new boolean[height][width];
    }

    public void activateBit(int x, int y) {
        grid[y][x] = true;
    }

    public int countNonActive() {
        int count = 0;
        for (var row : grid) {
            for (var column : row) {
                if (!column) {
                    count++;
                }
            }
        }
        return count;
    }

    public int countActive() {
        int count = 0;
        for (var row : grid) {
            for (var column : row) {
                if (column) {
                    count++;
                }
            }
        }
        return count;
    }

    public int size() {
        return grid.length * grid[0].length;
    }

    public Matrix foldOnX(int x) {
        var width = Math.max(x - 1, grid[0].length - x - 2);
        var leftShifted = x - width - 1;

        boolean[][] newGrid = new boolean[grid.length][width + 1];
        for (var row = 0; row < grid.length; row ++) {
            for (var column = 0; column < grid[0].length; column++) {
                if (column == x) {
                    continue;
                }
                if (column <= x) {
                    newGrid[row][column + leftShifted] |= grid[row][column];
                } else {
                    var newXPos = (width + 1) - (column - x);
                    newGrid[row][newXPos] |= grid[row][column];
                }
            }
        }

        return new Matrix(newGrid);
    }

    public Matrix foldOnY(int y) {
        var height = Math.max(y - 1, grid.length - y - 2);
        var topShifted = y - height - 1;

        boolean[][] newGrid = new boolean[height + 1][grid[0].length];
        for (var row = 0; row < grid.length; row ++) {
            if (row == y) {
                continue;
            }

            int shiftedY;
            if (row <= y) {
                shiftedY = row + topShifted;
            } else {
                shiftedY = (height + 1) - (row - y);
            }

            for (var column = 0; column < grid[0].length; column++) {
                newGrid[shiftedY][column] |= grid[row][column];
            }
        }

        return new Matrix(newGrid);
    }

    public String toString() {
        var builder = new StringBuilder();
        for (var row : grid) {
            for (var column : row) {
                builder.append(column ? "#" : " ");
            }
            builder.append(System.lineSeparator());
        }
        return builder.toString();
    }
}
