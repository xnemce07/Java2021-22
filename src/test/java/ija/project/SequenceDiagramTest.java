package ija.project;


import java.util.UUID;

import org.junit.Assert;
import org.junit.After;
import org.junit.Test;

import ija.project.model.classdiagram.UMLClass;
import ija.project.model.classdiagram.UMLClassDiagram;
import ija.project.model.exceptions.UUIDNotFoundException;
import ija.project.model.sequencediagram.UMLSeqClass;
import ija.project.model.sequencediagram.UMLSequenceDiagram;

public class SequenceDiagramTest {

    private UMLClassDiagram clsDia = UMLClassDiagram.getInstance();
    
    @After
    public void clear(){
        clsDia.clearDiagram();
    }

    @Test
    public void SeqClassCreateLinkTest() throws UUIDNotFoundException{
        UMLSequenceDiagram seqDia1 = new UMLSequenceDiagram("Seqence diagram 1");

        UMLClass cls1 = clsDia.createClass("cls1");

        UUID scls1 = seqDia1.createSeqClass("cls1", true);

        Assert.assertTrue(seqDia1.seqClassIsDefined(scls1));
    }

    @Test
    public void MessageCreateLinkTest() throws UUIDNotFoundException{
        UMLSequenceDiagram seqDia1 = new UMLSequenceDiagram("Seqence diagram 1");

        UMLClass cls1 = clsDia.createClass("cls1");
        UUID meth1 = cls1.createMethod("method1", "int");

        UUID scls1 = seqDia1.createSeqClass("cls1", true);
        UUID msg1 = seqDia1.createSeqMessage("method1", scls1, scls1, true);

        Assert.assertTrue(seqDia1.seqMessageIsDefined(msg1));
    }

    @Test
    public void ClassCreateLinkTest() throws UUIDNotFoundException{
        UMLSequenceDiagram seqDia1 = new UMLSequenceDiagram("Seqence diagram 1");

        UUID scls1 = seqDia1.createSeqClass("cls1", true);

        UMLClass cls1 = clsDia.createClass("cls1");

        Assert.assertTrue(seqDia1.seqClassIsDefined(scls1));
    }

    @Test
    public void MethodCreateLinkTest() throws UUIDNotFoundException{
        UMLSequenceDiagram seqDia1 = new UMLSequenceDiagram("Seqence diagram 1");

        UUID scls1 = seqDia1.createSeqClass("cls1", true);
        UUID msg1 = seqDia1.createSeqMessage("method1", scls1, scls1, true);

        UMLClass cls1 = clsDia.createClass("cls1");
        UUID meth1 = cls1.createMethod("method1", "int");

        Assert.assertTrue(seqDia1.seqMessageIsDefined(msg1));
    }

    @Test
    public void ClassRenameLinkTest() throws UUIDNotFoundException{
        UMLSequenceDiagram seqDia1 = new UMLSequenceDiagram("Seqence diagram 1");

        UMLClass cls1 = clsDia.createClass("newClass");

        UUID scls1 = seqDia1.createSeqClass("cls1", true);

        cls1.setName("cls1");

        Assert.assertTrue(seqDia1.seqClassIsDefined(scls1));
    }

    @Test
    public void MethodRenameLinkTest() throws UUIDNotFoundException{
        UMLSequenceDiagram seqDia1 = new UMLSequenceDiagram("Seqence diagram 1");

        UMLClass cls1 = clsDia.createClass("cls1");
        UUID meth1 = cls1.createMethod("newMethod", "int");

        UUID scls1 = seqDia1.createSeqClass("cls1", true);
        UUID msg1 = seqDia1.createSeqMessage("method1", scls1, scls1, true);

        cls1.setMethodName(meth1, "method1");

        Assert.assertTrue(seqDia1.seqMessageIsDefined(msg1));
    }

    @Test
    public void MethodRenameRelinkTest() throws UUIDNotFoundException{
        UMLSequenceDiagram seqDia1 = new UMLSequenceDiagram("Seqence diagram 1");

        UMLClass cls1 = clsDia.createClass("cls1");
        UUID meth1 = cls1.createMethod("method1", "int");
        
        UUID scls1 = seqDia1.createSeqClass("cls1", true);
        UUID msg1 = seqDia1.createSeqMessage("method1", scls1, scls1, true);

        cls1.setMethodName(meth1, "newMethodName");

        Assert.assertTrue(seqDia1.seqMessageIsDefined(msg1));
        Assert.assertEquals("newMethodName", seqDia1.getSeqMessageName(msg1));
    }

