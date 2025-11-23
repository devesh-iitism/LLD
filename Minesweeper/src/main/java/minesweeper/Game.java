package minesweeper;

import entities.Board;
import entities.Cell;
import enums.GameStatus;
import observer.GameObserver;
import strategy.MinePlacementStrategy;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private final Board board;
    private GameStatus gameStatus;
    private final int mineCount;
    private final List<GameObserver> observers = new ArrayList<>();

    private Game(Board board, int mineCount) {
        this.board = board;
        this.mineCount = mineCount;
        this.gameStatus = GameStatus.IN_PROGRESS;
    }

    public void addObserver(GameObserver observer) {
        observers.add(observer);
    }

    private void notifyObservers() {
        for (GameObserver observer : observers) {
            observer.update(this);
        }
    }

    public void revealCell(int r, int c) {
        if (gameStatus != GameStatus.IN_PROGRESS) return;

        Cell cell = board.getCell(r, c);
        if (cell.isRevealed() || cell.isFlagged()) return;

        cell.reveal();

        if (cell.isMine()) {
            gameStatus = GameStatus.LOST;
        } else {
            if (cell.getAdjacentMinesCount() == 0) {
                revealNeighbors(r, c);
            }
            checkWinCondition();
        }
        notifyObservers();
    }

    private void revealNeighbors(int r, int c) {
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) continue;
                int nr = r + i;
                int nc = c + j;
                if (nr >= 0 && nr < getRows() && nc >= 0 && nc < getCols()) {
                    revealCell(nr, nc); // Recursive call
                }
            }
        }
    }

    public void flagCell(int r, int c) {
        if (gameStatus != GameStatus.IN_PROGRESS) return;
        board.getCell(r, c).flag();
        notifyObservers();
    }

    public void unflagCell(int row, int col) {
        if (gameStatus != GameStatus.IN_PROGRESS) return;
        Cell cell = board.getCell(row, col);
        if (cell != null) cell.unflag();
    }


    private void checkWinCondition() {
        int revealedCount = 0;
        for (int r = 0; r < getRows(); r++) {
            for (int c = 0; c < getCols(); c++) {
                if (board.getCell(r, c).isRevealed()) {
                    revealedCount++;
                }
            }
        }
        if (revealedCount == (getRows() * getCols()) - mineCount) {
            gameStatus = GameStatus.WON;
        }
    }

    // Getters
    public GameStatus getStatus() { return gameStatus; }
    public int getRows() { return board.getRows(); }
    public int getCols() { return board.getCols(); }
    public char getCellDisplayChar(int r, int c) {
        // For final display when game is over
        if (gameStatus == GameStatus.LOST && board.getCell(r, c).isMine()) {
            return '*';
        }
        return board.getCell(r, c).getDisplayChar();
    }

    public Board getBoard() {
        return board;
    }

    // --- Builder Pattern ---
    public static class Builder {
        private int rows = 10;
        private int cols = 10;
        private int mineCount = 10;
        private MinePlacementStrategy minePlacementStrategy;

        public Builder withDimensions(int rows, int cols) {
            this.rows = rows;
            this.cols = cols;
            return this;
        }

        public Builder withMines(int mineCount) {
            this.mineCount = mineCount;
            return this;
        }

        public Builder withMinePlacementStrategy(MinePlacementStrategy strategy) {
            this.minePlacementStrategy = strategy;
            return this;
        }

        public Game build() {
            if (mineCount >= rows * cols) {
                throw new IllegalArgumentException("Mine count must be less than the total number of cells.");
            }
            Board board = new Board(rows, cols, mineCount, minePlacementStrategy);
            return new Game(board, mineCount);
        }
    }
}