/**
 * IJA 2021/22: Project 1
 * Testing class for JUnit.
 * (C) rk
 */

package ija.project;


//import ija.project.model.classdiagram.exceptions.NameUnavailableException;
import org.junit.Assert;
import org.junit.Test;
//import org.junit.jupiter.*;


import ija.project.model.classdiagram.UMLClass;
import ija.project.model.classdiagram.UMLClassDiagram;

/**
 * Unit test for Class Diagram backend. Tests
 */
public class ClassDiagramTest {
    @Test
    public void yes(){
        boolean yes = true;
        Assert.assertTrue(yes);
    }

    @Test
    public void TestNameGenerating(){
        UMLClassDiagram dia = UMLClassDiagram.getInstance();
        Assert.assertNotNull(dia);

        UMLClass cls1 = dia.createClass();
        //System.out.println(cls1.getName());
        //Assert.assertEquals("THIS ONE",cls1.getName(),"New class");
        Assert.assertTrue(cls1.getName().equals("New Class"));
        Assert.assertEquals("Class with name \"New Class\" was not found",dia.getUMLClass(cls1.getName()).getId(),cls1.getId());
        Assert.assertTrue("Name is not taken",dia.nameTaken(cls1.getName()));
        UMLClass cls2 = dia.createClass();
        Assert.assertTrue("Unexpected name: " + cls2.getName() ,cls2.getName().equals("New Class(1)"));

        UMLClass cls3;

        Assert.assertThrows(Exception.class, () -> {
           UMLClassDiagram.getInstance().createClass("New Class(1)");
        });
    }
}
