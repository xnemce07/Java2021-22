package ija.project.GUI.CommandStack;

import java.util.Stack;

public class CommandStack {

    private static final CommandStack commandStackInstance = new CommandStack();
    private Stack<Command> commandStack = new Stack<>();
    private Stack<Command> redoStack = new Stack<>();


    public static CommandStack getInstance(){
        return commandStackInstance;
    }

    private CommandStack(){

    }

    public void clear(){
        commandStack.clear();
        redoStack.clear();
    }

    public void execute(Command command){
        command.command.execute();
        commandStack.push(command);
    }

    public void undo(){
        if(commandStack.size() == 0){
            return;
        }
        Command command = commandStack.pop();
        command.undo.execute();
        redoStack.push(command);
    }

    public void redo(){
        if(redoStack.size() == 0){
            return;
        }
        Command command = redoStack.pop();
        execute(command);
    }

}
