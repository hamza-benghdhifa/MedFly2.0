/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TableView;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

import javafx.stage.Stage;

/**
 *
 * @author Dell
 */
public class Ges_window extends Application {
    
    @Override
    public void start(Stage primaryStage) {
     
        try {
        Parent parent = FXMLLoader.load(getClass().getResource("window_acceuil.fxml"));
        Scene scene = new Scene(parent);
        scene.setFill(Color.TRANSPARENT);
       // primaryStage.setTitle("**** CATEGORIES ****");
        primaryStage.setScene(scene);
        primaryStage.show();
        } catch (IOException ex) {
            Logger.getLogger(Ges_window.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws DocumentException {
        
       
   
        launch(args);
    }
    
}
