package ija.project.model.classdiagram;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class UMLClassDiagram {
    private List<UMLClass> classes = new ArrayList<UMLClass>();

    public List<UMLClass> getClasses() {
        return Collections.unmodifiableList(classes);
    }

    public UMLClass getClass(UUID id){
        for (UMLClass umlClass:classes){
            if (umlClass.getId() == id){
                return umlClass;
            }
        }
            return null;
    }

    public void addClass(UMLClass umlClass){
        classes.add(umlClass);
    }
}
