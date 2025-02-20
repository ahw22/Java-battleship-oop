package org.example.model;

import java.util.ArrayList;

public class Carrier extends Ship{


    public Carrier() {
        this.HP = 5;
        this.parts = new ArrayList<>();
        for (int i = HP; i > 0; i--) {
            ShipPart part = new ShipPart(this);
            parts.add(part);
        }
    }

    public String toString() {
        return "C";
    }

}
