package org.example.commands;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public abstract class AbstractCommand implements Command {
    private final String key;
    private final int argsCount;
    private final String helpText;

}
