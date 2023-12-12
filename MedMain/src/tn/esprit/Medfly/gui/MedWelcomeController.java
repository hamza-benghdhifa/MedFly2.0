/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.Medfly.gui;



import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import tn.esprit.Medfly.entities.messages;
import tn.esprit.Medfly.services.serviceMessages;
import java.sql.Date;
import java.util.ArrayList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
/*import org.controlsfx.control.Notifications;*/

/**
 *
 * @author khalf
 */
public class MedWelcomeController implements Initializable {
 
        @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
    @FXML
    private Button gotoenvinv;
    

    @FXML
    private Button gotoconsinv;

    @FXML
    private Button gotoenvmes;

    @FXML
    private Button gotoconsmes;

    @FXML
    void gotoconsinv(ActionEvent event) {
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MedInvTable.fxml"));
        Parent root = loader.load();

        // Create a new scene using the loaded FXML for the MedTable interface
        Scene scene = new Scene(root);

        // Get the current stage (window) and set the new scene
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    } catch (IOException e) {
        e.printStackTrace();
        // Handle exceptions, e.g., show an error message to the user
    }
    }
    

    @FXML
    void gotoconsmes(ActionEvent event) {
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MedTable.fxml"));
        Parent root = loader.load();

        // Create a new scene using the loaded FXML for the MedTable interface
        Scene scene = new Scene(root);

        // Get the current stage (window) and set the new scene
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    } catch (IOException e) {
        e.printStackTrace();
        // Handle exceptions, e.g., show an error message to the user
    }
    }

    @FXML
    void gotoenvinv(ActionEvent event) {
      try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MedInvitation.fxml"));
            Parent root = loader.load();
            MedInvitationController invitationController = loader.getController();

            // Create a new scene using the loaded FXML for the MedMessages interface
            Scene scene = new Scene(root);

            // Get the current stage (window) and set the new scene
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Handle exceptions, e.g., show an error message to the user
        }
    }

    @FXML
    void gotoenvmes(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MedMessages.fxml"));
            Parent root = loader.load();
            MedMessagesController messagesController = loader.getController();

            // Create a new scene using the loaded FXML for the MedMessages interface
            Scene scene = new Scene(root);

            // Get the current stage (window) and set the new scene
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Handle exceptions, e.g., show an error message to the user
        }
    }
}
