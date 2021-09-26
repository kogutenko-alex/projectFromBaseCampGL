package com.kogutenko.calculator;

import com.kogutenko.calculator.analizator.Analyzer;
import com.kogutenko.calculator.exception.NoSuchBracketsException;
import com.kogutenko.calculator.executor.CommandExecutor;
import com.kogutenko.calculator.memory.Memory;

import java.util.Scanner;

/**
 * The type Calculator.
 * Includes start of program.
 */
public class Calculator {
    /**
     * The entry point of application.
     * *
     *
     * @param args the input arguments
     */
    public static void main(String[] args) throws NoSuchBracketsException {
        //String str = args[0];
        String str = "1 / 0";

        //uncommented this lines for input your math expression
//        Scanner scanner = new Scanner(System.in);
//        System.out.print("Enter your math expression\n(you can use integers, \"+\", \"-\", \"/\", \"*\", \"(\", \")\", \"^\")\n> ");
//        str = scanner.nextLine();

        str = str.replaceAll("\\s","");

        Memory memory = new Memory();
        CommandExecutor commandExecutor = Analyzer.analyzer(str, memory);
        commandExecutor.execute(memory);
        System.out.println("Answer is " + memory.getAnswer());
    }
}
