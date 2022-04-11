package ija.project.model.classdiagram.exceptions;

import java.util.UUID;

public class UUIDNotFoundException extends Exception{
    public UUIDNotFoundException(UUID uuid){
        super("Object with uuid \"" + String.valueOf(uuid) + "\" was not found");
    }
}
