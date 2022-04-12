/**
 * IJA 2021/22: Project 1
 * Testing class for JUnit.
 * (C) rk
 */

package ija.project;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ija.project.model.classdiagram.UMLClass;
import ija.project.model.classdiagram.UMLClassDiagram;
import ija.project.model.classdiagram.UMLRelation;
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
