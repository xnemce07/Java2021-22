package ija.project.model.classdiagram;

import ija.project.model.UMLElement;

public class UMLRelation extends UMLElement {

    private UMLInterface startItf;
    private UMLInterface endItf;

    public enum relationType {
        ASOCIATION,
        AGREGATION,
        COMPOSITION,
        GENERALIZATION
    }

    public UMLRelation(String name, UMLInterface startInterface, UMLInterface endInterface) {
        super(name);
        startItf = startInterface;
        endItf = endInterface;
    }

    public UMLInterface getStartItf(){
        return startItf;
    }

    public UMLInterface getEndItf(){
        return endItf;
    }

    public void setStartInterface(UMLInterface newStartInterface){
        startItf = newStartInterface;
    }

    public void setEndInterface(UMLInterface newEndInterface){
        endItf = newEndInterface;
    }

}
