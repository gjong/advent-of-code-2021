import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class Day4Exercise2 implements Exercise {
    private final Logger logger = LoggerFactory.getLogger("Exercise");

    private Queue<Integer> drawnNumbers;
    private List<Board> boards;

    @Override
    public void runOnData(String dataString) {
        var lines = dataString.split(NEW_LINE_MATCHER);

        this.drawnNumbers = new LinkedList<>(Arrays.stream(lines[0].split(","))
                .map(Integer::parseInt)
                .toList());

        this.boards = Boards.generateBoards(lines);
    }

    @Override
    public String execute() {
        logger.info("Running bingo on drawn numbers: {}.", this.drawnNumbers);

        int round = 0;
        int lastAnnounced = 0;
        Board winningBoard = null;
        while (!drawnNumbers.isEmpty()) {
            lastAnnounced = drawnNumbers.poll();
            announceNumber(round, lastAnnounced);
            if (boards.stream().allMatch(b -> b.hasBingo(0))) {
                winningBoard = boards.stream()
                        .max(Comparator.comparingInt(Board::wonInRound))
                        .get();
                break;
            }

            round++;
        }

        return String.valueOf(lastAnnounced * winningBoard.getUnmatchedTotal());
    }

    private void announceNumber(Integer round, Integer number) {
        boards.forEach(b -> {
            b.hearNumber(number);
            b.hasBingo(round);
        });
    }

    @Override
    public String day() {
        return "day-4";
    }
}
