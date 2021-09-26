package com.kogutenko.calculator.elements.decimal;

import com.kogutenko.calculator.elements.Element;
import com.kogutenko.calculator.memory.Memory;

/**
 * The interface Decimal.
 */
public interface Decimal extends Element {
    /**
     * Gets decimal.
     *
     * @param memory the memory
     * @return the decimal
     */
    double getDecimal(Memory memory);
}
