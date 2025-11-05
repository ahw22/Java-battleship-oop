package org.example.player.init;

import org.example.input.UserInputHandler;
import org.example.model.board.Board;
import org.example.model.board.Field;
import org.example.model.board.Position;
import org.example.model.ship.Ship;
import org.example.player.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ManualInitTest {

    private UserInputHandler inputHandler;
    private ManualInit manualInit;
    private Board board;
    private Player player;
    private Ship ship;

    @BeforeEach
    void setup() {
        inputHandler = mock(UserInputHandler.class);
        manualInit = new ManualInit(inputHandler);
        board = mock(Board.class);
        player = mock(Player.class);
        ship = mock(Ship.class);
    }

    @Test
    void testInitBoardPlacesAllShips() {
        // Given
        Ship ship1 = mock(Ship.class);
        Ship ship2 = mock(Ship.class);
        Board board = spy(new Board()); // use real board or mock if needed
        Player player = mock(Player.class);

        when(player.getShips()).thenReturn(new ArrayList<>(List.of(ship1, ship2)));

        ManualInit manualInitSpy = spy(new ManualInit(inputHandler));

        // Spy placeShip so we don’t go into actual placement logic
        doNothing().when(manualInitSpy).placeShip(any(Ship.class), any(Board.class));

        // When
        Board returnedBoard = manualInitSpy.initBoard(player);

        // Then
        verify(manualInitSpy, times(2)).placeShip(any(Ship.class), eq(returnedBoard));
        assertNotNull(returnedBoard);
    }


    @Test
    void testPlaceShipPlacesShipWhenValidPositionAndDirection() {
        Position pos = new Position(2, 2);
        when(inputHandler.getPosFromUser()).thenReturn(pos);
        when(board.checkShipPosIsValid(pos)).thenReturn(true);
        when(ship.getHP()).thenReturn(3);

        // Mock directions to return all true
        mockBoardDirection(board, pos, 3, true);
        when(inputHandler.askForDirection(any())).thenReturn(1); // East

        Field mockField = mock(Field.class);
        when(board.getSize()).thenReturn(10);
        when(board.getField(anyInt(), anyInt())).thenReturn(mockField);
        when(mockField.isOccupied()).thenReturn(false);
        when(board.getFieldFromPosition(any())).thenReturn(mockField);

        manualInit.placeShip(ship, board);

        verify(mockField, times(3)).placeShip(ship); // Ship length is 3
    }

    @Test
    void testPlaceShipRetriesOnInvalidPosition() {
        Position invalid = new Position(0, 0);
        Position valid = new Position(2, 2);

        when(inputHandler.getPosFromUser()).thenReturn(invalid, valid);
        when(board.checkShipPosIsValid(invalid)).thenReturn(false);
        when(board.checkShipPosIsValid(valid)).thenReturn(true);
        when(ship.getHP()).thenReturn(2);

        mockBoardDirection(board, valid, 2, true);
        when(inputHandler.askForDirection(any())).thenReturn(2); // South

        Field mockField = mock(Field.class);
        when(mockField.isOccupied()).thenReturn(false);
        when(board.getSize()).thenReturn(10);
        when(board.getField(anyInt(), anyInt())).thenReturn(mockField);
        when(board.getFieldFromPosition(any())).thenReturn(mockField);

        manualInit.placeShip(ship, board);

        verify(mockField, times(2)).placeShip(ship); // Length 2
    }

    // Utility method to stub direction validation
    private void mockBoardDirection(Board board, Position pos, int length, boolean result) {
        when(board.getSize()).thenReturn(10);
        Field mockField = mock(Field.class);
        when(board.getField(anyInt(), anyInt())).thenReturn(mockField);
        when(mockField.isOccupied()).thenReturn(!result);
    }
}
