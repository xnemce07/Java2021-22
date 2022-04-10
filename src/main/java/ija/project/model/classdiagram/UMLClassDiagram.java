package ija.project.model.classdiagram;

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
    // Create an instance of itself at the time of calss loading
    public static final UMLClassDiagram classDiagramInstance = new UMLClassDiagram();

    private List<UMLInterface> interfaceList = new ArrayList<>();
    private List<UMLClass> classList = new ArrayList<>();

    private List<UMLRelation> relationList = new ArrayList<>();


    // Private constructor to avoid instantiation
    private UMLClassDiagram() {
    }

    public static UMLClassDiagram getInstance() {
        return classDiagramInstance;
    }

    //
    public List<UMLClass> getClassList(){
        return Collections.unmodifiableList(classList);
    }

    public List<UMLInterface> getInterfaceList(){
        return Collections.unmodifiableList(interfaceList);
    }


    public UMLInterface getInterface(UUID id){
        for (UMLInterface umlInterface:interfaceList){
            if(umlInterface.getId() == id){
                return umlInterface;
            }
        }
        return null;
    }

    public UMLClass getClass(UUID id){
        for (UMLClass umlClass:classList){
            if(umlClass.getId() == id){
                return umlClass;
            }
        }
        return null;
    }

    private UMLInterface getById(UUID id){
        UMLInterface find = getInterface(id);
        if (find == null){
            return getClass(id);
        }
        return find;
    }

    public void addClass(UMLClass umlClass) {
        classList.add(umlClass);
    }

    public void addInterface(UMLInterface umlInterface){
        interfaceList.add(umlInterface);
    }

    public void removeClass(UMLClass umlClass){
        relationList.removeIf(r -> (r.getStartItf() == umlClass || r.getEndItf() == umlClass));
        classList.remove(umlClass);
    }

    public void removeInterface(UMLInterface umlInterface){
        relationList.removeIf(r -> (r.getStartItf() == umlInterface || r.getEndItf() == umlInterface));
        interfaceList.remove(umlInterface);
    }

    /**
     * Removes a class from the Class Diagram using its ID
     * @param id ID of the class
     */
    public void removeClass(UUID id){
        removeClass(getClass(id));
    }

    /**
     * Removes an interface from the Class Diagram using its ID
     * @param id ID of the interface
     */
    public void removeInterface(UUID id){
        removeInterface(getInterface(id));
    }


    /**
     * Creates and adds a class into the class diagram by name.
     * @param className Class name
     * @return Created class
     */
    public UMLClass createClass(String className) {
        UMLClass newClass = new UMLClass(className);
        classList.add(newClass);
        return newClass;
    }

    /**
     * Creates and adds an interface into the class diagram by name.
     * @param interfaceName Interface name
     * @return Created interface
     */
    public UMLInterface createInterface(String interfaceName){
        UMLInterface newInterface = new UMLInterface(interfaceName);
        interfaceList.add(newInterface);
        return newInterface;
    }

    //
    public UMLRelation createRelation(String name, UMLInterface start, UMLInterface end){
        UMLRelation newR = new UMLRelation(name, start, end);
        relationList.add(newR);
        return newR;
    }

    public UMLRelation createRelation(String name, UUID start, UUID end){
        UMLRelation newR = new UMLRelation(name, getById(start), getById(end));
        relationList.add(newR);
        return newR;
    }

    public UMLRelation getRelation(UUID relationID){
        for (UMLRelation relation: relationList) {
            if (relation.getId().equals(relationID)){
                return relation;
            }
        }
        return null;
    }

    public void removeRelation(UUID relationID){
        relationList.remove(getRelation(relationID));
    }


    public void removeRelation(UMLRelation relation){
        relationList.remove(relation);
    }


}
