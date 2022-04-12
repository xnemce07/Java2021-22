/**
* Authors: Leopold Nemcek (xnemce07@stud.fit.vutbr.cz), Rudolf Hyksa (xhyksa00@stud.fit.vutbr.cz)
* Date: 12.4.2023
*/

package ija.project.model.classdiagram;

import ija.project.model.classdiagram.exceptions.UUIDNotFoundException;

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
public class UMLClassDiagram {
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

    public void addPropertyChangeListener(PropertyChangeListener listener){
        support.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener){
        support.removePropertyChangeListener(listener);
    }


    // ========================================================================= //
    //                                 UML CLASSES                               //
    // ========================================================================= //

    /**
     * Get list of all classes in class diagram
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
        UMLClass cls = new UMLClass(name);
        support.firePropertyChange("classList", null, cls);
        classList.add(cls);
        return cls;
    }

    /**
     * Create and add class with default properties
     * @return Instance of the new class
     */
    public UMLClass createClass(){
        UMLClass cls = new UMLClass("New Class");
        support.firePropertyChange("classList", null, cls);
        classList.add(cls);
        return cls;
    }

    /**
     * Get instance of class with specified UUID
     * @param id UUID
     * @return Instance of the class, null if none was found
     */
    public UMLClass getUMLClass(UUID id)throws UUIDNotFoundException{
        for (UMLClass umlClass : classList) {
            if(umlClass.getId().equals(id)){
                return umlClass;
            }
        }
        throw new UUIDNotFoundException(id);
    }

    /**
     * Remove class with specified UUID
     * @param id UUID
     */
    public void removeClass(UUID id){
        UMLClass cls = null;
        try {
            cls = getUMLClass(id);
        } catch (UUIDNotFoundException e) {
            return;
        }

        for(UMLRelation rel:relationList){
            if(rel.getStartNode().getId() == id || rel.getEndNode().getId() == id){
                support.firePropertyChange("relationList", rel, null);
            }
        }
        relationList.removeIf(r -> (r.getStartNode().getId() == id || r.getEndNode().getId() == id));
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
        UMLInterface itf = new UMLInterface(name);
        support.firePropertyChange("interfaceList", null, itf);
        interfaceList.add(itf);
        return itf;
    }

    /**
     * Creates an interface with a default name and adds it into the diagram
     * @return Interface instance
     */
    public UMLInterface createInterface(){
        UMLInterface itf = new UMLInterface("New Interface");
        support.firePropertyChange("interfaceList", null, itf);
        interfaceList.add(itf);
        return itf;
    }

    /**
     * Gets interface instance with the specified UUID
     * @param id Interface UUID
     * @return Interface instnace if it exists, otherwise returns null
     */
    public UMLInterface getInterface(UUID id) throws UUIDNotFoundException{
        for (UMLInterface umlInterface : interfaceList) {
            if(umlInterface.getId().equals(id)){
                return umlInterface;
            }
        }
        throw new UUIDNotFoundException(id);
    }

    /**
     * Removes an interface with the specified UUID from the diagram
     * @param id Interface UUID
     */
    public void removeInterface(UUID id){
        UMLInterface itf = null;
        try {
            itf = getInterface(id);
        } catch (UUIDNotFoundException e) {
            return;
        }

        for(UMLRelation rel:relationList){
            if(rel.getStartNode().getId() == id || rel.getEndNode().getId() == id){
                support.firePropertyChange("relationList", rel, null);
            }
        }
        relationList.removeIf(r -> (r.getStartNode().getId() == id || r.getEndNode().getId() == id));
        

        
        support.firePropertyChange("interfaceList", itf, null);
        interfaceList.remove(itf);
    }

    // ========================================================================= //
    //                           UML CLASSES + INTERFACES                        //
    // ========================================================================= //

    /**
     * Get either an Interface or a Class with the specidfied UUID
     * @param id Interface or Class UUID
     * @return Interface or Class instance if found, otherwise returns null
     */
    private UMLClassDiagramNode getById(UUID id)throws UUIDNotFoundException{
        UMLClassDiagramNode find = getInterface(id);
        if (find == null){
            return getUMLClass(id);
        }else{
            return find;
        }
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
     * @param id Relation UUID
     * @return Relation instance if found, otherwise returns null
     */
    public UMLRelation getRelation(UUID id)throws UUIDNotFoundException{
        for (UMLRelation umlRelation : relationList) {
            if(umlRelation.getId().equals(id)){
                return umlRelation;
            }
        }
        throw new UUIDNotFoundException(id);
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
     * @param id Relation UUID
     */
    public void removeRelation(UUID id){
        UMLRelation rel = null;
        try{
            rel = getRelation(id);
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


}
