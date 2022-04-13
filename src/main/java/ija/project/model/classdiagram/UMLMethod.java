/**
* Authors: Leopold Nemcek (xnemce07@stud.fit.vutbr.cz), Rudolf Hyksa (xhyksa00@stud.fit.vutbr.cz)
* Date: 12.4.2022
*/
package ija.project.model.classdiagram;



/**
 * Class representing a method of class or interface
 */
public class UMLMethod extends UMLAttribute{

    // private List<UMLType> paramList = new ArrayList<>();

    public UMLMethod(String name, String type) {
        super(name, type);
    }

    public UMLMethod(String name, UMLType type){
        super(name, type);
    }


}
