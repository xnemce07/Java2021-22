/**
* Authors: Leopold Nemcek (xnemce07@stud.fit.vutbr.cz), Rudolf Hyksa (xhyksa00@stud.fit.vutbr.cz)
* Date: 12.4.2022
*/
package ija.project.model.sequencediagram;

import ija.project.model.UMLElement;

import java.util.UUID;

public class UMLSeqMessage extends UMLElement {

    private UUID sender;
    private final UUID receiver;
    private boolean isDefined;
    private boolean highlightIfUnlinked;
    private UUID refMethod;

    public UMLSeqMessage(String name,UUID sender,UUID receiver,UUID refMethod,boolean highlightIfUnlinked, boolean isDefined){
        super(name);
        this.sender = sender;
        this.highlightIfUnlinked = highlightIfUnlinked;
        this.isDefined = isDefined;
        this.refMethod = refMethod;
        this.receiver = receiver;
    }

    public UUID getReceiverId(){
        return receiver;
    }

    public boolean isDefined() {
        return isDefined;
    }

    public void setDefined(UUID refMethod){
        isDefined = true;
        this.refMethod = refMethod;
    }

    public void setUndefined(){
        isDefined = false;
        refMethod = null;
    }

    public UUID getRefMethod(){
        return refMethod;
    }

    public boolean doHighlight() {
        return highlightIfUnlinked;
    }

    public void setChecked(boolean highlightIfUnlinked) {
        this.highlightIfUnlinked = highlightIfUnlinked;
    }

    public UUID getSender() {
        return sender;
    }

    public void setSender(UUID sender) {
        this.sender = sender;
    }

}
