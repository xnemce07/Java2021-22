/**
* Authors: Leopold Nemcek (xnemce07@stud.fit.vutbr.cz), Rudolf Hyksa (xhyksa00@stud.fit.vutbr.cz)
* Date: 12.4.2022
*/
package ija.project.model.sequencediagram;

import ija.project.model.UMLElement;

import java.util.UUID;

public class UMLSeqMessage extends UMLElement {

    private UUID sender;
    private boolean isDefined;
    private boolean isChecked;
    private UUID refMethod;

    public UMLSeqMessage(String name,UUID sender,UUID refMethod,boolean isChecked, boolean isDefined){
        super(name);
        this.sender = sender;
        this.isChecked = isChecked;
        this.isDefined = isDefined;
        this.refMethod = refMethod;
    }

    public boolean getIsDefined() {
        return isDefined;
    }

    public void setDefined(boolean defined) {
        isDefined = defined;
    }

    public boolean getIsChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public UUID getSender() {
        return sender;
    }

    public void setSender(UUID sender) {
        this.sender = sender;
    }
}
