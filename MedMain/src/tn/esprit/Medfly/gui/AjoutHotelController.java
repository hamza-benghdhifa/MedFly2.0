/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.Medfly.gui;

import java.io.IOException;
import java.net.URL;
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
import tn.esprit.Medfly.entities.Hotels;
import tn.esprit.Medfly.services.ServiceHotels;

/**
 * FXML Controller class
 *
 * @author 21653
 */
public class AjoutHotelController implements Initializable {

    @FXML
    private TextField txtNom;
    @FXML
    private TextField txtPrix;
    @FXML
    private TextField txtChambre;
    @FXML
    private TextField txtPays;
    @FXML
    private TextField txtEtoile;
    @FXML
    private Label controlNom;
    @FXML
    private Label controlPays;
    @FXML
    private Label controlEtoile;
    @FXML
    private Label controlChmabre;
    @FXML
    private Label ControlPrix;
    @FXML
    private Label controlRemplissage;
    @FXML
    private Label ControlAjout;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

   @FXML
private void AjouterHotel(ActionEvent event) {
    String nom = this.txtNom.getText();
    String prix = this.txtPrix.getText();
    String chambre = this.txtChambre.getText();
    String pays = this.txtPays.getText();
    String etoile = this.txtEtoile.getText();

    if (nom.isEmpty() || prix.isEmpty() || chambre.isEmpty() || pays.isEmpty() || etoile.isEmpty()) {
        controlRemplissage.setText("Veuillez remplir tous les champs.");
        return; // Quitter la méthode pour éviter d'ajouter un enregistrement vide
    }

    // Valider que "chambre" est un entier
    if (!isValidInteger(chambre)) {
        controlChmabre.setText("Le numéro de chambre doit contenir uniquement des chiffres.");
        return;
    }

    // Valider que "etoile" est une chaîne de chiffres de 1 à 7 caractères max
    if (!isValidStars(etoile)) {
        controlEtoile.setText("Le nombre d'étoiles doit contenir entre 1 et 7 chiffres.");
        return;
    }

    // Valider que "prix" est un nombre à virgule flottante
    if (!isValidFloat(prix)) {
        ControlPrix.setText("Le prix doit être un nombre valide.");
        return;
    }

    // Valider que "nom" et "pays" ne contiennent que des lettres
    if (!isValidLetters(nom) ) {
        controlNom.setText("Le nom doit contenir uniquement des lettres.");
        return;
    }
    if ( !isValidLetters(pays)){
                controlPays.setText("Le pays doit contenir uniquement des lettres.");
                        return;

    }

    // Si toutes les validations sont réussies, ajoutez l'hôtel
    int chambre1 = Integer.parseInt(chambre);
    float prix1 = Float.parseFloat(prix);

    ServiceHotels sh = new ServiceHotels();
    Hotels h = new Hotels(nom, etoile, pays, chambre1, prix1);
    sh.ajouter(h);
    ControlAjout.setText("Hôtel ajouté avec succès.");

    // Effacez les champs de saisie et les messages d'erreur
    txtNom.setText("");
    txtPrix.setText("");
    txtChambre.setText("");
    txtPays.setText("");
    txtEtoile.setText("");
    controlRemplissage.setText("");
    controlChmabre.setText("");
    controlEtoile.setText("");
    ControlPrix.setText("");
    controlNom.setText("");
    controlPays.setText("");
}

private boolean isValidInteger(String input) {
    return input.matches("^\\d+$");
}

private boolean isValidStars(String input) {
    return input.matches("^\\d{1,7}$");
}

private boolean isValidFloat(String input) {
    try {
        Float.parseFloat(input);
        return true;
    } catch (NumberFormatException e) {
        return false;
    }
}

private boolean isValidLetters(String input) {
    return input.matches("^[a-zA-Z]+$");
}

          
   @FXML
    private void RetourAjout(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("GestionHotels.fxml")));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();}
    

   
    

     private boolean isValidName(String name) {
    // Use a regular expression to check if the name contains only letters
    return name.matches("^[a-zA-Z]+$");
    }
     private boolean isValidGradNum(String gradNum)
    {
    // Use a regular expression to check if the gradNum contains only digits
    return gradNum.matches("^[0-9]+$");
    }
}

    