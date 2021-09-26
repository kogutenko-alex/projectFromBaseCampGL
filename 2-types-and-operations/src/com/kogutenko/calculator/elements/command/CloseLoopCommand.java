package com.kogutenko.calculator.elements.command;

import com.kogutenko.calculator.memory.Memory;

import java.util.ArrayList;

/**
 * The type Close loop command.
 */
public class CloseLoopCommand implements Command {
    @Override
    public int getPrioritet() {
        return -1;
    }

    @Override
    public void execute(Memory memory) {
    }
}
