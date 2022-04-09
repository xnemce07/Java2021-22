package ija.project.model.classdiagram;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UMLClass extends UMLClassifier{

    private List<UMLAttribute> attributes = new ArrayList<UMLAttribute>();
    private List<UMLMethod> methods = new ArrayList<UMLMethod>();

    public UMLClass(String name) {
        super(name, true);
    }

    //

    /**
     * Create and add attribute with type, that is not user defined
     * @param name Name of the attribute
     * @param type Type of the attribute
     * @return UUID of created attribute
     */
    public UUID addAttribute(String name,String type){
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


    //

    /**
     * Create and add method with type, that is not user defined
     * @param name Name of the method
     * @param type Type of the method
     * @return UUID of created method
     */
    public UUID addMethod(String name,String type){
        UMLMethod method = new UMLMethod(name,type);
        methods.add(method);
        return method.getId();
    }

    /**
     * Create and add method with type that already exists
     * @param name Name of the method
     * @param type Type of the method
     * @return UUID of created method
     */
    public UUID addMethod(String name,UMLClassifier type){
        UMLMethod method = new UMLMethod(name,type);
        methods.add(method);
        return method.getId();
    }

    /**
     * Add already created method
     * @param method Method to be added
     */
    public void addMethod(UMLMethod method){
        methods.add(method);
    }

    /**
     * Get method with specified UUID
     * @param id UUID
     * @return Method with UUID, null if none was found
     */
    public UMLMethod getMethod(UUID id){
        for (UMLMethod method:methods){
            if (method.getId() == id){
                return method;
            }
        }
        return null;
    }

    /**
     * Get method by index
     * @param index
     * @throws IndexOutOfBoundsException – if the index is out of range (index < 0 || index >= size(methods))
     */
    public  UMLMethod getMethod(int index){
        return methods.get(index);
    }

    /**
     * Remove method by index
     * @param index
     * @throws IndexOutOfBoundsException – if the index is out of range (index < 0 || index >= size(methods))
     */
    public void removeMethod(int index){
        methods.remove(index);
    }


    /**
     * Remove method by UUID
     * @param id UUID
     */
    public void removeMethod(UUID id){
        methods.remove(getMethod(id));
    }

    /**
     * Remove method
     * @param Method
     */
    public void removeMethod(UMLMethod Method){
        methods.remove(Method);
    }



}
