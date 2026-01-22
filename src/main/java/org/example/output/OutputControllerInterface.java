package org.example.output;

import org.example.event.GameEventListener;

public interface OutputControllerInterface extends GameEventListener {
    void printLine(String string);
    void print(String string);
    void flush();
}
