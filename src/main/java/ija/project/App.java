/**
 * Please do not get mad
 * I am running out of time
 * Spaghetti got it
 * 
 * Demo of Class Diagram editor
 * 
 * Authors: Rudolf Hyksa (xhyksa00@stud.fit.vutbr.cz), Leopold Nemcek (xnemce07.stud.fit.vutbr.cz)
 * Date: 13.4.2022
 */
package ija.project;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import ija.project.model.classdiagram.UMLClass;
import ija.project.model.classdiagram.UMLClassDiagram;
import ija.project.model.classdiagram.UMLClassDiagramNode;
import ija.project.model.classdiagram.UMLInterface;
import ija.project.model.classdiagram.UMLRelation;
import ija.project.model.classdiagram.UMLRelation.RelationType;
import ija.project.model.exceptions.UUIDNotFoundException;

public class App {

    private static char opt;
    private static Scanner in = new Scanner(System.in);
    private static UMLClassDiagram dia = UMLClassDiagram.getInstance();

    // ========================================================================== //
    //                                      MAIN                                  //
    // ========================================================================== //
    public static void main(String[] args) throws Exception{
        printHelp();

        createDemo();

        diagramOptions();

        in.close();
    }

    // ========================================================================== //
    //                                    OPTIONS                                 //
    // ========================================================================== //


    private static void diagramOptions() throws UUIDNotFoundException{
        while (true){
            // System.out.println("Choose an option (number)");
            System.out.println("\n-------------------");
            System.out.println("1: Print Diagram");
            System.out.println("2: Edit Classes");
            System.out.println("3: Edit Interfaces");
            System.out.println("4: Edit Relations");
            System.out.println("5: Clear Diagram");
            System.out.println("6: Create Demo");
            System.out.println("7: Exit application");
            System.out.println("-------------------\n");
    
            opt = in.nextLine().charAt(0);
            switch (opt){
                case '1':
                    dia.print();
                    break;
                case '2':
                    showEditOptions("Class");
                    break;
                case '3':
                    showEditOptions("Interface");
                    break;
                case '4':
                    showEditOptions("Relation");
                    break;
                case '5':
                    UMLClassDiagram.getInstance().clearDiagram();
                    break;
                case '6':
                    createDemo();
                    break;
                case '7':
                    return;
                default:
                    System.out.println("Invalid option");
                    break;
            }
        }
    }

    private static void showEditOptions(String entityType){
        while (true){
            System.out.println("\n------------------------");
            if(entityType.equals("Class")){
                System.out.println("1: Show " + entityType + "es");
            }
            else{
                System.out.println("1: Show " + entityType + "s");
            }
            System.out.println("2: Create " + entityType);
            System.out.println("3: Edit " + entityType);
            System.out.println("4: Remove " + entityType);
            System.out.println("5: Go Back");
            System.out.println("------------------------\n");
    
            opt = in.nextLine().charAt(0);
            switch (opt){
                case '1':
                    if(entityType.equals("Class")){
                        for (UMLClass umlClass : dia.getClassList()) {
                            umlClass.print();
                        }
                    }
                    else if(entityType.equals("Interface")){
                        for (UMLInterface umlInterface : dia.getInterfaceList()) {
                            umlInterface.print();
                        }
                    }
                    else if(entityType.equals("Relation")){
                        for (UMLRelation umlRelation : dia.getRelationList()) {
                            umlRelation.print();
                        }
                    }
                    break;
                case '2':
                    if(entityType.equals("Class")) classCreate();
                    else if(entityType.equals("Interface")) interfaceCreate();
                    else if(entityType.equals("Relation")) relationCreate();
                    break;
                case '3':
                    if(entityType.equals("Class")) classEdit();
                    else if(entityType.equals("Interface")) interfaceEdit();
                    else if(entityType.equals("Relation")) relationEdit();
                    break;
                case '4':
                    if(entityType.equals("Class")) classRemove();
                    else if(entityType.equals("Interface")) interfaceRemove();
                    else if(entityType.equals("Relation")) relationRemove();
                    break;
                case '5':
                    return;
                default:
                    System.out.println("Invalid option");
                    break;
            }
        }
    }

