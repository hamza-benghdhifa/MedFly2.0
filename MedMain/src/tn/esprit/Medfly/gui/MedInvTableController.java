/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.Medfly.gui;

import java.io.IOException;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import javafx.event.ActionEvent;
import tn.esprit.Medfly.services.serviceInvitations;
import tn.esprit.Medfly.entities.invitations;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
/*import org.controlsfx.control.Notifications;*/

/**
 *
 * @author khalf
 */
public class MedInvTableController implements Initializable {
    private ObservableList<invitations> data; 
   
    @FXML
    private TextField EmailSpecInv;

    @FXML
    private Button Accueilinv;

    @FXML
    private Button Ajoutinv;

    @FXML
    private Button Rechercheinv;

    @FXML
    private TableView<invitations> TableInv;

    @FXML
    private TableColumn<invitations, String> EmailinvColumn;

    @FXML
    private TableColumn<invitations, String> StatusinvColumn;

    @FXML
    private Button Acceptinv;
    private String userEmail = "hamza@esprit.com";
    
    
    public void initialize(URL url, ResourceBundle rb) {
        // Initialization code here
        initializeTable();
}

        @FXML
    void Acceptinv(ActionEvent event) {
    invitations selectedInvitation = TableInv.getSelectionModel().getSelectedItem();

    if (selectedInvitation != null) {
        // Update the "Status" in the TableView
        selectedInvitation.setStatus("accepted");

        // Update the "Status" in the database
        serviceInvitations service = new serviceInvitations();
        service.updateInvitationStatus(selectedInvitation.getIdinvi(), "accepted");
        
        /*sendNotificationToInviter(selectedInvitation.getEmailinv());*/
        // Refresh the TableView (optional)
        TableInv.refresh();

        // Optionally, you can display a confirmation message to the user
        System.out.println("Invitation status updated to 'accepted'.");

    } else {
        // Handle the case where no invitation is selected
        System.out.println("No invitation selected.");
    }
    }
    /*void sendNotificationToInviter(String inviterEmail) {
    if ("lamis@esprit.com".equals(inviterEmail)) {
        Notifications.create()
                .title("Invitation Accepted")
                .text("Your invitation has been accepted.")
                .showInformation();
}*/






    @FXML
    void Accueilinv(ActionEvent event) {
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
    void Ajoutinv(ActionEvent event) {
        System.out.println("Button clicked"); // For debugging
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
    void EmailinvColumn(ActionEvent event) {

    }

    @FXML
    void Rechercheinv(ActionEvent event) {
    String emailToSearch = EmailSpecInv.getText(); // Get the email to search from the EmailSpec text area

    // Create an instance of your serviceMessages
    serviceInvitations invitationService = new serviceInvitations();

    // Call the getMessagesByEmail method with the emailToSearch and userEmail parameters
    List<invitations> invitations = invitationService.getInvitationsByEmail(emailToSearch, userEmail);

    // Update the 'data' variable with the new data from the database
    data.setAll(invitations);
    TableInv.setItems(data);
    }

    @FXML
    void StatusinvColumn(ActionEvent event) {

    }

    @FXML
    void TableInv(ActionEvent event) {

    }
    @FXML   
void initializeTable() {
    // Initialize your table columns and data here
    EmailinvColumn.setCellValueFactory(new PropertyValueFactory<>("Emailinv"));
    StatusinvColumn.setCellValueFactory(new PropertyValueFactory<>("Status"));

    // Create an instance of your serviceInvitations
    serviceInvitations invitationService = new serviceInvitations();
    
    // Fetch all invitations from the "invitations" table
    List<invitations> invitations = invitationService.getFirst10InvitationsForUser(userEmail);

    // Update the table with the retrieved data
    data = FXCollections.observableArrayList(invitations);
    TableInv.setItems(data);
}
    @FXML
    void EmailSpecInv(ActionEvent event) {

    }
    
}
