package org.example.model.board;


import lombok.Getter;
import org.example.model.ship.Ship;
import org.example.player.Player;

public class Board {
    private final Field[][] fields;
    @Getter
    private final int size = 10;

    public Board() {
        this.fields = new Field[size][size];
        initFields();
    }

    private void initFields() {
        //initialize each index with a field
        for (int i = 0; i < fields.length; i++) {
            for (int j = 0; j < fields[1].length; j++) {
                fields[i][j] = new Field(new Position(i,j));
            }
        }
    }

    public void printBoard() {
        printInBetweenLine();
        //draw fields
        System.out.println("|   | A | B | C | D | E | F | G | H | I | J |");
        printInBetweenLine();
        int row = 0;
        for (Field[] rows : fields) {
            System.out.print("| "+ row +" ");
            for (Field column : rows) {
                System.out.print(column.draw());
            }
            System.out.println("|");
            printInBetweenLine();
            row++;
        }
    }

    public void printBoardWithCoordinates() {
        printInBetweenLine();
        //draw fields
        System.out.println("|   | A | B | C | D | E | F | G | H | I | J |");
        printInBetweenLine();
        int row = 0;
        for (Field[] rows : fields) {
            System.out.print("| "+ row +" ");
            for (Field column : rows) {
                System.out.print(column.drawCoordinates());
            }
            System.out.println("|");
            printInBetweenLine();
            row++;
        }
    }

    public boolean checkShipPosIsValid(Position pos) {
        Field targetField = fields[pos.getRow()][pos.getColumn()];
        return !targetField.isOccupied();
    }

    public boolean fire(Position pos) {
        Field targetField = fields[pos.getRow()][pos.getColumn()];
        if(targetField.isOccupied()) {
            targetField.markHit();
            targetField.getShip().hit();
            return true;
        }
        targetField.markMiss();
        return false;
    }

    private static void printInBetweenLine() {
        System.out.println("+---+" + "---+".repeat(10));
    }

    public Field getFieldFromPosition(Position position) {
        return fields[position.getRow()][position.getColumn()];
    }

    public boolean placeShip(Position start, Position end, Player player) {
        Ship ship = player.getShipsToPlace().peek();
        int distanceRows = end.getRow() - start.getRow();
        int distanceCol = end.getColumn() - start.getColumn();
        if (distanceRows > 0 && distanceCol == 0) {
            for (int i = 0; i < distanceRows; i++) {
                Field target = fields[start.getRow() + i][start.getColumn()];
                target.placeShip(ship);
            }
            player.getShipsToPlace().poll();
            return true;
        } else if (distanceCol > 0 && distanceRows == 0) {
            for (int i = 0; i < distanceCol; i++) {
                Field target = fields[start.getRow()][start.getColumn() + i];
                target.placeShip(ship);
            }
            player.getShipsToPlace().poll();
            return true;
        } else {
            return false;
        }
    }
}
