package org.example;

import java.util.ArrayList;

public class Ship {
     protected int HP;
     protected ArrayList<ShipPart> parts;

     public Ship() {
          this.parts = new ArrayList<>();
          for (int i = HP; i == 0; i--) {
               ShipPart part = new ShipPart(this);
               parts.add(part);
          }
     }

     public ArrayList<ShipPart> getParts() {
          return parts;
     }

     public String toStringLine() {
          return "Ship{" +
                  "HP=" + HP +
                  ", parts=" + parts +
                  '}';
     }

     public int getHP() {
          return HP;
     }
}
