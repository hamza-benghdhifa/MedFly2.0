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
import static javafx.application.ConditionalFeature.FXML;
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
public class AjoutMedController implements Initializable {

    @FXML
    private TextField tfgraddate;

    @FXML
  
    private TextField tfgradnum;

    @FXML
    private TextField tfname;

    @FXML
    private TextField tfpays;

    @FXML
    private TextField tfprenom;

    @FXML
    private TextField tfspecialite;
    
        @FXML
    private Label conn1;

    @FXML
    private Label conn2;

    @FXML
    private Label conn3;

    @FXML
    private Label conn4;

    @FXML
    private Label conn5;

    @FXML
    private Label conn6;

    @FXML
    private Label connf;

   @FXML
private void AjoutMedecin(ActionEvent event) {
    // Validate input fields
    String name = tfname.getText();
    String prenom = tfprenom.getText();
    String specialite = tfspecialite.getText();
    String pays = tfpays.getText();
    String gradDateStr = tfgraddate.getText();
    String gradNumStr = tfgradnum.getText();

    if (name.isEmpty() || prenom.isEmpty() || specialite.isEmpty() || pays.isEmpty() || gradDateStr.isEmpty() || gradNumStr.isEmpty()) {
        connf.setText("Please fill in all fields.");
        return; // Exit the method to prevent adding an empty record
    }

    // Validate the "prenom" and "name" fields for letters only
    if (!isValidName(prenom) || !isValidName(name)) {
        conn1.setText("Name and prenom should contain only letters.");
        conn2.setText("Name and prenom should contain only letters.");
        
        return;
    }

    // Validate the "gradNum" field for digits only
    if (!isValidGradNum(gradNumStr)) {
        conn6.setText("Graduation number should contain only digits.");
        return;
    }

    // Validate the date format
    if (!isValidDateFormat(gradDateStr)) {
        conn5.setText("Invalid date format. Use yyyy-MM-dd.");
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

    Medecin newMedecin = new Medecin(name, prenom, specialite, pays, gradDate, gradNum);
    ServiceMedecin serviceMedecin = new ServiceMedecin();
    serviceMedecin.ajouter(newMedecin);

    // Clear the error labels
    conn1.setText("");
    conn2.setText("");
    conn6.setText("");
    conn5.setText("");
    connf.setText("");
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
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TOD
    }    

    @FXML
    private void backback(ActionEvent event) throws IOException {
          Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("MedAffich.fxml")));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
    }
    
    
}