    // ========================================================================== //
    //                                   CREATORS                                 //
    // ========================================================================== //


    private static void classCreate(){
        System.out.println("Enter name (leave blank for default): ");
        String name = in.nextLine();
        if(name.isEmpty()) dia.createClass();
        else dia.createClass(name);
    }

    private static void interfaceCreate(){
        System.out.println("Enter name (leave blank for default): ");
        String name = in.nextLine();
        if(name.isEmpty()) dia.createInterface();
        else dia.createInterface(name);
    }

    private static void relationCreate(){
        if((dia.getClassList().size() == 0) && (dia.getInterfaceList().size() == 0)){
            System.out.println("There are currently no Classes or Interfaces in the diagram.");
            return;
        }

        List<UMLClassDiagramNode> nodeList = new ArrayList<>();
        for (UMLClass umlClass : dia.getClassList()) {
            nodeList.add(umlClass);
        }
        for (UMLInterface umlInterface : dia.getInterfaceList()) {
            nodeList.add(umlInterface);
        }

        System.out.println("Available nodes:");
        
        int nodeCount = 1;
        for (UMLClassDiagramNode umlClassDiagramNode : nodeList) {
            System.out.println(nodeCount + ": " + umlClassDiagramNode.getName());
            nodeCount++;
        }

        UMLClassDiagramNode startNode = null;
        UMLClassDiagramNode endNode = null;
        int nodeIdx;

        while (true){
            System.out.println("Choose start node: ");
            try {
                nodeIdx = Integer.parseInt(in.nextLine());
                startNode = nodeList.get(nodeIdx - 1);
            } catch (IndexOutOfBoundsException|NumberFormatException e) {
                System.out.println("Invalid index");
                continue;
            }
            break;
        }
        while (true){
            System.out.println("Choose end node: ");
            try {
                nodeIdx = Integer.parseInt(in.nextLine());
                endNode = nodeList.get(nodeIdx - 1);
            } catch (IndexOutOfBoundsException|NumberFormatException e) {
                System.out.println("Invalid index");
                continue;
            }
            break;
        }

        System.out.println("Enter name (leave blank for no name): ");
        String name = in.nextLine();

        try {
            if(name.isEmpty()) dia.createRelation(startNode.getId(), endNode.getId());
            else dia.createRelation(name, startNode.getId(), endNode.getId());
        } catch (UUIDNotFoundException e) {
            System.out.println("Something went horribly wrong and I apologize but we'll have to shut this entire operation down");
            e.getStackTrace();
            System.exit(1);
        }
    }

    // ========================================================================== //
    //                                   REMOVERS                                 //
    // ========================================================================== //

    private static void classRemove() {
        List<UMLClass> classList = dia.getClassList();
        int classIdx = 1;
        System.out.println("Classes available for removal (removing a Class removes all Relations connected to it):");
        for (UMLClass umlClass : classList) {
            System.out.println("  " + classIdx + ": " + umlClass.getName());
            classIdx++;
        }

        while (true){
            System.out.println("\nChoose Class to remove ('x' to cancel): ");
            try {
                String read = in.nextLine();
                if(read.charAt(0) == 'x') break;
                classIdx = Integer.parseInt(read);
                dia.removeClass(classList.get(classIdx - 1).getId());
            } catch (IndexOutOfBoundsException|NumberFormatException e) {
                System.out.println("Invalid index");
                continue;
            }
            break;
        }

    }

    private static void interfaceRemove() {
        List<UMLInterface> interfaceList = dia.getInterfaceList();
        int itfIdx = 1;
        System.out.println("Interfaces available for removal (removing an Interface removes all Relations connected to it):");
        for (UMLInterface umlInterface : interfaceList) {
            System.out.println("  " + itfIdx + ": " + umlInterface.getName());
            itfIdx++;
        }

        while (true){
            System.out.println("\nChoose Interface to remove ('x' to cancel): ");
            try {
                String read = in.nextLine();
                if(read.charAt(0) == 'x') break;
                itfIdx = Integer.parseInt(read);
                dia.removeInterface(interfaceList.get(itfIdx - 1).getId());
            } catch (IndexOutOfBoundsException|NumberFormatException e) {
                System.out.println("Invalid index");
                continue;
            }
            break;
        }
    }

