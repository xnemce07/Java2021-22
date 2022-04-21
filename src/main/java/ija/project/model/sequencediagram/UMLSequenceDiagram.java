/**
* Authors: Leopold Nemcek (xnemce07@stud.fit.vutbr.cz), Rudolf Hyksa (xhyksa00@stud.fit.vutbr.cz)
* Date: 12.4.2022
*/
package ija.project.model.sequencediagram;

import ija.project.model.classdiagram.UMLClass;
import ija.project.model.classdiagram.UMLClassDiagram;
import ija.project.model.classdiagram.UMLClassDiagramNode;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UMLSequenceDiagram implements PropertyChangeListener {

    private UMLClassDiagram classDiagram = UMLClassDiagram.getInstance();

    private List<UMLSeqClass> classList = new ArrayList<>();
    private List<UMLSeqMessage> messageList = new ArrayList<>();

    public UMLSequenceDiagram(){
        UMLClassDiagram.getInstance().addPropertyChangeListener(this);
    }

    public UUID createSeqClass(String name, boolean isChecked){
        UMLClassDiagramNode refNode =  classDiagram.getNodeByName(name);
        UMLSeqClass seqClass = null;
        if (refNode == null){
            seqClass = new UMLSeqClass(name,false,isChecked,null);
        }else{
            seqClass = new UMLSeqClass(name,true,isChecked,refNode.getId());
        }
        return seqClass.getId();
    }

    public UUID createMessage(UUID receiver,UUID sender,String name){
        return null;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
