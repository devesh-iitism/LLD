package observer;

import minesweeper.Game;

public interface GameObserver {
    void update(Game game);
}