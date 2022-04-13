/**
* Authors: Leopold Nemcek (xnemce07@stud.fit.vutbr.cz), Rudolf Hyksa (xhyksa00@stud.fit.vutbr.cz)
* Date: 12.4.2023
*/
package ija.project.model.classdiagram;
import ija.project.model.classdiagram.exceptions.UUIDNotFoundException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;


/**
 * Singleton class representing a class in a class diagram
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
        UMLAttribute attribute = new UMLAttribute(name, type);
        support.firePropertyChange("attributeList", null, attribute);
        attributeList.add(attribute);
        return attribute.getId();
    }

    /**
     * Create and add attribute with default properties
     * @return Instance of created attribute
     */
    public UUID createAttribute(){
        UMLAttribute attribute = new UMLAttribute("New attribute","");
        support.firePropertyChange("attributeList", null, attribute);
        attributeList.add(attribute);
        return attribute.getId();
    }

    /**
     * Get attribute by UUID
     * @param id UUID
     * @return Attribute with specified UUID, null if none was found
     */
    private UMLAttribute getAttribute(UUID id)throws UUIDNotFoundException{
        for (UMLAttribute attribute : attributeList){
            if(attribute.getId() == id){
                return attribute;
            }
        }
        throw new UUIDNotFoundException(id);
    }



    /**
     * Remove attribute with specified UUID
     * @param id UUID
     */
    public void removeAttribute(UUID id){
        UMLAttribute attribute = null;
        try{
            attribute = getAttribute(id);
        }catch(UUIDNotFoundException e){
            return;
        }
        support.firePropertyChange("attributeList", attribute , null);
        attributeList.remove(attribute);
    }


    ///////////////////////////////////////////////////////

    ///////////////////////////////////////////////////////

    /**
     * Sets name of attribute with specified UUID
     * @param id UUID
     * @param name New name
     * @throws UUIDNotFoundException If UUID wasn't found
     */
    public void setAttributeName(UUID id,String name)throws UUIDNotFoundException{
        support.firePropertyChange("attributeName", id, name);
        getAttribute(id).setName(name);
    }
    /**
     * Gets name of attribute with specified UUID
     * @param id UUID
     * @return Name
     * @throws UUIDNotFoundException If UUID wasn't found
     */
    public String getAttributeName(UUID id)throws UUIDNotFoundException{
        return getAttribute(id).getName();
    }
    /**
     * Sets name of type of attribute with specified UUID
     * @param attrId UUID of the attribute
     * @param name New name for type
     * @throws UUIDNotFoundException If UUID wasn't found
     */
    public void setAttributeTypeName(UUID attrId,String name)throws UUIDNotFoundException{
        support.firePropertyChange("attributeTypeName", attrId, name);
        getAttribute(attrId).getType().setName(name);
    }
    /**
     * Gets name of type of attribute with specified UUID
     * @param AttrId UUID of the attribute
     * @return Name of the type
     * @throws UUIDNotFoundException If UUID wasn't found
     */
    public String getAttributeTypeName(UUID AttrId)throws UUIDNotFoundException{
        return getAttribute(AttrId).getType().getName();
    }
    /**
     * Gets access modifier of attribute with specified UUID
     * @param id UUID of the attribute
     * @return Acces modifier
     * @throws UUIDNotFoundException If UUID wasn't found
     */
    public UMLAttribute.AccessModifier getAttributeAccessModifier(UUID id)throws UUIDNotFoundException{
        return getAttribute(id).getAccessModifier();
    }

    /**
     * Sets access modifier of a class
     * @param id UUID of the class
     * @param accessModifier New access modifier
     * @throws UUIDNotFoundException in case a class with specified UUID doesn't exist
     */
    public void setAttributeAccessModifier(UUID id,UMLAttribute.AccessModifier accessModifier) throws UUIDNotFoundException{
        support.firePropertyChange("attributeAccessModifier",getAttribute(id).getAccessModifier(),accessModifier);
        getAttribute(id).setAccessModifier(accessModifier);
    }

    /**
     * Sets access modifier of attribute with specified UUID
     * @param id UUID of the attribute
     * @param modifier New access modifer
     * @throws UUIDNotFoundException If UUID wasn't found
     */
    public void setAttributeTypeName(UUID id,UMLAttribute.AccessModifier modifier)throws UUIDNotFoundException{
        support.firePropertyChange("attributeAccessModifier", id, modifier);
        getAttribute(id).setAccessModifier(modifier);
    }
}
