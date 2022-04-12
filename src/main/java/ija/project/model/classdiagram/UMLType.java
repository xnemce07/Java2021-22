package ija.project.model.classdiagram;

public class UMLType extends UMLElement{


    private final Boolean isUserDefined;


    public UMLType(String name, Boolean isUserDefined){
        super(name);
        this.isUserDefined = isUserDefined;
    }


    /**
     * Get whether type is user defined or not
     * @return Boolean value
     */
    public Boolean getIsUserDefined(){
        return isUserDefined;
    }


    /**
     * Create UML type, that is not user defined (String, int, bool ...)
     * @param name Name of the type
     * @return Instance of created UMLType
     */
    public static UMLType forName(String name){
        return new UMLType(name, false);
    }

}
