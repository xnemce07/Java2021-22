/**
* Authors: Leopold Nemcek (xnemce07@stud.fit.vutbr.cz), Rudolf Hyksa (xhyksa00@stud.fit.vutbr.cz)
* Date: 12.4.2022
*/
package ija.project.model.sequencediagram;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class UMLSeqClass implements PropertyChangeListener {

    private UUID id;
    private String name;
    private List<UMLSeqMessage> messageList = new ArrayList<>();
    private UUID refNode;
    private boolean isDefined;
    private boolean isChecked;

    public UMLSeqClass(String name,boolean isDefined,boolean isChecked,UUID refNode){
        id = UUID.randomUUID();
        this.name = name;
        this.isDefined = isDefined;
        this.isChecked = isChecked;
        this.refNode = refNode;
    }

    public UUID getId(){
        return id;
    }

    public List<UMLSeqMessage> getMessageList() {
        return Collections.unmodifiableList(messageList);
    }

    public void addMessage(UMLSeqMessage message){
        messageList.add(message);
    }

    public boolean getIsDefined() {
        return isDefined;
    }

    public void setDefined(boolean defined) {
        if(!defined){
            for(UMLSeqMessage message:messageList){
                message.setDefined(false);
            }
        }
        isDefined = defined;
    }

    public boolean getIsChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public UUID getRefNode() {
        return refNode;
    }

    public void setRefNode(UUID refNode) {
        this.refNode = refNode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
