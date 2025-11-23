package minesweeper;

import command.MoveCommand;
import enums.GameStatus;
import observer.GameObserver;
import strategy.RandomMinePlacementStrategy;

public class MinesweeperSystem {
    private static final MinesweeperSystem INSTANCE = new MinesweeperSystem();
    private Game game;

    private MinesweeperSystem() {}

    public static MinesweeperSystem getInstance() {
        return INSTANCE;
    }

    public void createNewGame(int rows, int cols, int numMines) {
        this.game = new Game.Builder()
                .withDimensions(rows, cols)
                .withMines(numMines)
                .withMinePlacementStrategy(new RandomMinePlacementStrategy())
                .build();
        System.out.println("New game created (" + rows + "x" + cols + ", " + numMines + " mines).");
    }

    public void addObserver(GameObserver observer) {
        if (game != null) game.addObserver(observer);
    }

    public void processMove(MoveCommand command) {
        if (game != null && game.getStatus() != GameStatus.LOST && game.getStatus() != GameStatus.WON) {
            command.execute();
        } else {
            System.out.println("Cannot process move. Game is over or not started.");
        }
    }

    public Game getGame() {
        return game;
    }

    public GameStatus getGameStatus() {
        return (game != null) ? game.getStatus() : null;
    }
}