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


import ija.project.GUI.RootLayout;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    public static void main(String[] args){
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException{
        RootLayout rootLayout = new RootLayout();
        stage.setScene(new Scene(rootLayout));
        stage.setTitle("ya bish");
        stage.setWidth(1000);
        stage.setHeight(800);
        stage.show();
    }
}