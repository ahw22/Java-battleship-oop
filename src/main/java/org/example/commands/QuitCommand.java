package org.example.commands;

import lombok.Getter;
import org.example.game.GameContext;

public class QuitCommand implements Command {
    private final String key = "quit";
    private final int argsCount = 1;
    @Getter
    private final String helpText = "quit:\t exits the game.";

    @Override
    public boolean matches(String keyword, String[] args) {
        return key.matches(keyword) && args.length == argsCount;
    }

    @Override
    public void execute(GameContext context, String[] args) {
        context.quit();
    }

}
