package ija.project.model.classdiagram;

import ija.project.model.UMLElement;

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


    public UMLType getType() {
        return type;
    }

    /**
     * Set type to UMLClassifier with specified name
     * @param name Name
     */
    public void setType(String name){
        type = UMLType.forName(name);
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
