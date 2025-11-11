package org.example.model.board;


import lombok.Getter;

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

    private static void printInBetweenLine() {
        System.out.println("+---+" + "---+".repeat(10));
    }

    public int getSize() {
        return size;
    }

    public Field getField(int column, int row) {
        return fields[row][column];
    }

    public Field getFieldFromPosition(Position position) {
        return fields[position.getRow()][position.getColumn()];
    }
}
