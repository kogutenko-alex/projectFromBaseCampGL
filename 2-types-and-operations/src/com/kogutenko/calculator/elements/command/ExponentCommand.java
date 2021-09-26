package com.kogutenko.calculator.elements.command;

import com.kogutenko.calculator.elements.decimal.Number;
import com.kogutenko.calculator.memory.Memory;

/**
 * The type Exponent command.
 */
public class ExponentCommand implements Command {

    @Override
    public void execute(Memory memory) {
        Double el1 = memory.takeOperand().getNumber();
        Double el2 = memory.takeOperand().getNumber();
        memory.setOperands(new Number(Math.pow(el1, el2)));
    }

    @Override
    public int getPrioritet() {
        return 4;
    }
}
