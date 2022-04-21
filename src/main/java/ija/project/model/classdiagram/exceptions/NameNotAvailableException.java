/**
* Authors: Leopold Nemcek (xnemce07@stud.fit.vutbr.cz), Rudolf Hyksa (xhyksa00@stud.fit.vutbr.cz)
* Date: 12.4.2022
*/
package ija.project.model.classdiagram.exceptions;

/**
 * Thrown when trying to access an Element that doens't exist
 */
public class NameNotAvailableException extends Exception{
    public NameNotAvailableException(String name){
        super(name + " is already taken.");
    }
}
