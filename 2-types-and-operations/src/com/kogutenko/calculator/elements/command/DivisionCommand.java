package com.kogutenko.calculator.elements.command;

import com.kogutenko.calculator.elements.decimal.Number;
import com.kogutenko.calculator.exception.ZeroDivisionException;
import com.kogutenko.calculator.memory.Memory;

/**
 * The type Division command.
 */
public class DivisionCommand implements Command {

    @Override
    public int getPrioritet() {
        return 3;
    }

    @Override
    public void execute(Memory memory) {
        Double el1 = memory.takeOperand().getNumber();
        Double el2 = memory.takeOperand().getNumber();
        if(el1 == 0) throw new ZeroDivisionException();
        memory.setOperands(new Number(el2 / el1));
    }

}
