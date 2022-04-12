package ija.project.model.classdiagram;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import ija.project.model.classdiagram.exceptions.NameUnavailableException;

public class UMLInterface extends UMLType {

    private final List<UMLMethod> methodList = new ArrayList<>();

    public UMLInterface(String name){
        super(name, true);
    }

    // ========================================================================= //
    //                                UML METHODS                                //
    // ========================================================================= //

    public List<UMLMethod> getMethodList(){
        return Collections.unmodifiableList(methodList);
    }

    /**
     * Create and add method with type, that is not user defined
     * @param name Name of the method
     * @param type Type of the method
     * @return Instance of created method
     */
    public UMLMethod createMethod(String name, String type){
        UMLMethod method = new UMLMethod(name, type);
        methodList.add(method);
        return method;
    }

    /**
     * Create and add method with type that already exists
     * @param name Name of the method
     * @param type Type of the method
     * @return Instance of created method
     */
    public UMLMethod createMethod(String name, UMLType type){
        UMLMethod method = new UMLMethod(name, type);
        methodList.add(method);
        return method;
    }

    /**
     * Create and add method with default properties
     * @return Instance of created method
     */
    public UMLMethod createMethod(){
        /*String potentialName = "New method";
        String nameCounter = "";
        int counter = 1;
        while(methodExists(potentialName + nameCounter)){
            nameCounter = "(" + counter + ")";
            counter++;
        }

        UMLMethod method = new UMLMethod(potentialName + nameCounter, "");*/
        UMLMethod method = new UMLMethod("New method","");
        methodList.add(method);
        return method;
    }

    /**
     * Get method with specified UUID
     * @param id UUID
     * @return Method with specified UUID, null if none was found
     */
    public UMLMethod getMethod(UUID id){
        for (UMLMethod method:methodList){
            if (method.getId() == id){
                return method;
            }
        }
        return null;
    }

    /*public UMLMethod getMethod(String name){
        for (UMLMethod method:methodList){
            if (method.getName().equals(name)){
                return method;
            }
        }
        return null;
    }*/

    /*private boolean methodExists(String name){
        return getMethod(name) != null;
    }*/

    /**
     * Remove method by UUID
     * @param id UUID
     */
    public void removeMethod(UUID id){
        methodList.removeIf(m -> m.getId() == id);
    }

}
