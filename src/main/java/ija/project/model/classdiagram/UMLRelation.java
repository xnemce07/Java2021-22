/**
 * Authors: Leopold Nemcek (xnemce07@stud.fit.vutbr.cz), Rudolf Hyksa (xhyksa00@stud.fit.vutbr.cz)
 * Date: 12.4.2022
 */
package ija.project.model.classdiagram;

import ija.project.model.UMLElement;

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

    /**
     * Adds property change listener
     * @param listener Listener instance
     */
    public void addPropertyChangeListener(PropertyChangeListener listener){
        support.addPropertyChangeListener(listener);
    }

    /**
     * Removes property change listener
     * @param listener Listener instance
     */
    public void removePropertyChangeListener(PropertyChangeListener listener){
        support.removePropertyChangeListener(listener);
    }

    /**
     * Set name of the Relation
     */
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
     * Getter for beginning Diagram Node
     * @return Beginning Diagram Node
     */
    public UMLClassDiagramNode getStartNode(){
        return startNode;
    }

    /**
     * Getter for ending Diagram Node
     * @return Ending Diagram Node
     */
    public UMLClassDiagramNode getEndNode(){
        return endNode;
    }

    /**
     * Setter for starting Diagram Node
     * @param newStartNode Starting Diagram Node
     */
    private void setStartNode(UMLClassDiagramNode newStartNode){
        support.firePropertyChange("startNode", startNode,newStartNode);
        startNode = newStartNode;
    }

    /**
     * Setter for ending Diagram Node
     * @param newEndNode Ending Diagram Node
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
    
    /**
     * Prints a text represantation of the Relation
     */
    public void print(){
        System.out.println(toString());
    }

    /**
     * Returns a string representation of the Relation
     * 
     * @return Relation as a string
     */
    public String toString(){
        switch(getRelationType()){
            case ASSOCIATION:
                return (getStartNode().getName() + " -----(" + getName() + ")-----> " + getEndNode().getName());
            case AGGREGATION:
                return (getStartNode().getName() + " -----(" + getName() + ")-----<a> " + getEndNode().getName());
            case COMPOSITION:
                return (getStartNode().getName() + " -----(" + getName() + ")-----<c> " + getEndNode().getName());
            case GENERALIZATION:
                return (getStartNode().getName() + " -----(" + getName() + ")-----|> " + getEndNode().getName());
        }
        return "";
    }
}
