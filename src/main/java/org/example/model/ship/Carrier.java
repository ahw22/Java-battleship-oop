package org.example.model.ship;

public class Carrier extends Ship{

    public Carrier() {
        HP = 5;
        name = "Carrier";
    }

    public String draw() {
        return "C";
    }

}
