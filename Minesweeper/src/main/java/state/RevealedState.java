package state;

import entities.Cell;

public class RevealedState implements CellState {
    @Override
    public void reveal(Cell context) {
        // Already revealed. Do nothing.
    }

    @Override
    public void flag(Cell context) {
        // Cannot flag a revealed cell. Do nothing.
    }

    @Override
    public void unflag(Cell context) { /* Do nothing */ }

    @Override
    public char getDisplayChar() {
        // This is handled by Cell's getDisplayChar method, as it needs access to mine count.
        // This method shouldn't be called directly when the state is Revealed.
        return ' ';
    }
}