    private static void relationRemove() {
        List<UMLRelation> relationList = dia.getRelationList();
        int relIdx = 1;
        System.out.println("Relations available for removal:");
        for (UMLRelation umlRelation : relationList) {
            System.out.println("  " + relIdx + ": " + umlRelation.toString());
            relIdx++;
        }

        while (true){
            System.out.println("\nChoose Relation to remove ('x' to cancel): ");
            try {
                String read = in.nextLine();
                if(read.charAt(0) == 'x') break;
                relIdx = Integer.parseInt(read);
                dia.removeRelation(relationList.get(relIdx - 1).getId());
            } catch (IndexOutOfBoundsException|NumberFormatException e) {
                System.out.println("Invalid index");
                continue;
            }
            break;
        }
    }

    // ========================================================================== //
    //                                    EDITORS                                 //
    // ========================================================================== //


    private static void classEdit(){
        List<UMLClass> classList = dia.getClassList();
        if(classList.size() == 0){
            System.out.println("No Classes to edit.");
            return;
        }
        int classIdx = 1;
        System.out.println("Classes available for editing:");
        for (UMLClass umlClass : classList) {
            System.out.println("  " + classIdx + ": " + umlClass.getName());
            classIdx++;
        }

        UMLClass cls = null;

        while (true){
            System.out.println("\nChoose Class to edit: ");
            try {
                classIdx = Integer.parseInt(in.nextLine());
                cls = dia.getUMLClass(classList.get(classIdx - 1).getId());
            } catch (IndexOutOfBoundsException|NumberFormatException e) {
                System.out.println("Invalid index");
                continue;
            } catch (UUIDNotFoundException ex){
                System.out.println("Something went horribly wrong and I apologize but we'll have to shut this entire operation down");
                ex.getStackTrace();
                System.exit(1);
            }
            break;
        }

        System.out.println("\n----------------");
        System.out.println("1: Edit Name");
        System.out.println("2: Edit Attributes");
        System.out.println("3: Edit Methods");
        System.out.println("4: Back");
        System.out.println("----------------\n");

        opt = in.nextLine().charAt(0);
        while(true){
            switch(opt){
                case '1':
                    System.out.println("Enter new Name: ");
                    String newName = in.nextLine();
                    cls.setName(newName);
                    return;
                case '2':
                    System.out.println("Currently unsupported, I'm afraid");
                    // attributeOptions(cls);
                    return;
                case '3':
                    System.out.println("Currently unsupported, I'm afraid");
                    // editMethod(cls);
                    return;
                case '4':
                    return;
                default:
                    System.out.println("Invalid option");
                    break;
            }
        }
    }
    

