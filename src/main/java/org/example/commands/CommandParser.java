package org.example.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class CommandParser {
    @Getter
    private final List<Command> commandList = List.of(
            new HelpCommand(),
            new FireCommand(),
//            new PlaceShipCommand(),
            new ShowCommand(),
            new QuitCommand()
    );

    public ParsedCommand parse(String input) {
        if (input == null) return new ParsedCommand(new UnknownCommand(null), new String[]{});
        String[] args = input.trim().split("\\s+");
        for (Command cmd : commandList) {
           if (cmd.matches(args[0].toLowerCase(), args)) return new ParsedCommand(cmd, args);
        }
        return new ParsedCommand(new UnknownCommand(input), args);
    }
}
