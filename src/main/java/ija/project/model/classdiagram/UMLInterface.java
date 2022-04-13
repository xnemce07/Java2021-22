/**
* Authors: Leopold Nemcek (xnemce07@stud.fit.vutbr.cz), Rudolf Hyksa (xhyksa00@stud.fit.vutbr.cz)
* Date: 12.4.2023
*/
package ija.project.model.classdiagram;

/**
 * Class representing an interface in class diagram
 */
public class UMLInterface extends UMLClassDiagramNode {

    public UMLInterface(String name) {
        super(name);
    }

    /**
     * Prints a text representation of the interface
     */
    public void print(){
        System.out.println("=".repeat(20));
        System.out.println("Name: " + getName());
        // System.out.println("-".repeat(20));
        System.out.println("Methods:");
        
        for (UMLMethod umlMethod : getMethodList()) {
            System.out.println("  " + umlMethod.toString());
        }
        System.out.println("=".repeat(20));
    }
}
