package com.kogutenko.calculator.analizator;

import com.kogutenko.calculator.exception.NoSuchBracketsException;
import com.kogutenko.calculator.executor.CommandExecutor;
import com.kogutenko.calculator.memory.Memory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

/**
 * The type Analyzer.
 */
public class Analyzer {
    /**
     * Analyzer math expression.
     *
     * @param mathCode the math expression
     * @param memory   the memory
     * @return the command executor
     */
    public static CommandExecutor analyzer(String mathCode, Memory memory) throws NoSuchBracketsException {
        if(!isParenthesisMatch(mathCode)) throw new NoSuchBracketsException("no matches in open and closed brackets");
        CommandExecutor executor = new CommandExecutor();
        String[] str = mathCode.split("(?<=[^0-9]{1,})" +
                                            "(?=[^+\\\\-\\\\*/()]?)" +
                                            "|(?<=[^+\\\\-\\\\*/()]?)" +
                                            "(?=[^0-9]+)");
        ArrayList<String> tokens = new ArrayList<>(Arrays.asList(str));
        for(int i = 0; i < tokens.size(); i++) {
            if(i == tokens.size() - 1) { break; }
            else if (tokens.get(i).equals("(")) {continue;}
            else if (tokens.get(i).equals(")")) {continue;}
            else if (tokens.get(i).equals(tokens.get(i + 1))) {
                tokens.set(i, tokens.get(i) + tokens.remove(i + 1));
            }
        }
        executor.register(tokens, memory);
        return executor;
    }

    /**
     * Is parenthesis match brackets.
     *
     * @param str the str
     * @return the boolean
     */
    public static boolean isParenthesisMatch(String str) {
        Stack<Character> stack = new Stack<Character>();
        char c;
        for(int i = 0; i < str.length(); i++) {
            c = str.charAt(i);

            if(c == '(')
                stack.push(c);
            else if(c == ')')
                if(stack.empty())
                    return false;
                else if(stack.peek() == '(')
                    stack.pop();
                else
                    return false;
        }
        return stack.empty();
    }
}
