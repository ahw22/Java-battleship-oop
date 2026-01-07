package org.example.model.ship;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public abstract class Ship {
    @Getter
    protected int HP;
    @Getter
    protected String name;
    protected List<ShipObserver> observers = new ArrayList<>();

    public void hit() {
        HP--;
        observers.forEach(o -> o.onShipHit(this));
        if (HP <= 0) {
            observers.forEach(o -> o.onShipSunk(this));
        }
    }

    public void addObserver(ShipObserver observer) {
        observers.add(observer);
    }

    public abstract String draw();
}
