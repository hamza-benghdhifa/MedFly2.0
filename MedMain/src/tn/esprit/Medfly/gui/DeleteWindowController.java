/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TableView;

import Service.gestion_service;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class DeleteWindowController implements Initializable {

    static int integer;

    @FXML
    private TextField txt_Id_supp;
    @FXML
    private Button delete;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    
 private Stage stage;
    private Scene scene;
    private Parent Parent;
    @FXML
    private void retourner_aj(ActionEvent event) {
          try {
        Parent parent = FXMLLoader.load(getClass().getResource("Acceuil.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
        } catch (IOException ex) {
            Logger.getLogger(Ges_window.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
      /*  public int getID() {
        String text = txt_Id_supp.getText();   
         int integer = 0;
         integer = Integer.parseInt(text);
        try {
         integer = Integer.parseInt(text); 
        
            } catch (NumberFormatException e) {
        System.err.println("La chaîne de caractères n'est pas un entier valide.");
        }
        return integer;
    }*/
    public static boolean isInteger(String input){
        try{
                             Integer.parseInt(input);
                             return true;

            } catch (NumberFormatException e) {
                             return false;

            }
    }
    @FXML
    private void Delete(ActionEvent event) {
          String text = txt_Id_supp.getText(); 
        int integer = 0;
        try {
         integer = Integer.parseInt(text); 
        
            } catch (NumberFormatException e) {
        System.err.println("La chaîne de caractères n'est pas un entier valide.");
        }
        gestion_service gs = new gestion_service();
        if (gs.chercher(integer)==false){
            JOptionPane.showMessageDialog(null, "id n'existe pas ");
        }else if(isInteger(txt_Id_supp.getText())== true){
            gs.supprimer(integer);
            JOptionPane.showMessageDialog(null, "Suppression effectuer ");
        }else
            JOptionPane.showMessageDialog(null, "veuillez entrez un entier! ");
       /* if(isInteger(txt_Id_supp.getText())== true  ){
            //gestion_service gs = new gestion_service();
            
            gs.supprimer(integer);
            JOptionPane.showMessageDialog(null, "Suppression effectuer ");
        }else if(gs.chercher(integer)==false){
            JOptionPane.showMessageDialog(null, "id n'existe pas ");
        }else
            JOptionPane.showMessageDialog(null, "veuillez entrez un entier! ");*/
         //gestion_service gs = new gestion_service();
           /*try {
        Parent parent = FXMLLoader.load(getClass().getResource("confirmation.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
        } catch (IOException ex) {
            Logger.getLogger(Ges_window.class.getName()).log(Level.SEVERE, null, ex);
        }*/
           
         //gs.supprimer(integer);
         //JOptionPane.showMessageDialog(null, "Suppression effectuer ");
    }
    
    
}
