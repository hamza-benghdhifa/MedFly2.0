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
import tn.esprit.Medfly.entities.invitations;
import tn.esprit.Medfly.services.serviceInvitations;
/*import org.controlsfx.control.Notifications;*/
/**
 *
 * @author khalf
 */
public class MedInvitationController implements Initializable {
        @FXML
    private TextField AfficheEmailinv;

    @FXML
    private Button Envoiinv;

    @FXML
    private Button Acceuilinv;

    @FXML
    private Button consinvi;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    @FXML
    void Acceuilinv(ActionEvent event) {
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MedWelcome.fxml"));
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
    void AfficheEmailinv(ActionEvent event) {

    }

    @FXML
    void Envoiinv(ActionEvent event) throws IOException {
    // Get user input from the JavaFX interface fields
    try {
    String EmailDestinataireinv = AfficheEmailinv.getText();
    String Emailinv = "lamis@esprit.com"; // Use the locally declared email
    String Status = "pending"; // Set Status to "pending"

    // Create a new invitation object with the input data
    invitations newInvitation = new invitations(0,EmailDestinataireinv, Emailinv, Status);

    // Add the new invitation to the database using the service
    serviceInvitations service = new serviceInvitations();
    service.addInvitation(newInvitation);

    // Optionally, you can display a confirmation message to the user
    System.out.println("Invitation added to the database.");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MedConfirmationInv.fxml"));
        Parent root = loader.load();
        MedConfirmationInvController confirmationController = loader.getController();
        confirmationController.setConfAjMessage("Invitation added to the database.");
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    // You can transition to a confirmation interface or take other actions if needed.
    } catch (Exception e) {
        // Handle the exception when messages aren't added successfully
        e.printStackTrace();
        System.err.println("Error adding the invitation to the database: " + e.getMessage());
    }
    }

    @FXML
    void consinvi(ActionEvent event) {
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
    
}
