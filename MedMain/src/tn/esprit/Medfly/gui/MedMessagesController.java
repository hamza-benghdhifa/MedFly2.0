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
import java.util.List;
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
 * FXML Controller class
 *
 * @author khalf
 */
public class MedMessagesController implements Initializable {

    @FXML
    private TextField AfficheEmail;
    @FXML
    private TextField AfficheObjet;
    @FXML
    private TextArea AfficheContenu;
    @FXML
    private Button Retour;
    @FXML
    private Button Consulter;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void AfficheEmail(ActionEvent event) {
    }

    @FXML
    private void AfficheObjet(ActionEvent event) {
    }

    @FXML
    private void AfficheContenu(MouseEvent event) {
    }

@FXML
public void Envoi(ActionEvent event) {
    try {
        // Get user input from the JavaFX interface fields
        String EmailDestinataire = AfficheEmail.getText();
        String ObjetMessage = AfficheObjet.getText();
        String ContenuMessage = AfficheContenu.getText();
        String Email = "lamis@esprit.com"; // Replace with the value you want

        // Check if there are approved invitations
        serviceInvitations invitationsService = new serviceInvitations();
        List<invitations> approvedInvitations = invitationsService.getApprovedInvitations(EmailDestinataire, Email);

        if (!approvedInvitations.isEmpty()) {
            // There are approved invitations, proceed with sending the message
            // Create a new message object with the current date and user input
            java.util.Date currentDate = new java.util.Date();
            java.sql.Timestamp timestamp = new java.sql.Timestamp(currentDate.getTime());

            // Create a new message object with all the fields
            messages newMessage = new messages(0, ObjetMessage, ContenuMessage, EmailDestinataire, Email);
            newMessage.setDateEnvoi(timestamp); // Set the current date

            // Add the new message to the database using the add function
            serviceMessages service = new serviceMessages();
            service.add(newMessage);

            // Display a confirmation message
            System.out.println("Message added to the database.");

            // Check if 'EmailDestinataire' is 'hamza@esprit.com' and trigger a notification
            /*if ("hamza@esprit.com".equals(EmailDestinataire)) {
                Notifications.create()
                        .title("New Message")
                        .text("You have received a new message.")
                        .showInformation();
            }*/

            // Transition to the MedConfirmation interface
            MedConfirmationController confirmationController = loadConfirmationController();
            confirmationController.setConfirmationMessage("Message added to the database.");
        } else {
            // There are no approved invitations, show a specific message
            MedConfirmationController confirmationController = loadConfirmationController();
            confirmationController.setConfirmationMessage("Veuillez patienter jusqu'à ce que votre invitation soit acceptée par cet utilisateur.");
        }
    } catch (Exception e) {
        // Handle the exception when messages aren't added successfully
        e.printStackTrace();
        System.err.println("Error adding the message to the database: " + e.getMessage());
    }
}
@FXML
private void Retour(ActionEvent event) {
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
    void Consulter(ActionEvent event) {
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
    private MedConfirmationController loadConfirmationController() throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("MedConfirmation.fxml"));
    Parent root = loader.load();
    MedConfirmationController confirmationController = loader.getController();
    Scene scene = new Scene(root);
    Stage stage = new Stage();
    stage.setScene(scene);
    stage.show();
    return confirmationController;
    }





    }
    
