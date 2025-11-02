package strategy;

import entities.Board;
import entities.Player;

public interface WinningStrategy {
    boolean checkWinner(Board board, Player player);
}