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

    public void addParam(String name){
        parameters.add(UMLClassifier.forName(name));
    }

    public void addParam(UMLClassifier param){
        parameters.add(param);
    }

    public void removeParam(int index){
        parameters.remove(index);
    }

    public void removeParam(UMLClassifier param){
        parameters.remove(param);
    }

    public UMLClassifier getParam(int index){
        return parameters.get(index);
    }

}
