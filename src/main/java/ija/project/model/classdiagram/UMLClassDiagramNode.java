/**
* Authors: Leopold Nemcek (xnemce07@stud.fit.vutbr.cz), Rudolf Hyksa (xhyksa00@stud.fit.vutbr.cz)
* Date: 12.4.2022
*/

package ija.project.model.classdiagram;
import ija.project.model.UMLElement;
import ija.project.model.exceptions.UUIDNotFoundException;


import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;



/**
 * Class representing an interface or a class in a class diagram
 */
public class UMLClassDiagramNode extends UMLElement {

    private final ArrayList<UMLMethod> methodList = new ArrayList<>();

    public UMLClassDiagramNode(String name){
        super(name);
        support = new PropertyChangeSupport(this);
    }

    // ========================================================================= //
    //                         Property Change Support                           //
    // ========================================================================= //

    protected final PropertyChangeSupport support;

    /**
     * Adds property change listener
     * @param listener Listener instance
     */
    public void addPropertyChangeListener(PropertyChangeListener listener){
        support.addPropertyChangeListener(listener);
    }

    /**
     * Removes property change listener
     * @param listener Listener instance
     */
    public void removePropertyChangeListener(PropertyChangeListener listener){
        support.removePropertyChangeListener(listener);
    }

    /**
     * Set Node name
     * @param name New name
     */
    public void setName(String name){
        super.setName(name);
        support.firePropertyChange("changeName", this.getId(), name);
    }

    // ========================================================================= //
    //                                UML METHODS                                //
    // ========================================================================= //

    public boolean nameExists(String name){
        for (UMLMethod umlMethod : methodList) {
            if(umlMethod.getName().equals(name)){
                return true;
            }
        }
        return false;
    }


    public List<UMLMethod> getMethodList(){
        return Collections.unmodifiableList(methodList);
    }

    /**
     * Create and add method with type, that is not user defined
     * @param name Name of the method
     * @param type Type of the method
     * @return Instance of created method
     */
    public UUID createMethod(String name, String type){
        String nameCounter = "";
        int count = 0;
        while(nameExists(name + nameCounter)){
            count++;
            nameCounter = "(" + count + ")";
        }
        UMLMethod method = new UMLMethod(name + nameCounter, type);
        //support.firePropertyChange(new PropertyChangeEvent(this, "methodList", null , method));
        methodList.add(method);
        support.firePropertyChange("createMethod", method.getId() , name + nameCounter);
        return method.getId();
    }

    /**
     * Create and add method with default properties
     * @return Instance of created method
     */
    public UUID createMethod(){
        return createMethod("New method", "");
    }

    /**
     * Get method with specified UUID
     * @param methodId Method UUID
     * @throws UUIDNotFoundException in case a method with specified UUID doesn't exist
     * @return Method instance
     */
    private UMLMethod getMethod(UUID methodId) throws UUIDNotFoundException{
        for (UMLMethod method:methodList){
            if (method.getId() == methodId){
                return method;
            }
        }
        throw new UUIDNotFoundException(methodId);
    }

    // ========================================================================= //
    //                              METHOD SETTERS                               //
    // ========================================================================= //

    /**
     * Sets the name of a Method with the specified UUID
     * @param methodId Method UUID
     * @param name new Method name
     * @throws UUIDNotFoundException in case a method with specified UUID doesn't exist
     */
    public void setMethodName(UUID methodId, String name) throws UUIDNotFoundException{
        String nameCounter = "";
        int count = 0;
        while(nameExists(name + nameCounter)){
            count++;
            nameCounter = "(" + count + ")";
        }
        getMethod(methodId).setName(name + nameCounter);
        support.firePropertyChange("methodName", methodId, name + nameCounter);
    }




    /**
     * Sets the name of the type of a method with the specified UUID
     * @param methodId method UUID
     * @param name new method type name
     * @throws UUIDNotFoundException in case a method with specified UUID doesn't exist
     */
    public void setMethodTypeName(UUID methodId, String name) throws UUIDNotFoundException{
        getMethod(methodId).getType().setName(name);
        support.firePropertyChange("methodTypeName", methodId, name);
    }

    /**
     * Sets access modifier of a method
     * @param methodId UUID of the method
     * @param accessModifier New access modifier
     * @throws UUIDNotFoundException in case a method with specified UUID doesn't exist
     */
    public void setMethodAccessModifier(UUID methodId, UMLAttribute.AccessModifier accessModifier) throws UUIDNotFoundException{
        UMLMethod meth = getMethod(methodId);
        meth.setAccessModifier(accessModifier);
        support.firePropertyChange("methodAccessModifier",methodId,accessModifier);
    }

    // ========================================================================= //
    //                              METHOD GETTERS                               //
    // ========================================================================= //

    public UUID getMethodIdByName(String name){
        for (UMLMethod umlMethod: methodList) {
            if(umlMethod.getName().equals(name)){
                return umlMethod.getId();
            }
        }
        return null;
    }

    /**
     * Gets the name of a method with the specified UUID
     * @param methodId method UUID
     * @return name of the method
     * @throws UUIDNotFoundException in case a method with specified UUID doesn't exist
     */
    public String getMethodName(UUID methodId) throws UUIDNotFoundException{
        return getMethod(methodId).getName();
    }

    /**
     * Gets the name of the type of method with the specified UUID
     * @param methodId method UUID
     * @return name of the method's type
     * @throws UUIDNotFoundException in case a method with specified UUID doesn't exist
     */
    public String getMethodTypeName(UUID methodId) throws UUIDNotFoundException{
        return getMethod(methodId).getType().getName();
    }

    /**
     * Gets access modifier of a method
     * @param methodId UUID of the method
     * @return access modifier of the method
     * @throws UUIDNotFoundException in case a method with specified UUID doesn't exist
     */
    public UMLAttribute.AccessModifier getMethodAccessModifier(UUID methodId) throws UUIDNotFoundException{
        return getMethod(methodId).getAccessModifier();
    }

    // ========================================================================= //
    // ========================================================================= //

    /**
     * Remove method by UUID, if the method doesn't exist, nothing happens
     * @param methodId UUID
     */
    public void removeMethod(UUID methodId){
        UMLMethod meth = null;
        try{
            meth = getMethod(methodId);
        }catch(UUIDNotFoundException e){
            return;
        }
        methodList.remove(meth);
        support.firePropertyChange("removeMethod", meth.getId() , null);
    }


}
