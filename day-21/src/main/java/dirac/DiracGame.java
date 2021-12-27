package dirac;

import java.util.*;
import java.util.function.Predicate;

import static util.InputProcessing.summingInt;

public abstract class DiracGame {

    private GameState startState;
    private final Predicate<Player> wonRule;

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
        var gameComputeQueue = new LinkedList<RoundResult>();
        gameComputeQueue.add(new RoundResult(startState, 1));
        while (!gameComputeQueue.isEmpty()) {
            var gameState = gameComputeQueue.poll();

            var winningPlayer = computeWinningPlayer(gameState.gameState());
            if (winningPlayer.isPresent()) {
                wonGame(gameState);
                continue;
            }

            gameComputeQueue.addAll(computeRound(gameState));
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

    public static record Player(int position, int score) {
        public Player move(int positions) {
            var newPosition = (position + positions) % 10;
            var newScore = score + (newPosition + 1);
            return new Player(newPosition, newScore);
        }
    }
    public static record RoundResult(GameState gameState, long weight) {}
    public static record GameState(int playerTurn, Player[] players) {
        public GameState throwDice(DiracDice dice) {
            var diceResults = dice.roll();
            var nextPlayerTurn = (playerTurn + 1) % players.length;
            var updatePlayers = new Player[players.length];
            System.arraycopy(players, 0, updatePlayers, 0, players.length);
            updatePlayers[playerTurn] = players[playerTurn].move(summingInt(diceResults));
            return new GameState(nextPlayerTurn, updatePlayers);
        }
    }
}
