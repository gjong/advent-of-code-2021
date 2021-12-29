package dirac;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public abstract class DiracGame {

    private final Predicate<Player> wonRule;
    private GameState startState;

    public DiracGame(Predicate<Player> wonRule) {
        this.wonRule = wonRule;
    }

    public DiracGame setupBoard(int[] startPositionPlayers) {
        var players = new Player[startPositionPlayers.length];
        for (var index = 0; index < startPositionPlayers.length; index++) {
            players[index] = new Player(startPositionPlayers[index] - 1, 0);
        }
        this.startState = new GameState(0, players);
        return this;
    }

    public void playGame() {
        computeGameState(new RoundResult(startState, 1));
    }

    private void computeGameState(RoundResult currentState) {
        var hasWinner = computeWinningPlayer(currentState.gameState);
        if (hasWinner.isPresent()) {
            wonGame(currentState);
        } else {
            computeRound(currentState)
                    .forEach(this::computeGameState);
        }
    }

    protected abstract void wonGame(RoundResult result);

    protected abstract List<RoundResult> computeRound(RoundResult previousRound);

    protected Optional<Player> computeWinningPlayer(GameState gameState) {
        for (var player : gameState.players()) {
            if (wonRule.test(player)) {
                return Optional.of(player);
            }
        }

        return Optional.empty();
    }

    public record Player(int position, int score) {
        public Player move(int positions) {
            var newPosition = (position + positions) % 10;
            var newScore = score + (newPosition + 1);
            return new Player(newPosition, newScore);
        }
    }

    public record RoundResult(GameState gameState, long weight) {
    }

    public record GameState(int playerTurn, Player[] players) {
        public GameState throwDice(DiracDice dice) {
            var diceResults = dice.roll();
            var updatePlayers = Arrays.copyOf(players, players.length);
            updatePlayers[playerTurn] = players[playerTurn].move(diceResults);
            return new GameState((playerTurn + 1) % players.length, updatePlayers);
        }
    }
}
