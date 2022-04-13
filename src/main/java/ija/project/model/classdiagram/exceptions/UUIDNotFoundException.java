/**
* Authors: Leopold Nemcek (xnemce07@stud.fit.vutbr.cz), Rudolf Hyksa (xhyksa00@stud.fit.vutbr.cz)
* Date: 12.4.2022
*/
package ija.project.model.classdiagram.exceptions;

import java.util.UUID;

/**
 * Thrown when trying to access an Element that doens't exist
 */
public class UUIDNotFoundException extends Exception{
    public UUIDNotFoundException(UUID uuid){
        super("Object with uuid \"" + String.valueOf(uuid) + "\" was not found");
    }
}
