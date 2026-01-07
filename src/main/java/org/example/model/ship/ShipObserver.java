package org.example.model.ship;

public interface ShipObserver {
    void onShipHit(Ship ship);
    void onShipSunk(Ship ship);
}
