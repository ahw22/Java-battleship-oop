package org.example;

import java.util.Random;

public class Position {
    private int row;
    private int col;

    public Position() {
        Random rand = new Random();
        this.row = rand.nextInt(0, 10);
        this.col = rand.nextInt(0, 10);
    }

    public Position(int x, int y) {
        this.row = x;
        this.col = y;
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
