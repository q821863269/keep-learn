package cn.goduck.kl.design.pattern.behavior.command.command;

import java.util.Stack;

/**
 * Desc:
 * Author: Kon
 * Date: 2021/11/7 15:16
 */
public class CommandHistory {

    private Stack<Command> history = new Stack<>();

    public void push(Command c) {
        history.push(c);
    }

    public Command pop() {
        return history.pop();
    }

    public boolean isEmpty() {
        return history.isEmpty();
    }

}
