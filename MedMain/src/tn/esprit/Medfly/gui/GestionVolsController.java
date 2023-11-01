/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.Medfly.gui;

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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tn.esprit.Medfly.entities.Vols;
import tn.esprit.Medfly.services.ServiceVols;

/**
 * FXML Controller class
 *
 * @author 21653
 */
public class GestionVolsController implements Initializable {

    @FXML
    private TextField txtNom;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtPrix;
  
    private TextField txtArrivee;
    @FXML
    private TextField txtDepart;
    @FXML
    private TextField txtDestinaton;
     @FXML
    private Label controlNomCompagnie;

    @FXML
    private Label controlNbreBillet;

    @FXML
    private Label controlPrixBillet;

    @FXML
    private Label controlDate;

    @FXML
    private Label controlDestiation;

    @FXML
    private Label controlRemplissage;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void AjouterVol(ActionEvent event) {
         String nom = this.txtNom.getText();
        String nombre = this.txtNombre.getText();
        String prix = this.txtPrix.getText();
        String depart = this.txtDepart.getText();
        String destination = this.txtDestinaton.getText();

        if (nom.isEmpty() || nombre.isEmpty() || prix.isEmpty() || depart.isEmpty() || destination.isEmpty()) {
            controlRemplissage.setText("Veuillez remplir tous les champs.");
            return; // Quitter la méthode pour éviter d'ajouter un enregistrement vide
        }

        // Valider que "nom" contient uniquement des lettres
        if (!isValidLetters(nom)) {
            controlNomCompagnie.setText("Le nom de compagnie doit contenir uniquement des lettres.");
            return;
        }

        // Valider que "destination" contient uniquement des lettres
        if (!isValidLetters(destination)) {
            controlDestiation.setText("La destination doit contenir uniquement des lettres.");
            return;
        }

        // Valider que "depart" a le format "aaaa-mm-jj"
        if (!isValidDate(depart)) {
            controlDate.setText("Le format de la date doit être 'aaaa-mm-jj'.");
            return;
        }

        int nombre1 = Integer.parseInt(nombre);
        Float prix1 = null;
        try {
            prix1 = Float.valueOf(prix);
        } catch (NumberFormatException e) {
            System.err.println("Format de prix invalide");
        }

        ServiceVols sv = new ServiceVols();
        Vols v = new Vols(nom, nombre1, prix1, depart, destination);
        sv.ajouter(v);

        controlRemplissage.setText("Ajout réussi.");
        System.out.println("Vol ajouté avec succès.");
    }

    private boolean isValidLetters(String input) {
        return input.matches("^[a-zA-Z]+$");
    }

    private boolean isValidDate(String input) {
        return input.matches("^\\d{4}-\\d{2}-\\d{2}$");
    }


    @FXML
    private void RetourAjoutVol(ActionEvent event) throws IOException {
       
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AffichageVols.fxml")));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    }

  

