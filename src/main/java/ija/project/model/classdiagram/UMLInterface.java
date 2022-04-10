package ija.project.model.classdiagram;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import ija.project.model.classdiagram.exceptions.NameUnavailableException;

public class UMLInterface extends UMLType {

    private List<UMLMethod> methodList = new ArrayList<UMLMethod>();

    public UMLInterface(String name){
        super(name, true);
    }

    // ========================================================================= //
    //                                UML METHODS                                //
    // ========================================================================= //

    /**
     * Create and add method with type, that is not user defined
     * @param name Name of the method
     * @param type Type of the method
     * @return UUID of created method
     * @throws NameUnavailableException If name is already taken.
     */
    public UMLMethod createMethod(String name, String type) throws NameUnavailableException{
        if(methodExists(name)){
            throw new NameUnavailableException(name);
        }
        UMLMethod method = new UMLMethod(name, type);
        methodList.add(method);
        return method;
    } 

    /**
     * Create and add method with type that already exists
     * @param name Name of the method
     * @param type Type of the method
     * @return UUID of created method 
     * @throws NameUnavailableException If name is already taken.
     */
    public UMLMethod createMethod(String name, UMLType type) throws NameUnavailableException{
        if(methodExists(name)){
            throw new NameUnavailableException(name);
        }
        UMLMethod method = new UMLMethod(name, type);
        methodList.add(method);
        return method;
    }

    public UMLMethod getMethodByName(String name){
        for (UMLMethod method:methodList){
            if (method.getName() == name){
                return method;
            }
        }
        return null;
    }

    private boolean methodExists(String name){
        return getMethodByName(name) != null;
    }

    public UMLMethod createMethod(){
        String potentialName = "New method";
        String nameCounter = "";
        int counter = 1;
        while(methodExists(potentialName + nameCounter)){
            nameCounter = "(" + counter + ")";
            counter++; 
        }

        UMLMethod method = new UMLMethod(potentialName + nameCounter, "");
        methodList.add(method);
        return method;
    }

    /**
     * Get method with specified UUID
     * @param id UUID
     * @return Method with UUID, null if none was found
     */
    public UMLMethod getMethod(UUID id){
        for (UMLMethod method:methodList){
            if (method.getId() == id){
                return method;
            }
        }
        return null;
    }

    /**
     * Remove method by UUID
     * @param id UUID
     */
    public void removeMethod(UUID id){
        methodList.remove(getMethod(id));
    }

    public List<UMLMethod> getMethodList(){
        return Collections.unmodifiableList(methodList);
    }

}
