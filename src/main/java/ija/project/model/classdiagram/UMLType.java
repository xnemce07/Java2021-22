/**
* Authors: Leopold Nemcek (xnemce07@stud.fit.vutbr.cz), Rudolf Hyksa (xhyksa00@stud.fit.vutbr.cz)
* Date: 12.4.2022
*/
package ija.project.model.classdiagram;

import ija.project.model.UMLElement;

/**
 * Class representing a type of a method or attribute
 */
public class UMLType extends UMLElement {


    private final Boolean isUserDefined;


    public UMLType(String name, Boolean isUserDefined){
        super(name);
        this.isUserDefined = isUserDefined;
    }


    /**
     * Get whether type is user defined or not
     * @return Boolean value
     */
    public Boolean getIsUserDefined(){
        return isUserDefined;
    }


    /**
     * Create UML type, that is not user defined (String, int, bool ...)
     * @param name Name of the type
     * @return Instance of created UMLType
     */
    public static UMLType forName(String name){
        return new UMLType(name, false);
    }

}
