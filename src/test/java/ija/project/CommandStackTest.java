package ija.project;

import ija.project.GUI.CommandStack.Command;
import ija.project.GUI.CommandStack.CommandStack;
import org.junit.Assert;
import org.junit.Test;

public class CommandStackTest {

    @Test
    public void testTest(){
        Assert.assertTrue(true);
    }

    @Test
    public void commandStackTest(){
        CommandStack stack = new CommandStack();

        Command command = new Command(()-> System.out.println("Exec"),()-> System.out.println("Undo"));

        stack.execute(command);
        stack.undo();

    }

}
