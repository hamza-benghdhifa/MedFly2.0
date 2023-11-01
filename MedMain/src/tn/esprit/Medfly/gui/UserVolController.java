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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableRow;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.stage.Stage;
import tn.esprit.Medfly.entities.Facture;
import tn.esprit.Medfly.entities.UserVol;
import tn.esprit.Medfly.entities.Vols;
import tn.esprit.Medfly.services.ServiceFacture;
import tn.esprit.Medfly.services.ServiceVols;
import tn.esprit.Medfly.services.ServiveReservation;

/**
 * FXML Controller class
 *
 * @author 21653
 */
public class UserVolController implements Initializable {

    @FXML
    private TreeTableView<Vols> treeview;
    @FXML
    private TreeTableColumn<?, ?> colCompagnie;
    @FXML
    private TreeTableColumn<?, ?> colNombre;
    @FXML
    private TreeTableColumn<?, ?> colPrix;
    @FXML
    private TreeTableColumn<?, ?> coldepart;
    @FXML
    private TreeTableColumn<?, ?> colDestination;
    @FXML
    private TextField nomcompagnie;
    @FXML
    private TextField txtNombreBilletDispo;
    @FXML
    private TextField txtPrixbillet;
    @FXML
    private TextField txtDepart;
    @FXML
    private TextField txtDestinaton;
    @FXML
    private TextField txtNbreBilletReservee;
    @FXML
    private TextField Passport;
    @FXML
    private TextField PrenomU;
    @FXML
    private TextField NomU;
    @FXML
    private Label Prixtotal;
        private final TreeItem<Vols> toor = new TreeItem<>();
    @FXML
    private Label labelPassport;
    @FXML
    private Label labelNOmUser;
    @FXML
    private Label labelPrenomUser;
    @FXML
    private Label labelBilletaReserver;
    @FXML
    private Label labelREmplissage;
    @FXML
    private TextField txtRecherche;
    @FXML
    private Label factureTot;


    /**
     * Initializes the controller class.
     */
   @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Associer les colonnes du tableau aux propriétés de l'entité Vols
        colCompagnie.setCellValueFactory(new TreeItemPropertyValueFactory<>("nom_airways"));
        colNombre.setCellValueFactory(new TreeItemPropertyValueFactory<>("nb_billet"));
        colPrix.setCellValueFactory(new TreeItemPropertyValueFactory<>("prix_billet"));
        coldepart.setCellValueFactory(new TreeItemPropertyValueFactory<>("date_depart"));
        colDestination.setCellValueFactory(new TreeItemPropertyValueFactory<>("destination"));

        // Définir le nœud racine pour le TreeTableView
        treeview.setRoot(toor);

        // Charger les données des vols
        loadVol();

