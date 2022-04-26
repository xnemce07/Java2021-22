package ija.project.GUI.CommandStack;

public class Command {
    public Executable command;
    public Executable undo;

    public Command(Executable command, Executable undo){
        this.command = command;
        this.undo = undo;
    }
}
