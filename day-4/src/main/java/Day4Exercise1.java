import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class Day4Exercise1 implements Exercise {
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

        int lastAnnounced = 0;
        Board winningBoard = null;
        while (winningBoard == null && !drawnNumbers.isEmpty()) {
            lastAnnounced = drawnNumbers.poll();
            var potentialWinner = announceNumber(lastAnnounced);
            if (potentialWinner.isPresent()) {
                winningBoard = potentialWinner.get();
            }
        }

        return String.valueOf(lastAnnounced * winningBoard.getUnmatchedTotal());
    }

    private Optional<Board> announceNumber(Integer number) {
        boards.forEach(b -> b.hearNumber(number));

        return boards.stream()
                .filter(b -> b.hasBingo(0))
                .findAny();
    }

    @Override
    public String day() {
        return "day-4";
    }
}
