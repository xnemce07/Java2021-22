/**
* Authors: Leopold Nemcek (xnemce07@stud.fit.vutbr.cz), Rudolf Hyksa (xhyksa00@stud.fit.vutbr.cz)
* Date: 12.4.2022
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
 * Represents the entire class diagram. Since there will always be
 * exactly one of these at any given time, and the application is always
 * using it, it's suitable to be an Eagerly Initialized Singleton.
 */
public class UMLClassDiagram implements PropertyChangeListener{
    // Create an instance of itself at the time of class loading
    private static final UMLClassDiagram classDiagramInstance = new UMLClassDiagram();

    private final List<UMLInterface> interfaceList = new ArrayList<>();
    private final List<UMLClass> classList = new ArrayList<>();
    private final List<UMLRelation> relationList = new ArrayList<>();

    
    public static UMLClassDiagram getInstance(){
        return classDiagramInstance;
    }

    // Private constructor to avoid instantiation
    private UMLClassDiagram() {
        support = new PropertyChangeSupport(this);
    }

    // ========================================================================= //
    //                         Property Change Support                           //
    // ========================================================================= //

    private final PropertyChangeSupport support;

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


    // ========================================================================= //
    //                                 UML CLASSES                               //
    // ========================================================================= //

    /**
     * Get list of all Classes in the Class Diagram
     * @return Unmodifiable list
     */
    public List<UMLClass> getClassList(){
        return Collections.unmodifiableList(classList);
    }


    /**
     * Create and add class with specified name
     * @param name Name of the new class
     * @return Instance of the new class
     */
    public UMLClass createClass(String name){
        String nameCounter = "";
        int count = 0;
        while(nameExists(name + nameCounter)){
            count++;
            nameCounter = "(" + count + ")";
        }

        UMLClass cls = new UMLClass(name + nameCounter);
        support.firePropertyChange("classList", null, cls);
        classList.add(cls);
        return cls;
    }

    /**
     * Create and add class with default properties
     * @return Instance of the new class
     */
    public UMLClass createClass(){
        return createClass("New Class");
    }

    /**
     * Get instance of class with specified UUID
     * @param classId UMLClass UUID
     * @return Instance of the class, null if none was found
     * @throws UUIDNotFoundException in case Class with specified UUID doesn't exist
     */
    public UMLClass getUMLClass(UUID classId) throws UUIDNotFoundException{
        for (UMLClass umlClass : classList) {
            if(umlClass.getId().equals(classId)){
                return umlClass;
            }
        }
        throw new UUIDNotFoundException(classId);
    }

    /**
     * Remove class with specified UUID
     * @param classId UMLClass UUID
     */
    public void removeClass(UUID classId){
        UMLClass cls = null;
        try {
            cls = getUMLClass(classId);
        } catch (UUIDNotFoundException e) {
            return;
        }

        for(UMLRelation rel:relationList){
            if(rel.getStartNode().getId() == classId || rel.getEndNode().getId() == classId){
                support.firePropertyChange("relationList", rel, null);
            }
        }
        relationList.removeIf(r -> (r.getStartNode().getId() == classId || r.getEndNode().getId() == classId));
        support.firePropertyChange("classList", cls, null);
        classList.remove(cls);
    }

    // ========================================================================= //
    //                                UML INTERFACES                             //
    // ========================================================================= //

    /**
     * Get list of all interfaces
     * @return List of interfaces
     */
    public List<UMLInterface> getInterfaceList(){
        return Collections.unmodifiableList(interfaceList);
    }

    /**
     * Creates an interface with specified name and adds it into the diagram
     * @param name Interface name
     * @return Interface instance
     */
    public UMLInterface createInterface(String name){
        String nameCounter = "";
        int count = 0;
        while(nameExists(name + nameCounter)){
            count++;
            nameCounter = "(" + count + ")";
        }

        UMLInterface itf = new UMLInterface(name + nameCounter);
        support.firePropertyChange("interfaceList", null, itf);
        interfaceList.add(itf);
        return itf;
    }

    /**
     * Creates an interface with a default name and adds it into the diagram
     * @return Interface instance
     */
    public UMLInterface createInterface(){
        return createInterface("New Interface");
    }

    /**
     * Gets interface instance with the specified UUID
     * @param interfaceId Interface UUID
     * @return Interface instnace if it exists, otherwise returns null
     * @throws UUIDNotFoundException in case Interface with specified UUID doesn't exist
     */
    public UMLInterface getInterface(UUID interfaceId) throws UUIDNotFoundException{
        for (UMLInterface umlInterface : interfaceList) {
            if(umlInterface.getId().equals(interfaceId)){
                return umlInterface;
            }
        }
        throw new UUIDNotFoundException(interfaceId);
    }

    /**
     * Removes an interface with the specified UUID from the diagram
     * @param interfaceId Interface UUID
     */
    public void removeInterface(UUID interfaceId){
        UMLInterface itf = null;
        try {
            itf = getInterface(interfaceId);
        } catch (UUIDNotFoundException e) {
            return;
        }

        for(UMLRelation rel:relationList){
            if(rel.getStartNode().getId() == interfaceId || rel.getEndNode().getId() == interfaceId){
                support.firePropertyChange("relationList", rel, null);
            }
        }
        relationList.removeIf(r -> (r.getStartNode().getId() == interfaceId || r.getEndNode().getId() == interfaceId));
        

        
        support.firePropertyChange("interfaceList", itf, null);
        interfaceList.remove(itf);
    }

