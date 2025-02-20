package org.example;

import java.io.InputStream;
import java.util.Scanner;

public class UserInputHandler {
    private final Scanner scanner;

    public UserInputHandler(InputStream inputStream) {
        this.scanner = new Scanner(inputStream);
    }

    public Position getPosFromUser() {
        int[] result;
        while (true) {
            try {
                String input = getUserInput("Enter a Position using the Format: {Column}{Number} eg.: D3");
                result = getCoordinatesFromString(input);
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        Position pos = new Position(result[0], result[1]);
        System.out.println(pos);
        return pos;
    }

    private String getUserInput(String query) {
        System.out.println(query);
        return scanner.nextLine();
    }

    private int[] getCoordinatesFromString(String input) {
        input = input.toUpperCase();

        // Ensure input is at least 2 characters long
        if (input.length() < 2) {
            throw new IllegalArgumentException("Input is too short! Please enter a letter followed by a single digit.");
        }

        // Extract column and row
        char columnChar = input.charAt(0);
        String rowPart = input.substring(1);

        // Validate row is a single digit (0-9)
        if (!rowPart.matches("[0-9]")) {
            throw new ArithmeticException("Only use a single digit (0-9) for your Row.");
        }

        int row = rowPart.charAt(0) - '0';
        int column = columnChar - 'A';

        // Validate column (A-J)
        if (column < 0 || column > 9) {
            throw new ArithmeticException("Only use letters A through J for your Column.");
        }

        return new int[]{row, column};
    }
}
