package org.example.game;

import org.example.commands.AbstractCommand;
import org.example.commands.ParsedCommand;
import org.example.commands.UnknownCommand;
import org.example.output.BufferedOutputController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GameRunnerTest {

    @Mock
    BufferedOutputController controller;
    @Mock
    Game game;
    @Mock
    ParsedCommand parsedCommand;
    @Mock
    AbstractCommand command;

    @InjectMocks
    GameRunner runner = new GameRunner(controller);

    ParsedCommand unknownCommand = new ParsedCommand(new UnknownCommand(""), new String[0]);


    @Test
    void printsWelcomeMessagesOnStart() {
        when(game.isGameOver()).thenReturn(false, true);
        when(game.getNextCommand()).thenReturn(unknownCommand);

        GameRunner runner = new GameRunner(controller);
        runner.run(game);

        verify(game).printLine("Welcome to Battleship!");
        verify(game).printLine("Type 'help' for a list of commands.");
        verify(controller, times(2)).flush();
    }

    @Test
    void run_shouldExecuteCommandIfNotNull() {
        when(game.getNextCommand()).thenReturn(parsedCommand);
        when(parsedCommand.command()).thenReturn(command);
        when(parsedCommand.args()).thenReturn(new String[0]);
        when(game.isGameOver()).thenReturn(false, true);

        runner.run(game);

        verify(command).execute(game, new String[0]);
        verify(controller, times(2)).flush();
    }

    @Test
    void run_shouldPrintErrorIfCommandIsNull() {
        when(game.getNextCommand()).thenReturn(null, unknownCommand);
        when(game.isGameOver()).thenReturn(false, true);

        runner.run(game);

        verify(game, times(1)).printError("Unknown command: Type 'help' to see the available options");
    }

    @Test
    void run_shouldPrintErrorIfExceptionIsThrown() {
        when(game.getNextCommand()).thenThrow(new IllegalArgumentException("Invalid"));
        when(game.isGameOver()).thenReturn(false, true);

        runner.run(game);

        verify(game, times(1)).printError("Error: Invalid");
        verify(controller, times(2)).flush();
    }

}