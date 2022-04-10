package ija.project.model.classdiagram;

import ija.project.model.UMLElement;

public class UMLAttribute extends UMLElement{

    private UMLClassifier type;
    private AccessibilityModifier accessibilityModifier = AccessibilityModifier.PRIVATE;

    public UMLAttribute(String name,String type) {
        super(name);
        this.type = UMLClassifier.forName(type);
    }

    public UMLAttribute(String name,UMLClassifier type){
        super(name);
        this.type = type;
    }


    public UMLClassifier getType() {
        return type;
    }

    /**
     * Set type to a instance of UMLClassifier
     * @param type UMLClassifier
     */
    public void setType(UMLClassifier type) {
        this.type = type;
    }

    /**
     * Set type to UMLClassifier with specified name
     * @param name Name
     */
    public void setTpe(String name){
        type = UMLClassifier.forName(name);
    }

    /**
     * Set accessibility modifier
     * @param accessibilityModifier Accessibility modifier
     */
    public void setAccessibilityModifier(AccessibilityModifier accessibilityModifier){
        this.accessibilityModifier = accessibilityModifier;
    }

    /**
     * Get accessibility modifier
     * @return Accessibility modifier
     */
    public AccessibilityModifier getAccessibilityModifier(){
        return accessibilityModifier;
    }

    /**
     * Enum with all possible access modifiers
     */
    public enum AccessibilityModifier {
        PRIVATE,
        PUBLIC,
        PROTECTED
    }
}
