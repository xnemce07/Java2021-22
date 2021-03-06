/**
* Authors: Leopold Nemcek (xnemce07@stud.fit.vutbr.cz), Rudolf Hyksa (xhyksa00@stud.fit.vutbr.cz)
* Date: 12.4.2022
*/
package ija.project.model;


import java.util.UUID;

/**
 * Base class of all classes in ija.project.model.classdiagram, giving them unique id and a name
 */
public class UMLElement {


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
     * @return Element UUID
     */
    public UUID getId() {
        return id;
    }
}
