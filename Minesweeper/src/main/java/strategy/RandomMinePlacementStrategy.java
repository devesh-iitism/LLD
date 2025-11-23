package strategy;

import entities.Board;

import java.util.Random;

public class RandomMinePlacementStrategy implements MinePlacementStrategy {
    @Override
    public void placeMines(Board board, int mineCount) {
        Random random = new Random();
        int minesPlaced = 0;
        int rows = board.getRows();
        int cols = board.getCols();

        while (minesPlaced < mineCount) {
            int r = random.nextInt(rows);
            int c = random.nextInt(cols);
            if (!board.getCell(r, c).isMine()) {
                board.getCell(r, c).setMine(true);
                minesPlaced++;
            }
        }
    }
}
