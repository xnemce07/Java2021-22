/**
* Authors: Leopold Nemcek (xnemce07@stud.fit.vutbr.cz), Rudolf Hyksa (xhyksa00@stud.fit.vutbr.cz)
* Date: 12.4.2022
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
     * Set type to an instance of UMLType
     * @param type UMLType
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

    /**
     * Prints a text representation of the Attribute
     */
    public void print() {
        System.out.println(toString());
    }

    /**
     * Returns a string representation of the Attribute
     * 
     * @return Attribute as a string
     */
    public String toString() {
        String am = "";
        switch(getAccessModifier()){
            case PACKAGE:
                am = "~";
                break;
            case PRIVATE:
                am = "-";
                break;
            case PROTECTED:
                am = "#";
                break;
            case PUBLIC:
                am = "+";
                break;
            default:
                break;
        }
        return (am + " " + getName() + " : " + getType().getName());
    }
}
