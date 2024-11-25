package org.example;

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
