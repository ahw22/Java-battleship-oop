package org.example.player;

import lombok.Getter;
import lombok.Setter;
import org.example.commands.Command;
import org.example.model.board.Board;
import org.example.model.ship.*;
import org.example.player.init.BoardInitializer;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@Getter
public abstract class AbstractPlayer {
    protected String name;
    protected Board ownBoard;
    @Setter
    protected Board targetBoard = null;
    protected List<Ship> ships;
    protected Queue<Ship> shipsToPlace = new LinkedList<>();

    public AbstractPlayer(BoardInitializer initializer, String name) {
        this.name = name;
        this.ships = List.of(new Carrier(), new Battleship(), new Destroyer(), new Submarine());
        shipsToPlace.addAll(ships);
        this.ownBoard = initializer.initBoard(this);
    }

    public int getNumberOfSunkShips() {
        long numOfSunkShips = ships.stream()
                .filter(ship -> ship.getHP() <= 0)
                .count();
        return (int) numOfSunkShips;
    }

    public abstract Command getNextCommand();
}
