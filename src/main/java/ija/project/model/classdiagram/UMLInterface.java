package ija.project.model.classdiagram;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UMLInterface extends UMLClassifier {

    private List<UMLMethod> methodList = new ArrayList<UMLMethod>();

    public UMLInterface(String name){
        super(name, true);
    }

    public UMLInterface(String name, boolean b) {
        super(name, b);
    }


    // ========================================================================= //
    //                                UML METHODS                                //
    // ========================================================================= //

    /**
     * Create and add method with type, that is not user defined
     * @param name Name of the method
     * @param type Type of the method
     * @return UUID of created method
     */
    public UUID addMethod(String name, String type){
        UMLMethod method = new UMLMethod(name,type);
        methodList.add(method);
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
        methodList.add(method);
        return method.getId();
    }

    /**
     * Add already created method
     * @param method Method to be added
     */
    public void addMethod(UMLMethod method){
        methodList.add(method);
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
     * Get method by index
     * @param index
     * @throws IndexOutOfBoundsException – if the index is out of range (index < 0 || index >= size(methods))
     */
    public  UMLMethod getMethod(int index){
        return methodList.get(index);
    }

    /**
     * Remove method by index
     * @param index
     * @throws IndexOutOfBoundsException – if the index is out of range (index < 0 || index >= size(methods))
     */
    public void removeMethod(int index){
        methodList.remove(index);
    }


    /**
     * Remove method by UUID
     * @param id UUID
     */
    public void removeMethod(UUID id){
        methodList.remove(getMethod(id));
    }

    /**
     * Remove method
     * @param Method
     */
    public void removeMethod(UMLMethod Method){
        methodList.remove(Method);
    }

}
