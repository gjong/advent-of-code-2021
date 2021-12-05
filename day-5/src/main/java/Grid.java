import java.util.List;

public record Grid(int width, int height) {

    int[][] computeGrid(List<Vector> vectors) {
        int[][] grid = new int[width][height];

        vectors.forEach(v -> v.toLine()
                .forEach(p -> grid[p.x()][p.y()] += 1));

        return grid;
    }
}
