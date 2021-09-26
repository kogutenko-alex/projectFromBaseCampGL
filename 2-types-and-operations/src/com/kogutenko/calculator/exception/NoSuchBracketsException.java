package com.kogutenko.calculator.exception;

/**
 * The type No such brackets exception.
 */
public class NoSuchBracketsException extends Exception {
    /**
     * Instantiates a new No such brackets exception.
     * @param s
     */
    public NoSuchBracketsException(String s) {
        super("No such brackets");
    }
}
