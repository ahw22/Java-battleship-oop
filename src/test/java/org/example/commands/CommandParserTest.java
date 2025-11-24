package org.example.commands;

import org.example.model.board.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CommandParserTest {

    CommandParser commandParser = new CommandParser();

    @BeforeEach
    void setUp() {

    }

    @Test
    void convertStringToPositon_WithAcceptedValues() {
        List<String> inputStrings = List.of("A0", "J9", "D5","a0", "j9", "d5");
        List<Position> expectedResults = List.of(
                new Position(0,0),
                new Position(9,9),
                new Position(3,5),
                new Position(0,0),
                new Position(9,9),
                new Position(3,5));


        for (int i = 0; i < inputStrings.size(); i++) {
            Position result = commandParser.convertStringToPosition(inputStrings.get(i));
            Position expected = expectedResults.get(i);
            assertEquals(expected.getColumn(), result.getColumn());
            assertEquals(expected.getRow(), result.getRow());
        }
    }

    @Test
    void convertStringToPositon_WithOutOfBoundsValues() {
        List<String> inputStrings = List.of("Z0", "*5");

        for (String s : inputStrings) {
            assertThrows(ArithmeticException.class, () -> commandParser.convertStringToPosition(s));
        }
    }

    @Test
    void convertStringToPositon_WithInvalidLength() {
        List<String> inputStrings = List.of("ZZZ345", "1", "a");


        for (String s : inputStrings) {
            assertThrows(IllegalArgumentException.class, () -> commandParser.convertStringToPosition(s));
        }
    }
}