package org.example.input;

import org.example.commands.ParsedCommand;

public interface InputHandler {

    ParsedCommand getNextCommand();
}
