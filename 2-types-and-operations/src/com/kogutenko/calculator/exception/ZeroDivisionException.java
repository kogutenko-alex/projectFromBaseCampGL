package com.kogutenko.calculator.exception;

/**
 * The type Zero division exception.
 */
public class ZeroDivisionException extends ArithmeticException {
    /**
     * Instantiates a new Zero division exception.
     */
    public ZeroDivisionException() {
        super("Division is illegal (division on zero)");
    }
}
