package strategy;

import entities.Board;

public interface MinePlacementStrategy {
    void placeMines(Board board, int mineCount);
}