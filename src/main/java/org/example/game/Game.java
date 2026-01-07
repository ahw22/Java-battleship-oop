package org.example.game;

import lombok.Getter;
import org.example.commands.ParsedCommand;
import org.example.model.board.Position;
import org.example.model.ship.Ship;
import org.example.output.OutputControllerInterface;
import org.example.player.AbstractPlayer;
import org.example.player.PlayerObserver;

@Getter
public class Game implements PlayerObserver {
    private final AbstractPlayer player1;
    private final AbstractPlayer player2;
    private AbstractPlayer currentPlayer;
    private AbstractPlayer opponent;
    private final OutputControllerInterface out;
    private boolean isGameOver = false;

    public Game(AbstractPlayer player1, AbstractPlayer player2, OutputControllerInterface out) {
        this.player1 = player1;
        this.player2 = player2;
        player1.addObserver(this);
        player2.addObserver(this);
        this.currentPlayer = player1;
        this.opponent = player2;
        this.out = out;
    }

    public void fireAt(Position coord) {
        boolean hit = opponent.getOwnBoard().fire(coord);
        printLine(hit ? "Hit at " + coord + "!" : "Miss at " + coord + ".");
        nextTurn();
    }

    public boolean placeShip(Position start, Position end) {
        return currentPlayer.getOwnBoard().placeShip(start, end, currentPlayer);
    }

    public ParsedCommand getNextCommand() {
        return currentPlayer.getNextCommand(this);
    }

    public void nextTurn() {
        if (isGameOver) {
            return;
        }
        AbstractPlayer temp = currentPlayer;
        currentPlayer = opponent;
        opponent = temp;
        printLine("\nIt's " + currentPlayer.getName() + "'s turn!\n");
    }

    public void showTarget() {
        printLine("\nYour Opponents Board:");
        printLine(currentPlayer.getTargetBoard().printBoardWithCoordinates());
    }

    public void showOwnBoard() {
        printLine(currentPlayer.getName() + " - Your Fleet:");
        printLine(currentPlayer.getOwnBoard().printBoard());
    }

    public void gameOver() {
        isGameOver = true;
        showTarget();
        printLine(currentPlayer.getName() + " has won the game!");
    }

    public void quit() {
        printLine("Thanks for playing Battleship!");
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
        out.printLine(message);
    }

    public void print(String message) {
        out.print(message);
    }

    @Override
    public void onShipHit(Ship ship) {
    }

    @Override
    public void onShipSunk(Ship ship) {
        printLine("You sunk your opponents " + ship.getName() + "!\n");
    }

    @Override
    public void onAllShipsSunk(AbstractPlayer player) {
        gameOver();
    }
}
