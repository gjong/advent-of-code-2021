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

        int lastAnnounced = 0;
        while (!drawnNumbers.isEmpty() && boards.size() > 1) {
            announceNumber(drawnNumbers.poll());
            boards.removeIf(Board::hasBingo);
        }

        while (!drawnNumbers.isEmpty()) {
            lastAnnounced = drawnNumbers.poll();
            announceNumber(lastAnnounced);
            if (boards.stream().allMatch(Board::hasBingo)) {
                break;
            }
        }

        return String.valueOf(lastAnnounced * boards.get(0).getUnmatchedTotal());
    }

    private void announceNumber(Integer number) {
        boards.forEach(b -> b.hearNumber(number));
    }

    @Override
    public String day() {
        return "day-4";
    }
}
