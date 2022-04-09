
package ija.project.model.classdiagram;

import java.util.ArrayList;
import java.util.List;


public class UMLMethod extends UMLAttribute{

    private List<UMLClassifier> parameters = new ArrayList<UMLClassifier>();

    public UMLMethod(String name, String type) {
        super(name,type);
    }

    public UMLMethod(String name,UMLClassifier type){
        super(name,type);
    }

    /**
     * Create and add UMLClassifier to parameter list
     * @param name Name of the new UMLClassifier
     */
    public void addParam(String name){
        parameters.add(UMLClassifier.forName(name));
    }


    /**
     * Add UMLClassifier to list of parameter list
     * @param param Created UMLClassifier
     */
    public void addParam(UMLClassifier param){
        parameters.add(param);
    }

    /**
     * Remove parameter by index
     * @param index Index of removed parameter
     */
    public void removeParam(int index){
        parameters.remove(index);
    }

    /**
     * Remove parameter
     * @param param Parameter to be removed
     */
    public void removeParam(UMLClassifier param){
        parameters.remove(param);
    }

    /**
     * Get parameter by index
     * @param index Index
     * @return Parameter from index
     */
    public UMLClassifier getParam(int index){
        return parameters.get(index);
    }

}
