package ija.project.model.classdiagram;

import ija.project.model.UMLElement;

public class UMLType extends UMLElement{
    private Boolean isUserDefined;

    public UMLType(String name, Boolean isUserDefined){
        super(name);
        this.isUserDefined = isUserDefined;
    }

    public static UMLType forName(String name){
        return new UMLType(name, false);
    }

    public Boolean getIsUserDefined(){
        return isUserDefined;
    }

}
