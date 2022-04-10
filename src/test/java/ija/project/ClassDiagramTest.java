/**
 * IJA 2021/22: Project 1
 * Testing class for JUnit.
 * (C) rk
 */

package ija.project;

//import org.junit.Assert;
import org.junit.Assert;
import org.junit.Test;


import ija.project.model.UMLElement;
import ija.project.model.classdiagram.UMLClass;
import ija.project.model.classdiagram.UMLClassDiagram;
import ija.project.model.classdiagram.UMLClassifier;
import ija.project.model.classdiagram.UMLInterface;
import ija.project.model.classdiagram.UMLAttribute;
import ija.project.model.classdiagram.UMLMethod;

import java.util.UUID;

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

        UMLClass gameClass = clsDiagram.createClass("Game");
        Assert.assertEquals("Class " + gameClass.getName() + " is present in the diagram.", clsDiagram.getClass(gameClass.getId()), gameClass);

        UUID mainMethodID = gameClass.addMethod("main", "void");
        Assert.assertEquals("kok", gameClass.getMethod(mainMethodID), )

        UMLClass loaderClass = new UMLClass("Loader");
        clsDiagram.addClass(loaderClass);

        UUID loadMethodID = loaderClass.addMethod("load_world", "void");
        UUID generateMethodID = loaderClass.addMethod("generate_map", "void");

        clsDiagram.createRelation("relation1", gameClass, loaderClass);


    }
}
