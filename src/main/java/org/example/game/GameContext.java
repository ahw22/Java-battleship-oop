package org.example.game;

import lombok.Getter;
import org.example.model.board.Position;
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
        return opponent.getOwnBoard().fire(coord);
    }

    public boolean placeShip(Position start, Position end) {
        return currentPlayer.getOwnBoard().placeShip(start, end, currentPlayer);
    }

    public void nextTurn() {
        Player temp = currentPlayer;
        currentPlayer = opponent;
        opponent = temp;
        System.out.println("\n It's " + currentPlayer.getName() + "'s turn!");
    }

    public void showBoards() {
        System.out.println(currentPlayer.getName() + " - Your Fleet:");
        currentPlayer.getOwnBoard().printBoard();
        System.out.println("\nYour Target Board:");
        currentPlayer.getTargetBoard().printBoardWithCoordinates();
    }

    public void quit() {
        System.out.println("Thanks for playing Battleship!");
        System.exit(0);
    }
}
