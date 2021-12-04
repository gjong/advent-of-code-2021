import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

class Board {
    private final int boardSize = 5;
    private List<Integer> numbers;
    private int bingoInRound = -1;

    public Board(String[] lines) {
        this.numbers = Arrays.stream(String.join(" ", lines)
                        .split("\s+"))
                .filter(number -> !number.isEmpty())
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    public void hearNumber(Integer number) {
        if (bingoInRound == -1) {
            this.numbers.replaceAll(ingoing -> number.equals(ingoing) ? null : ingoing);
        }
    }

    public boolean hasBingo(int round) {
        if (bingoInRound > -1) {
            return true;
        }

        boolean hasBingo = false;

        for (var row = 0; row < boardSize && !hasBingo; row++) {
            var startIndex = row * 5;
            var rowNumbers = numbers.subList(startIndex, Math.min(startIndex + 5, numbers.size()));
            hasBingo = rowNumbers.stream().allMatch(Objects::isNull);
        }

        for (var column = 0; column < boardSize && !hasBingo; column++) {
            hasBingo = numbers.get(column) == null
                    && numbers.get(column + boardSize) == null
                    && numbers.get(column + boardSize * 2) == null
                    && numbers.get(column + boardSize * 3) == null
                    && numbers.get(column + boardSize * 4) == null;
        }

        if (hasBingo && bingoInRound == -1) {
            bingoInRound = round;
        }

        return hasBingo;
    }

    public int wonInRound() {
        return bingoInRound;
    }

    public int getUnmatchedTotal() {
        return this.numbers.stream()
                .filter(Objects::nonNull)
                .mapToInt(i -> i)
                .sum();
    }

    @Override
    public String toString() {
        var builder = new StringBuilder("Board [");
        for (var i=0; i < numbers.size(); i++) {
            if (numbers.get(i) == null) {
                builder.append(" X");
            } else {
                builder.append(String.format("%02d", numbers.get(i)));
            }

            builder.append(" ");
            if ((i % boardSize) == 0) {
                builder.append(System.lineSeparator());
            }
        }
        builder.append("]");
        return builder.toString();
    }
}
