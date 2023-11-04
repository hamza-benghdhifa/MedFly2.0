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
import javafx.event.ActionEvent;
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
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.stage.Stage;
import tn.esprit.Medfly.entities.Vols;
import tn.esprit.Medfly.services.ServiceVols;

/**
 * FXML Controller class
 *
 * @author 21653
 */
public class AffichageVolsController implements Initializable {

    @FXML
    private TreeTableView<Vols> treeview;
    @FXML
    private TreeTableColumn<Vols,Integer> colId;
    @FXML
    private TreeTableColumn<Vols, String> colCompagnie;
    @FXML
    private TreeTableColumn<Vols, Integer> colNombre;
    @FXML
    private TreeTableColumn<Vols,Float> colPrix;
    @FXML
    private TreeTableColumn<Vols, String> coldepart;
    @FXML
    private TreeTableColumn<Vols,String> colDestination;
    @FXML
    private TextField txtid1;
    
    private final TreeItem<Vols> toor = new TreeItem<>();
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         // Associate TableColumn with Medecin properties
        colId.setCellValueFactory(new TreeItemPropertyValueFactory<>("id_vol"));
        colCompagnie.setCellValueFactory(new TreeItemPropertyValueFactory<>("nom_airways"));
        colNombre.setCellValueFactory(new TreeItemPropertyValueFactory<>("nb_billet"));
        colPrix.setCellValueFactory(new TreeItemPropertyValueFactory<>("prix_billet"));
        coldepart.setCellValueFactory(new TreeItemPropertyValueFactory<>("date_depart"));
        colDestination.setCellValueFactory(new TreeItemPropertyValueFactory<>("destination"));
        

        // Set the root node for the TreeTableView
        treeview.setRoot(toor);

        // Load Medecin data
        loadVol();
    }
      private void loadVol() {
        // Fetch Medecin data from your ServiceMedecin
        ServiceVols service = new ServiceVols();
        List<Vols> VolData = service.getAll();

        // Create TreeItems for each Medecin and add them to the root
        for (Vols vol: VolData) {
            TreeItem<Vols> medecinItem = new TreeItem<>(vol);
            toor.getChildren().add(medecinItem);
        }
    }
       

    @FXML
    private void AjoutVol(ActionEvent event)throws IOException {
       
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("GestionVols.fxml")));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void ModifierVol(ActionEvent event) throws IOException {
       
         // Récupérer l'hôtel sélectionné dans le TreeTableView
    TreeItem<Vols> selectedVolItem = treeview.getSelectionModel().getSelectedItem();

    if (selectedVolItem != null) {
        Vols selectedVols = selectedVolItem.getValue();

        // Charger la fenêtre ModifierHotel
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierVol.fxml"));
        Parent root = loader.load();

        // Obtenir le contrôleur ModifierHotelController de la fenêtre
        ModifierVolController modifierVolController = loader.getController();

        // Passer les informations de l'hôtel sélectionné au contrôleur ModifierHotelController
        modifierVolController.initData(selectedVols);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    }

    @FXML
     void SupprimerVol(ActionEvent event) {
        
              // Récupérez l'élément sélectionné dans le TreeTableView
    TreeItem<Vols> selectedHotelItem = treeview.getSelectionModel().getSelectedItem();
    
    if (selectedHotelItem != null) {
        Vols selectedHotel = selectedHotelItem.getValue();
        int id = selectedHotel.getId_vol();

        // Affichez une boîte de dialogue de confirmation
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de suppression");
        alert.setHeaderText("Voulez-vous supprimer cet vol ?");
        alert.setContentText("Confirmez la suppression du vol sélectionné.");

        // Ajoutez des boutons "Oui" et "Non" à la boîte de dialogue
        ButtonType buttonTypeYes = new ButtonType("Oui");
        ButtonType buttonTypeNo = new ButtonType("Non");
        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

        // Attendez la réponse de l'utilisateur
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == buttonTypeYes) {
            // L'utilisateur a choisi de supprimer l'hôtel
            ServiceVols sh = new ServiceVols();
            sh.supprimer(id);
            
            // Supprimez également l'élément de l'arborescence visuelle
            toor.getChildren().remove(selectedHotelItem);
            
            
        }
    }
        
    }

    @FXML
    private void rechercheVol(ActionEvent event) {
         String idText = txtid1.getText();

    // Check if the input is a valid integer
    if (idText.matches("\\d+")) {
        int id = Integer.parseInt(idText);

        // Retrieve the patient with the specified ID using ServicePatient
         ServiceVols sh = new ServiceVols();
        Vols hotel = sh.getOne(id);

        if (hotel != null) {
            // Clear the existing data
            toor.getChildren().clear();

            // Add the retrieved patient to the TreeTableView
            TreeItem<Vols> hoteltItem = new TreeItem<>(hotel);
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
    private void rafraîchirbtn(ActionEvent event) {
          // Clear the existing data
    toor.getChildren().clear();

    // Load Medecin data again
    loadVol();
    }

    @FXML
    private void Homevol(ActionEvent event) throws IOException {
       
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("home.fxml")));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
     @FXML
    void imprimervols(ActionEvent event) {
        
           PrinterJob printerJob = PrinterJob.createPrinterJob();

            if (printerJob != null && printerJob.showPrintDialog(treeview.getScene().getWindow())) {
                boolean success = printerJob.printPage(treeview);
                if (success) {
                    printerJob.endJob();
                    System.out.println("Printing completed successfully.");
                } else {
                    System.out.println("Printing failed.");
                }
            }

    }
    
}
  