    @Test
    public void ClassRenameRelinkTest() throws UUIDNotFoundException{
        UMLSequenceDiagram seqDia1 = new UMLSequenceDiagram("Seqence diagram 1");

        UMLClass cls1 = clsDia.createClass("cls1");
        UUID meth1 = cls1.createMethod("method1", "int");

        UUID scls1 = seqDia1.createSeqClass("cls1", true);
        UUID msg1 = seqDia1.createSeqMessage("method1", scls1, scls1, true);

        cls1.setName("newname");

        Assert.assertTrue(seqDia1.seqClassIsDefined(scls1));
        Assert.assertTrue(seqDia1.seqMessageIsDefined(msg1));

        Assert.assertEquals("newname", seqDia1.getSeqClassName(scls1));
    }

    @Test
    public void MessageRenameLinkTest() throws UUIDNotFoundException{
        UMLSequenceDiagram seqDia1 = new UMLSequenceDiagram("Seqence diagram 1");

        UMLClass cls1 = clsDia.createClass("cls1");
        UUID meth1 = cls1.createMethod("method1", "int");

        UUID scls1 = seqDia1.createSeqClass("cls1", true);
        UUID msg1 = seqDia1.createSeqMessage("method1", scls1, scls1, true);

        Assert.assertTrue(seqDia1.seqMessageIsDefined(msg1));

        // See UMLSequenceDiagram.checkSeqMessage for this exception throw
        seqDia1.renameSeqMessage("newMessageName", msg1);

        Assert.assertFalse(seqDia1.seqMessageIsDefined(msg1));
    }

    @Test
    public void SeqClassRenameLinkTest() throws UUIDNotFoundException{
        UMLSequenceDiagram seqDia1 = new UMLSequenceDiagram("Seqence diagram 1");

        UMLClass cls1 = clsDia.createClass("cls1");
        UUID meth1 = cls1.createMethod("method1", "int");

        UUID scls1 = seqDia1.createSeqClass("cls1", true);
        UUID msg1 = seqDia1.createSeqMessage("method1", scls1, scls1, true);
        
        seqDia1.renameSeqClass("newSeqClassName", scls1);
        
        Assert.assertFalse(seqDia1.seqClassIsDefined(scls1));
        Assert.assertFalse(seqDia1.seqMessageIsDefined(msg1));
    }

    @Test
    public void MessageRenameRelinkTest() throws UUIDNotFoundException{
        UMLSequenceDiagram seqDia1 = new UMLSequenceDiagram("Seqence diagram 1");

        UMLClass cls1 = clsDia.createClass("cls1");
        UUID meth1 = cls1.createMethod("method1", "int");
        UUID meth2 = cls1.createMethod("method2", "String");

        UUID scls1 = seqDia1.createSeqClass("cls1", true);
        UUID msg1 = seqDia1.createSeqMessage("method1", scls1, scls1, true);

        Assert.assertTrue(seqDia1.seqMessageIsDefined(msg1));

        // See UMLSequenceDiagram.checkSeqMessage for this exception throw
        seqDia1.renameSeqMessage("method2", msg1);

        Assert.assertTrue(seqDia1.seqMessageIsDefined(msg1));
        Assert.assertEquals("method2", seqDia1.getSeqMessageName(msg1));
    }

    @Test
    public void SeqClassRenameRelinkTest() throws UUIDNotFoundException{
        UMLSequenceDiagram seqDia1 = new UMLSequenceDiagram("Seqence diagram 1");

        UMLClass cls1 = clsDia.createClass("cls1");
        UUID meth1 = cls1.createMethod("method1", "int");

        UMLClass cls2 = clsDia.createClass("cls2");
        UUID meth2 = cls2.createMethod("method2", "String");

        UUID scls1 = seqDia1.createSeqClass("cls1", true);
        UUID msg1 = seqDia1.createSeqMessage("method1", scls1, scls1, true);

        Assert.assertTrue(seqDia1.seqClassIsDefined(scls1));
        Assert.assertTrue(seqDia1.seqMessageIsDefined(msg1));
        
        seqDia1.renameSeqClass("cls2", scls1);
        
        Assert.assertTrue(seqDia1.seqClassIsDefined(scls1));
        Assert.assertTrue(seqDia1.seqMessageIsDefined(msg1));

        Assert.assertEquals("cls2", seqDia1.getSeqClassName(scls1));
        Assert.assertEquals("method2", seqDia1.getSeqMessageName(msg1));
    }

    @Test
    public void ClassRemovedUnlinkTest() throws UUIDNotFoundException{
        UMLSequenceDiagram seqDia1 = new UMLSequenceDiagram("Seqence diagram 1");
        UMLClass cls1 = clsDia.createClass("cls1");
        UUID meth1 = cls1.createMethod("method1", "");

        UUID scls1 = seqDia1.createSeqClass("cls1", true);
        UUID msg1 = seqDia1.createSeqMessage("method1", scls1, scls1, true);
        
        Assert.assertTrue(seqDia1.seqClassIsDefined(scls1));
        Assert.assertTrue(seqDia1.seqMessageIsDefined(msg1));
        
        // I'm not touching this NullPointerException..
        clsDia.removeClass(cls1.getId());
        
        Assert.assertFalse(seqDia1.seqClassIsDefined(scls1));
        Assert.assertFalse(seqDia1.seqMessageIsDefined(msg1));
    }

