/**
* Authors: Leopold Nemcek (xnemce07@stud.fit.vutbr.cz), Rudolf Hyksa (xhyksa00@stud.fit.vutbr.cz)
* Date: 12.4.2022
*/
package ija.project.model.sequencediagram;

import ija.project.model.UMLElement;
import ija.project.model.classdiagram.UMLClassDiagramNode;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class UMLSeqClass extends UMLElement implements PropertyChangeListener {

    private List<UMLSeqMessage> messageList = new ArrayList<>();
    private UUID refNode;
    private boolean isDefined;
    private boolean highlightIfUnlinked;

    public UMLSeqClass(String name,boolean isDefined,boolean highlightIfUnlinked,UUID refNode){
        super(name);
        this.isDefined = isDefined;
        this.highlightIfUnlinked = highlightIfUnlinked;
        this.refNode = refNode;
    }



    public List<UMLSeqMessage> getMessageList() {
        return Collections.unmodifiableList(messageList);
    }

    public void addMessage(UMLSeqMessage message){
        messageList.add(message);
    }

    public void removeMessage(UMLSeqMessage message){
        messageList.remove(message);
    }

    public boolean isDefined() {
        return isDefined;
    }

    public void setUndefined(){
        isDefined = false;
        for(UMLSeqMessage seqMessage:messageList){
            seqMessage.setUndefined();
        }
    }

    public void setRefNode(UUID refNode){
        isDefined = true;
        this.refNode = refNode;
        for(UMLSeqMessage seqMessage:messageList){
            seqMessage.setUndefined();
        }
    }

    public boolean getHighlightIfUnlinked() {
        return highlightIfUnlinked;
    }

    public void setIsChecked(boolean checked) {
        highlightIfUnlinked = checked;
    }

    public UUID getRefNodeId() {
        return refNode;
    }

    public boolean doHighlight(){
        return highlightIfUnlinked;
    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if(evt.getSource() instanceof UMLClassDiagramNode){
            if(evt.getPropertyName().equals("methodName")){
                String newName = (String) evt.getNewValue();
                UUID changedMeth = (UUID) evt.getOldValue();
                for (UMLSeqMessage seqMessage:messageList){
                    if(seqMessage.getRefMethod() == changedMeth){
                        seqMessage.setName(newName);
                    }
                }
                for (UMLSeqMessage seqMessage:messageList){
                    if(seqMessage.getName().equals(newName) && !seqMessage.isDefined()){
                        seqMessage.setDefined((UUID) evt.getOldValue());
                    }
                }
            }else if(evt.getPropertyName().equals("createMethod")){
                String newMethodName = (String) evt.getNewValue();
                for (UMLSeqMessage seqMessage:messageList){
                    if(seqMessage.getName().equals(newMethodName) && !seqMessage.isDefined()){
                        seqMessage.setDefined((UUID) evt.getOldValue());
                    }
                }
            }else if(evt.getPropertyName().equals("removeMethod")){
                for (UMLSeqMessage seqMessage:messageList){
                    if(seqMessage.getRefMethod() ==  (UUID) evt.getOldValue()){
                        seqMessage.setUndefined();
                    }
                }
            }else if(evt.getPropertyName().equals("changeName")){
                setName((String) evt.getNewValue());
            }
        }
    }
}
