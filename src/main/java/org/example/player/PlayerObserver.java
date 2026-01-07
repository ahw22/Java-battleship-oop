package org.example.player;

import org.example.model.ship.Ship;

public interface PlayerObserver {
    void onShipHit(Ship ship);
    void onShipSunk(Ship ship);
    void onAllShipsSunk(AbstractPlayer player);
}
