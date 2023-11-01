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
import tn.esprit.Medfly.entities.Hotels;
import tn.esprit.Medfly.services.ServiceHotels;

/**
 * FXML Controller class
 *
 * @author 21653
 */
public class ModifierHotelController implements Initializable {

    @FXML
    private TextField txtNom1;
    @FXML
    private TextField txtPrix1;
    @FXML
    private TextField txtChambre1;
    @FXML
    private TextField txtPays1;
    @FXML
    private TextField txtEtoile1;
    @FXML
    private TextField txtid1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void initData(Hotels selectedHotel) {
        // Remplir les champs de texte avec les informations de l'hôtel
        txtid1.setText(String.valueOf(selectedHotel.getId_hotel()));
        txtNom1.setText(selectedHotel.getNom_hotel());
        txtPrix1.setText(String.valueOf(selectedHotel.getPrix_nuit()));
        txtChambre1.setText(String.valueOf(selectedHotel.getNbre_chambre()));
        txtPays1.setText(selectedHotel.getPays());
        txtEtoile1.setText(selectedHotel.getEtoile());
    }
    @FXML
    private void ModifierHotel(ActionEvent event) {
         // Récupérer l'ID de l'hôtel à partir du champ txtid
    int hotelId = Integer.parseInt(txtid1.getText());

    // Récupérer les valeurs des champs de texte
    String nomHotel = txtNom1.getText();
    String etoile = txtEtoile1.getText();
    String pays = txtPays1.getText();
    int nbChambre = Integer.parseInt(txtChambre1.getText());
    float prixNuit = Float.parseFloat(txtPrix1.getText());

    // Créer un objet Hotels avec ces valeurs
    Hotels hotel = new Hotels();
    hotel.setId_hotel(hotelId); // Utiliser l'ID de l'hôtel existant
    hotel.setNom_hotel(nomHotel);
    hotel.setEtoile(etoile);
    hotel.setPays(pays);
    hotel.setNbre_chambre(nbChambre);
    hotel.setPrix_nuit(prixNuit);

    // Appeler la méthode de modification du service avec l'ID et les nouvelles données
    ServiceHotels service = new ServiceHotels();
    service.modifier(hotelId, hotel);

    System.out.println("Modification réussie.");
    // Vous pouvez ajouter une logique pour afficher un message de confirmation
    // ou rediriger l'utilisateur vers une autre vue ici.
    }

    @FXML
    private void Remplissage(ActionEvent event) {
    int hotelId = Integer.parseInt(txtid1.getText());

    ServiceHotels service = new ServiceHotels();
    Hotels hotel = service.getOne(hotelId);

    txtNom1.setText(hotel.getNom_hotel());
    txtPrix1.setText(String.valueOf(hotel.getPrix_nuit()));
    txtChambre1.setText(String.valueOf(hotel.getNbre_chambre()));
    txtPays1.setText(hotel.getPays());
    txtEtoile1.setText(hotel.getEtoile());
    }

    @FXML
    private void RetourModifier(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("GestionHotels.fxml")));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
}