    // ========================================================================= //
    //                           UML CLASSES + INTERFACES                        //
    // ========================================================================= //

    /**
     * Get either an Interface or a Class with the specidfied UUID
     * @param nodeId Interface or Class UUID
     * @return Interface or Class instance if found, otherwise returns null
     */
    private UMLClassDiagramNode getById(UUID nodeId)throws UUIDNotFoundException{
        UMLClassDiagramNode find;

        try{
            find = getUMLClass(nodeId);
        }catch(UUIDNotFoundException e){
            find = getInterface(nodeId);
        }
        return find;
    }

    public List<UMLClassDiagramNode> getNodes(){
        ArrayList<UMLClassDiagramNode> nodes = new ArrayList<UMLClassDiagramNode>(classList);
        nodes.addAll(interfaceList);
        return Collections.unmodifiableList(nodes);
    }

    /**
     * Get class diagram node with specified name
     * @param name Name
     * @return class diagram node, null if nothing was found
     */
    public UMLClassDiagramNode getNodeByName(String name){
        for(UMLClass umlClass:classList){
            if (umlClass.getName().equals(name)){
                return umlClass;
            }
        }
        for(UMLInterface umlInterface:interfaceList){
            if(umlInterface.getName().equals(name)){
                return umlInterface;
            }
        }
        return null;
    }

    // ========================================================================= //
    //                                UML RELATIONS                              //
    // ========================================================================= //

    /**
     * Get list of all Relations
     * @return Unmodifiable list of all Relations
     */
    public List<UMLRelation> getRelationList(){
        return Collections.unmodifiableList(relationList);
    }

    /**
     * Get Relation instance with the specified UUID
     * @param relationId Relation UUID
     * @return Relation instance if found, otherwise returns null
     * @throws UUIDNotFoundException in case Relation with specified UUID doesn't exist
     */
    public UMLRelation getRelation(UUID relationId)throws UUIDNotFoundException{
        for (UMLRelation umlRelation : relationList) {
            if(umlRelation.getId().equals(relationId)){
                return umlRelation;
            }
        }
        throw new UUIDNotFoundException(relationId);
    }

    /**
     * Creates and adds to the diagram a Relation with specified Start interface, End interface and name
     * @param name Relation name
     * @param startNodeId Start node
     * @param endNodeId End node
     * @return Relation instance
     * @throws UUIDNotFoundException in case either of the endpoints don't exist
     */
    public UMLRelation createRelation(String name, UUID startNodeId, UUID endNodeId) throws UUIDNotFoundException{
        UMLRelation rel = new UMLRelation(name,getById(startNodeId),getById(endNodeId));
        support.firePropertyChange("relationList", null, rel);
        relationList.add(rel);
        return rel;
    }

    /**
     * Creates and adds to the diagram a Relation with default name and specified Start interface and End interface
     * @param startNodeId Start interface
     * @param endNodeId End interface
     * @return Relation instance
     * @throws UUIDNotFoundException in case either of the endpoints don't exist
     */
    public UMLRelation createRelation(UUID startNodeId, UUID endNodeId) throws UUIDNotFoundException{
        return createRelation("", startNodeId, endNodeId);
    }

    /**
     * Removes a Relation with the specified UUID from the diagram
     * @param relationId Relation UUID
     */
    public void removeRelation(UUID relationId){
        UMLRelation rel = null;
        try{
            rel = getRelation(relationId);
        }catch(UUIDNotFoundException e){
            return;
        }

        support.firePropertyChange("relationList", rel, null);
        relationList.remove(rel);
    }

    // ========================================================================= //
    // ========================================================================= //
    /**
     * Clears all diagram element lists
     */
    public void clearDiagram(){
        support.firePropertyChange("ClearAll", null, null);
        classList.clear();
        interfaceList.clear();
        relationList.clear();
    }

    /**
     * Prints a text representation of the Class Diagram
     */
    public void print(){
        System.out.println("\nCLASSES:");
        for (UMLClass umlClass : classList) {
            umlClass.print();
        }
        System.out.println("\nINTERFACES:");
        for (UMLInterface umlInterface : interfaceList) {
            umlInterface.print();
        }
        System.out.println("\nRELATIONS:");
        for (UMLRelation umlRelation : relationList) {
            umlRelation.print();
        }
    }

    ////////////////////////////////////////////////////////////////////


    public boolean nameExists(String name){
        for (UMLClass umlClass : classList) {
            if(umlClass.getName().equals(name)){
                return true;
            }
        }

        for (UMLInterface umlInterface : interfaceList) {
            if(umlInterface.getName().equals(name)){
                return true;
            }
        }
        return false;
    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if(evt.getPropertyName().equals("name")){
            String newName = (String) evt.getNewValue();
            if(!nameExists(newName)){
                return;
            }
            String nameCounter = "";
            int count = 0;
            while(nameExists(newName + nameCounter)){
                count++;
                nameCounter = "(" + count + ")";
            }
            ((UMLClassDiagramNode)(evt.getSource())).setName(newName + nameCounter);
        }
    }

}
