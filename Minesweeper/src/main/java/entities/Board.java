package entities;

import strategy.MinePlacementStrategy;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private final int rows;
    private final int cols;
    private final Cell[][] cells;

    public Board(int rows, int cols, int mineCount, MinePlacementStrategy minePlacementStrategy) {
        this.rows = rows;
        this.cols = cols;
        this.cells = new Cell[rows][cols];
        initializeCells();
        minePlacementStrategy.placeMines(this, mineCount);
        calculateAdjacentMines();
    }

    private void initializeCells() {
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                cells[r][c] = new Cell();
            }
        }
    }

    private void calculateAdjacentMines() {
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (!cells[r][c].isMine()) {
                    int count = (int) getNeighbors(r, c).stream().filter(Cell::isMine).count();
                    cells[r][c].setAdjacentMinesCount(count);
                }
            }
        }
    }

    public List<Cell> getNeighbors(int r, int c) {
        List<Cell> neighbors = new ArrayList<>();
        int[] dr = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dc = {-1, 0, 1, -1, 1, -1, 0, 1};
        for (int i = 0; i < 8; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            if (nr >= 0 && nr < rows && nc >= 0 && nc < cols) {
                neighbors.add(cells[nr][nc]);
            }
        }
        return neighbors;
    }

    public Cell getCell(int r, int c) {
        return cells[r][c];
    }
    public int getRows() { return rows; }
    public int getCols() { return cols; }
}