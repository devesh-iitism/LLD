package entities;

import state.CellState;
import state.FlaggedState;
import state.HiddenState;
import state.RevealedState;

public class Cell {
    private boolean isMine;
    private int adjacentMinesCount;
    private CellState currentState;

    public Cell() {
        this.isMine = false;
        this.adjacentMinesCount = 0;
        this.currentState = new HiddenState();
    }

    public void setState(CellState state) {
        this.currentState = state;
    }

    public void reveal() {
        this.currentState.reveal(this);
    }

    public void flag() {
        this.currentState.flag(this);
    }

    public void unflag() {
        currentState.unflag(this);
    }

    public boolean isRevealed() {
        return this.currentState instanceof RevealedState;
    }

    public boolean isFlagged() {
        return this.currentState instanceof FlaggedState;
    }

    public char getDisplayChar() {
        if (isRevealed()) {
            if (isMine) return '*';
            return adjacentMinesCount > 0 ? (char) (adjacentMinesCount + '0') : ' ';
        } else {
            return currentState.getDisplayChar();
        }
    }

    // Getters and Setters
    public boolean isMine() { return isMine; }
    public void setMine(boolean mine) { isMine = mine; }
    public int getAdjacentMinesCount() { return adjacentMinesCount; }
    public void setAdjacentMinesCount(int count) { adjacentMinesCount = count; }
}