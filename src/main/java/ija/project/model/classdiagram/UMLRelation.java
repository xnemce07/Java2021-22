/**
* Authors: Leopold Nemcek (xnemce07@stud.fit.vutbr.cz), Rudolf Hyksa (xhyksa00@stud.fit.vutbr.cz)
* Date: 12.4.2023
*/
package ija.project.model.classdiagram;

/**
 * Class representing a relation between two class diagram nodes
 */
public class UMLRelation extends UMLElement {

    private UMLClassDiagramNode startItf;
    private UMLClassDiagramNode endItf;

    public enum relationType {
        ASSOCIATION,
        AGGREGATION,
        COMPOSITION,
        GENERALIZATION
    }

    public UMLRelation(String name, UMLClassDiagramNode startNode, UMLClassDiagramNode endNode) {
        super(name);
        startItf = startNode;
        endItf = endNode;
    }

    /**
     * Getter for beginning interface
     * @return Beginning interface
     */
    public UMLClassDiagramNode getStartNode(){
        return startItf;
    }

    /**
     * Getter for ending interface
     * @return Ending interface
     */
    public UMLClassDiagramNode getEndNode(){
        return endItf;
    }

    /**
     * Setter for starting interface
     * @param newStartNode Starting interface
     */
    public void setStartNode(UMLClassDiagramNode newStartNode){
        startItf = newStartNode;
    }

    /**
     * Setter for ending interface
     * @param newEndNode Ending interface
     */
    public void setEndNode(UMLClassDiagramNode newEndNode){
        endItf = newEndNode;
    }

}
