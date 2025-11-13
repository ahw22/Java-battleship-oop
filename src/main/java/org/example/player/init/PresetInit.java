package org.example.player.init;

import org.example.model.board.Board;
import org.example.model.ship.Ship;
import org.example.player.Player;

import java.util.List;

public class PresetInit implements BoardInitializer{
    @Override
    public Board initBoard(Player player) {
        List<Ship> ships = player.getShips();

        return null;
    }
}
