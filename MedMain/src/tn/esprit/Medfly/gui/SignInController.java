/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.Medfly.gui;



import tn.esprit.Medfly.services.ServiceMedecin;
import tn.esprit.Medfly.utilities.MyConnection;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Objects;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author hamza
 */


public class SignInController implements Initializable {

    @FXML
    private TextField inputmail;
    @FXML
    private TextField inputMotdePasse;
    @FXML
    private Label Emaillabel;
    @FXML
    private Label MotLabel;
    
        private Stage stage;
        
        private Scene scene;
        
        private Parent root;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

        
    @FXML
    private void loginMed(ActionEvent event) throws IOException {
        
       
      PreparedStatement preparedStatement = null;
    ServiceMedecin service = new ServiceMedecin();

    String email = inputmail.getText();
    String password = inputMotdePasse.getText();

    try {
        Connection cnx = MyConnection.getInstance().getcnx();
        preparedStatement = cnx.prepareStatement("SELECT MotDePasse FROM Medecin WHERE Email = ?");
        preparedStatement.setString(1, email);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            String storedPassword = resultSet.getString("MotDePasse");

            // Check if the entered password matches the stored password
            if (password.equals(storedPassword)) {
                // Password is correct, allow login and navigate to the next page
                // Create a new FXMLLoader to load the "MedAffich" FXML file
                FXMLLoader loader = new FXMLLoader(getClass().getResource("MedAffich.fxml"));
                root = loader.load(); // Load the FXML root element

                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
                // You can also perform other actions on the MedAffichController if needed

            } else {
                // Password is incorrect, show an error message
                Emaillabel.setText(""); // Clear any previous email error
                MotLabel.setText("Incorrect password. Please try again.");
            }
        } else {
            Emaillabel.setText("User not found. Please check your email.");
            MotLabel.setText(""); // Clear any previous password error
        }
    } catch (SQLException e) {
        // Handle database-related errors and FXML loading errors
        e.printStackTrace();
    }
}

    @FXML
    private void medsign(ActionEvent event) throws IOException {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("SignUp_Med.fxml")));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
    }
}
