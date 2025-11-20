package org.example.model.board;

import lombok.Getter;

import java.util.Random;

@Getter
public class Position {
    private final int row;
    private final int column;

    public Position() {
        Random rand = new Random();
        this.row = rand.nextInt(0, 10);
        this.column = rand.nextInt(0, 10);
    }

    public Position(int col, int row) {
        this.row = row;
        this.column = col;
    }

    @Override
    public String toString() {
        return "" + (char)('A' + column) + row;
    }
}
