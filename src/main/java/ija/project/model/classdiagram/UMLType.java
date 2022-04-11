package ija.project.model.classdiagram;

public class UMLType extends UMLElement{


    private final Boolean isUserDefined;


    public UMLType(String name, Boolean isUserDefined){
        super(name);
        this.isUserDefined = isUserDefined;
    }


    public Boolean getIsUserDefined(){
        return isUserDefined;
    }


    public static UMLType forName(String name){
        return new UMLType(name, false);
    }

}
