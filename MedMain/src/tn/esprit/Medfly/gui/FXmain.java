/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.Medfly.gui;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



/**
 *
 * @author khalf
 */
public class FXmain extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Load the FXML file and create the JavaFX scene
        Parent root = FXMLLoader.load(getClass().getResource("MedWelcome.fxml")); // Change this line to load MedTable.fxml
        Scene scene = new Scene(root);

        // Set the stage title
        primaryStage.setTitle("MedFly");

        // Set the scene and show the stage
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}