package ija.project.model.classdiagram;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import ija.project.model.classdiagram.exceptions.NameUnavailableException;

public class UMLClass extends UMLInterface{
    private List<UMLAttribute> attributeList = new ArrayList<>();

    public UMLClass(String name) {
        super(name);
    }

    // ========================================================================= //
    //                               UML ATTRIBUTES                              //
    // ========================================================================= //

    /**
     * Create and add attribute with type that is not user defined
     * @param name Name of the attribute
     * @param type Type of the attribute
     * @return UUID of created attribute
     * @throws NameUnavailableException when name is not available
     */
    public UUID createAttribute(String name, String type) throws NameUnavailableException{
        UMLAttribute attr = new UMLAttribute(name, type);
        attributeList.add(attr);
        return attr.getId();
    }

    /**
     * Create and add attribute with type that already exists
     * @param name Name of the attribute
     * @param type Type of the attribute
     * @return UUID of created attribute
     * @throws NameUnavailableException when name is not available
     */
    public UUID createAttribute(String name, UMLType type) throws NameUnavailableException{
        UMLAttribute attr = new UMLAttribute(name, type);
        attributeList.add(attr);
        return attr.getId();
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

    /**
     * Remove attribute by UUID
     * @param id UUID
     */
    public void removeAttribute(UUID id){
        attributeList.remove(getAttribute(id));
    }

    public List<UMLAttribute> getAttributeList(){
        return Collections.unmodifiableList(attributeList);
    }

    public UMLAttribute getAttributeByName(String name){
        for (UMLAttribute umlAttribute : attributeList) {
            if(umlAttribute.getName() == name){
                return umlAttribute;
            }
        }

        return null;
    }

    private boolean attributeExists(String name){
        return getAttributeByName(name) != null;
    }

    public UMLAttribute createAttribute(){
        String potentialName = "New attribute";
        String nameCounter = "";
        int counter = 1;
        while(attributeExists(potentialName + nameCounter)){
            nameCounter = "(" + counter + ")";
            counter++; 
        }

        UMLAttribute attribute = new UMLAttribute(potentialName + nameCounter, "");
        attributeList.add(attribute);
        return attribute;
    }

}
