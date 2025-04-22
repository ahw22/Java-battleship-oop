package org.example.model.board;

import java.util.Random;

public class Position {
    private final int row;
    private final int col;

    public Position() {
        Random rand = new Random();
        this.row = rand.nextInt(0, 10);
        this.col = rand.nextInt(0, 10);
    }

    public Position(int col, int row) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return col;
    }


    @Override
    public String toString() {
        return "Position{" +
                "col=" + col +
                ", row=" + row +
                '}';
    }
}
