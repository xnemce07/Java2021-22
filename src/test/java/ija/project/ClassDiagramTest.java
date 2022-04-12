/**
 * IJA 2021/22: Project 1
 * Testing class for JUnit.
 * (C) rk
 */

package ija.project;

import java.util.UUID;

// import static org.junit.jupiter.api.Assertions.assertAll;



import org.junit.Assert;
import org.junit.After;
import org.junit.Test;


import ija.project.model.classdiagram.UMLClass;
import ija.project.model.classdiagram.UMLClassDiagram;
import ija.project.model.classdiagram.UMLInterface;
import ija.project.model.classdiagram.UMLRelation;
import ija.project.model.classdiagram.exceptions.UUIDNotFoundException;

/**
 * Unit test for Class Diagram backend. Tests
 */
public class ClassDiagramTest {

    private UMLClassDiagram dia = UMLClassDiagram.getInstance();

    @After
    public void clear(){
        dia.clearDiagram();
    }


    @Test
    public void RelationLinkingTest() throws UUIDNotFoundException{
        UMLClass cls1 = dia.createClass();
        UMLClass cls2 = dia.createClass();
        UMLClass cls3 = dia.createClass();


        UMLRelation rel1to2 = dia.createRelation("1to2", cls1.getId(), cls2.getId());
        UMLRelation rel2to3 = dia.createRelation("2to3", cls2.getId(), cls3.getId());

        
        Assert.assertTrue("Relation \"" + rel1to2.getName() + "\" not linked properly", rel1to2.getStartNode().equals(cls1));
        Assert.assertTrue("Relation \"" + rel1to2.getName() + "\" not linked properly", rel1to2.getEndNode().equals(cls2));

        Assert.assertTrue("Relation \"" + rel2to3.getName() + "\" not linked properly", rel2to3.getStartNode().equals(cls2));
        Assert.assertTrue("Relation \"" + rel2to3.getName() + "\" not linked properly", rel2to3.getEndNode().equals(cls3));
    }

    @Test
    public void RelationDeleOnNodeDeleteTest() throws UUIDNotFoundException{
        UMLClass cls1 = dia.createClass();
        UMLClass cls2 = dia.createClass();
        UMLClass cls3 = dia.createClass();

        UMLRelation rel1to2 = dia.createRelation("1to2", cls1.getId(), cls2.getId());
        UMLRelation rel2to3 = dia.createRelation("2to3", cls2.getId(), cls3.getId());

        dia.removeClass(cls2.getId());
        Assert.assertEquals("Relations don't delete with corresponing nodes",dia.getRelationList().size(), 0);
    }

    @Test
    public void ClassCreationTest() throws UUIDNotFoundException{
        UMLClass cls1 = dia.createClass("Class 1");
        UUID attr1Id = cls1.createAttribute("attr1","integer");
        UUID meth1Id = cls1.createMethod("meth1","void");
        
        

        Assert.assertEquals(cls1.getAttributeName(attr1Id), "attr1");
        Assert.assertEquals(cls1.getAttributeTypeName(attr1Id), "integer");
        Assert.assertEquals(cls1.getMethodName(meth1Id), "meth1");
        Assert.assertEquals(cls1.getMethodTypeName(meth1Id), "void");
    }

    @Test
    public void InterfaceCreationTest() throws UUIDNotFoundException{
        UMLInterface itf1 = dia.createInterface("Interface 1");
        UUID meth2Id = itf1.createMethod("meth2", "");

        Assert.assertEquals(itf1.getMethodName(meth2Id), "meth2");
        Assert.assertEquals(itf1.getMethodTypeName(meth2Id), "");
    }

    @Test
    public void AttributeChangeTest() throws UUIDNotFoundException{
        UMLClass cls = dia.createClass();
        UUID attr = cls.createAttribute("oldname", "oldtype");
        cls.setAttributeName(attr, "newname");
        cls.setAttributeTypeName(attr, "newtype");

        Assert.assertEquals(cls.getAttributeName(attr), "newname");
        Assert.assertEquals(cls.getAttributeTypeName(attr), "newtype");
    }

    @Test
    public void MethodChangeTest() throws UUIDNotFoundException{
        UMLClass cls = dia.createClass();
        UUID meth = cls.createMethod("oldname", "oldtype");
        cls.setMethodName(meth, "newname");
        cls.setMethodTypeName(meth, "newtype");

        Assert.assertEquals(cls.getMethodName(meth), "newname");
        Assert.assertEquals(cls.getMethodTypeName(meth), "newtype");
    }

    @Test
    public void AttributeRemovalTest() throws UUIDNotFoundException{
        UMLClass cls = dia.createClass();
        UUID attr = cls.createAttribute();
        cls.createMethod();
        UUID attr2 = cls.createAttribute();
        
        Assert.assertEquals(cls.getAttributeList().size(), 2);

        cls.removeAttribute(attr);

        Assert.assertEquals(cls.getAttributeList().size(), 1);

        cls.removeAttribute(attr2);

        Assert.assertEquals(cls.getAttributeList().size(), 0);
        
    }
}
