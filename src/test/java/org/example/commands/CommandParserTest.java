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
    void parse_WithValidInputs() {
        List<String> inputStrings = List.of("fire A3", "show", "help", "quit", "place A1 A5");

        for (String s : inputStrings) {
            assertFalse(commandParser.parse(s) instanceof UnknownCommand);
        }
    }

    @Test
    void parse_validPlaceCommand() {
        String input = "place A3 D3";
        assertInstanceOf(PlaceShipCommand.class, commandParser.parse(input));
    }

    @Test
    void parse_invalidPlaceCommand() {
        List<String> input = List.of("place ", "place A1 A2 A3", "place Y3 A3", "place 33 B4");
        for (String s : input) {
            assertInstanceOf(UnknownCommand.class, commandParser.parse(s));
        }
    }

    @Test
    void parse_validFireCommand() {
        String input = "fire A3";
        assertInstanceOf(FireCommand.class, commandParser.parse(input));
    }

    @Test
    void parse_invalidFireCommand() {
        List<String> input = List.of("fire", "fire Z3", "fire **", "fire A3 B4");
        for (String s : input) {
            assertInstanceOf(UnknownCommand.class, commandParser.parse(s));
        }
    }

    @Test
    void parse_validShowCommand() {
        String input = "show";
        assertInstanceOf(ShowCommand.class, commandParser.parse(input));
    }

    @Test
    void parse_validQuitCommand() {
        String input = "quit";
        assertInstanceOf(QuitCommand.class, commandParser.parse(input));
    }

    @Test
    void parse_validHelpCommand() {
        String input = "help";
        assertInstanceOf(HelpCommand.class, commandParser.parse(input));
    }

    @Test
    void parse_noInput() {
        assertInstanceOf(UnknownCommand.class, commandParser.parse(""));
    }

    @Test
    void parse_nullInput() {
        assertInstanceOf(UnknownCommand.class, commandParser.parse(null));
    }

    @Test
    void parse_UnknownInput() {
        assertInstanceOf(UnknownCommand.class, commandParser.parse("hello"));
    }


    @Test
    void convertStringToPositon_WithAcceptedValues() {
        List<String> inputStrings = List.of("A0", "J9", "D5", "a0", "j9", "d5");
        List<Position> expectedResults = List.of(
                new Position(0, 0),
                new Position(9, 9),
                new Position(3, 5),
                new Position(0, 0),
                new Position(9, 9),
                new Position(3, 5));

        for (int i = 0; i < inputStrings.size(); i++) {
            Position result = commandParser.convertStringToPosition(inputStrings.get(i));
            Position expected = expectedResults.get(i);
            assertEquals(expected.getColumn(), result.getColumn());
            assertEquals(expected.getRow(), result.getRow());
        }
    }

    @Test
    void convertStringToPositon_WithOutOfBoundsValues() {
        List<String> inputStrings = List.of("Z0", "*5", "AA");

        for (String s : inputStrings) {
            assertThrows(IllegalArgumentException.class, () -> commandParser.convertStringToPosition(s));
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