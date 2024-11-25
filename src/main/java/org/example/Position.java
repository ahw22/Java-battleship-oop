package org.example;

import java.util.Random;

public class Position {
    private Random rand = new Random();
    private int x;
    private int y;

    public Position() {
        this.x = rand.nextInt(0, 10);
        this.y = rand.nextInt(0, 10);
    }

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
