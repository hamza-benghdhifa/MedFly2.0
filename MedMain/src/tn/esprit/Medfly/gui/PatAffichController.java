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
import java.util.Date;
import java.util.List;
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
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author hamza
 */
public class PatAffichController implements Initializable {

  
     @FXML
    private TreeTableColumn<Patient,String> CLMaladie;

    @FXML
    private TreeTableColumn<Patient,String> CLPrenomPat;

    @FXML
    private TreeTableColumn<Patient,Integer> CLidPat;

    @FXML
    private TreeTableColumn<Patient,String> CLnomPat;

    @FXML
    private TreeTableColumn<Patient,Date> DatePat;

    @FXML
    private TreeTableColumn<Patient, Integer> NumAss;
    
      @FXML
    private TextField tfsearchPat;

    @FXML
    private TreeTableView<Patient> TablePat;
    
     private Stage stage;
        
     private Scene scene;
        
     private Parent root;
    
    private final TreeItem<Patient> toor = new TreeItem<>();

    @FXML
    void Deletepat(ActionEvent event) {
        int id = Integer.parseInt(tfsearchPat.getText());
        ServicePatient sp = new  ServicePatient();
        sp.supprimer(id);
        tfsearchPat.clear();

    }

    @FXML
    void ToAjouterpat(ActionEvent event)throws IOException {
        
        
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AjoutPat.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    
}

    @FXML
    void ToModifierpat(ActionEvent event) throws IOException {
        
        
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("ModPat.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    
}
    @FXML
    void searchPat(ActionEvent event) {
           String idText = tfsearchPat.getText();

    // Check if the input is a valid integer
    if (idText.matches("\\d+")) {
        int id = Integer.parseInt(idText);

        // Retrieve the patient with the specified ID using ServicePatient
        ServicePatient servicePatient = new ServicePatient();
        Patient patient = servicePatient.getOne(id);

        if (patient != null) {
            // Clear the existing data
            toor.getChildren().clear();

            // Add the retrieved patient to the TreeTableView
            TreeItem<Patient> patientItem = new TreeItem<>(patient);
            toor.getChildren().add(patientItem);
        } else {
            // Display an error message if the patient is not found
            // You can use a label for this purpose
            // For example: errorLabel.setText("Patient not found.");
        }
    } else {
        // Display an error message if the input is not a valid integer
        // You can use a label for this purpose
        // For example: errorLabel.setText("Invalid ID format.");
    }
}
    
    @FXML
    void refreshTable(ActionEvent event) {
    // Clear the existing data
    toor.getChildren().clear();

    // Load Medecin data again
    loadMed();
}

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
          
        // Associate TableColumn with Medecin properties
        
        CLnomPat.setCellValueFactory(new TreeItemPropertyValueFactory<>("name"));
        CLPrenomPat.setCellValueFactory(new TreeItemPropertyValueFactory<>("prenom"));
        DatePat.setCellValueFactory(new TreeItemPropertyValueFactory<>("date_naissance"));
        NumAss.setCellValueFactory(new TreeItemPropertyValueFactory<>("num_assurance"));
        CLMaladie.setCellValueFactory(new TreeItemPropertyValueFactory<>("maladie"));
        

        // Set the root node for the TreeTableView
        TablePat.setRoot(toor);

        // Load Medecin data
        loadMed();
    }

    private void loadMed() {
        // Fetch Medecin data from your ServiceMedecin  
        ServicePatient service = new ServicePatient();
        List<Patient> patientData = service.getAll();

        // Create TreeItems for each Medecin and add them to the root
        for (Patient patient : patientData) {
            TreeItem<Patient> medecinItem = new TreeItem<>(patient);
            toor.getChildren().add(medecinItem);
        }
    }

    
    }    
    

