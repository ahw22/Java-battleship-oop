package org.example;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class UserInputHandlerTest {

    @Test
    void testGetPosFromUser_ValidInput() {
        String simulatedInput = "D3\n";
        InputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
        UserInputHandler userInputHandler = new UserInputHandler(inputStream);

        Position position = userInputHandler.getPosFromUser();

        assertEquals(3, position.getRow());   // '3' -> index 3
        assertEquals(3, position.getColumn()); // 'D' -> index 3
    }

    @Test
    void testGetPosFromUser_InvalidColumn() {
        String simulatedInput = "X3\nD3\n"; // 'X3' is invalid, then valid 'D3'
        InputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
        UserInputHandler userInputHandler = new UserInputHandler(inputStream);

        String output = captureOutput(userInputHandler::getPosFromUser);

        assertTrue(output.contains("Only use letters A through J for your Column."));
    }

    @Test
    void testGetPosFromUser_InvalidRow_MultipleDigits() {
        String simulatedInput = "D10\nD3\n"; // 'D10' should fail, 'D3' is valid
        InputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
        UserInputHandler userInputHandler = new UserInputHandler(inputStream);

        String output = captureOutput(userInputHandler::getPosFromUser);

        assertTrue(output.contains("Only use a single digit (0-9) for your Row."));
    }

    @Test
    void testGetPosFromUser_InvalidShortInput() {
        String simulatedInput = "A\nD3\n"; // 'A' is too short, then valid 'D3'
        InputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
        UserInputHandler userInputHandler = new UserInputHandler(inputStream);

        String output = captureOutput(userInputHandler::getPosFromUser);

        assertTrue(output.contains("Input is too short! Please enter a letter followed by a single digit."));
    }

    @Test
    void testGetPosFromUser_InvalidCharacters() {
        String simulatedInput = "!5\nD3\n"; // '!5' is invalid, 'D3' is valid
        InputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
        UserInputHandler userInputHandler = new UserInputHandler(inputStream);

        String output = captureOutput(userInputHandler::getPosFromUser);

        assertTrue(output.contains("Only use letters A through J for your Column."));
    }

    private String captureOutput(Runnable task) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        try {
            task.run();
        } finally {
            System.setOut(originalOut);
        }

        return outputStream.toString();
    }
}
