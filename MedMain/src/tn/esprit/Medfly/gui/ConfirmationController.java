/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TableView;

import Service.gestion_service;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class ConfirmationController implements Initializable {

    @FXML
    private Button ou;
    @FXML
    private Button no;
    private DeleteWindowController delete;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
public void setDeleteWindowController(DeleteWindowController controller) {
        this.delete = controller;
    }
    @FXML
    private void oui(ActionEvent event) {
       /* int integer=delete.getID();
        System.out.println("ID est :"+integer);
        gestion_service gs = new gestion_service();
        gs.supprimer(integer);*/
        
    }

    @FXML
    private void non(ActionEvent event) {
    }
    
}
