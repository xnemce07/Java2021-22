
package ija.project.model.classdiagram;

import java.util.ArrayList;
import java.util.List;


public class UMLMethod extends UMLAttribute{

    // private List<UMLType> paramList = new ArrayList<>();

    public UMLMethod(String name, String type) {
        super(name, type);
    }

    public UMLMethod(String name, UMLType type){
        super(name, type);
    }

    // /**
    //  * Create and add UMLClassifier to parameter list
    //  * @param name Name of the new UMLClassifier
    //  */
    // public void addParam(String name){
    //     paramList.add(UMLType.forName(name));
    // }


    // /**
    //  * Add UMLClassifier to list of parameter list
    //  * @param param Created UMLClassifier
    //  */
    // public void addParam(UMLType param){
    //     paramList.add(param);
    // }

    // /**
    //  * Remove parameter by index
    //  * @param index Index of removed parameter
    //  */
    // public void removeParam(int index){
    //     paramList.remove(index);
    // }

    // /**
    //  * Remove parameter
    //  * @param param Parameter to be removed
    //  */
    // public void removeParam(UMLType param){
    //     paramList.remove(param);
    // }

    // /**
    //  * Get parameter by index
    //  * @param index Index
    //  * @return Parameter from index
    //  */
    // public UMLType getParam(int index){
    //     return paramList.get(index);
    // }

}
