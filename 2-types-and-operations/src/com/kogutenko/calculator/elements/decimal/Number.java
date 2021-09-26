package com.kogutenko.calculator.elements.decimal;

import com.kogutenko.calculator.memory.Memory;

/**
 * The type Number.
 */
public class Number implements Decimal {

    private Double number;

    /**
     * Instantiates a new Number.
     *
     * @param number the number
     */
    public Number (int number) {
        this.number = (double) number;
    }

    /**
     * Instantiates a new Number.
     *
     * @param str the str
     */
    public Number(String str) {
        this.number = Double.parseDouble(str);
    }

    /**
     * Instantiates a new Number.
     *
     * @param number the number
     */
    public Number(double number) {
        this.number = number;
    }

    /**
     * Gets number.
     *
     * @return the number
     */
    public double getNumber() {
        return number;
    }

    @Override
    public double getDecimal(Memory memory) {
        return memory.getOperand().getNumber();
    }
}
