package org.example;

import java.util.Scanner;

public class UserInputHandler {
    private static String getUserInput(String query) {
        Scanner sc = new Scanner(System.in);
        System.out.println(query);
        String input = sc.nextLine();
        return input;
    }

    public static Position getPosFromUser() {
        String input = getUserInput("Enter a Position using the Format: A,4");
        Position pos = new Position(input);
        System.out.println(pos);
        return pos;
    }
}
