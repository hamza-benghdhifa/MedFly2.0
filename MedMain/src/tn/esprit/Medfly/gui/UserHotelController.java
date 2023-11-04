/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.Medfly.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableRow;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import tn.esprit.Medfly.entities.Facture;
import tn.esprit.Medfly.entities.Hotels;
import tn.esprit.Medfly.entities.UserHotel;
import tn.esprit.Medfly.services.ReservationHotel;
import tn.esprit.Medfly.services.ServiceFacture;
import tn.esprit.Medfly.services.ServiceHotels;

/**
 * FXML Controller class
 *
 * @author 21653
 */
public class UserHotelController implements Initializable {

    @FXML
    private TextField txtNomHotel;
    @FXML
    private TextField txtEtoile;
    @FXML
    private TextField txtPrixNuit;
    @FXML
    private TextField txtChambreDispo;
    @FXML
    private TextField txtPays;
    @FXML
    private TreeTableView<Hotels> hotelTree;
    @FXML
    private TreeTableColumn<?, ?> clNom;
    @FXML
    private TreeTableColumn<?, ?> clEtoile;
    @FXML
    private TreeTableColumn<?, ?> clPays;
    @FXML
    private TreeTableColumn<?, ?> clChambre;
    @FXML
    private TreeTableColumn<?, ?> clPrix;
    @FXML
    private TextField userNom;
    @FXML
    private TextField UserPrenom;
    @FXML
    private TextField Passport;
    @FXML
    private TextField txtChambreReserver;
    @FXML
    private TextField txtNbreNuit;
 private final TreeItem<Hotels> toor = new TreeItem<>();
    @FXML
    private Label ControlSaisie;
    @FXML
    private TextField txtSearch;
    
        private ServiceHotels hotelService = new ServiceHotels();
    @FXML
    private Label Facturetot;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       clNom.setCellValueFactory(new TreeItemPropertyValueFactory<>("nom_hotel"));
    clEtoile.setCellValueFactory(new TreeItemPropertyValueFactory<>("etoile"));
    clPays.setCellValueFactory(new TreeItemPropertyValueFactory<>("pays"));
    clChambre.setCellValueFactory(new TreeItemPropertyValueFactory<>("nbre_chambre"));
    clPrix.setCellValueFactory(new TreeItemPropertyValueFactory<>("prix_nuit"));

    // Set the root node for the TreeTableView
    hotelTree.setRoot(toor);

    // Load hotel data
    loadHotel();
    hotelTree.setRowFactory(tv -> {
    TreeTableRow<Hotels> row = new TreeTableRow<>();
    row.setOnMouseClicked(event -> {
        if (event.getClickCount() == 2 && !row.isEmpty()) {
            Hotels hotel = row.getItem();
            if (hotel != null) {
                // Remplissez les champs de texte avec les données de l'hôtel
                txtNomHotel.setText(hotel.getNom_hotel());
                txtEtoile.setText(hotel.getEtoile());
                txtPrixNuit.setText(String.valueOf(hotel.getPrix_nuit()));
                txtChambreDispo.setText(String.valueOf(hotel.getNbre_chambre()));
                txtPays.setText(hotel.getPays());
            }
        }
    });
    return row;
});
 // Mettre en place la recherche avancée en écoutant les modifications du champ de recherche
txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
    // Appeler la méthode RechercheAvancee lorsque le texte change
    RechercheAvancee(null);
});
        ServiceFacture sf = new ServiceFacture(); // Initialisez le service Facture

    }   
private void loadHotel() {
    // Fetch hotel data from your ServiceHotels
    ServiceHotels hotelService = new ServiceHotels();
    List<Hotels> hotelData = hotelService.getAll();

    // Create TreeItems for each hotel and add them to the root
    for (Hotels hotel : hotelData) {
        TreeItem<Hotels> hotelItem = new TreeItem<>(hotel);
        toor.getChildren().add(hotelItem);
    }
}
    

   @FXML
