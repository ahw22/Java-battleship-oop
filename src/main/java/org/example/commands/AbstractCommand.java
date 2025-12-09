package org.example.commands;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public abstract class AbstractCommand implements Command {
    protected String key;
    protected int argsCount;
    @Getter
    protected String helpText;

    protected AbstractCommand() {
    }
}
