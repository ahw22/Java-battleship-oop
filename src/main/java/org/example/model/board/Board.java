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
        if (ship == null) return false;

        int shipLength = ship.getHP();

        int dRow = end.getRow() - start.getRow();
        int dCol = end.getColumn() - start.getColumn();

        // Horizontal placement
        if (dRow == 0 && Math.abs(dCol) + 1 == shipLength) {
            int step = dCol > 0 ? 1 : -1;
            for (int i = 0; i < shipLength; i++) {
                Field target = fields[start.getRow()][start.getColumn() + i * step];
                if (target.isOccupied()) return false; // Optional collision check
            }
            for (int i = 0; i < shipLength; i++) {
                Field target = fields[start.getRow()][start.getColumn() + i * step];
                target.placeShip(ship);
            }
            player.getShipsToPlace().poll();
            return true;

            // Vertical placement
        } else if (dCol == 0 && Math.abs(dRow) + 1 == shipLength) {
            int step = dRow > 0 ? 1 : -1;
            for (int i = 0; i < shipLength; i++) {
                Field target = fields[start.getRow() + i * step][start.getColumn()];
                if (target.isOccupied()) return false;
            }
            for (int i = 0; i < shipLength; i++) {
                Field target = fields[start.getRow() + i * step][start.getColumn()];
                target.placeShip(ship);
            }
            player.getShipsToPlace().poll();
            return true;
        }

        return false;
    }

}
