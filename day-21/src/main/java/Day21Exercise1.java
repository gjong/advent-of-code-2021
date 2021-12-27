import dirac.DiracDice;
import dirac.DiracGame;
import util.InputProcessing;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.Integer.parseInt;

public class Day21Exercise1 implements Exercise {
    int pawn1Position;
    int pawn2Position;

    @Override
    public void runOnData(String dataString) {
        var lines = InputProcessing.convertToLines(dataString);
        pawn1Position = parseInt(lines.get(0).substring(28));
        pawn2Position = parseInt(lines.get(1).substring(28));
    }

    @Override
    public String execute() {
        var lowestScore = new AtomicInteger();
        var totalThrows = new AtomicInteger();
        var direcDice = new DiracDice.SimpleDice();
        new DiracGame(player -> player.score() >= 1000) {
            @Override
            protected List<RoundResult> computeRound(RoundResult previousRound) {
                totalThrows.addAndGet(3);
                var nextState = previousRound.gameState().throwDice(direcDice);
                return List.of(new RoundResult(nextState, 1));
            }

            @Override
            protected void wonGame(RoundResult result) {
                lowestScore.set(Math.min(
                        result.gameState().players()[0].score(),
                        result.gameState().players()[1].score()));
            }
        }
                .setupBoard(new int[]{pawn1Position, pawn2Position})
                .playGame();

        return String.valueOf(totalThrows.get() * lowestScore.get());
    }

    @Override
    public String day() {
        return "day-21";
    }
}
