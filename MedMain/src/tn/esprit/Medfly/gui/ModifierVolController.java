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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tn.esprit.Medfly.entities.Vols;
import tn.esprit.Medfly.services.ServiceVols;

/**
 * FXML Controller class
 *
 * @author 21653
 */
public class ModifierVolController implements Initializable {

    @FXML
    private TextField txtNom1;
    @FXML
    private TextField txtNombre1;
    @FXML
    private TextField txtPrix1;
    @FXML
    private TextField txtDestination1;
    @FXML
    private TextField txtDepart1;
    @FXML
    private TextField txtidV;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
     public void initData(Vols selectedVol) {
        // Remplir les champs de texte avec les informations de l'hôtel
        txtDepart1.setText(selectedVol.getDate_depart());
        txtNom1.setText(selectedVol.getNom_airways());
        txtPrix1.setText(String.valueOf(selectedVol.getPrix_billet()));
        txtNombre1.setText(String.valueOf(selectedVol.getNb_billet()));
        txtDestination1.setText(selectedVol.getDestination());
        txtidV.setText(String.valueOf(selectedVol.getId_vol()));

    }

    @FXML
    private void ModifierVol(ActionEvent event) {
         // Récupérer l'ID de l'hôtel à partir du champ txtid
    int volId = Integer.parseInt(txtidV.getText());

    // Récupérer les valeurs des champs de texte
    String nomVol = txtNom1.getText();
    String depart = txtDepart1.getText();
    String destination = txtDestination1.getText();
    int nombre = Integer.parseInt(txtNombre1.getText());
    float prix = Float.parseFloat(txtPrix1.getText());

    // Créer un objet Hotels avec ces valeurs
    Vols vol = new Vols();
    vol.setId_vol(volId); // Utiliser l'ID de l'hôtel existant
    vol.setNom_airways(nomVol);
    vol.setDate_depart(depart);
    vol.setDestination(destination);
    vol.setNb_billet(nombre);
    vol.setPrix_billet(prix);

    // Appeler la méthode de modification du service avec l'ID et les nouvelles données
        ServiceVols service = new ServiceVols();
    service.modifier(volId, vol);

    System.out.println("Modification réussie.");
    // Vous pouvez ajouter une logique pour afficher un message de confirmation
    // ou rediriger l'utilisateur vers une autre vue ici.
    }

    @FXML
    private void RetourModifierVol(ActionEvent event) throws IOException {
       
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AffichageVols.fxml")));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void RemplissageVol(ActionEvent event) {
         int volID = Integer.parseInt(txtidV.getText());

    ServiceVols service = new ServiceVols();
     Vols vol = service.getOne(volID);

    txtNom1.setText(vol.getNom_airways());
    txtNombre1.setText(String.valueOf(vol.getNb_billet()));
    txtPrix1.setText(String.valueOf(vol.getPrix_billet()));
    txtDestination1.setText(vol.getDestination());
    txtDepart1.setText(vol.getDate_depart());
    
    }
    
}

    