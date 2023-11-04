/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.Medfly.gui;

import tn.esprit.Medfly.utilities.MyConnection;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author hamza
 */
public class AdminLoginController implements Initializable {

    @FXML
    private TextField adname;
    @FXML
    private TextField adpass;
    @FXML
    private Label adlab;
    
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
    private void loginAdmin(ActionEvent event) throws IOException {
        



        
        

    try {
        // Establish a database connection using a custom class MyConnection
        Connection cnx = MyConnection.getInstance().getcnx();

        // Prepare an SQL statement to retrieve the password based on the admin's name (Nom)
        PreparedStatement preparedStatement = cnx.prepareStatement("SELECT Password FROM admin WHERE Nom = ?");
        String Nom = adname.getText();
        preparedStatement.setString(1, Nom);

        // Execute the SQL query
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            // Retrieve the stored password from the database
            String storedPassword = resultSet.getString("Password");

            // Check if the entered password (adpass) matches the stored password
            if (adpass.equals(storedPassword)) {
                // Password is correct, allow login and navigate to the next page
                FXMLLoader loader = new FXMLLoader(getClass().getResource("MedAffich.fxml"));
                Parent root = loader.load();
                Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } else {
                // Display an error message if the password is incorrect
                adlab.setText("Password is incorrect.");
            }
        }
    } catch (SQLException e) {
        // Handle any SQL-related exceptions, such as database connection issues
        e.printStackTrace();
    }
}
    
    }
