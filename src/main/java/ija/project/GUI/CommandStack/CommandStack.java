package ija.project.GUI.CommandStack;

import java.util.Stack;

public class CommandStack {

    private Stack<Command> commandStack = new Stack<>();

    public void execute(Command command){
        command.command.execute();
        commandStack.add(command);
    }

    public void undo(){
        commandStack.pop().undo.execute();
    }

}
