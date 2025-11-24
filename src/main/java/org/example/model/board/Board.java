package org.example.model.board;

import lombok.Getter;
import org.example.model.ship.Ship;
import org.example.player.Player;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private final Field[][] fields;
    @Getter
    private final int size = 10;

    public Board() {
        this.fields = new Field[size][size];
        initFields();
    }

    private void initFields() {
        for (int i = 0; i < fields.length; i++) {
            for (int j = 0; j < fields[1].length; j++) {
                fields[i][j] = new Field(new Position(i, j));
            }
        }
    }

    public void printBoard() {
        printInBetweenLine();
        System.out.println("|   | A | B | C | D | E | F | G | H | I | J |");
        printInBetweenLine();
        int row = 0;
        for (Field[] rows : fields) {
            System.out.print("| " + row + " ");
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
        System.out.println("|   | A | B | C | D | E | F | G | H | I | J |");
        printInBetweenLine();
        int row = 0;
        for (Field[] rows : fields) {
            System.out.print("| " + row + " ");
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
        if (targetField.getState() != State.NONE) {
            throw new IllegalStateException("Field has already been shot at!");
        }
        if (targetField.isOccupied()) {
            targetField.markHit();
            targetField.getShip().hit();
            return true;
        }
        targetField.markMiss();
        return false;
    }

    public Field getFieldFromPosition(Position position) {
        return fields[position.getRow()][position.getColumn()];
    }

    public boolean placeShip(Position start, Position end, Player player) {
        Ship shipToPlace = player.getShipsToPlace().peek();
        if (shipToPlace == null) return false;

        int shipLength = shipToPlace.getHP();

        List<Position> positionList = getListOfPositionsBetweenPositions(start, end, shipLength);

        for (Position position : positionList) {
            if (!checkShipPosIsValid(position)) {
                throw new IllegalArgumentException("One of the fields is already occupied! Field: " + position);
            }
            Field target = getFieldFromPosition(position);
            target.placeShip(shipToPlace);
        }

        return true;
    }


    private List<Position> getListOfPositionsBetweenPositions(Position start, Position end, int shipLength) {
        if (!isStraightLine(start, end)) {
            throw new IllegalArgumentException("Line between start and end has to be horizontal or vertical!");
        }

        int length = getDistance(start, end) + 1;
        if (length != shipLength) {
            throw new IllegalArgumentException("Start and end positions given are longer than the ship being placed.");
        }

        List<Position> positionList = new ArrayList<>(shipLength);

        int dRow = Integer.compare(end.getRow(), start.getRow());     // -1, 0, or 1
        int dCol = Integer.compare(end.getColumn(), start.getColumn()); // -1, 0, or 1

        for (int i = 0; i < shipLength; i++) {
            int row = start.getRow() + i * dRow;
            int col = start.getColumn() + i * dCol;
            positionList.add(new Position(col, row));
        }

        if (positionList.size() != shipLength) {
            throw new IllegalArgumentException("List of positions to place ship in is longer than ship!");
        }

        return positionList;
    }

    private int getDistance(Position a, Position b) {
        return Math.max(Math.abs(a.getRow() - b.getRow()), Math.abs(a.getColumn() - b.getColumn()));
    }

    private boolean isStraightLine(Position start, Position end) {
        return start.getColumn() == end.getColumn() || start.getRow() == end.getRow();
    }

    private static void printInBetweenLine() {
        System.out.println("+---+" + "---+".repeat(10));
    }

}
