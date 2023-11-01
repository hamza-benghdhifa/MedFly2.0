/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.Medfly.gui;

import static com.sun.org.apache.xerces.internal.util.XMLChar.isValidName;
import tn.esprit.Medfly.entities.Medecin;
import tn.esprit.Medfly.services.ServiceMedecin;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
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
public class Signup_MedController implements Initializable {

    @FXML
    private TextField NOMMED;
    @FXML
    private TextField PRENOMED;
    @FXML
    private TextField SPECMED;
    @FXML
    private TextField PAYSMED;
    @FXML
    private TextField DATEMED;
    @FXML
    private TextField NUMED;
    @FXML
    private TextField EMAILMED;
    @FXML
    private TextField MPMED;
    @FXML
    private Label lab1;
    @FXML
    private Label lab2;
    @FXML
    private Label lab3;
    @FXML
    private Label lab4;
    @FXML
    private Label lab5;
    @FXML
    private Label lab6;
    @FXML
    private Label lab7;
    @FXML
    private Label labs;

  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Signup(ActionEvent event) {
        
        
        // Validate input fields
    String name = NOMMED.getText();
    String prenom = PRENOMED.getText();
    String specialite = SPECMED.getText();
    String pays = PAYSMED.getText();
    String gradDateStr = DATEMED.getText();
    String gradNumStr = NUMED.getText();
    String email = EMAILMED.getText();
    String mot_passe  = MPMED.getText();
    

    if (name.isEmpty() || prenom.isEmpty() || specialite.isEmpty() || pays.isEmpty() || gradDateStr.isEmpty() || gradNumStr.isEmpty()) {
        labs.setText("Please fill in all fields.");
        return; // Exit the method to prevent adding an empty record
    }

    // Validate the "prenom" and "name" fields for letters only
    if (!isValidName(prenom) || !isValidName(name)) {
        lab1.setText("Name and prenom should contain only letters.");
        lab2.setText("Name and prenom should contain only letters.");
        
        return;
    }

    // Validate the "gradNum" field for digits only
    if (!isValidGradNum(gradNumStr)) {
        lab6.setText("Graduation number should contain only digits.");
        return;
    }

    // Validate the date format
    if (!isValidDateFormat(gradDateStr)) {
        lab5.setText("Invalid date format. Use yyyy-MM-dd.");
        return;
    }

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    Date gradDate = null;

    try {
        gradDate = dateFormat.parse(gradDateStr);
    } catch (ParseException e) {
        // Handle the parsing error, e.g., show an error message or log it
    }

    int gradNum = Integer.parseInt(gradNumStr);

    Medecin newMedecin = new Medecin(name, prenom, specialite, pays, gradDate, gradNum,email,mot_passe);
    ServiceMedecin serviceMedecin = new ServiceMedecin();
    serviceMedecin.ajouter(newMedecin);

    // Clear the error labels
    lab1.setText("");
    lab2.setText("");
    lab3.setText("");
    lab4.setText("");
    lab5.setText("");
    lab6.setText("");
    lab7.setText("");
    labs.setText("Welcome to MedFly , now you became a member sign in please ");
}

private boolean isValidGradNum(String gradNum) {
    // Use a regular expression to check if the gradNum contains only digits
    return gradNum.matches("^[0-9]+$");
}

private boolean isValidDateFormat(String dateStr) {
    // Define a regular expression for the "yyyy-MM-dd" format
    String regex = "\\d{4}-\\d{2}-\\d{2}";

    return dateStr.matches(regex);
}

    @FXML
    private void tomenuu(ActionEvent event) throws IOException {
        
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("FirstPageMedFly.fxml")));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        
    }

    @FXML
    private void tosigninn(ActionEvent event) throws IOException {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("SignIn.fxml")));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
    }
   
        
        
    }
    

