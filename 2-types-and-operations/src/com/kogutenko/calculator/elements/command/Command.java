package com.kogutenko.calculator.elements.command;

import com.kogutenko.calculator.elements.Element;
import com.kogutenko.calculator.memory.Memory;

/**
 * The interface Command.
 */
public interface Command extends Element {
    /**
     * Execute the command from Memory
     *
     * @param memory the memory
     */
    void execute(Memory memory);

    /**
     * Gets prioritet of command.
     *
     * @return the prioritet
     */
    int getPrioritet();
}
