package org.example.output;

import lombok.NoArgsConstructor;
import org.example.event.Event;
import org.example.event.GameEventListener;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
public class BufferedOutputController implements OutputControllerInterface, GameEventListener {
    private final PrintStream out = System.out;
    private final List<Event> buffer = new ArrayList<>();
    private final Set<Event.Type> ignoredTypes = new HashSet<>();

    @Override
    public void handleGameEvent(Event event) {
        if (event.getType() == Event.Type.INPUT) {
            out.print(event.getMessage());
            return;
        }
        buffer.add(event);
    }

    @Override
    public void flush() {
        buffer.removeIf(e -> ignoredTypes.contains(e.getType()));

        Collections.sort(buffer);

        for (Event event : buffer) {
            printToConsole(event);
        }

        buffer.clear();
    }

    private void printToConsole(Event event) {
        switch (event.getType()) {
            case ERROR -> System.err.println(event.getMessage());
            case BOARD_VIEW -> out.println(event.getMessage());
            default -> out.println(event.getMessage());
        }
    }

    @Override
    public void printLine(String string) {
        handleGameEvent(new Event(Event.Type.INFO, string));
    }

    @Override
    public void print(String string) {
        handleGameEvent(new Event(Event.Type.INFO, string));
    }
}
