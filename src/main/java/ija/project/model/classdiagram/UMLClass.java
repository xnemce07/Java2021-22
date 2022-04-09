package ija.project.model.classdiagram;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UMLClass extends UMLInterface{
    private List<UMLAttribute> attributes = new ArrayList<UMLAttribute>();

    public UMLClass(String name) {
        super(name, true);
    }

    // ========================================================================= //
    //                               UML ATTRIBUTES                              //
    // ========================================================================= //

    /**
     * Create and add attribute with type that is not user defined
     * @param name Name of the attribute
     * @param type Type of the attribute
     * @return UUID of created attribute
     */
    public UUID addAttribute(String name, String type){
        UMLAttribute attr = new UMLAttribute(name,type);
        attributes.add(attr);
        return attr.getId();
    }

    /**
     * Create and add attribute with type that already exists
     * @param name Name of the attribute
     * @param type Type of the attribute
     * @return UUID of created attribute
     */
    public UUID addAttribute(String name,UMLClassifier type){
        UMLAttribute attr = new UMLAttribute(name,type);
        attributes.add(attr);
        return attr.getId();
    }

    /**
     * Add already created attribute
     * @param attribute Attribute to be added
     */
    public void addAttribute(UMLAttribute attribute){
        attributes.add(attribute);
    }

    /**
     * Get attribute by UUID
     * @param id UUID
     * @return Attribute with specified UUID, null if none was found
     */
    public UMLAttribute getAttribute(UUID id){
        for (UMLAttribute attribute:attributes){
            if(attribute.getId() == id){
                return attribute;
            }
        }
        return null;
    }

    /**
     * Get attribute by index
     * @param index Index
     * @throws IndexOutOfBoundsException – if the index is out of range (index < 0 || index >= size(attributes))
     * @return Attribute
     */
    public  UMLAttribute getAttribute(int index){
        return attributes.get(index);
    }

    /**
     * Remove attribute by index
     * @param index
     * @throws IndexOutOfBoundsException – if the index is out of range (index < 0 || index >= size(attributes))
     */
    public void removeAttribute(int index){
        attributes.remove(index);
    }

    /**
     * Remove attribute by UUID
     * @param id UUID
     */
    public void removeAttribute(UUID id){
        attributes.remove(getAttribute(id));
    }

    /**
     * Remove attribute
     * @param attribute
     */
    public void removeAttribute(UMLAttribute attribute){
        attributes.remove(attribute);
    }
}
