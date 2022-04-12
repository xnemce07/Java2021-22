package ija.project;

import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;
// import org.junit.runner.Result;
// import org.junit.runner.notification.Failure;
// import org.junit.runner.notification.RunListener;

public class RunTests {
    public static void main(String args[]){
        System.out.println("Hello World!!!");

        JUnitCore junit = new JUnitCore();
        junit.addListener(new TextListener(System.out));
        junit.run(ClassDiagramTest.class);

        // Result result = JUnitCore.runClasses(ClassDiagramTest.class);
        // for (Failure failure : result.getFailures()) {
        //     System.out.println(failure.toString());
        // }
        // System.out.println(result.wasSuccessful());
    }
}
