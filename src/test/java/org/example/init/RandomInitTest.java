package org.example.init;

import org.example.model.board.Board;
import org.example.model.board.Field;
import org.example.model.board.Position;
import org.example.model.board.State;
import org.example.model.ship.Battleship;
import org.example.model.ship.Carrier;
import org.example.model.ship.Destroyer;
import org.example.model.ship.Ship;
import org.example.model.ship.Submarine;
import org.example.player.AbstractPlayer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RandomInitTest {

    private RandomInit randomInit;
    private AbstractPlayer mockPlayer;
    private Queue<Ship> shipsToPlace;

    @BeforeEach
    void setUp() {
        randomInit = new RandomInit();
        mockPlayer = mock(AbstractPlayer.class);
        shipsToPlace = new LinkedList<>();
        when(mockPlayer.getShipsToPlace()).thenReturn(shipsToPlace);
    }

    @Test
    void testInitBoard_placesAllShipsForStandardPlayer() {
        shipsToPlace.add(new Carrier());
        shipsToPlace.add(new Battleship());
        shipsToPlace.add(new Destroyer());
        shipsToPlace.add(new Submarine());
        shipsToPlace.add(new Submarine());

        Board board = randomInit.initBoard(mockPlayer);

        assertTrue(shipsToPlace.isEmpty(), "All ships should be placed from the player's queue.");

        int occupiedFields = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (board.getFieldFromPosition(new Position(j, i)).isOccupied()) {
                    occupiedFields++;
                }
            }
        }
        assertEquals(1 * 5 + 1 * 4 + 1 * 3 + 2 * 2, occupiedFields, "Total occupied fields should match ship lengths.");

        // Verify no overlapping ships (implicitly checked by placeShip, but good to ensure board integrity)
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Field field = board.getFieldFromPosition(new Position(j, i));
                if (field.isOccupied()) {
                    assertNotNull(field.getShip(), "Occupied field must have a ship reference.");
                } else {
                    assertNull(field.getShip(), "Empty field must not have a ship reference.");
                }
            }
        }
    }

    @Test
    void testInitBoard_withNoShips() {
        Board board = randomInit.initBoard(mockPlayer);

        assertTrue(shipsToPlace.isEmpty(), "Ships queue should be empty if player starts with no ships.");

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                assertEquals(State.NONE, board.getFieldFromPosition(new Position(j, i)).getState(), "Board should be empty if no ships to place.");
                assertNull(board.getFieldFromPosition(new Position(j, i)).getShip(), "Empty fields should not have ship references.");
            }
        }
    }

    @RepeatedTest(100)
    void testInitBoard_placesManyShipsForStandardPlayer() {
        shipsToPlace.add(new Carrier());
        shipsToPlace.add(new Carrier());
        shipsToPlace.add(new Carrier());
        shipsToPlace.add(new Carrier());
        shipsToPlace.add(new Carrier());
        shipsToPlace.add(new Battleship());
        shipsToPlace.add(new Battleship());
        shipsToPlace.add(new Battleship());
        shipsToPlace.add(new Battleship());
        shipsToPlace.add(new Destroyer());
        shipsToPlace.add(new Destroyer());
        shipsToPlace.add(new Destroyer());
        shipsToPlace.add(new Submarine());
        shipsToPlace.add(new Submarine());

        Board board = randomInit.initBoard(mockPlayer);

        assertTrue(shipsToPlace.isEmpty(), "All ships should be placed from the player's queue.");

        int occupiedFields = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (board.getFieldFromPosition(new Position(j, i)).isOccupied()) {
                    occupiedFields++;
                }
            }
        }

        System.out.println(board.printBoard());
        assertEquals(5 * 5 + 4 * 4 + 3 * 3 + 2 * 2, occupiedFields, "Total occupied fields should match ship lengths.");

        // Verify no overlapping ships (implicitly checked by placeShip, but good to ensure board integrity)
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Field field = board.getFieldFromPosition(new Position(j, i));
                if (field.isOccupied()) {
                    assertNotNull(field.getShip(), "Occupied field must have a ship reference.");
                } else {
                    assertNull(field.getShip(), "Empty field must not have a ship reference.");
                }
            }
        }
    }
}
