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
        ships.add(new Carrier());
        for (int i = 0; i < 2; i++) {
            ships.add(new Battleship());
        }
        for (int i = 0; i < 3; i++) {
            ships.add(new Destroyer());
        }
        for (int i = 0; i < 4; i++) {
            ships.add(new Submarine());
        }
    }

    public ArrayList<Ship> getShips() {
        return ships;
    }
}
