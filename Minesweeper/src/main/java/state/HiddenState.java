package state;

import entities.Cell;

public class HiddenState implements CellState {
    @Override
    public void reveal(Cell context) {
        context.setState(new RevealedState());
    }

    @Override
    public void flag(Cell context) {
        context.setState(new FlaggedState());
    }

    @Override
    public void unflag(Cell context) { /* Do nothing, can't unflag a hidden cell */ }

    @Override
    public char getDisplayChar() {
        return '-'; // Represents a hidden cell
    }
}
