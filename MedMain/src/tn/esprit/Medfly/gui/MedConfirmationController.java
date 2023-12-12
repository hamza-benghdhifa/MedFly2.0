package tn.esprit.Medfly.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.scene.input.MouseEvent;

public class MedConfirmationController implements Initializable {
    
    @FXML
    private Label Confirmation;
    
    @FXML
    private Button Retour;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Initialization code here
    }
    @FXML
    void Confirmation(MouseEvent event) {

    }

    public void setConfirmationMessage(String message) {
        Confirmation.setText(message);
    }
    
    @FXML
    private void Retour(ActionEvent event) {
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





