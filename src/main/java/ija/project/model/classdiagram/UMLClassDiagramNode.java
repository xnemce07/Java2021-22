/**
* Authors: Leopold Nemcek (xnemce07@stud.fit.vutbr.cz), Rudolf Hyksa (xhyksa00@stud.fit.vutbr.cz)
* Date: 12.4.2023
*/

package ija.project.model.classdiagram;
import ija.project.model.classdiagram.exceptions.UUIDNotFoundException;

import java.beans.PropertyChangeEvent;
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

    public void addPropertyChangeListener(PropertyChangeListener listener){
        support.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener){
        support.removePropertyChangeListener(listener);
    }

    public void setName(String name){
        support.firePropertyChange("name", this.getName(), name);
        super.setName(name);
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
        //support.firePropertyChange(new PropertyChangeEvent(this, "methodList", null , method));
        support.firePropertyChange("methodList", null , method);
        methodList.add(method);
        return method;
    }

    /**
     * Create and add method with default properties
     * @return Instance of created method
     */
    public UMLMethod createMethod(){
        UMLMethod method = new UMLMethod("New method","");
        support.firePropertyChange("methodList", null , method);
        methodList.add(method);
        return method;
    }

    /**
     * Get method with specified UUID
     * @param id UUID
     * @throws UUIDNotFoundException in case a method with specified UUID doesn't exist
     * @return Method instance
     */
    private UMLMethod getMethod(UUID id) throws UUIDNotFoundException{
        for (UMLMethod method:methodList){
            if (method.getId() == id){
                return method;
            }
        }
        throw new UUIDNotFoundException(id);
    }

    // ========================================================================= //
    //                              METHOD SETTERS                               //
    // ========================================================================= //

    /**
     * Sets the name of a method with the specified UUID
     * @param methId method UUID
     * @param name new method name
     * @throws UUIDNotFoundException in case a method with specified UUID doesn't exist
     */
    public void setMethodName(UUID methId, String name) throws UUIDNotFoundException{
        support.firePropertyChange("methodName", methId, name);
        getMethod(methId).setName(name);
    }

    // /**
    //  * Sets the type as a String of a method with the specified UUID
    //  * @param methId method UUID
    //  * @param type new method type
    //  * @throws UUIDNotFoundException in case a method with specified UUID doesn't exist
    //  */
    // public void setMethodType(UUID methId, String type) throws UUIDNotFoundException{
    //     getMethod(methId).setType(type);
    //     support.firePropertyChange("methodType", methId, type);
    // }

    // /**
    //  * Sets the type as a UMLType of a method with the specified UUID
    //  * @param methId method UUID
    //  * @param type new method type
    //  * @throws UUIDNotFoundException in case a method with specified UUID doesn't exist
    //  */
    // public void setMethodType(UUID methId, UMLType type) throws UUIDNotFoundException{
    //     getMethod(methId).setType(type);
    // }

    /**
     * Sets the name of the type of a method with the specified UUID
     * @param methId method UUID
     * @param name new method type name
     * @throws UUIDNotFoundException in case a method with specified UUID doesn't exist
     */
    public void setMethodTypeName(UUID methId, String name) throws UUIDNotFoundException{
        support.firePropertyChange("methodTypeName", methId, name);
        getMethod(methId).getType().setName(name);
    }

    // ========================================================================= //
    //                              METHOD GETTERS                               //
    // ========================================================================= //

    /**
     * Gets the name of the type of a method with the specified UUID
     * @param methId method UUID
     * @return name of the method's type
     * @throws UUIDNotFoundException in case a method with specified UUID doesn't exist
     */
    public String getMethodType(UUID methId) throws UUIDNotFoundException{
        return getMethod(methId).getType().getName();
    }

    // ========================================================================= //
    // ========================================================================= //

    /**
     * Remove method by UUID, if the method doesn't exist, nothing happens
     * @param id UUID
     */
    public void removeMethod(UUID id){
        UMLMethod meth = null;
        try{
            meth = getMethod(id);
        }catch(UUIDNotFoundException e){
            return;
        }
        support.firePropertyChange("methodList", meth , null);
        methodList.remove(meth);
    }


}
