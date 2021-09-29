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
        int el1_ = el1.intValue();
        int el2_ = el2.intValue();
        if(el1_ == 0) throw new ZeroDivisionException();
        memory.setOperands(new Number(el2_ / el1_));
    }

}
