package com.kogutenko.calculator;

import com.kogutenko.calculator.analizator.Analyzer;
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
        String str = "5//2+(((9)))";
        while(true) {
            //uncommented this lines for input your math expression
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter your math expression\n(you can use integers, \"+\", \"-\", \"/\", \"//\", \"*\", \"(\", \")\", \"^\")\nif you want to exit write \"exit\"\n> ");
            str = scanner.nextLine();
            if (str.equals("exit")) {
                break;
            }
            str = str.replaceAll("\\s", "");

            Memory memory = new Memory();
            CommandExecutor commandExecutor = Analyzer.analyzer(str, memory);
            commandExecutor.execute(memory);
            System.out.println("Answer is " + memory.getAnswer());
        }
    }
}
