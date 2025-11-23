package state;

import entities.Cell;

public interface CellState {
    void reveal(Cell context);
    void flag(Cell context);
    void unflag(Cell context);
    char getDisplayChar();
}