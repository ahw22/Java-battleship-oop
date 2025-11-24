package org.example.game;

import lombok.Getter;
import org.example.model.board.Position;
import org.example.model.ship.Ship;
import org.example.player.Player;

@Getter
public class GameContext {
    private final Player player1;
    private final Player player2;
    private Player currentPlayer;
    private Player opponent;

    public GameContext(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.currentPlayer = player1;
        this.opponent = player2;
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
        Player temp = currentPlayer;
        currentPlayer = opponent;
        opponent = temp;
        System.out.println("\nIt's " + currentPlayer.getName() + "'s turn!\n");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        showTarget();
    }

    public void showTarget() {
        System.out.println("\nYour Target Board:");
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
        System.out.println(currentPlayer.getName() + " has won the game!");
        System.exit(0);
    }

    public void quit() {
        System.out.println("Thanks for playing Battleship!");
        System.exit(0);
    }
}
