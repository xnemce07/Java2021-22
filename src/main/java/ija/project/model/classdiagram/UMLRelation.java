package ija.project.model.classdiagram;

public class UMLRelation extends UMLElement {

    private UMLInterface startItf;
    private UMLInterface endItf;

    public enum relationType {
        ASSOCIATION,
        AGGREGATION,
        COMPOSITION,
        GENERALIZATION
    }

    public UMLRelation(String name, UMLInterface startInterface, UMLInterface endInterface) {
        super(name);
        startItf = startInterface;
        endItf = endInterface;
    }

    public UMLInterface getStartInterface(){
        return startItf;
    }

    public UMLInterface getEndInterface(){
        return endItf;
    }

    public void setStartInterface(UMLInterface newStartInterface){
        startItf = newStartInterface;
    }

    public void setEndInterface(UMLInterface newEndInterface){
        endItf = newEndInterface;
    }

}
