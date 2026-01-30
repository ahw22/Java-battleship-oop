package org.example.game;

import lombok.Getter;
import lombok.NonNull;
import org.example.commands.ParsedCommand;
import org.example.event.Event;
import org.example.event.GameEventListener;
import org.example.model.board.Position;
import org.example.model.ship.Ship;
import org.example.output.OutputControllerInterface;
import org.example.player.AbstractPlayer;
import org.example.player.PlayerObserver;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Game implements PlayerObserver {
    private final AbstractPlayer player1;
    private final AbstractPlayer player2;
    private AbstractPlayer currentPlayer;
    private AbstractPlayer opponent;
    private final List<GameEventListener> listeners = new ArrayList<>();
    private boolean isGameOver = false;

    public Game(AbstractPlayer player1, AbstractPlayer player2, OutputControllerInterface out) {
        this.player1 = player1;
        this.player2 = player2;
        player1.addObserver(this);
        player2.addObserver(this);
        this.currentPlayer = player1;
        this.opponent = player2;
        addListener(out);
    }

    public void addListener(@NonNull GameEventListener listener) {
        listeners.add(listener);
    }

    public void emit(Event.Type type, String message) {
        Event event = new Event(type, message);
        for (GameEventListener listener : listeners) {
            listener.handleGameEvent(event);
        }
    }

    public void fireAt(Position coord) {
        boolean hit = opponent.getOwnBoard().fire(coord);
        emit(hit ? Event.Type.HIT : Event.Type.MISS, hit ? "Hit at " + coord + "!" : "Miss at " + coord + ".");
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
        emit(Event.Type.INFO, "\nIt's " + currentPlayer.getName() + "'s turn!\n");
    }

    public void showTarget() {
        emit(Event.Type.INPUT, "\nYour Opponents Board:\n");
        emit(Event.Type.INPUT, currentPlayer.getTargetBoard().printBoardWithCoordinates());
    }

    public void showOwnBoard() {
        emit(Event.Type.INFO, currentPlayer.getName() + " - Your Fleet:");
        emit(Event.Type.BOARD_VIEW, currentPlayer.getOwnBoard().printBoard());
    }

    private void showTargetEndOfGame() {
        emit(Event.Type.INFO, "\nYour Opponents Board:\n");
        emit(Event.Type.BOARD_VIEW, currentPlayer.getTargetBoard().printBoardWithCoordinates());
    }

    public void gameOver() {
        isGameOver = true;
        emit(Event.Type.GAME_OVER, currentPlayer.getName() + " has won the game!");
        showTargetEndOfGame();
    }

    public void quit() {
        emit(Event.Type.GAME_OVER, "Thanks for playing Battleship!");
        isGameOver = true;
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
        emit(Event.Type.INFO, message);
    }

    public void printNow(String message) {
        emit(Event.Type.INPUT, message);
    }

    public void printError(String message) {
        emit(Event.Type.ERROR, message);
    }

    @Override
    public void onShipHit(Ship ship) {
    }

    @Override
    public void onShipSunk(Ship ship) {
        emit(Event.Type.SUNK, "You sunk your opponents " + ship.getName() + "!\n");
    }

    @Override
    public void onAllShipsSunk(AbstractPlayer player) {
        gameOver();
    }
}
