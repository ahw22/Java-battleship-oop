package org.example.game;

import lombok.Getter;
import org.example.model.board.Position;
import org.example.model.ship.Ship;
import org.example.player.AbstractPlayer;

import java.io.PrintStream;

@Getter
public class GameContext {
    private final AbstractPlayer player1;
    private final AbstractPlayer player2;
    private AbstractPlayer currentPlayer;
    private AbstractPlayer opponent;
    private final PrintStream out;

    public GameContext(AbstractPlayer player1, AbstractPlayer player2, PrintStream out) {
        this.player1 = player1;
        this.player2 = player2;
        this.currentPlayer = player1;
        this.opponent = player2;
        this.out = out;
    }

    public boolean fireAt(Position coord) {
        boolean hit = opponent.getOwnBoard().fire(coord);
        if (hit) {
            Ship damagedShip = opponent.getOwnBoard().getFieldFromPosition(coord).getShip();
            if (damagedShip.getHP() <= 0) System.out.printf("You sunk your opponents " + damagedShip.getName() + "!\n");
        }
        return hit;
    }

    public boolean placeShip(Position start, Position end) {
        return currentPlayer.getOwnBoard().placeShip(start, end, currentPlayer);
    }

    public void nextTurn() {
        AbstractPlayer temp = currentPlayer;
        currentPlayer = opponent;
        opponent = temp;
        System.out.println("\nIt's " + currentPlayer.getName() + "'s turn!\n");
        showTarget();
    }

    public void showTarget() {
        System.out.println("\nYour Opponents Board:");
        currentPlayer.getTargetBoard().printBoardWithCoordinates();
    }

    public void showOwnBoard() {
        System.out.println(currentPlayer.getName() + " - Your Fleet:");
        currentPlayer.getOwnBoard().printBoard();
    }

    public boolean isGameOver() {
        return opponent.getNumberOfSunkShips() == opponent.getShips().size();
    }

    public void gameOver() {
        showTarget();
        System.out.println(currentPlayer.getName() + " has won the game!");
        System.exit(0);
    }

    public void quit() {
        System.out.println("Thanks for playing Battleship!");
        System.exit(0);
    }

    public Position convertStringToPosition(String input) {
        input = input.toUpperCase();

        if (input.length() != 2) {
            throw new IllegalArgumentException(
                    "Input is invalid length! Please enter a letter followed by a single digit.");
        }

        // Extract column and row
        char columnChar = input.charAt(0);
        String rowPart = input.substring(1);

        // Validate row is a single digit (0-9)
        if (!rowPart.matches("[0-9]")) {
            throw new IllegalArgumentException("Only use a single digit (0-9) for your Row.");
        }

        int row = rowPart.charAt(0) - '0';
        int column = columnChar - 'A';

        // Validate column (A-J)
        if (column < 0 || column > 9) {
            throw new IllegalArgumentException("Only use letters A through J for your Column.");
        }

        return new Position(column, row);
    }

    public void printLine(String message) {
        out.println(message);
    }
}