private void ReserverHotel(ActionEvent event) {
  // Effectuez la vérification des contrôles de saisie ici
        if (txtNomHotel.getText().isEmpty() || txtEtoile.getText().isEmpty() || txtPrixNuit.getText().isEmpty()
                || txtChambreDispo.getText().isEmpty() || txtPays.getText().isEmpty()) {
            // Affichez un message d'erreur dans la Label ControlSaisie
            ControlSaisie.setText("Veuillez remplir tous les champs.");
        } else {
            // Les contrôles de saisie sont valides, vous pouvez poursuivre avec l'ajout dans la base de données
            ControlSaisie.setText(""); // Effacez tout message d'erreur précédent

            // Récupérer les données du formulaire
            String nomUtilisateur = userNom.getText();
            String prenomUtilisateur = UserPrenom.getText();
            int numPassport = Integer.parseInt(Passport.getText());
            String nomHotel = txtNomHotel.getText();
            String pays = txtPays.getText();
            int nbreChambreReservee = Integer.parseInt(txtChambreReserver.getText());
            float prixNuit = Float.parseFloat(txtPrixNuit.getText());
            int nbreNuit = Integer.parseInt(txtNbreNuit.getText());

            // Calculer la facture
            float factureHotel = prixNuit * nbreChambreReservee * nbreNuit;

            // Créer une instance de UserHotel avec les données du formulaire
            UserHotel userHotel = new UserHotel(nomUtilisateur, prenomUtilisateur, numPassport, nomHotel, pays, nbreChambreReservee, factureHotel);

            // Ajouter les données de réservation à la base de données
            ReservationHotel rh = new ReservationHotel();
            rh.ajouter(userHotel);

            // Vous pouvez également afficher un message de confirmation ici si nécessaire

            // Vérifiez si une ligne de facture existe déjà pour cet utilisateur
            ServiceFacture serviceFacture = new ServiceFacture();
            Facture existingFacture = serviceFacture.getOne(numPassport);

            if (existingFacture != null) {
                // Mettez à jour la ligne de facture existante avec les nouveaux montants
                float updatedMontant = existingFacture.getMontant() + factureHotel;

                // Mettez à jour le champ nom_hotel
                String existingNomHotel = existingFacture.getNom_hotel();
                if (existingNomHotel != null) {
                    // Si le champ nom_hotel n'est pas vide, ajoutez le nouveau nom d'hôtel
                    existingNomHotel += ", " + nomHotel;
                } else {
                    // Si le champ nom_hotel est vide, ajoutez simplement le nom d'hôtel
                    existingNomHotel = nomHotel;
                }

                existingFacture.setMontant(updatedMontant);
                existingFacture.setNom_hotel(existingNomHotel);
                serviceFacture.modifier(numPassport, existingFacture);
            } else {
                // Aucune ligne de facture n'existe, créez-en une nouvelle
                Facture factureHotelObj = new Facture(numPassport, nomUtilisateur, prenomUtilisateur, pays, nomHotel, null , factureHotel);
                serviceFacture.ajouter(factureHotelObj);
            }
            
    
        try {
            
            Facture f = serviceFacture.getOne(numPassport);
            if (f != null) {
                Facturetot.setText("votre facture totaale est :  " + String.valueOf(f.getMontant()));
            } else {
                Facturetot.setText("Facture introuvable pour le passeport " + numPassport);
            }
        } catch (NumberFormatException e) {
            Facturetot.setText("Le numéro de passeport doit être un entier.");
        }
    
            

           /* // Fermer la fenêtre UserHotel.fxml
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();

            try {
                // Charger la nouvelle fenêtre Paiment.fxml
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Paiment.fxml"));
                Parent root = loader.load();

                Stage newStage = new Stage();
                newStage.setScene(new Scene(root));

                // Afficher la nouvelle fenêtre
                newStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }*/
        }
    
   
    
    }





@FXML
private void RechercheAvancee(ActionEvent event) {
    // Récupérez le texte saisi dans le champ txtRecherche
    String recherche = txtSearch.getText().trim();

    // Assurez-vous que la recherche n'est pas vide
    if (!recherche.isEmpty()) {
        // Utilisez la nouvelle méthode de recherche par destination
        ServiceHotels serviceVols = new ServiceHotels();
        ArrayList<Hotels> result = serviceVols.Advancedsearch(recherche, null, null);

        // Effacez le contenu actuel du TreeTableView
        toor.getChildren().clear();

        // Ajoutez les résultats de la recherche à la TreeTableView
        for (Hotels vol : result) {
            TreeItem<Hotels> volItem = new TreeItem<>(vol);
            toor.getChildren().add(volItem);
        }
    } else {
        // Si le champ de recherche est vide, rechargez tous les vols
        loadHotel();
    
}
}
   @FXML
    private void onSearchTextChanged(KeyEvent event) {
        
        
}

}

    

