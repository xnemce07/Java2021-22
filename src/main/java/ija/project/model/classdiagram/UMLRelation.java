/**
* Authors: Leopold Nemcek (xnemce07@stud.fit.vutbr.cz), Rudolf Hyksa (xhyksa00@stud.fit.vutbr.cz)
* Date: 12.4.2023
*/
package ija.project.model.classdiagram;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Class representing a relation between two class diagram nodes
 */
public class UMLRelation extends UMLElement {


    private UMLClassDiagramNode startNode;
    private UMLClassDiagramNode endNode;
    private RelationType relationType = RelationType.ASSOCIATION;

    private PropertyChangeSupport support;

    public enum RelationType {
        ASSOCIATION,
        AGGREGATION,
        COMPOSITION,
        GENERALIZATION
    }


    public void addPropertyChangeListener(PropertyChangeListener listener){
        support.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener){
        support.removePropertyChangeListener(listener);
    }

    public void setName(String name){
        support.firePropertyChange("name", this.getName(), name);
        super.setName(name);
    }


    public UMLRelation(String name, UMLClassDiagramNode startNode, UMLClassDiagramNode endNode) {
        super(name);
        this.startNode = startNode;
        this.endNode = endNode;
        support = new PropertyChangeSupport(this);
    }

    /**
     * Getter for beginning interface
     * @return Beginning interface
     */
    public UMLClassDiagramNode getStartNode(){
        return startNode;
    }

    /**
     * Getter for ending interface
     * @return Ending interface
     */
    public UMLClassDiagramNode getEndNode(){
        return endNode;
    }

    /**
     * Setter for starting interface
     * @param newStartNode Starting interface
     */
    private void setStartNode(UMLClassDiagramNode newStartNode){
        support.firePropertyChange("startNode", startNode,newStartNode);
        startNode = newStartNode;
    }

    /**
     * Setter for ending interface
     * @param newEndNode Ending interface
     */
    private void setEndNode(UMLClassDiagramNode newEndNode){
        support.firePropertyChange("endNode", endNode,newEndNode);
        endNode = newEndNode;
    }

    /**
     * Getter for relation type
     * @return Relation type
     */
    public RelationType getRelationType(){
        return relationType;
    }

    /**
     * Setter for relation type
     * @param relationType new relation type
     */
    public void setRelationType(RelationType relationType) {
        support.firePropertyChange("relationType",this.relationType,relationType);
        this.relationType = relationType;
    }
}
