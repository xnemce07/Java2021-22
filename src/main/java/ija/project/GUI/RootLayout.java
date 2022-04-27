package ija.project.GUI;


import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class RootLayout extends BorderPane {



    public RootLayout(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("RootLayout.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);


        try{
            fxmlLoader.load();
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }


}
