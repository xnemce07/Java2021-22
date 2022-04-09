package ija.project.model.classdiagram;

import java.util.Observable;
import java.util.UUID;

public class UMLElement extends Observable {

    private String name;
    private UUID id = UUID.randomUUID();

    public UMLElement(String name){
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public UUID getId() {
        return id;
    }
}
