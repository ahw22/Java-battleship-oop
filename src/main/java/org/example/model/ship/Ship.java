package org.example.model.ship;

import lombok.Getter;

public abstract class Ship {
     @Getter
     protected int HP;
     @Getter
     protected String name;

    public void hit() {
         HP--;
     }

     public String draw() {
          return "?";
     }
}
