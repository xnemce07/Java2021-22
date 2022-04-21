/**
* Authors: Leopold Nemcek (xnemce07@stud.fit.vutbr.cz), Rudolf Hyksa (xhyksa00@stud.fit.vutbr.cz)
* Date: 12.4.2022
*/
package ija.project.model.sequencediagram;

import ija.project.model.classdiagram.UMLClass;
import ija.project.model.classdiagram.UMLClassDiagram;

import java.util.ArrayList;
import java.util.List;

public class UMLSequenceDiagram {
    private List<UMLSeqClass> classList = new ArrayList<>();
    private List<UMLSeqMessage> messageList = new ArrayList<>();

    public UMLSeqClass createSeqClass(){
        return null;
    }

    private UMLClass bindToClass(String seqClassName){
        return null;
    }

    public UMLSeqMessage createSeqMethod(){
        return null;
    }
}
