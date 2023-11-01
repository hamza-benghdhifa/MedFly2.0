/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.Medfly.gui;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.TextFieldTreeTableCell;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import tn.esprit.Medfly.entities.Hotels;
import tn.esprit.Medfly.services.ServiceHotels;

/**
 * FXML Controller class
 *
 * @author 21653
 */
public class GestionHotelsController implements Initializable {

    @FXML
    private TreeTableColumn<?, ?> clId;
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
    private TextField txtid;
    @FXML
    private TreeTableView<Hotels> hotelTree;
    
        private final TreeItem<Hotels> toor = new TreeItem<>();


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       

    // ...
    
    // Configurez d'autres colonnes de la même manière pour les rendre éditables
        // TODO
        clId.setCellValueFactory(new TreeItemPropertyValueFactory<>("id_hotel"));
        clNom.setCellValueFactory(new TreeItemPropertyValueFactory<>("nom_hotel"));
        clEtoile.setCellValueFactory(new TreeItemPropertyValueFactory<>("etoile"));
        clPays.setCellValueFactory(new TreeItemPropertyValueFactory<>("pays"));
        clChambre.setCellValueFactory(new TreeItemPropertyValueFactory<>("nbre_chambre"));
        clPrix.setCellValueFactory(new TreeItemPropertyValueFactory<>("prix_nuit"));
        

        // Set the root node for the TreeTableView
        hotelTree.setRoot(toor);

        // Load Medecin data
        loadHotel();
    }
      private void loadHotel() {
        // Fetch Medecin data from your ServiceMedecin
        ServiceHotels service = new ServiceHotels();
        List<Hotels> hotelData = service.getAll();

        // Create TreeItems for each Medecin and add them to the root
        for (Hotels hotel: hotelData) {
            TreeItem<Hotels> HotelItem = new TreeItem<>(hotel);
            toor.getChildren().add(HotelItem);
        }
        
    }    

    @FXML
    private void RechercheHotel(ActionEvent event) {
         String idText = txtid.getText();

    // Check if the input is a valid integer
    if (idText.matches("\\d+")) {
        int id = Integer.parseInt(idText);

        // Retrieve the patient with the specified ID using ServicePatient
        ServiceHotels sh = new ServiceHotels();
        Hotels hotel = sh.getOne(id);

        if (hotel != null) {
            // Clear the existing data
            toor.getChildren().clear();

            // Add the retrieved patient to the TreeTableView
            TreeItem<Hotels> hoteltItem = new TreeItem<>(hotel);
            toor.getChildren().add(hoteltItem);
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
    private void SupprimerHotel(ActionEvent event) {
        // Récupérez l'élément sélectionné dans le TreeTableView
    TreeItem<Hotels> selectedHotelItem = hotelTree.getSelectionModel().getSelectedItem();
    
    if (selectedHotelItem != null) {
        Hotels selectedHotel = selectedHotelItem.getValue();
        int id = selectedHotel.getId_hotel();

        // Affichez une boîte de dialogue de confirmation
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de suppression");
        alert.setHeaderText("Voulez-vous supprimer cet hôtel ?");
        alert.setContentText("Confirmez la suppression de l'hôtel sélectionné.");

        // Ajoutez des boutons "Oui" et "Non" à la boîte de dialogue
        ButtonType buttonTypeYes = new ButtonType("Oui");
        ButtonType buttonTypeNo = new ButtonType("Non");
        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

        // Attendez la réponse de l'utilisateur
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == buttonTypeYes) {
            // L'utilisateur a choisi de supprimer l'hôtel
            ServiceHotels sh = new ServiceHotels();
            sh.supprimer(id);
            
            // Supprimez également l'élément de l'arborescence visuelle
            toor.getChildren().remove(selectedHotelItem);
            
            
        }
    }
    }
 

    @FXML
    private void toModifier(ActionEvent event)  throws IOException {
       
         // Récupérer l'hôtel sélectionné dans le TreeTableView
    TreeItem<Hotels> selectedHotelItem = hotelTree.getSelectionModel().getSelectedItem();

    if (selectedHotelItem != null) {
        Hotels selectedHotel = selectedHotelItem.getValue();

        // Charger la fenêtre ModifierHotel
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierHotel.fxml"));
        Parent root = loader.load();

        // Obtenir le contrôleur ModifierHotelController de la fenêtre
        ModifierHotelController modifierHotelController = loader.getController();

        // Passer les informations de l'hôtel sélectionné au contrôleur ModifierHotelController
        modifierHotelController.initData(selectedHotel);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    }

    @FXML
    private void toAjouter(ActionEvent event) throws IOException {
       
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AjoutHotel.fxml")));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void rafraîchir(ActionEvent event) {
         // Clear the existing data
    toor.getChildren().clear();

    // Load Medecin data again
    loadHotel();
    }

    @FXML
    private void Homehotel(ActionEvent event) throws IOException {
       
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("home.fxml")));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
     @FXML
    void imprimerHotel(ActionEvent event) {
         PrinterJob printerJob = PrinterJob.createPrinterJob();

            if (printerJob != null && printerJob.showPrintDialog(hotelTree.getScene().getWindow())) {
                boolean success = printerJob.printPage(hotelTree);
                if (success) {
                    printerJob.endJob();
                    System.out.println("Printing completed successfully.");
                } else {
                    System.out.println("Printing failed.");
                }
            }

    }

}
