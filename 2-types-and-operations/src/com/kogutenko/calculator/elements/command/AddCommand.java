package com.kogutenko.calculator.elements.command;

import com.kogutenko.calculator.elements.Element;
import com.kogutenko.calculator.elements.decimal.Number;
import com.kogutenko.calculator.memory.Memory;

/**
 * The type Add command.
 */
public class AddCommand implements Command {

    @Override
    public void execute(Memory memory) {
        Double el1 = memory.takeOperand().getNumber();
        Double el2 = memory.takeOperand().getNumber();
        memory.setOperands(new Number(el1 + el2));
    }

    @Override
    public int getPrioritet() {
        return 2;
    }
}
