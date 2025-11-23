package state;

import entities.Cell;

public class FlaggedState implements CellState {
    @Override
    public void reveal(Cell context) {
        // Cannot reveal a flagged cell. Do nothing.
        System.out.println("Cannot reveal a flagged cell. Unflag it first.");
    }

    @Override
    public void flag(Cell context) {
        // Unflag the cell
        context.setState(new HiddenState());
    }

    @Override
    public void unflag(Cell context) {
        context.setState(new HiddenState());
    }

    @Override
    public char getDisplayChar() {
        return 'F'; // Represents a flagged cell
    }
}
