package org.example.output;

import lombok.NoArgsConstructor;

import java.io.PrintStream;

@NoArgsConstructor
public class ConsoleOutputController implements OutputControllerInterface {
    PrintStream out = System.out;

    @Override
    public void printLine(String string) {
       out.println(string);
    }

    @Override
    public void print(String string) {
        out.print(string);
    }
}
