package com.kogutenko.calculator.elements.command;

import com.kogutenko.calculator.memory.Memory;

/**
 * The type Start loop command.
 */
public class StartLoopCommand implements Command {

    @Override
    public int getPrioritet() {
        return 1;
    }

    @Override
    public void execute ( Memory memory ) {

    }
}
