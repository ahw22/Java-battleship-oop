package org.example.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.game.GameContext;

@NoArgsConstructor
public class ShowCommand implements Command {
    private final String key = "show";
    private final int argsCount = 1;
    @Getter
    private final String helpText = "show:\t used to show your board.";

    @Override
    public boolean matches(String keyword, String[] args) {
        return key.matches(keyword) && args.length == argsCount;
    }

    @Override
    public void execute(GameContext context, String[] args) {
        context.showOwnBoard();
    }
}
