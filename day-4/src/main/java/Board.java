import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

class Board {
    private final int boardSize = 5;
    private List<List<Integer>> boardRows;

    public Board(String[] lines) {
        boardRows = Arrays.stream(lines)
                .map(line ->
                        Arrays.stream(line.split(" "))
                                .filter(number -> !number.isEmpty())
                                .map(Integer::parseInt)
                                .collect(Collectors.toList()))
                .toList();
    }

    public void hearNumber(Integer number) {
        this.boardRows.forEach(row -> row.replaceAll(ingoing -> number.equals(ingoing) ? -1 : ingoing));
    }

    public boolean hasBingo() {
        var hasBingo = this.boardRows
                .stream()
                .anyMatch(row -> row.stream().allMatch(number -> number == -1));

        for (var column = 0; !hasBingo && column < boardSize; column++) {
            var columnBingo = true;
            for (var row = 0; columnBingo && row < boardSize; row++) {
                columnBingo &= boardRows.get(row).get(column) == -1;
            }

            hasBingo |= columnBingo;
        }

        return hasBingo;
    }

    public int getUnmatchedTotal() {
        return this.boardRows.stream()
                .flatMap(Collection::stream)
                .filter(number -> number > -1)
                .mapToInt(i -> i)
                .sum();
    }

    @Override
    public String toString() {
        return "Board{" +
                "boardRows=" + boardRows +
                '}';
    }
}
