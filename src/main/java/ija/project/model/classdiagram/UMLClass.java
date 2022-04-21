/**
* Authors: Leopold Nemcek (xnemce07@stud.fit.vutbr.cz), Rudolf Hyksa (xhyksa00@stud.fit.vutbr.cz)
* Date: 12.4.2022
*/
package ija.project.model.classdiagram;
import ija.project.model.classdiagram.exceptions.NameNotAvailableException;
import ija.project.model.classdiagram.exceptions.UUIDNotFoundException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;


/**
 * Class representing a UMLClass in the Class Diagram
 */
public class UMLClass extends UMLClassDiagramNode{
    private final List<UMLAttribute> attributeList = new ArrayList<>();

    public UMLClass(String name) {
        super(name);
    }

    // ========================================================================= //
    //                               UML ATTRIBUTES                              //
    // ========================================================================= //

    /**
     * Getter for list of all attributes of this class
     * @return Unmodifiable list
     */
    public List<UMLAttribute> getAttributeList(){
        return Collections.unmodifiableList(attributeList);
    }

    /**
     * Create and add attribute with type that is not user defined
     * @param name Name of the attribute
     * @param type Type of the attribute
     * @return Instance of created attribute
     */
    public UUID createAttribute(String name, String type){
        String nameCounter = "";
        int count = 0;
        while(nameExists(name + nameCounter)){
            count++;
            nameCounter = "(" + count + ")";
        }
        UMLAttribute attribute = new UMLAttribute(name + nameCounter, type);
        support.firePropertyChange("attributeList", null, attribute.getId());
        attributeList.add(attribute);
        return attribute.getId();
    }

    /**
     * Create and add attribute with default properties
     * @return Instance of created attribute
     */
    public UUID createAttribute(){
        return createAttribute("New attribute","");
    }

    /**
     * Get attribute by UUID
     * @param attrId Attribute UUID
     * @return Attribute with specified UUID, null if none was found
     */
    private UMLAttribute getAttribute(UUID attrId)throws UUIDNotFoundException{
        for (UMLAttribute attribute : attributeList){
            if(attribute.getId() == attrId){
                return attribute;
            }
        }
        throw new UUIDNotFoundException(attrId);
    }



    /**
     * Remove attribute with specified UUID
     * @param attrId Attribute UUID
     */
    public void removeAttribute(UUID attrId){
        UMLAttribute attribute = null;
        try{
            attribute = getAttribute(attrId);
        }catch(UUIDNotFoundException e){
            return;
        }
        support.firePropertyChange("attributeList", attribute.getId() , null);
        attributeList.remove(attribute);
    }


    ///////////////////////////////////////////////////////

    ///////////////////////////////////////////////////////


    public boolean nameExists(String name){
        for (UMLAttribute umlAttribute : attributeList) {
            if(umlAttribute.getName().equals(name)){
                return true;
            }
        }
        return false;
    }

    /**
     * Sets name of attribute with specified UUID
     * @param attrId Attribute UUID
     * @param name New Attribute name
     * @throws UUIDNotFoundException If UUID wasn't found
     */
    public void setAttributeName(UUID attrId,String name)throws UUIDNotFoundException{
        String nameCounter = "";
        int count = 0;
        while(nameExists(name + nameCounter)){
            count++;
            nameCounter = "(" + count + ")";
        }
        support.firePropertyChange("attributeName", attrId, name + nameCounter);
        getAttribute(attrId).setName(name + nameCounter);
    }
    /**
     * Gets name of attribute with specified UUID
     * @param attrId UUID
     * @return Attribute name
     * @throws UUIDNotFoundException If UUID wasn't found
     */
    public String getAttributeName(UUID attrId)throws UUIDNotFoundException{
        return getAttribute(attrId).getName();
    }
    /**
     * Sets name of type of attribute with specified UUID
     * @param attrId Attribute UUID
     * @param name New name for type
     * @throws UUIDNotFoundException If UUID wasn't found
     */
    public void setAttributeTypeName(UUID attrId,String name)throws UUIDNotFoundException{
        support.firePropertyChange("attributeTypeName", attrId, name);
        getAttribute(attrId).getType().setName(name);
    }
    /**
     * Gets name of type of attribute with specified UUID
     * @param attrId Attribute UUID
     * @return Name of the type
     * @throws UUIDNotFoundException If UUID wasn't found
     */
    public String getAttributeTypeName(UUID attrId)throws UUIDNotFoundException{
        return getAttribute(attrId).getType().getName();
    }
    /**
     * Gets access modifier of attribute with specified UUID
     * @param attrId Attribute UUID
     * @return Access modifier
     * @throws UUIDNotFoundException If UUID wasn't found
     */
    public UMLAttribute.AccessModifier getAttributeAccessModifier(UUID attrId)throws UUIDNotFoundException{
        return getAttribute(attrId).getAccessModifier();
    }

    /**
     * Sets access modifier of Attribute specified by UUID
     * @param attrId Attribute UUID
     * @param accessModifier New access modifier
     * @throws UUIDNotFoundException If UUID wasn't found
     */
    public void setAttributeAccessModifier(UUID attrId,UMLAttribute.AccessModifier accessModifier) throws UUIDNotFoundException{
        support.firePropertyChange("attributeAccessModifier",getAttribute(attrId).getAccessModifier(),accessModifier);
        getAttribute(attrId).setAccessModifier(accessModifier);
    }

    /**
     * Prints a text representation of the Class
     */
    public void print(){
        System.out.println("=".repeat(20));
        System.out.println("Name: " + getName());
        System.out.println("Attributes:");
        
        for (UMLAttribute umlAttribute : attributeList) {
            System.out.println("  " + umlAttribute.toString());
        }

        System.out.println("Methods:");
        
        for (UMLMethod umlMethod : getMethodList()) {
            System.out.println("  " + umlMethod.toString());
        }
        System.out.println("=".repeat(20));
    }
}
