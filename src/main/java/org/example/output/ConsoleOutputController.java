package org.example.output;

import lombok.NoArgsConstructor;
import org.example.event.Event;
import org.example.event.GameEventListener;

import java.io.PrintStream;

@NoArgsConstructor
public class ConsoleOutputController implements OutputControllerInterface, GameEventListener {
    PrintStream out = System.out;

    @Override
    public void handleGameEvent(Event event) {
        switch (event.getType()) {
            case ERROR -> System.err.println(event.getMessage());
            case BOARD_VIEW -> System.out.println(event.getMessage());
            default -> System.out.println(event.getMessage());
        }
    }

    @Override
    public void printLine(String string) {
       out.println(string);
    }

    @Override
    public void print(String string) {
        out.print(string);
    }

    @Override
    public void flush() {
        // Direct console output doesn't buffer by default
    }
}
