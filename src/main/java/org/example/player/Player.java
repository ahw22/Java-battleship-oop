package org.example.player;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.example.player.init.BoardInitializer;
import org.example.model.board.Board;
import org.example.model.ship.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@Getter
public class Player {
    private final String name;
    private final Board ownBoard;
    @Setter
    private Board targetBoard;
    private final List<Ship> ships;
    private final Queue<Ship> shipsToPlace = new LinkedList<>();

    public Player(BoardInitializer initializer, String name) {
        this.targetBoard = null;
        this.name = name;
        this.ships = List.of(new Carrier(), new Battleship(), new Destroyer(), new Submarine());
        shipsToPlace.addAll(ships);
        this.ownBoard = initializer.initBoard(this);
    }

}