        // Gérer les clics sur les lignes du tableau
        treeview.setRowFactory(tv -> {
            TreeTableRow<Vols> row = new TreeTableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    Vols vol = row.getItem();
                    if (vol != null) {
                        // Remplir les champs de texte avec les données du vol
                        nomcompagnie.setText(vol.getNom_airways());
                        txtNombreBilletDispo.setText(String.valueOf(vol.getNb_billet()));
                        txtPrixbillet.setText(String.valueOf(vol.getPrix_billet()));
                        txtDepart.setText(vol.getDate_depart());
                        txtDestinaton.setText(vol.getDestination());
                    }
                }
            });
            return row;
        });

        // Mettre en place la recherche avancée en écoutant les modifications du champ de recherche
        txtRecherche.textProperty().addListener((observable, oldValue, newValue) -> {
            // Appeler la méthode RechercheAvance lorsque le texte change
            RechercheAvance(null);
        });
    }

    private void loadVol() {
        // Charger les données des vols depuis le service ServiceVols
        ServiceVols service = new ServiceVols();
        List<Vols> VolData = service.getAll();

        // Créer des éléments TreeItem pour chaque vol et les ajouter au nœud racine
        for (Vols vol : VolData) {
            TreeItem<Vols> volItem = new TreeItem<>(vol);
            toor.getChildren().add(volItem);
        }
    }

    @FXML
    private void ReserverVol(ActionEvent event) {
     // Récupérez les données de réservation à partir des champs de texte
    String compagnie = nomcompagnie.getText();
    int nbBilletReserve;
    float prixBillet;
    float facture;
    String nomUser = NomU.getText();
    String prenomUser = PrenomU.getText();
    int passport;

    try {
        nbBilletReserve = Integer.parseInt(txtNbreBilletReservee.getText());
        prixBillet = Float.parseFloat(txtPrixbillet.getText());

        // Calcul de la facture
        facture = prixBillet * nbBilletReserve;
    } catch (NumberFormatException e) {
        // Gérez l'erreur ici, par exemple, affichez un message d'erreur ou loggez l'exception
        System.err.println("Erreur de conversion : " + e.getMessage());
        return; // Quittez la méthode en cas d'erreur
    }

    try {
        passport = Integer.parseInt(Passport.getText());
    } catch (NumberFormatException e) {
        // Gérez l'erreur ici, par exemple, affichez un message d'erreur ou loggez l'exception
        System.err.println("Erreur lors de la conversion du passeport en entier : " + e.getMessage());
        return; // Quittez la méthode car le passeport n'est pas un entier valide
    }

    // Vérifiez si les champs obligatoires sont remplis
    if (compagnie.isEmpty() || nomUser.isEmpty() || prenomUser.isEmpty()) {
        labelREmplissage.setText("Veuillez remplir tous les champs.");
    } else {
        labelREmplissage.setText(""); // Effacez le message d'erreur

        // Créez un nouvel objet UserVol avec les données de réservation
        UserVol reservation = new UserVol(nomUser, prenomUser, passport, compagnie, nbBilletReserve, txtDestinaton.getText(), txtDepart.getText(), facture);

        // Ajoutez la réservation à la table "uservol"
        ServiveReservation serviceReservation = new ServiveReservation();
        serviceReservation.ajouter(reservation);

        // Réinitialisez les champs de texte après la réservation
        nomcompagnie.clear();
        txtNbreBilletReservee.clear();
        Passport.clear();
        NomU.clear();
        PrenomU.clear();
        labelREmplissage.setText("Réservation effectuée avec succès!");

        // Affichez le prix total dans la Label Prixtotal
        Prixtotal.setText("Prix total de la réservation : " + facture + " DT");
         try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("userhotel.fxml"));
        Parent root = loader.load();

        // Vous pouvez obtenir le contrôleur de la nouvelle fenêtre si nécessaire
        UserHotelController userHotelController = loader.getController();
        
        Facture factureVol = new Facture(passport, nomUser, prenomUser, txtDestinaton.getText(), "gammarth", compagnie, facture);
        ServiceFacture serviceFacture = new ServiceFacture();
        serviceFacture.ajouter(factureVol);

        Stage stage = new Stage();
        stage.setScene(new Scene(root));

        // Fermer la fenêtre UserVol.fxml
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.close();

        // Afficher la nouvelle fenêtre
        stage.show();
    } catch (IOException e) {
        e.printStackTrace();
    }
    }
    }
    




@FXML
private void RechercheAvance(ActionEvent event) {
 // Récupérez le texte saisi dans le champ txtRecherche
    String recherche = txtRecherche.getText().trim();

    // Assurez-vous que la recherche n'est pas vide
    if (!recherche.isEmpty()) {
        // Utilisez la nouvelle méthode de recherche par destination
        ServiceVols serviceVols = new ServiceVols();
        ArrayList<Vols> result = serviceVols.Advancedsearch(recherche, null, null);

        // Effacez le contenu actuel du TreeTableView
        toor.getChildren().clear();

        // Ajoutez les résultats de la recherche à la TreeTableView
        for (Vols vol : result) {
            TreeItem<Vols> volItem = new TreeItem<>(vol);
            toor.getChildren().add(volItem);
        }
    } else {
        // Si le champ de recherche est vide, rechargez tous les vols
        loadVol();
    }
}




    
}
