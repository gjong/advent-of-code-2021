import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public interface Boards {

    static List<Board> generateBoards(String[] boardLines) {
        var boards = new ArrayList<Board>((boardLines.length - 1) / 6);
        for (var line = 1; line < boardLines.length; line = line + 6) {
            boards.add(new Board(Arrays.copyOfRange(boardLines, line + 1, line + 6)));
        }
        return boards;
    }
}
