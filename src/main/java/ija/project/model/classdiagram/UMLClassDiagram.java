package ija.project.model.classdiagram;

import ija.project.model.classdiagram.exceptions.NameUnavailableException;
import ija.project.model.classdiagram.exceptions.UUIDNotFoundException;

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
    }

    // ========================================================================= //
    //                                 UML CLASSES                               //
    // ========================================================================= //

    public List<UMLClass> getClassList(){
        return Collections.unmodifiableList(classList);
    }

    public UMLClass createClass(String name) throws NameUnavailableException {
        if(nameTaken(name)){
            throw new NameUnavailableException(name);
        }
        UMLClass cls = new UMLClass(name);
        classList.add(cls);
        return cls;
    }

    public UMLClass createClass(){
        String potentialName = "New Class";
        String nameCounter = "";

        int counter = 1;

        while(nameTaken(potentialName + nameCounter)){
            nameCounter = "(" + counter + ")";
            counter++;
        }

        UMLClass cls = new UMLClass(potentialName + nameCounter);
        classList.add(cls);
        return cls;
    }

    public UMLClass getUMLClass(UUID id){
        for (UMLClass umlClass : classList) {
            if(umlClass.getId().equals(id)){
                return umlClass;
            }
        }
        return null;
    }

    public UMLClass getUMLClass(String name){
        for (UMLClass umlClass : classList){
            if(umlClass.getName().equals(name)){
                return umlClass;
            }
        }
        return null;
    }

    public void removeClass(UUID id){
        relationList.removeIf(r -> (r.getStartInterface().getId() == id || r.getEndInterface().getId() == id));
        classList.removeIf(c -> c.getId() == id);
    }

    // ========================================================================= //
    //                                UML INTERFACES                             //
    // ========================================================================= //

    public List<UMLInterface> getInterfaceList(){
        return Collections.unmodifiableList(interfaceList);
    }

    public UMLInterface createInterface(String name) throws NameUnavailableException{
        if(nameTaken(name)){
            throw new NameUnavailableException(name);
        }

        UMLInterface itf = new UMLInterface(name);
        interfaceList.add(itf);
        return itf;
    }

    public UMLInterface createInterface(){
        String potentialName = "New Interface";
        String nameCounter = "";

        int counter = 1;

        while(nameTaken(potentialName + nameCounter)){
            nameCounter = "(" + counter + ")";
            counter++;
        }

        UMLInterface itf = new UMLInterface(potentialName + nameCounter);
        interfaceList.add(itf);
        return itf;
    }

    public UMLInterface getInterface(UUID id){
        for (UMLInterface umlInterface : interfaceList) {
            if(umlInterface.getId().equals(id)){
                return umlInterface;
            }
        }
        return null;
    }

    public UMLInterface getInterface(String name){
        for (UMLInterface umlInterface : interfaceList){
            if(umlInterface.getName().equals(name)){
                return umlInterface;
            }
        }

        return null;
    }

    public void removeInterface(UUID id){
        relationList.removeIf(r -> (r.getStartInterface().getId() == id || r.getEndInterface().getId() == id));
        interfaceList.removeIf(i -> i.getId() == id);
    }

    // ========================================================================= //
    //                           UML CLASSES + INTERFACES                        //
    // ========================================================================= //

    private UMLInterface getById(UUID id){
        UMLInterface find = getInterface(id);
        if (find == null){
            return getUMLClass(id);
        }else{
            return find;
        }
    }

    public boolean nameTaken(String name){
        return (getUMLClass(name) != null || getInterface(name) != null);
    }

    // ========================================================================= //
    //                                 UML RELATIONS                             //
    // ========================================================================= //

    public List<UMLRelation> getRelationList(){
        return Collections.unmodifiableList(relationList);
    }

    public UMLRelation createRelation(String name, UUID startInterfaceId, UUID endInterfaceId) throws UUIDNotFoundException{
        UMLInterface startInterface = getById(startInterfaceId);
        UMLInterface endInterface = getById(endInterfaceId);
        if (startInterface == null){
            throw new UUIDNotFoundException(startInterfaceId);
        }
        if(endInterface == null){
            throw new UUIDNotFoundException(endInterfaceId);
        }
        UMLRelation rel = new UMLRelation(name,startInterface,endInterface);
        relationList.add(rel);
        return rel;
    }

    public void removeRelation(UUID id){
        relationList.removeIf(r -> r.getId() == id);
    }
}
