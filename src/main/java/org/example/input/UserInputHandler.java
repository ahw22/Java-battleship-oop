package org.example.input;

import org.example.model.board.Position;

import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

public class UserInputHandler {
    private final Scanner scanner;

    public UserInputHandler(InputStream inputStream) {
        this.scanner = new Scanner(inputStream);
    }

}
