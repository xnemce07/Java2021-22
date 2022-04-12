/**
 * IJA 2021/22: Project 1
 * Testing class for JUnit.
 * (C) rk
 */

package ija.project;

// import static org.junit.jupiter.api.Assertions.assertAll;

import javax.print.attribute.standard.DialogOwner;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ija.project.model.classdiagram.UMLAttribute;
import ija.project.model.classdiagram.UMLClass;
import ija.project.model.classdiagram.UMLClassDiagram;
import ija.project.model.classdiagram.UMLMethod;
import ija.project.model.classdiagram.UMLRelation;
import ija.project.model.classdiagram.UMLType;
import ija.project.model.classdiagram.exceptions.NameUnavailableException;
import ija.project.model.classdiagram.exceptions.UUIDNotFoundException;

/**
 * Unit test for Class Diagram backend. Tests
 */
public class ClassDiagramTest {
    @Before
    public void clear(){
        UMLClassDiagram dia = UMLClassDiagram.getInstance();
        dia.clearDiagram();
    }

    @Test
    public void yes(){
        boolean yes = true;
        Assert.assertTrue(yes);
    }

    @Test
    public void TestClasses(){
        UMLClassDiagram dia = UMLClassDiagram.getInstance();

        try{
            UMLClass clsBook = dia.createClass("Book");
            UMLAttribute attrAuthor = clsBook.createAttribute("Author", "String");
            UMLMethod methThrow = clsBook.createMethod("Throw", "boolean");

            Assert.assertThrows("Attribute names are unique within a class", NameUnavailableException.class, () -> {
                clsBook.createAttribute("Author", "void");
            });
            Assert.assertThrows("Method names are unique within a class", NameUnavailableException.class, () -> {
                clsBook.createMethod("Throw", "void");
            });
            
            UMLAttribute attr2 = clsBook.createAttribute();
            Assert.assertThrows("Attribute names are unique within a class", NameUnavailableException.class, () -> {
                clsBook.setAttributeName(attr2.getId(), "Author");
            });

        } catch (NameUnavailableException e){
            e.printStackTrace();
        }

    }

    @Test
    public void TestNameGenerating(){
        UMLClassDiagram dia = UMLClassDiagram.getInstance();
        Assert.assertNotNull(dia);
        Assert.assertEquals("Same instance u.u", 0, dia.getClassList().size());

        UMLClass cls1 = dia.createClass();
        //System.out.println(cls1.getName());
        //Assert.assertEquals("THIS ONE",cls1.getName(),"New class");
        Assert.assertTrue(cls1.getName().equals("New Class"));
        Assert.assertEquals("Class with name \"New Class\" was not found",dia.getUMLClass(cls1.getName()).getId(),cls1.getId());
        Assert.assertTrue("Name is not taken",dia.nameTaken(cls1.getName()));
        UMLClass cls2 = dia.createClass();
        Assert.assertTrue("Unexpected name: " + cls2.getName() ,cls2.getName().equals("New Class(1)"));

        try{
            UMLClass cls3 = dia.createClass("New Class(2)");
        } catch (NameUnavailableException e){
            System.out.println("zdochni");
        }
        UMLClass cls4 = dia.createClass();
        Assert.assertTrue("Expected name: New Class(3)", cls4.getName().equals("New Class(3)"));

        // Assert.assertThrows("kok", Exception.class, () -> {
        //     UMLClassDiagram.getInstance();
        // });

        Assert.assertThrows(NameUnavailableException.class, () -> {
            UMLClassDiagram.getInstance().createClass("New Class(1)");
        });
    }

    @Test
    public void TestClassRelations(){
        UMLClassDiagram dia = UMLClassDiagram.getInstance();
        // Assert.assertEquals("Same instance u.u", 0, dia.getClassList().size());
        UMLClass cls1 = dia.createClass();
        UMLClass cls2 = dia.createClass();
        UMLClass cls3 = dia.createClass();

        try{
            UMLRelation rel1to2 = dia.createRelation("1to2", cls1.getId(), cls2.getId());
            UMLRelation rel2to3 = dia.createRelation("2to3", cls2.getId(), cls3.getId());

            Assert.assertTrue("Relation \"" + rel1to2.getName() + "\" not linked properly", rel1to2.getStartNode().equals(cls1));
            Assert.assertTrue("Relation \"" + rel1to2.getName() + "\" not linked properly", rel1to2.getEndNode().equals(cls2));

            Assert.assertTrue("Relation \"" + rel2to3.getName() + "\" not linked properly", rel2to3.getStartNode().equals(cls2));
            Assert.assertTrue("Relation \"" + rel2to3.getName() + "\" not linked properly", rel2to3.getEndNode().equals(cls3));
        } catch (UUIDNotFoundException e){
            e.printStackTrace();
        }

        // Test whether relations get removed when unlinked from their start/endpoint
        dia.removeClass(cls2.getId());
        Assert.assertEquals(0, dia.getRelationList().size());

    }
}
