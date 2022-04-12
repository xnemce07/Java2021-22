/**
* Authors: Leopold Nemcek (xnemce07@stud.fit.vutbr.cz), Rudolf Hyksa (xhyksa00@stud.fit.vutbr.cz)
* Date: 12.4.2023
*/
package ija.project.model.classdiagram;

/**
 * Class representing an attribute of a class
 */
public class UMLAttribute extends UMLElement{


    private UMLType type;
    private AccessModifier accessModifier = AccessModifier.PRIVATE;


    /**
     * Enum with all possible access modifiers
     */
    public enum AccessModifier {
        PRIVATE,
        PUBLIC,
        PROTECTED,
        PACKAGE
    }

    public UMLAttribute(String name, String type) {
        super(name);
        this.type = UMLType.forName(type);
    }

    public UMLAttribute(String name, UMLType type){
        super(name);
        this.type = type;
    }


    /**
     * Get argument type
     * @return argument type instance
     */
    public UMLType getType() {
        return type;
    }

    /**
     * Set argument type
     * @param type argument type
     */
    public void setType(String type){
        this.type = UMLType.forName(type);
    }

    /**
     * Set type to a instance of UMLClassifier
     * @param type UMLClassifier
     */
    public void setType(UMLType type) {
        this.type = type;
    }


    /**
     * Get accessibility modifier
     * @return Accessibility modifier
     */
    public AccessModifier getAccessModifier(){
        return accessModifier;
    }

    /**
     * Set accessibility modifier
     * @param accessModifier Accessibility modifier
     */
    public void setAccessModifier(AccessModifier accessModifier){
        this.accessModifier = accessModifier;
    }


}
