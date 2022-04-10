package ija.project.model.classdiagram.exceptions;

public class NameUnavailableException extends Exception{
    public NameUnavailableException(String name){
        super("\"" + name + "\" is not available.");
    }
}
