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

    /**
     * Getter for beginning interface
     * @return Beginning interface
     */
    public UMLInterface getStartInterface(){
        return startItf;
    }

    /**
     * Getter for ending interface
     * @return Ending interface
     */
    public UMLInterface getEndInterface(){
        return endItf;
    }

    /**
     * Setter for starting interface
     * @param newStartInterface Starting interface
     */
    public void setStartInterface(UMLInterface newStartInterface){
        startItf = newStartInterface;
    }

    /**
     * Setter for ending interface
     * @param newEndInterface Ending interface
     */
    public void setEndInterface(UMLInterface newEndInterface){
        endItf = newEndInterface;
    }

}
