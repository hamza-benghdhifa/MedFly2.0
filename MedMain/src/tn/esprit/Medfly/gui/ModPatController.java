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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author hamza
 */
public class ModPatController implements Initializable {

    @FXML
    private TextField tfpmaladie;
    @FXML
    private TextField tfpid;
    @FXML
    private TextField tfpprenom;
    @FXML
    private TextField tfpdaten;
    @FXML
    private TextField tfpnumassu;
    @FXML
    private TextField tfpname;
    
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
    private void apllyit(ActionEvent event) {
                
    
        // Récupérer l'ID du patient à partir du champ tfpid
        int patientId = Integer.parseInt(tfpid.getText());

        // Récupérer les valeurs des champs de texte
        String name = tfpname.getText();
        String prenom = tfpprenom.getText();
        String dateNaissanceStr = tfpdaten.getText();
        String numAssuranceStr = tfpnumassu.getText();
        String maladie = tfpmaladie.getText();

        // Parse date and number fields
        Date dateNaissance = null;
        int numAssurance = 0;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            dateNaissance = dateFormat.parse(dateNaissanceStr);
            numAssurance = Integer.parseInt(numAssuranceStr);
        } catch (ParseException e) {
            // Handle parsing errors
            e.printStackTrace();
            // You should display an error message to the user in a real application
            return;
        }

        // Create a Patient object with the updated data
        Patient patient = new Patient(patientId, name, prenom, dateNaissance, numAssurance, maladie);

        // Call the service method to update the patient
        ServicePatient servicePatient = new ServicePatient();
        servicePatient.modifier(patientId, patient);

        System.out.println("Modification réussie.");
        // You can add logic to display a confirmation message
    }

    @FXML
    private void searchbyId(ActionEvent event) {
   int patientId = Integer.parseInt(tfpid.getText());

    ServicePatient service = new ServicePatient();
    Patient patient = service.getOne(patientId);

    tfpname.setText(patient.getName());
    tfpprenom.setText(patient.getPrenom());

    // Format the date and set it to the date TextField
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    tfpdaten.setText(dateFormat.format(patient.getDate_naissance()));

    tfpnumassu.setText(String.valueOf(patient.getNum_assurance()));
    tfpmaladie.setText(patient.getMaladie());
}

    @FXML
    private void backaff1(ActionEvent event) throws IOException {
        
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("PatAffich.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    }
    

