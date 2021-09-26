package com.kogutenko.calculator.memory;

import com.kogutenko.calculator.elements.Element;
import com.kogutenko.calculator.elements.command.Command;
import com.kogutenko.calculator.elements.decimal.Decimal;
import com.kogutenko.calculator.elements.decimal.Number;

import java.util.Stack;

/**
 * The type Memory.
 * Has a stacks for operations and operands of math expression
 * And final answer
 */
public class Memory {
    private Stack<Number> operands;
    private Stack<Command> operators;
    private Double answer;

    /**
     * Gets answer.
     *
     * @return the answer
     */
    public Double getAnswer() {
        return answer;
    }

    /**
     * Sets answer when finished of calculating
     */
    public void setAnswer() {
        Double ans = operands.pop().getNumber();
        this.answer = ans;
    }

    /**
     * Instantiates a new Memory.
     */
    public Memory() {
        operands = new Stack<>();
        operators = new Stack<>();
    }

    /**
     * Checking for empty operands stack.
     *
     * @return the boolean
     */
    public boolean operandsIsEmpty() {
        return operands.isEmpty();
    }

    /**
     * Checking for empty operators stack.
     *
     * @return the boolean
     */
    public boolean operatorsIsEmpty() {
        return operators.isEmpty();
    }

    /**
     * Gets operand.
     *
     * @return the top of operand's stack
     */
    public Number getOperand() {
        return operands.peek();
    }

    /**
     * Take operand number.
     *
     * @return the top of operand's stack
     */
    public Number takeOperand() {
        return operands.pop();
    }

    /**
     * Sets operands.
     *
     * @param element the number
     */
    public void setOperands(Number element) {
        this.operands.push(element);
    }

    /**
     * Gets operators.
     *
     * @return the top of operator's stack
     */
    public Command getOperators() {
        return operators.peek();
    }

    /**
     * Take operators command.
     *
     * @return the top of operator's stack
     */
    public Command takeOperators() {
        return operators.pop();
    }

    /**
     * Sets operators.
     *
     * @param element the command
     */
    public void setOperators(Command element) {
        this.operators.push(element);
    }
}
