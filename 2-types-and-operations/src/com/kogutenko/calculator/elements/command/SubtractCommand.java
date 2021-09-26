package com.kogutenko.calculator.elements.command;

import com.kogutenko.calculator.elements.decimal.Number;
import com.kogutenko.calculator.memory.Memory;

/**
 * The type Subtract command.
 */
public class SubtractCommand implements Command {

    @Override
    public int getPrioritet() {
        return 2;
    }

    @Override
    public void execute(Memory memory) {
        Double el1 = memory.takeOperand().getNumber();
        Double el2 = memory.takeOperand().getNumber();
        memory.setOperands(new Number(el2 - el1));
    }
}