    private static void interfaceEdit(){
        List<UMLInterface> interfaceList = dia.getInterfaceList();
        if(interfaceList.size() == 0){
            System.out.println("No Interfaces to edit.");
            return;
        }
        int itfIdx = 1;
        System.out.println("Interfaces available for editing:");
        for (UMLInterface umlInterface : interfaceList) {
            System.out.println("  " + itfIdx + ": " + umlInterface.getName());
            itfIdx++;
        }

        UMLInterface itf = null;

        while (true){
            System.out.println("\nChoose Interface to edit: ");
            try {
                itfIdx = Integer.parseInt(in.nextLine());
                itf = dia.getInterface(interfaceList.get(itfIdx - 1).getId());
            } catch (IndexOutOfBoundsException|NumberFormatException e) {
                System.out.println("Invalid index");
                continue;
            } catch (UUIDNotFoundException ex){
                System.out.println("Something went horribly wrong and I apologize but we'll have to shut this entire operation down");
                ex.getStackTrace();
                System.exit(1);
            }
            break;
        }

        System.out.println("\n----------------");
        System.out.println("1: Edit Name");
        System.out.println("2: Edit Attributes");
        System.out.println("3: Back");
        System.out.println("----------------\n");

        opt = in.nextLine().charAt(0);
        while(true){
            switch(opt){
                case '1':
                    System.out.println("Enter new Name: ");
                    String newName = in.nextLine();
                    itf.setName(newName);
                    return;
                case '2':
                    System.out.println("Currently unsupported, I'm afraid");
                    // attributeOptions(cls);
                    return;
                case '3':
                    return;
                default:
                    System.out.println("Invalid option");
                    break;
            }
        }
    }
    private static void relationEdit(){
        List<UMLRelation> relList = dia.getRelationList();
        if(relList.size() == 0){
            System.out.println("No Relations to edit.");
            return;
        }
        int relIdx = 1;
        System.out.println("Relations available for editing:");
        for (UMLRelation umlRelation : relList) {
            System.out.println("  " + relIdx + ": " + umlRelation.toString());
            relIdx++;
        }
        
        UMLRelation rel = null;

        while (true){
            System.out.println("\nChoose Relation to edit: ");
            try {
                relIdx = Integer.parseInt(in.nextLine());
                rel = dia.getRelation(relList.get(relIdx - 1).getId());
            } catch (IndexOutOfBoundsException|NumberFormatException e) {
                System.out.println("Invalid index");
                continue;
            } catch (UUIDNotFoundException ex){
                System.out.println("Something went horribly wrong and I apologize but we'll have to shut this entire operation down");
                ex.getStackTrace();
                System.exit(1);
            }
            break;
        }

        System.out.println("\n----------------");
        System.out.println("1: Edit Name");
        System.out.println("2: Edit Relation Type");
        System.out.println("3: Back");
        System.out.println("----------------\n");

        opt = in.nextLine().charAt(0);
        while(true){
            switch(opt){
                case '1':
                    System.out.println("Enter new Name: ");
                    String newName = in.nextLine();
                    rel.setName(newName);
                    return;
                case '2':
                    System.out.println("Choose Relation Type:");
                    System.out.println("  1: Association");
                    System.out.println("  2: Aggregation");
                    System.out.println("  3: Composition");
                    System.out.println("  4: Generalization");
                    opt = in.nextLine().charAt(0);
                    switch(opt){
                        case '1':
                            rel.setRelationType(RelationType.ASSOCIATION);
                            return;
                        case '2':
                            rel.setRelationType(RelationType.AGGREGATION);
                            return;
                        case '3':
                            rel.setRelationType(RelationType.COMPOSITION);
                            return;
                        case '4':
                            rel.setRelationType(RelationType.GENERALIZATION);
                            return;
                        default:
                            System.out.println("Invalid option");
                            break;
                    }
                    return;
                case '3':
                    return;
                default:
                    System.out.println("Invalid option");
                    break;
            }
        }
    }

    // ========================================================================== //
    //                                     UTILS                                  //
    // ========================================================================== //

    private static void printHelp(){
        System.out.println("Description:\n");
        System.out.println("This is a simple Text UI for a simple class diagram editor.");
        System.out.println("As of now, we support creating class diagram Nodes (Classes and Interfaces)");
        System.out.println("with Methods - and Attributes for Classes. Nodes can be connected via Relations.");
        System.out.println("Choose options using their listed index.");
        System.out.println("PS: Unfortunately, our time has ran out and some features may be");
        System.out.println("    unsupported in the UI, even though we managed to implement them;");
        System.out.println("    Namely Attribute and Method creation and editing.");
    }

    private static void createDemo() throws UUIDNotFoundException{
        dia.clearDiagram();

        UMLClass cls1 = dia.createClass("Class1");
        dia.getUMLClass(cls1.getId()).createAttribute("attr1", "String");
        dia.getUMLClass(cls1.getId()).createMethod("method1", "int");

        UMLInterface itf1 = dia.createInterface("Interface1");
        dia.getInterface(itf1.getId()).createMethod("interfaceMethod1", "void");

        UMLRelation rel1 = dia.createRelation("Relation1", cls1.getId(), itf1.getId());
        rel1.setRelationType(RelationType.GENERALIZATION);
    }
}