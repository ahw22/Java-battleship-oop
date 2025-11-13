package org.example.player;

import lombok.Builder;
import lombok.Getter;
import org.example.player.init.BoardInitializer;
import org.example.model.board.Board;
import org.example.model.ship.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@Getter
@Builder
public class Player {
    private final String name;
    private final Board ownBoard;
    private final Board targetBoard;
    private final List<Ship> ships;
    private final Queue<Ship> shipsToPlace = new LinkedList<>();

    public Player(BoardInitializer initializer, String name) {
        this.ownBoard = initializer.initBoard(this);
        this.targetBoard = new Board();
        this.name = name;
        this.ships = List.of(new Battleship(), new Carrier(), new Destroyer(), new Submarine());
        shipsToPlace.addAll(ships);
    }

}
