package sample;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import sample.ui.Memorama;

import javax.xml.soap.Text;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

        primaryStage.setTitle("Practicas de Topicos 2020");
        primaryStage.setMaximized(true);
       // primaryStage.setScene(new Scene(hbox, 500, 500));
        primaryStage.show();

        new Memorama();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
