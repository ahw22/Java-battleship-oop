package org.example.model.ship;

import lombok.Getter;
import org.example.model.board.Field;

import java.util.ArrayList;

public abstract class Ship {
     @Getter
     protected int HP;
     @Getter
     protected String name;
     protected ArrayList<Field> fields;

    public void hit() {
         HP--;
     }

     public String draw() {
          return "?";
     }
}
