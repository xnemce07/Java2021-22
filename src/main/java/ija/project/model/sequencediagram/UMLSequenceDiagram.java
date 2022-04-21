/**
* Authors: Leopold Nemcek (xnemce07@stud.fit.vutbr.cz), Rudolf Hyksa (xhyksa00@stud.fit.vutbr.cz)
* Date: 12.4.2022
*/
package ija.project.model.sequencediagram;

import ija.project.model.UMLElement;
import ija.project.model.classdiagram.UMLClass;
import ija.project.model.classdiagram.UMLClassDiagram;
import ija.project.model.classdiagram.UMLClassDiagramNode;
import ija.project.model.classdiagram.UMLMethod;
import ija.project.model.exceptions.UUIDNotFoundException;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UMLSequenceDiagram extends UMLElement implements PropertyChangeListener {

    private UMLClassDiagram classDiagram = UMLClassDiagram.getInstance();

    private List<UMLSeqClass> classList = new ArrayList<>();
    private List<UMLSeqMessage> messageList = new ArrayList<>();

    public UMLSequenceDiagram(String name){
        super(name);
        UMLClassDiagram.getInstance().addPropertyChangeListener(this);
    }

    public UUID createSeqClass(String name, boolean isChecked){
        UMLClassDiagramNode refNode =  classDiagram.getNodeByName(name);
        UMLSeqClass seqClass = null;
        if (refNode == null){
            seqClass = new UMLSeqClass(name,false,isChecked,null);
        }else{
            seqClass = new UMLSeqClass(name,true,isChecked,refNode.getId());
            refNode.addPropertyChangeListener(seqClass);
        }
        return seqClass.getId();
    }

    public UUID createSeqMessage(String name,UUID receiver, UUID sender,boolean isChecked) throws UUIDNotFoundException{
        UMLSeqMessage message;
        if(getSeqClass(receiver).getIsDefined()){
            UUID refMethod = classDiagram.getNode(receiver).getIdByName(name);
            if(refMethod == null){
                message = new UMLSeqMessage(name,sender,null,isChecked,false);
            }else{
                message = new UMLSeqMessage(name,sender,refMethod,isChecked,true);
            }
        }else{
            message = new UMLSeqMessage(name,sender,null,isChecked,false);
        }
        return message.getId();
    }

    private UMLSeqClass getSeqClass(UUID id) throws UUIDNotFoundException{
        for (UMLSeqClass seqClass:classList){
            if (seqClass.getId().equals(id)){
                return seqClass;
            }
        }
        throw new UUIDNotFoundException(id);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
