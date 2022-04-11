package ija.project.model.classdiagram;

import java.util.Observable;
import java.util.UUID;

public class UMLElement extends Observable {


    private String name;
    private UUID id = UUID.randomUUID();


    public UMLElement(String name){
        this.name = name;
    }


    /**
     * Get name of the element
     * @return Name
     */
    public String getName(){
        return name;
    }

    /**
     * Set name of the element
     * @param name Name
     */
    public void setName(String name) {
        this.name = name;
    }



    /**
     * Get UUID of the element
     * @return UUID
     */
    public UUID getId() {
        return id;
    }
}
