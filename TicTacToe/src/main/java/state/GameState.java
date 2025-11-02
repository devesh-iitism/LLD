package state;
import entities.Player;
import game.Game;

public interface GameState {
    void handleMove(Game game, Player player, int row, int col);
}