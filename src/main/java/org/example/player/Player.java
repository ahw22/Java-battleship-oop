package org.example.player;

import org.example.player.init.BoardInitializer;
import org.example.model.board.Board;
import org.example.model.ship.*;

import java.util.ArrayList;

public class Player {
    private final ArrayList<Ship> ships;
    private final Board board;

    public Player(BoardInitializer initializer) {
        this.ships = new ArrayList<>();
        addShips(); // fill the list
        this.board = initializer.initBoard(this); // initialize AFTER ships exist
    }

    private void addShips() {
        ships.add(new Carrier());
        for (int i = 0; i < 2; i++) ships.add(new Battleship());
        for (int i = 0; i < 3; i++) ships.add(new Destroyer());
        for (int i = 0; i < 4; i++) ships.add(new Submarine());
    }

    public ArrayList<Ship> getShips() {
        return ships;
    }

    public Board getBoard() {
        return board;
    }
}
