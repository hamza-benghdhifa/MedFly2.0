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
public class SignUpPatientController implements Initializable {

    @FXML
    private TextField emailfield;
    @FXML
    private TextField passwordfield;
    @FXML
    private Label labemaill;
    @FXML
    private Label labelpasswordp;
    @FXML
    private Label labname;
    @FXML
    private Label labpre;
    @FXML
    private Label labdate;
    @FXML
    private Label labnum;
    @FXML
    private Label labmaladie;
    @FXML
    private Label labff;
    @FXML
    private TextField maladief;
    @FXML
    private TextField numf;
    @FXML
    private TextField datef;
    @FXML
    private TextField prenomf;
    @FXML
    private TextField nomf;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void signpatup(ActionEvent event) {
        
                // Validate input fields
    String name = nomf.getText();
    String prenom = prenomf.getText();
    String date_naissance = datef.getText();
    String num_assurance = numf.getText();
    String maladie = maladief.getText();
    String email = emailfield.getText();
    String mot_passe  = passwordfield.getText();
    
    /*    private int id ;
    private String name;
    private String prenom;
    private Date date_naissance;
    private int num_assurance;
    private String maladie;
    private String email;;
    private String password;*/
    

           // Validate input fields

        if (name.isEmpty() || prenom.isEmpty() || date_naissance.isEmpty() || num_assurance.isEmpty() || maladie.isEmpty() || email.isEmpty()) {
            labff.setText("Please fill in all fields.");
            return;
        }

        if (!isValidName(prenom) || !isValidName(name)) {
            labname.setText("Name and prenom should contain only letters.");
            labpre.setText("Name and prenom should contain only letters.");
            return;
        }

        if (!isValidGradNum(num_assurance)) {
            labnum.setText("Assurance number should contain only digits.");
            return;
        }

        if (!isValidDateFormat(date_naissance)) {
            labdate.setText("Invalid date format. Use yyyy-MM-dd.");
            return;
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date gradDate = null;

        try {
            gradDate = dateFormat.parse(date_naissance);
        } catch (ParseException e) {
            // Handle the parsing error, e.g., show an error message or log it
        }

        int gradNum = Integer.parseInt(num_assurance);

        // Create and save the patient record
        Patient newPatient = new Patient(name, prenom, gradDate, gradNum, maladie, email, mot_passe);
        ServicePatient servicePatient = new ServicePatient();
        servicePatient.ajouter(newPatient);

        // Clear the error labels
        labname.setText("");
        labpre.setText("");
        labdate.setText("");
        labnum.setText("");
        labmaladie.setText("");
        labemaill.setText("");
        labelpasswordp.setText("");
        labff.setText("Welcome to MedFly, you are now a member. Please sign in.");
    }

    private boolean isValidGradNum(String gradNum) {
        return gradNum.matches("^[0-9]+$");
    }

    private boolean isValidDateFormat(String dateStr) {
        String regex = "\\d{4}-\\d{2}-\\d{2}";
        return dateStr.matches(regex);
    }

    private boolean isValidName(String name) {
        // Use a regular expression to check if the name contains only letters
        return name.matches("^[A-Za-z]+$");
    }

    @FXML
    private void gotomenu(ActionEvent event) throws IOException {
            
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("FirstPageMedFly.fxml")));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
    }

    @FXML
    private void tosingin(ActionEvent event) throws IOException {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("LoginPat.fxml")));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
    }
}
