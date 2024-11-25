package org.example;

import java.util.ArrayList;

public class Player {
    private ArrayList<Ship> ships;
    private Board board;
    private BoardInitializer init;

    public Player() {
        this.ships = new ArrayList<>();
        this.init = new RandomInit();
        this.board = init.initBoard(this);
    }
}
