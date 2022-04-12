package ija.project.model.classdiagram;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import ija.project.model.classdiagram.exceptions.NameUnavailableException;

public class UMLClass extends UMLInterface{
    private final List<UMLAttribute> attributeList = new ArrayList<>();

    public UMLClass(String name) {
        super(name);
    }

    // ========================================================================= //
    //                               UML ATTRIBUTES                              //
    // ========================================================================= //

    public List<UMLAttribute> getAttributeList(){
        return Collections.unmodifiableList(attributeList);
    }

    /**
     * Create and add attribute with type that is not user defined
     * @param name Name of the attribute
     * @param type Type of the attribute
     * @return Instance of created attribute
     */
    public UMLAttribute createAttribute(String name, String type){
        UMLAttribute attr = new UMLAttribute(name, type);
        attributeList.add(attr);
        return attr;
    }

    /**
     * Create and add attribute with type that already exists
     * @param name Name of the attribute
     * @param type Type of the attribute
     * @return Instance of created attribute
     */
    public UMLAttribute createAttribute(String name, UMLType type){
        UMLAttribute attr = new UMLAttribute(name, type);
        attributeList.add(attr);
        return attr;
    }

    /**
     * Create and add attribute with default properties
     * @return Instance of created attribute
     */
    public UMLAttribute createAttribute(){
        /*String potentialName = "New attribute";
        String nameCounter = "";
        int counter = 1;
        while(attributeExists(potentialName + nameCounter)){
            nameCounter = "(" + counter + ")";
            counter++;
        }

        UMLAttribute attribute = new UMLAttribute(potentialName + nameCounter, "");*/
        UMLAttribute attribute = new UMLAttribute("New attribute","");
        attributeList.add(attribute);
        return attribute;
    }

    /**
     * Get attribute by UUID
     * @param id UUID
     * @return Attribute with specified UUID, null if none was found
     */
    public UMLAttribute getAttribute(UUID id){
        for (UMLAttribute attribute : attributeList){
            if(attribute.getId() == id){
                return attribute;
            }
        }

        return null;
    }

    /*public UMLAttribute getAttribute(String name){
        for (UMLAttribute umlAttribute : attributeList) {
            if(umlAttribute.getName().equals(name)){
                return umlAttribute;
            }
        }

        return null;
    }*/

    /*private boolean attributeExists(String name){
        return getAttribute(name) != null;
    }*/

    /**
     * Remove attribute with specified UUID
     * @param id UUID
     */
    public void removeAttribute(UUID id){
        attributeList.removeIf(a -> a.getId() == id);
    }
    



}
