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

    public void setType(UMLClassifier type) {
        this.type = type;
    }

    public void setTpe(String name){
        type = UMLClassifier.forName(name);
    }

    public void setAccessibilityModifier(AccessibilityModifier accessibilityModifier){
        this.accessibilityModifier = accessibilityModifier;
    }

    public AccessibilityModifier getAccessibilityModifier(){
        return accessibilityModifier;
    }

    public enum AccessibilityModifier {
        PRIVATE,
        PUBLIC,
        PROTECTED
    }
}
