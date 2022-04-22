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

    // ========================================================================= //
    //                              SEQUENCE CLASSES                             //
    // ========================================================================= //

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

    private UMLSeqClass getSeqClass(UUID id) throws UUIDNotFoundException{
        for (UMLSeqClass seqClass:classList){
            if (seqClass.getId().equals(id)){
                return seqClass;
            }
        }
        throw new UUIDNotFoundException(id);
    }

    public void renameSeqClass(String name, UUID id) throws UUIDNotFoundException{
        UMLSeqClass seqClass = getSeqClass(id);
        seqClass.setUndefined();
        seqClass.setName(name);
        checkSeqClass(id);
    }

    public void removeSeqClass(UUID id) throws UUIDNotFoundException{
        UMLSeqClass seqClass = getSeqClass(id);
        List<UMLSeqMessage> toDelete = new ArrayList<>();
        for(UMLSeqMessage seqMessage: messageList){
           if(seqMessage.getSender().equals(seqClass) || seqClass.getMessageList().contains(seqMessage)) {
               toDelete.add(seqMessage);
           }
        }
        for(UMLSeqMessage seqMessage:toDelete){
            removeSeqMessage(seqMessage);
        }
        classList.remove(seqClass);

    }

    private List<UMLSeqClass> getSeqClasses(String name){
        ArrayList<UMLSeqClass> classes = new ArrayList<>();

        for (UMLSeqClass umlSeqClass:classList){
            classes.add(umlSeqClass);
        }
        return classes;
    }

    private void checkSeqClass(UUID seqClassID) throws UUIDNotFoundException{
        UMLSeqClass seqClass = getSeqClass(seqClassID);
        UMLClassDiagramNode refNode = classDiagram.getNodeByName(seqClass.getName());
        if(refNode == null){
            seqClass.setUndefined();
            return;
        }else{
            seqClass.setRefNode(refNode.getId());
            for (UMLSeqMessage seqMessage:seqClass.getMessageList()){
                UUID refMethod = refNode.getMethodIdByName(seqMessage.getName());
                if (refMethod == null){
                    seqMessage.setUndefined();
                }else{
                    seqMessage.setDefined(refMethod);
                }
            }
        }

    }

    private void checkSeqClassMessages(UUID seqClassID) throws UUIDNotFoundException{
        UMLSeqClass seqClass = getSeqClass(seqClassID);
        if(!seqClass.getIsDefined()){
            seqClass.setUndefined();
            return;
        }
        UMLClassDiagramNode refNode = classDiagram.getNode(seqClass.getRefNodeId());
        for (UMLSeqMessage seqMessage:seqClass.getMessageList()){
            UUID refMethod = refNode.getMethodIdByName(seqMessage.getName());
            if (refMethod == null){
                seqMessage.setUndefined();
            }else{
                seqMessage.setDefined(refMethod);
            }
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if(evt.getSource() instanceof UMLClassDiagram){
            if (evt.getPropertyName().equals("nodeName") || evt.getPropertyName().equals("newClass") || evt.getPropertyName().equals("newInterface")){
                UMLClassDiagramNode changedNode = null;
                try {
                    changedNode = classDiagram.getUMLClass((UUID) evt.getOldValue());
                }catch (UUIDNotFoundException e){
                    e.printStackTrace();
                    return;
                }
                for (UMLSeqClass seqClass:classList){
                    if(seqClass.getName().equals(changedNode.getName())){
                        seqClass.setRefNode(changedNode.getId());
                        try {
                            checkSeqClassMessages(seqClass.getId());
                        }catch (UUIDNotFoundException e){
                            e.printStackTrace();
                            return;
                        }
                        changedNode.addPropertyChangeListener(seqClass);
                    }
                }
            }else if(evt.getPropertyName().equals("removeClass") || evt.getPropertyName().equals("removeInterface")){
                for(UMLSeqClass seqClass: classList){
                    if(seqClass.getRefNodeId().equals((UUID) evt.getOldValue())){
                        seqClass.setUndefined();
                    }
                }
            }
        }
    }

    // ========================================================================= //
    //                             SEQUENCE MESSAGES                             //
    // ========================================================================= //


    public UUID createSeqMessage(String name,UUID receiver, UUID sender,boolean isChecked) throws UUIDNotFoundException{
        getSeqClass(sender);
        UMLSeqMessage message;
        UMLSeqClass seqReceiver = getSeqClass(receiver);
        if(seqReceiver.getIsDefined()){
            UUID refMethod = classDiagram.getNode(seqReceiver.getRefNodeId()).getMethodIdByName(name);
            if(refMethod == null){
                message = new UMLSeqMessage(name,sender,receiver,null,isChecked,false);
            }else{
                message = new UMLSeqMessage(name,sender,receiver,refMethod,isChecked,true);
            }
        }else{
            message = new UMLSeqMessage(name,sender,receiver,null,isChecked,false);
        }
        return message.getId();
    }

    public void renameSeqMessage(String name,UUID messageID) throws UUIDNotFoundException{
        UMLSeqMessage message = getSeqMessage(messageID);
        message.setName(name);
        checkSeqMessage(messageID);
    }


    private void checkSeqMessage(UUID messageId) throws UUIDNotFoundException{
        UMLSeqMessage message = getSeqMessage(messageId);
        if(!message.getIsDefined()){
            message.setUndefined();
            return;
        }

        UMLClassDiagramNode refNode = classDiagram.getNode(getSeqClass(message.getReceiverId()).getId());
        UUID refMethod = refNode.getMethodIdByName(message.getName());

        if(refMethod == null){
            message.setUndefined();
        }else{
            message.setDefined(refMethod);
        }
    }

    private UMLSeqMessage getSeqMessage(UUID id) throws UUIDNotFoundException{
        for (UMLSeqMessage seqMessage:messageList){
            if (seqMessage.getId().equals(id)){
                return seqMessage;
            }
        }
        throw new UUIDNotFoundException(id);
    }

    public void removeSeqMessage(UUID id){
        UMLSeqMessage message = null;
        try{
            message = getSeqMessage(id);
        }catch (UUIDNotFoundException e){
            return;
        }
        messageList.remove(message);
    }

    public void removeSeqMessage(UMLSeqMessage message){
        messageList.remove(message);
    }




}
