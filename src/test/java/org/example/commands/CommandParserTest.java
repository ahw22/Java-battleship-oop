package org.example.commands;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

class CommandParserTest {

    CommandParser commandParser = new CommandParser();


    @Test
    void parse_WithValidInputs() {
        List<String> inputStrings = List.of("fire A3", "show", "help", "quit", "place A1 A5");

        for (String s : inputStrings) {
            assertFalse(commandParser.parse(s).command() instanceof UnknownCommand);
        }
    }

    @Test
    void parse_validPlaceCommand() {
        String input = "place A3 D3";
        assertInstanceOf(PlaceShipCommand.class, commandParser.parse(input).command());
    }

    @Test
    void parse_invalidPlaceCommand() {
        List<String> input = List.of("place ", "place A1 A2 A3", "place Y3 A3", "place 33 B4");
        for (String s : input) {
            if (s.split("\\s+").length == 3) {
                assertInstanceOf(PlaceShipCommand.class, commandParser.parse(s).command());
            } else {
                assertInstanceOf(UnknownCommand.class, commandParser.parse(s).command());
            }
        }
    }

    @Test
    void parse_validFireCommand() {
        String input = "fire A3";
        assertInstanceOf(FireCommand.class, commandParser.parse(input).command());
    }

    @Test
    void parse_invalidFireCommand() {
        List<String> input = List.of("fire", "fire Z3", "fire **", "fire A3 B4");
        for (String s : input) {
            if (s.split("\\s+").length == 2) {
                assertInstanceOf(FireCommand.class, commandParser.parse(s).command());
            } else {
                assertInstanceOf(UnknownCommand.class, commandParser.parse(s).command());
            }
        }
    }

    @Test
    void parse_validShowCommand() {
        String input = "show";
        assertInstanceOf(ShowCommand.class, commandParser.parse(input).command());
    }

    @Test
    void parse_validQuitCommand() {
        String input = "quit";
        assertInstanceOf(QuitCommand.class, commandParser.parse(input).command());
    }

    @Test
    void parse_validHelpCommand() {
        String input = "help";
        assertInstanceOf(HelpCommand.class, commandParser.parse(input).command());
    }

    @Test
    void parse_noInput() {
        assertInstanceOf(UnknownCommand.class, commandParser.parse("").command());
    }

    @Test
    void parse_nullInput() {
        assertInstanceOf(UnknownCommand.class, commandParser.parse(null).command());
    }

    @Test
    void parse_UnknownInput() {
        assertInstanceOf(UnknownCommand.class, commandParser.parse("hello").command());
    }

}