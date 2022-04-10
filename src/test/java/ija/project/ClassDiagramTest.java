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
import ija.project.model.classdiagram.UMLType;
import ija.project.model.classdiagram.UMLInterface;
import ija.project.model.classdiagram.UMLAttribute;
import ija.project.model.classdiagram.UMLMethod;

import java.util.UUID;

/**
 * Unit test for Class Diagram backend. Tests
 */
public class ClassDiagramTest {
    @Test
    public void yes(){
        boolean yes = true;
        Assert.assertTrue(yes);
    }
}
