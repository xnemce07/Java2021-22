package ija.project.model.classdiagram;

import ija.project.model.UMLElement;

public class UMLClassifier extends UMLElement{
    private Boolean isUserDefined;

    public UMLClassifier(String name, Boolean isUserDefined){
        super(name);
        this.isUserDefined = isUserDefined;
    }

    public static UMLClassifier forName(String name){
        return new UMLClassifier(name, false);
    }

    public Boolean getIsUserDefined(){
        return isUserDefined;
    }

}
