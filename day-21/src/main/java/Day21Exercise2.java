import dirac.DiracDice;
import dirac.DiracGame;
import util.InputProcessing;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import static java.lang.Integer.parseInt;

public class Day21Exercise2 implements Exercise {

    private record QuantumDice(DiracDice diceValues, int odds) {
    }

    private static final List<QuantumDice> DICE_OPTIONS = List.of(
            new QuantumDice(() -> 3, 1),
            new QuantumDice(() -> 4, 3),
            new QuantumDice(() -> 5, 6),
            new QuantumDice(() -> 6, 7),
            new QuantumDice(() -> 7, 6),
            new QuantumDice(() -> 8, 3),
            new QuantumDice(() -> 9, 1));

    private int pawn1Position;
    private int pawn2Position;

    @Override
    public void runOnData(String dataString) {
        var lines = InputProcessing.convertToLines(dataString);
        pawn1Position = parseInt(lines.get(0).substring(28));
        pawn2Position = parseInt(lines.get(1).substring(28));
    }

    @Override
    public String execute() {
        var player1Wins = new AtomicLong();
        var player2Wins = new AtomicLong();

        new DiracGame(player -> player.score() >= 21) {
            @Override
            protected List<RoundResult> computeRound(RoundResult previousRound) {
                var originalState = previousRound.gameState();
                return DICE_OPTIONS.stream()
                        .map(dice -> new RoundResult(
                                originalState.throwDice(dice.diceValues),
                                previousRound.weight() * dice.odds))
                        .toList();
            }

            @Override
            protected void wonGame(RoundResult result) {
                if (result.gameState().players()[0].score() >= 21) {
                    player1Wins.addAndGet(result.weight());
                } else {
                    player2Wins.addAndGet(result.weight());
                }
            }
        }
                .setupBoard(new int[]{pawn1Position, pawn2Position})
                .playGame();

        return String.valueOf(Math.max(player1Wins.get(), player2Wins.get()));
    }

    @Override
    public String day() {
        return "day-21";
    }
}
