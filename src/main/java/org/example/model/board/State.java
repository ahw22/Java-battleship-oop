package org.example.model.board;

public enum State {
    HIT, MISS, NONE;

    public String toString() {
        return switch (this) {
            case HIT -> "X";
            case MISS -> "O";
            default -> "~";
        };
    }
}
