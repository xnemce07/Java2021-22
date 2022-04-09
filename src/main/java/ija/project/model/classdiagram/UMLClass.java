package ija.project.model.classdiagram;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UMLClass extends UMLClassifier{

    private List<UMLAttribute> attributes;
    private List<UMLMethod> methods;

    public UMLClass(String name) {
        super(name, true);
    }

    //
    public void addAttribute(String name,String type){
        attributes.add(new UMLAttribute(name,type));
    }

    public void addAttribute(String name,UMLClassifier type){
        attributes.add(new UMLAttribute(name,type));
    }

    public void addAttribute(UMLAttribute attribute){
        attributes.add(attribute);
    }

    public UMLAttribute getAttribute(UUID id){
        for (UMLAttribute attribute:attributes){
            if(attribute.getId() == id){
                return attribute;
            }
        }
        return null;
    }

    public  UMLAttribute getAttribute(int index){
        return attributes.get(index);
    }

    public void removeAttribute(int index){
        attributes.remove(index);
    }

    public void removeAttribute(UUID id){
        attributes.remove(getAttribute(id));
    }

    public void removeAttribute(UMLAttribute attribute){
        attributes.remove(attribute);
    }


    //
    public void addMethod(String name,String type){
        methods.add(new UMLMethod(name,type));
    }

    public void addMethod(String name,UMLClassifier type){
        methods.add(new UMLMethod(name,type));
    }

    public void addMethod(UMLMethod method){
        methods.add(method);
    }

    public UMLMethod getMethod(UUID id){
        for (UMLMethod method:methods){
            if (method.getId() == id){
                return method;
            }
        }
        return null;
    }

    public  UMLMethod getMethod(int index){
        return methods.get(index);
    }

    public void removeMethod(int index){
        methods.remove(index);
    }

    public void removeMethod(UUID id){
        methods.remove(getMethod(id));
    }

    public void removeMethod(UMLMethod Method){
        methods.remove(Method);
    }



}
