package org.example.input;

import org.example.commands.CommandParser;
import org.example.commands.ParsedCommand;
import org.example.model.board.Position;

public class RandomInputHandler implements InputHandler{
    CommandParser parser = new CommandParser();


    @Override
    public ParsedCommand getNextCommand() {
        return parser.parse("fire " + new Position().toString());
    }
}
