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

    public UUID createSeqClass(String name, boolean highlightIfUnlinked){

        UMLClassDiagramNode refNode =  classDiagram.getNodeByName(name);
        UMLSeqClass seqClass = null;
        if (refNode == null){
            seqClass = new UMLSeqClass(name, false, highlightIfUnlinked, null);
        }else{
            seqClass = new UMLSeqClass(name, true, highlightIfUnlinked, refNode.getId());
            refNode.addPropertyChangeListener(seqClass);
        }
        classList.add(seqClass);
        return seqClass.getId();
    }

    private UMLSeqClass getSeqClass(UUID seqClassID) throws UUIDNotFoundException{
        for (UMLSeqClass seqClass:classList){
            if (seqClass.getId().equals(seqClassID)){
                return seqClass;
            }
        }
        throw new UUIDNotFoundException(seqClassID);
    }

    public String getSeqClassName(UUID seqClassID) throws UUIDNotFoundException{
        for (UMLSeqClass seqClass:classList){
            if (seqClass.getId().equals(seqClassID)){
                return seqClass.getName();
            }
        }
        throw new UUIDNotFoundException(seqClassID);
    }

    public void renameSeqClass(String name, UUID seqClassID) throws UUIDNotFoundException{
        UMLSeqClass seqClass = getSeqClass(seqClassID);
        seqClass.setUndefined();
        seqClass.setName(name);
        checkSeqClass(seqClassID);
    }

    public void removeSeqClass(UUID seqClassID) throws UUIDNotFoundException{
        UMLSeqClass seqClass = getSeqClass(seqClassID);
        List<UMLSeqMessage> toDelete = new ArrayList<>();
        for(UMLSeqMessage seqMessage: messageList){
            if(seqMessage.getSender().equals(seqClass.getId()) || seqClass.getMessageList().contains(seqMessage)) {
                toDelete.add(seqMessage);
            }
        }
        for(UMLSeqMessage seqMessage:toDelete){
            removeSeqMessage(seqMessage);
        }
        classList.remove(seqClass);

    }

    private List<UMLSeqClass> getSeqClassList(String name){
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
        if(!seqClass.isDefined()){
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

    public boolean seqClassIsDefined(UUID seqClassID) throws UUIDNotFoundException{
        for (UMLSeqClass seqClass : classList) {
            if (seqClass.getId().equals(seqClassID)){
                return seqClass.isDefined();
            }
        }

        throw new UUIDNotFoundException(seqClassID);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if(evt.getSource() instanceof UMLClassDiagram){
            if (evt.getPropertyName().equals("nodeName") || evt.getPropertyName().equals("createClass") || evt.getPropertyName().equals("createInterface")){
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
                    if(seqClass.getRefNodeId().equals((UUID)evt.getOldValue())){
                        seqClass.setUndefined();
                    }
                }
            }
        }
    }

    // ========================================================================= //
    //                             SEQUENCE MESSAGES                             //
    // ========================================================================= //
    
    public UUID createSeqMessage(String name, UUID receiver, UUID sender, boolean highlightIfUnlinked) throws UUIDNotFoundException{
        getSeqClass(sender);
        UMLSeqMessage message;
        UMLSeqClass seqReceiver = getSeqClass(receiver);
        if(seqReceiver.isDefined()){
            UUID refMethod = classDiagram.getNode(seqReceiver.getRefNodeId()).getMethodIdByName(name);
            if(refMethod == null){
                message = new UMLSeqMessage(name,sender,receiver,null,highlightIfUnlinked,false);
            }else{
                message = new UMLSeqMessage(name,sender,receiver,refMethod,highlightIfUnlinked,true);
            }
        }else{
            message = new UMLSeqMessage(name,sender,receiver,null,highlightIfUnlinked,false);
        }
        seqReceiver.addMessage(message);
        messageList.add(message);
        return message.getId();
    }

    public void renameSeqMessage(String name,UUID messageID) throws UUIDNotFoundException{
        UMLSeqMessage message = getSeqMessage(messageID);
        message.setName(name);
        checkSeqMessage(messageID);
    }

    private void checkSeqMessage(UUID messageID) throws UUIDNotFoundException{
        UMLSeqMessage message = getSeqMessage(messageID);


        // FIXME: There is probably an issue here..
        // I think we are looking for a Class Diagram Node using the ID of a Sequence Class
        //
        // Looking for a Node
        // Using a Sequence ID
        // Something's fishy here
        //
        // There seems to be a
        // NullPointerException here
        // That's what the tests say
        //
        // Sorry in advance
        // I'm unable to fix it
        // I've got other work
        //
        // This one is all yours
        // I have tracked it down for you
        // Please, knock yourself out
        //
        // NOTE:
        // "Unable" has three
        // Syllables, while "please" has one
        // What the fuck is this
        UMLClassDiagramNode refNode = classDiagram.getNode(getSeqClass(message.getReceiverId()).getRefNodeId());
        UUID refMethod = refNode.getMethodIdByName(message.getName());

        if(refMethod == null){
            message.setUndefined();
        }else{
            message.setDefined(refMethod);
        }
    }

    private UMLSeqMessage getSeqMessage(UUID messageID) throws UUIDNotFoundException{
        for (UMLSeqMessage seqMessage:messageList){
            if (seqMessage.getId().equals(messageID)){
                return seqMessage;
            }
        }
        throw new UUIDNotFoundException(messageID);
    }

    public String getSeqMessageName(UUID messageID) throws UUIDNotFoundException{
        for (UMLSeqMessage seqMessage : messageList){
            if (seqMessage.getId().equals(messageID)){
                return seqMessage.getName();
            }
        }
        throw new UUIDNotFoundException(messageID);
    }

    public void removeSeqMessage(UUID messageID) throws UUIDNotFoundException{
        UMLSeqMessage message = null;
        try{
            message = getSeqMessage(messageID);
        }catch (UUIDNotFoundException e){
            return;
        }
        UMLSeqClass receiver = getSeqClass(message.getReceiverId());
        messageList.remove(message);
        receiver.removeMessage(message);
    }

    private void removeSeqMessage(UMLSeqMessage message) throws  UUIDNotFoundException{
        UMLSeqClass receiver = getSeqClass(message.getReceiverId());
        receiver.removeMessage(message);
        messageList.remove(message);
    }

    public void setMessageSender(UUID messageId,UUID senderId) throws UUIDNotFoundException{
        UMLSeqMessage message = getSeqMessage(messageId);
        getSeqClass(senderId);
        message.setSender(senderId);
    }

    public void setMessageReceiver(UUID messageId, UUID receiverId) throws UUIDNotFoundException{
        UMLSeqMessage message = getSeqMessage(messageId);
        UMLSeqClass oldReceiver = getSeqClass(message.getReceiverId());
        UMLSeqClass newReceiver = getSeqClass(receiverId);
        oldReceiver.removeMessage(message);
        newReceiver.addMessage(message);
        message.setReceiverId(receiverId);
        checkSeqMessage(messageId);
    }

    public boolean seqMessageIsDefined(UUID messageID) throws UUIDNotFoundException{
        for (UMLSeqMessage message : messageList) {
            if (message.getId().equals(messageID)){
                return message.isDefined();
            }
        }

        throw new UUIDNotFoundException(messageID);
    }
}
