package org.example;

import java.util.Random;

public class Position {
    private Random rand = new Random();
    private int row;
    private int col;

    public Position() {
        this.row = rand.nextInt(0, 10);
        this.col = rand.nextInt(0, 10);
    }

    public Position(int x, int y) {
        this.row = x;
        this.col = y;
    }

    public Position(String string) {
        String[] parts = string.split(",");
        char x = parts[0].charAt(0);
        this.row = Integer.parseInt(parts[1]);
        this.col = x - 'A' + 1;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
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
