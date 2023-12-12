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
import tn.esprit.Medfly.services.serviceMessages;
import tn.esprit.Medfly.entities.messages;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class MedTableController implements Initializable {

    private ObservableList<messages> data;
    @FXML
    private TableView<messages> MessagesTable;

    @FXML
    private TableColumn<messages, Integer> IDcolumn;

    @FXML
    private TableColumn<messages, String> ObjetColumn;

    @FXML
    private TableColumn<messages, String> ContenuColumn;

    @FXML
    private TableColumn<messages, Timestamp> DateColumn;
    
    @FXML
    private TableColumn<messages, String> EmailColumn;
    
    @FXML
    private TextField EmailSpec;

    @FXML
    private Button Recherche;

    @FXML
    private Button Envoyer;
    private String userEmail = "hamza@esprit.com";
    @FXML
    private Button Acceuil;



    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Initialization code here
        initializeTable(); // Call this to populate the table initially
    }
    @FXML
    void Acceuil(ActionEvent event) {
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
    void ContenuColumn(ActionEvent event) {

    }

    @FXML
    void DateColumn(ActionEvent event) {

    }

    @FXML
    void Envoyer(ActionEvent event) {
        System.out.println("Button clicked"); // For debugging
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
    
    @FXML
    void EmailSpec(ActionEvent event) {

    }

    @FXML
    void IDColumn(ActionEvent event) {

    }

    @FXML
    void MessageTable(ActionEvent event) {

    }

    @FXML
    void ObjetColumn(ActionEvent event) {

    }

    void updateTable() {
        // Make sure 'messageService' is properly initialized here
        serviceMessages messageService = new serviceMessages();
        // Update the 'data' variable with new data from your database
        data.setAll(messageService.getFirst10MessagesByEmailDestinataire(userEmail));
    }

    @FXML
    void initializeTable() {
        // Initialize your table columns and data here
        IDcolumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        ObjetColumn.setCellValueFactory(new PropertyValueFactory<>("ObjetMessage"));
        ContenuColumn.setCellValueFactory(new PropertyValueFactory<>("ContenuMessage"));
        DateColumn.setCellValueFactory(new PropertyValueFactory<>("DateEnvoi"));
        EmailColumn.setCellValueFactory(new PropertyValueFactory<>("Email"));

        // Create an instance of your serviceMessages
        serviceMessages messageService = new serviceMessages();
        
        // Replace LoadLast10Messages with getFirst10MessagesByEmailDestinataire
        List<messages> messages = messageService.getFirst10MessagesByEmailDestinataire(userEmail);
        data = FXCollections.observableArrayList(messages); // Correctly update the class-level 'data'
        MessagesTable.setItems(data);
    }

@FXML
void Recherche(ActionEvent event) {
    String emailToSearch = EmailSpec.getText(); // Get the email to search from the EmailSpec text area

    // Create an instance of your serviceMessages
    serviceMessages messageService = new serviceMessages();

    // Call the getMessagesByEmail method with the emailToSearch and userEmail parameters
    List<messages> messages = messageService.getMessagesByEmail(emailToSearch, userEmail);

    // Update the 'data' variable with the new data from the database
    data.setAll(messages);
    MessagesTable.setItems(data);
}
      @FXML
    void EmailColumn(ActionEvent event) {

    }
}

