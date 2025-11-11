package org.example.model.ship;

import lombok.Getter;
import org.example.model.board.Field;

import java.util.ArrayList;

public class Ship {
     @Getter
     protected int HP;
     protected ArrayList<Field> fields;

     public int getHP() {
          return HP;
     }

     public String draw() {
          return "?";
     }
}
