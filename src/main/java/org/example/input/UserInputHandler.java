package org.example.input;

import org.example.model.board.Position;

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
        return new Position(result[0], result[1]);
    }

    private String getUserInput(String query) {
        System.out.println(query);
        return scanner.nextLine();
    }

    private int[] getCoordinatesFromString(String input) {
        input = input.toUpperCase().trim();

        if (input.length() < 2) {
            throw new IllegalArgumentException("Invalid format! Example of a valid input: D3");
        }

        char columnChar = input.charAt(0);
        String rowPart = input.substring(1);

        if (!rowPart.matches("\\d+")) {
            throw new IllegalArgumentException("Row must be a number (e.g., D3, A0, J9, B10)");
        }

        int row = Integer.parseInt(rowPart);
        int column = columnChar - 'A';

        // Assuming a 10x10 board
        if (column < 0 || column >= 10 || row < 0 || row >= 10) {
            throw new IllegalArgumentException("Position out of bounds! Use columns A–J and rows 0–9.");
        }

        return new int[]{row, column};
    }


    public int askForDirection(Boolean[] validDirections) {
        String[] directionNames = {"North", "East", "South", "West"};

        System.out.println("Choose a direction:");
        for (int i = 0; i < 4; i++) {
            if (validDirections[i]) {
                System.out.printf("%d - %s%n", i, directionNames[i]);
            }
        }

        while (true) {
            try {
                System.out.print("Enter the number of your chosen direction: ");
                int choice = Integer.parseInt(scanner.nextLine());

                if (choice >= 0 && choice < 4 && validDirections[choice]) {
                    return choice;
                } else {
                    System.out.println("Invalid choice or direction not available.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number (0-3).");
            }
        }
    }

    public boolean askYesNo(String question) {
        while (true) {
            System.out.println(question + " (y/n): ");
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("y")) return true;
            if (input.equals("n")) return false;
            System.out.println("Invalid input. Please enter 'y' or 'n'.");
        }
    }


}
