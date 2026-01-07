package org.example.player;

import lombok.Getter;
import lombok.Setter;
import org.example.commands.ParsedCommand;
import org.example.game.Game;
import org.example.input.InputHandler;
import org.example.model.board.Board;
import org.example.model.ship.*;
import org.example.init.BoardInitializer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@Getter
public abstract class AbstractPlayer implements ShipObserver {
    protected String name;
    protected Board ownBoard;
    @Setter
    protected Board targetBoard = null;
    protected List<Ship> ships;
    protected Queue<Ship> shipsToPlace = new LinkedList<>();
    protected InputHandler inputHandler;
    protected List<PlayerObserver> observers = new ArrayList<>();
    protected int numberOfSunkShips = 0;

    public AbstractPlayer(BoardInitializer initializer, InputHandler inputHandler,  String name) {
        this.name = name;
        this.ships = List.of(new Carrier(), new Battleship(), new Destroyer(), new Submarine());
        ships.forEach(s -> s.addObserver(this));
        shipsToPlace.addAll(ships);
        this.ownBoard = initializer.initBoard(this);
        this.inputHandler = inputHandler;
    }

    public void addObserver(PlayerObserver observer) {
       observers.add(observer);
    }

    @Override
    public void onShipHit(Ship ship) {
        observers.forEach(o -> o.onShipHit(ship));
    }

    @Override
    public void onShipSunk(Ship ship) {
        numberOfSunkShips++;
        observers.forEach(o -> o.onShipSunk(ship));
        if (numberOfSunkShips == ships.size()) {
            observers.forEach(o -> o.onAllShipsSunk(this));
        }
    }

    public abstract ParsedCommand getNextCommand(Game context);
}
