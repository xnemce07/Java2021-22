/**
 * IJA 2021/22: Project 1
 * Testing class for JUnit.
 * (C) rk
 */

package ija.project;

import org.junit.Assert;
import org.junit.Test;

import ija.project.model.UMLElement;
import ija.project.model.classdiagram.UMLClass;
import ija.project.model.classdiagram.UMLClassDiagram;
import ija.project.model.classdiagram.UMLClassifier;
import ija.project.model.classdiagram.UMLInterface;
import ija.project.model.classdiagram.UMLAttribute;
import ija.project.model.classdiagram.UMLMethod;

/**
 * Unit test for Class Diagram backend. Tests
 */
public class ClassDiagramTest {
    @Test
    public void testUMLClassifier() {
        UMLClassifier cls1 = new UMLClass("C1");
        Assert.assertTrue("Class " + cls1.getName() + " is user defined.", cls1.getIsUserDefined());

        UMLClassifier cls2 = UMLClassifier.forName("int");
        Assert.assertFalse("Data type " + cls2.getName() + " is not user defined.", cls2.getIsUserDefined());

        UMLClassifier itf1 = new UMLInterface("ITF1");
        Assert.assertTrue("Interface " + itf1.getName() + " is user defined.", itf1.getIsUserDefined());
    }

    /**
     * Tests presence of the class diagram and classes created insode of it
     */
    @Test
    public void testUMLClassDiagram() {
        UMLClassDiagram clsDiagram = UMLClassDiagram.getInstance();
        Assert.assertNotNull(clsDiagram);

        UMLClass cls1 = clsDiagram.createClass("Class1");
        Assert.assertEquals("Class " + cls1.getName() + " is present in the diagram.", clsDiagram.getClass(cls1.getId()), cls1);
        
        UMLClass cls2 = new UMLClass("Class2");
        UMLClassifier cls3 = UMLClassifier.forName("int");

        UMLClassifier cls4 = new UMLClass("Class4");


    }
}
