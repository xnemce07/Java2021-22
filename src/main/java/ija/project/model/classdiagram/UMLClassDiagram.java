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

    private List<UMLClassifier> classList = new ArrayList<UMLClassifier>();

    // Private constructor to avoid instatiation
    private UMLClassDiagram() {
    }

    public static UMLClassDiagram getInstance() {
        return classDiagramInstance;
    }

    public List<UMLClassifier> getClasses() {
        return Collections.unmodifiableList(classList);
    }

    public UMLClassifier getClass(UUID id) {
        for (UMLClassifier umlClass : classList) {
            if (umlClass.getId() == id) {
                return umlClass;
            }
        }
        return null;
    }

    public void addClass(UMLClass umlClass) {
        classList.add(umlClass);
    }

    // TODO: Unless we want to be able to add multiple calsses of the same name
    /**
     * Creates and adds a class into the class diagram by name.
     * If a class of the same name already exists, does nothing
     * and returns null.
     * @param className
     * @return
     */
    public UMLClass createClass(String className) {
        // We'll throw this out if we decide that we wanna be able to cereate two calsses of the same name
        for (UMLClassifier umlClass : classList) {
            if (umlClass.getName().equals(className)) {
                return null;
            }
        }

        UMLClass newClass = new UMLClass(className);
        classList.add(newClass);
        return newClass;
    }
}
