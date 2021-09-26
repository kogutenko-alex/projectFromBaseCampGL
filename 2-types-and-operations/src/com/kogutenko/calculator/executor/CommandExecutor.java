package com.kogutenko.calculator.executor;

import com.kogutenko.calculator.elements.Element;
import com.kogutenko.calculator.elements.command.*;
import com.kogutenko.calculator.elements.decimal.Decimal;
import com.kogutenko.calculator.elements.decimal.Number;
import com.kogutenko.calculator.memory.Memory;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * The type Command executor.
 */
public class CommandExecutor {
    private final ArrayList<Element> commandList = new ArrayList<>();

    /**
     * Register.
     *
     * @param command the command
     */
    public void register(Element command) {
        commandList.add(command);
    }

    /**
     * Register.
     *
     * @param tokens the tokens
     * @param memory the memory
     */
    public void register(ArrayList tokens, Memory memory) {
        for(Object token : tokens){
            commandList.add(returnedElement((String) token, memory));
        }
    }

    /**
     * Execute.
     *
     * @param memory the memory
     */
//"2^(4/2)+1"
    public void execute(Memory memory) {
        for (Object token : commandList) {
            if(token instanceof Decimal){
                memory.setOperands((Number) token);
            } else {
                Command command = (Command) token;
                if(memory.operatorsIsEmpty()) {
                    memory.setOperators(command);
                } else {
                    if(command instanceof CloseLoopCommand) {
                        while(!(memory.getOperators() instanceof StartLoopCommand)) {
                            if(!memory.operatorsIsEmpty())
                                memory.takeOperators().execute(memory);
                        }
                        memory.takeOperators();
                        continue;
                    } else {
                        if (command.getPrioritet() < memory.getOperators().getPrioritet() && !(command instanceof StartLoopCommand)) {
                            memory.takeOperators().execute(memory);
                            if(!memory.operatorsIsEmpty())
                                if (command.getPrioritet() == memory.getOperators().getPrioritet()) {
                                    memory.takeOperators().execute(memory);
                                }
                            memory.setOperators(command);
                            continue;
                        }
                        if (command.getPrioritet() > memory.getOperators().getPrioritet()
                                || command instanceof StartLoopCommand) {
                            memory.setOperators(command);
                            continue;
                        }
                        if (command.getPrioritet() == memory.getOperators().getPrioritet()) {
                            memory.takeOperators().execute(memory);
                            memory.setOperators(command);
                        }
                    }
                }
            }
        }
        if(!memory.operatorsIsEmpty())
            memory.takeOperators().execute(memory);
        memory.setAnswer();
    }

    private static boolean checkedContains(HashMap hashMap, String token) {
        return hashMap.containsKey(token);
    }

    private static Element returnedElement(String token, Memory memory) {
        HashMap<String, Element> map = new HashMap<>();
        map.put("+", new AddCommand());
        map.put("-", new SubtractCommand());
        map.put("*", new MultiplicationCommand());
        map.put("/", new DivisionCommand());
        map.put("//", new DivisionDoubleCommand());
        map.put("(", new StartLoopCommand());
        map.put(")", new CloseLoopCommand());
        map.put("^", new ExponentCommand());
        if (checkedContains(map, token)) {
            return map.get(token);
        }
        return new Number(token);
    }
}