    @Test
    public void MethodRemovedUnlinkTest() throws UUIDNotFoundException{
        UMLSequenceDiagram seqDia1 = new UMLSequenceDiagram("Seqence diagram 1");

        UMLClass cls1 = clsDia.createClass("cls1");
        UUID meth1 = cls1.createMethod("method1", "int");
        
        UUID scls1 = seqDia1.createSeqClass("cls1", true);
        UUID scls2 = seqDia1.createSeqClass("cls2", true);
        UUID msg1 = seqDia1.createSeqMessage("method1", scls1, scls2, true);

        Assert.assertTrue(seqDia1.seqMessageIsDefined(msg1));

        cls1.removeMethod(meth1);

        Assert.assertFalse(seqDia1.seqMessageIsDefined(msg1));
    }

    @Test
    public void MessageSetReceiverRelinkTest() throws UUIDNotFoundException{
        // This is not implemented, or rather, I don't know how it's implemented

        // UMLSequenceDiagram seqDia1 = new UMLSequenceDiagram("Seqence diagram 1");

        // UMLClass cls1 = clsDia.createClass("cls1");
        // UMLClass cls2 = clsDia.createClass("cls2");
        // UUID meth1 = cls1.createMethod("method1", "int");
        // UUID meth2 = cls1.createMethod("method2", "int");
        
        // UUID scls1 = seqDia1.createSeqClass("cls1", true);
        // UUID scls2 = seqDia1.createSeqClass("cls2", true);
        // UUID msg1 = seqDia1.createSeqMessage("method1", scls1, scls2, true);
        Assert.assertTrue(false);
    }

    @Test
    public void DualSeqClassCreateLinkTest() throws UUIDNotFoundException{
        UMLSequenceDiagram seqDia1 = new UMLSequenceDiagram("Seqence diagram 1");
        UMLClass cls1 = clsDia.createClass("cls1");

        UUID scls1 = seqDia1.createSeqClass("cls1", true);
        UUID scls2 = seqDia1.createSeqClass("cls1", true);
        
        Assert.assertTrue(seqDia1.seqClassIsDefined(scls1));
        Assert.assertTrue(seqDia1.seqClassIsDefined(scls2));
    }

    @Test
    public void DualClassRenameRelinkTest() throws UUIDNotFoundException{
        UMLSequenceDiagram seqDia1 = new UMLSequenceDiagram("Seqence diagram 1");
        UMLClass cls1 = clsDia.createClass("cls1");

        UUID scls1 = seqDia1.createSeqClass("cls1", true);
        UUID scls2 = seqDia1.createSeqClass("cls1", true);
        
        cls1.setName("newClassName");

        Assert.assertTrue(seqDia1.seqClassIsDefined(scls1));
        Assert.assertTrue(seqDia1.seqClassIsDefined(scls2));
        
        Assert.assertEquals("newClassName", seqDia1.getSeqClassName(scls1));
        Assert.assertEquals("newClassName", seqDia1.getSeqClassName(scls2));
    }

    @Test
    public void DualSeqClassRenameRelinkTest() throws UUIDNotFoundException{
        UMLSequenceDiagram seqDia1 = new UMLSequenceDiagram("Seqence diagram 1");
        UMLClass cls1 = clsDia.createClass("cls1");
        UMLClass cls2 = clsDia.createClass("cls2");

        UUID scls1 = seqDia1.createSeqClass("cls1", true);
        UUID scls2 = seqDia1.createSeqClass("cls1", true);

        seqDia1.renameSeqClass("cls2", scls1);
        seqDia1.renameSeqClass("cls2", scls2);

        Assert.assertTrue(seqDia1.seqClassIsDefined(scls1));
        Assert.assertTrue(seqDia1.seqClassIsDefined(scls2));
    }

    @Test
    public void DualSeqClassRenameUnlinkTest() throws UUIDNotFoundException{
        UMLSequenceDiagram seqDia1 = new UMLSequenceDiagram("Seqence diagram 1");
        UMLClass cls1 = clsDia.createClass("cls1");

        UUID scls1 = seqDia1.createSeqClass("cls1", true);
        UUID scls2 = seqDia1.createSeqClass("cls1", true);

        seqDia1.renameSeqClass("newSeqName1", scls1);

        Assert.assertFalse(seqDia1.seqClassIsDefined(scls1));
        Assert.assertTrue(seqDia1.seqClassIsDefined(scls2));

        seqDia1.renameSeqClass("newSeqName2", scls2);

        Assert.assertFalse(seqDia1.seqClassIsDefined(scls2));
    }
}
