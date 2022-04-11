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
    public static final UMLClassDiagram classDiagramInstance = new UMLClassDiagram();

    private List<UMLInterface> interfaceList = new ArrayList<>();
    private List<UMLClass> classList = new ArrayList<>();
    private List<UMLRelation> relationList = new ArrayList<>();


    // Private constructor to avoid instantiation
    private UMLClassDiagram() {
    }

    public UMLClass getClassByName(String name){
        for (UMLClass umlClass : classList){
            if(umlClass.getName().equals(name)){
                return umlClass;
            }
        }

        return null;
    }

    private UMLInterface getById(UUID id){
        UMLInterface find = getInterface(id);
        if (find == null){
            return getUMLClass(id);
        }else{
        return find;
        }
    }

    private boolean nameTaken(String name){
        return (getClassByName(name) != null || getInterfaceByName(name) != null);
    }

    public UMLClass createClass(String name) throws NameUnavailableException {
        if(nameTaken(name)){
            throw new NameUnavailableException(name);
        }

        return new UMLClass(name);
    }

    public UMLClass createClass(){
        String potentialName = "New Class";
        String nameCounter = "";

        int counter = 1;

        while(nameTaken(potentialName + nameCounter)){
            nameCounter = "(" + counter + ")";
            counter++;
        }

        return new UMLClass(potentialName);
    }

    // Interface shenanigans
    public UMLInterface getInterfaceByName(String name){
        for (UMLInterface umlInterface : interfaceList){
            if(umlInterface.getName().equals(name)){
                return umlInterface;
            }
        }

        return null;
    }

    public UMLInterface createInterface(String name) throws NameUnavailableException{
        if(nameTaken(name)){
            throw new NameUnavailableException(name);
        }

        return new UMLInterface(name);
    }

    public UMLInterface createInterface(){
        String potentialName = "New Interface";
        String nameCounter = "";

        int counter = 1;

        while(nameTaken(potentialName + nameCounter)){
            nameCounter = "(" + counter + ")";
            counter++;
        }

        return new UMLInterface(potentialName);
    }

    public List<UMLClass> getClassList(){
        return Collections.unmodifiableList(classList);
    }

    public UMLClass getUMLClass(UUID id){
        for (UMLClass umlClass : classList) {
            if(umlClass.getId().equals(id)){
                return umlClass;
            }
        }
        return null;
    }

    public List<UMLInterface> getInterfaceList(){
        return Collections.unmodifiableList(interfaceList);
    }

    public UMLInterface getInterface(UUID id){
        for (UMLInterface umlInterface : interfaceList) {
            if(umlInterface.getId().equals(id)){
                return umlInterface;
            }
        }
        return null;
    }

    public void removeClass(UUID id){
        UMLClass toDelete = getUMLClass(id);
        relationList.removeIf(r -> (r.getStartInterface().getId() == id || r.getEndInterface().getId() == id));
        classList.remove(getUMLClass(id));
    }

    public void removeInterface(UUID id){
        UMLInterface toDelete = getInterface(id);
        relationList.removeIf(r -> (r.getStartInterface().getId() == id || r.getEndInterface().getId() == id));
        interfaceList.remove(toDelete);
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
}
