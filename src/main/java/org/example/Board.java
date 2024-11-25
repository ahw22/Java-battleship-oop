package org.example;

public class Board {
    private Field[][] fields;

    public Board() {
        this.fields = new Field[10][10];
        initFields();
    }

    private void initFields() {
        //initialize each index with a field
        for (int i = 0; i < fields.length; i++) {
            for (int j = 0; j < fields[1].length; j++) {
                fields[i][j] = new Field();
            }
        }
    }

    public void printBoard() {
        printInBetweenLine();
        //draw fields
        for (Field[] rows : fields) {
            System.out.print("| ");
            for (Field column : rows) {
                System.out.print(column + " | ");
            }
            System.out.println();
            printInBetweenLine();
        }
    }

    private static void printInBetweenLine() {
        System.out.println("+" + "---+".repeat(10));
    }
}
