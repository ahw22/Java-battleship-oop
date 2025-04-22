package org.example.player.init;

import org.example.input.UserInputHandler;
import org.example.model.board.Board;
import org.example.model.board.Position;
import org.example.model.ship.Destroyer;
import org.example.model.ship.Ship;
import org.example.player.Player;

import java.util.Arrays;


public class ManualInit implements BoardInitializer {

    private final UserInputHandler inputHandler;

    public ManualInit(UserInputHandler inputHandler) {
        this.inputHandler = inputHandler;
    }


    public Board initBoard(Player player) {
        Board board = new Board();
        Ship testShip = new Destroyer();
        board.printBoard();
        placeShip(testShip, board);
        return board;
    }

    @Override
    public void placeShip(Ship ship, Board board) {
        while (true) {
            Position position = inputHandler.getPosFromUser();
            if (!board.checkShipPosIsValid(position)) {
                System.out.println("Invalid starting position.");
                continue;
            }

            Boolean[] validDirs = findValidDirections(position, ship, board);
            if (Arrays.stream(validDirs).noneMatch(Boolean::booleanValue)) {
                System.out.println("No valid directions from this position.");
                continue;
            }

            int dir = inputHandler.askForDirection(validDirs); // You’d prompt user to choose 0–3
            actuallyPlaceShip(position, dir, ship, board);
            break;
        }
    }


    private Boolean[] findValidDirections(Position pos, Ship ship, Board board) {
        Boolean[] directions = new Boolean[4];
        int length = ship.getHP();

        for (int dir = 0; dir < 4; dir++) {
            directions[dir] = canPlaceInDirection(pos, dir, length, board);
        }
        return directions;
    }

    private boolean canPlaceInDirection(Position pos, int dir, int length, Board board) {
        int row = pos.getRow();
        int col = pos.getColumn();

        for (int i = 0; i < length; i++) {
            int r = row;
            int c = col;

            switch (dir) {
                case 0: r -= i; break; // North
                case 1: c += i; break; // East
                case 2: r += i; break; // South
                case 3: c -= i; break; // West
            }

            if (r < 0 || r >= board.getSize() || c < 0 || c >= board.getSize()) return false;
            if (board.getField(r, c).isOccupied()) return false;
        }

        return true;
    }

    private void actuallyPlaceShip(Position start, int direction, Ship ship, Board board) {
        int row = start.getRow();
        int col = start.getColumn();

        for (int i = 0; i < ship.getHP(); i++) {
            switch (direction) {
                case 0: // North
                    board.getFieldFromPosition(new Position(col, row - i)).placeShip(ship);
                    break;
                case 1: // East
                    board.getFieldFromPosition(new Position(col + i, row)).placeShip(ship);
                    break;
                case 2: // South
                    board.getFieldFromPosition(new Position(col, row + i)).placeShip(ship);
                    break;
                case 3: // West
                    board.getFieldFromPosition(new Position(col - i, row)).placeShip(ship);
                    break;
            }
        }
    }
}
