package org.example.input;

import org.example.commands.Command;

import java.util.Scanner;

public class ConsoleInputHandler implements InputHandler {
    private final Scanner scanner = new Scanner(System.in);


    @Override
    public Command getNextCommand() {
        System.out.print("> ");
        String input = scanner.nextLine();
        return null;
    }
}
