/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.Medfly.gui;

import tn.esprit.Medfly.entities.Patient;
import tn.esprit.Medfly.services.ServicePatient;
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
public class AjoutPatController implements Initializable {

       @FXML
    private TextField TFMaladie;

    @FXML
    private TextField TFNumeroAss;

    @FXML
    private TextField TFdatenaissanceP;

    @FXML
    private TextField TFnamepat;

    @FXML
    private TextField TFprenompat;
    
    private Stage stage;
        
    private Scene scene;
        
    private Parent root;
    
    
     @FXML
    private Label con1;

    @FXML
    private Label con2;

    @FXML
    private Label con3;
        
    @FXML
    private Label labname;

    @FXML
    private Label labpre;

@FXML
private void AjoutPatient(ActionEvent event) {
    // Validate input fields
    String name = TFnamepat.getText();
    String prenom = TFprenompat.getText();
    String maladie = TFMaladie.getText();
    String dateStr = TFdatenaissanceP.getText();
    String assNumStr = TFNumeroAss.getText();

    if (name.isEmpty() || prenom.isEmpty() || maladie.isEmpty() || dateStr.isEmpty() || assNumStr.isEmpty()) {
        con1.setText("Please fill in all fields.");
        return; 
    }

    // Validate the "prenom" and "name" fields for letters only
    if (!isValidName(prenom) || !isValidName(name)) {
        labname.setText("Name and familyname should contain only letters.");
        labpre.setText("Name and familyname should contain only letters.");
        return;
    }

    // Validate the "assNum" field for digits only
    if (!isValidAssNum(assNumStr)) {
        con2.setText("Assurance number should contain only digits.");
        return;
    }

    // Validate the date format
    if (!isValidDateFormat(dateStr)) {
        con3.setText("Invalid date format. Use yyyy-MM-dd.");
        return;
    }

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    Date DateNaiss = null;

    try {
        DateNaiss = dateFormat.parse(dateStr);
    } catch (ParseException e) {
      
    }

    int AssNum = Integer.parseInt(assNumStr);

    Patient newPatient = new Patient(name, prenom, DateNaiss, AssNum, maladie);
    ServicePatient servicePatient = new ServicePatient();
    servicePatient.ajouter(newPatient);

    // Clear the error label
    con1.setText("");
    con2.setText("");
    con3.setText("");
}

private boolean isValidName(String name) {
    // Use a regular expression to check if the name contains only letters
    return name.matches("^[a-zA-Z]+$");
}

private boolean isValidAssNum(String assNum) {
    // Use a regular expression to check if the assNum contains only digits
    return assNum.matches("^[0-9]+$");
}
private boolean isValidDateFormat(String dateStr) {
    // Define a regular expression for the "yyyy-MM-dd" format
    String regex = "\\d{4}-\\d{2}-\\d{2}";

    return dateStr.matches(regex);
}

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void backaff2(ActionEvent event) throws IOException {
        
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("PatAffich.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
}
