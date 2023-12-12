/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package tn.esprit.Medfly.gui;
/*import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;*/
import javafx.scene.paint.Color;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Pair;
import tn.esprit.Medfly.entities.Reservation;
import tn.esprit.Medfly.services.ReservationService;
 import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
public class ConsulterMyReservationController implements Initializable {

    @FXML
    private TableColumn<  Reservation, String> date;

 

    @FXML
    private TableColumn<  Reservation, String> nomMedecin;
    @FXML
    private TableColumn<  Reservation, String> Pays;
    @FXML
    private TableColumn<  Reservation, Boolean> payement;
 
    

    @FXML
    private TableView<Reservation> table;
    @FXML
    private TextField r1 ;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
show();
    }    
     @FXML
    public void handleSearch(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            String searchText = r1.getText().trim();
            if (searchText.isEmpty()) {
                table.setItems(listeB);
            } else {
                ObservableList<Reservation> filteredList = FXCollections.observableArrayList();
                for (Reservation b : listeB) {
                    if (b.getNameDoctor().toLowerCase().contains(searchText.toLowerCase())) {
                        filteredList.add(b);
                    }
                }
                  if (filteredList.size()==0) {
        // Afficher un message d'erreur
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Impossible de trouver le Medecin ");
        alert.setContentText("Verifierr SVP !");
        alert.showAndWait();
                  }
                
                table.setItems(filteredList);
            }
        }
    }
    
          ObservableList<  Reservation> listeB = FXCollections.observableArrayList();

    public void show(){
        ReservationService bs=new ReservationService();
         listeB=bs.getAllReservations();
        date.setCellValueFactory(new PropertyValueFactory<>("DateRdv"));
         nomMedecin.setCellValueFactory(new PropertyValueFactory<>("NameDoctor"));
        Pays.setCellValueFactory(new PropertyValueFactory<>("Pays"));
payement.setCellValueFactory(new PropertyValueFactory<>("Payement"));
payement.setCellFactory(column -> {
    return new TableCell<Reservation, Boolean>() {
        @Override
        protected void updateItem(Boolean item, boolean empty) {
            super.updateItem(item, empty);
            if (empty) {
                setText(null);
                setGraphic(null);
            } else {
                if (item != null && item) {
                    setText("Payé");
                    setTextFill(Color.GREEN);
                } else {
                    setText("Non Payé");
                    setTextFill(Color.RED);
                }
            }
        }
    };
});
 
TableColumn<Reservation, Void> iconColumn = new TableColumn<>("Payé");
        iconColumn.setSortable(false);

        iconColumn.setCellFactory(column -> {
            return new TableCell<Reservation, Void>() {
                final Button button = new Button("Payé");

                {
                    // Appliquez une couleur verte au texte du bouton
                    button.setTextFill(Color.GREEN);

            // Définissez un gestionnaire d'événements pour le clic du bouton
            button.setOnAction(event -> {
       Reservation selectedReservation = getTableView().getItems().get(getIndex());
    if (selectedReservation.getPayement()==true) {
        // Affichez une alerte indiquant que le paiement est déjà effectué
        showAlert("Paiement déjà effectué", "Le paiement pour cette réservation a déjà été effectué.");
    } else {
        try {
            int selectedReservationID = selectedReservation.getId();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/Payement.fxml"));
            Parent page2 = loader.load();
            PayementController controller = loader.getController();
            controller.setId(selectedReservationID);
            Scene scene = new Scene(page2);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(PayementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
});
            }

        @Override
        protected void updateItem(Void item, boolean empty) {
            super.updateItem(item, empty);
            if (empty) {
                setGraphic(null);
            } else {
                setGraphic(button);
            }
        }
    };
});

table.getColumns().add(iconColumn);

  
        table.setItems(listeB);
    }
    @FXML  
    void Supprimer(ActionEvent event) {
          Reservation selectedLN =  table.getSelectionModel().getSelectedItem();
         if (selectedLN == null) {
        // Afficher un message d'erreur
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Impossible de supprimer une Reservation ");
        alert.setContentText("Veuillez selectionner une Reservation Ã  supprimer !");
        alert.showAndWait();
         }
    if(selectedLN != null){
             Alert alert = new Alert(AlertType.CONFIRMATION);
alert.setTitle("Confirmation de suppression");
alert.setHeaderText("Voulez-vous vraiment supprimer ce Reservation ?");
alert.setContentText("Cliquez sur OK pour confirmer la suppression.");

Optional<ButtonType> result = alert.showAndWait();

if (result.isPresent() && result.get() == ButtonType.OK) {
    // L'utilisateur a confirmé la suppression, vous pouvez maintenant supprimer la réservation.
    ReservationService bs = new ReservationService();
    bs.deleteReservation(selectedLN.getId());
    show(); // Peut-être une mise à jour de l'interface utilisateur après la suppression
    Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
    alert1.setTitle("Information");
    alert1.setHeaderText(null);
    alert1.setContentText("Réservation supprimée avec succès!");
    alert1.showAndWait();
}
        } 
 
    }
        public java.sql.Date localDateToDate(LocalDate localDate) {
    if (localDate != null) {
        return java.sql.Date.valueOf(localDate);
    }
    return null; // Handle null LocalDate, if needed
}
    
    @FXML
    void Modifier(ActionEvent event) {
        Reservation selectedLN = table.getSelectionModel().getSelectedItem();
        if (selectedLN == null) {
            // Afficher un message d'erreur
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Impossible de modifier");
            alert.setContentText("Veuillez selectionner pour modifier !");
            alert.showAndWait();
        } 
        
        else {
            // Show input dialogs to get the new event details.
// Create a custom dialog
            Dialog<Pair<String, LocalDate>> dialog = new Dialog<>();
            dialog.setTitle("Modifier une Réservation");
            dialog.setHeaderText("Modifier le commentaire et la date de la Réservation");

            // Set the button types (OK and Cancel)
            ButtonType saveButtonType = new ButtonType("Enregistrer", ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);

            // Create the content of the dialog
            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);
            grid.setPadding(new Insets(20, 150, 10, 10));

            // Add text input for comment
            TextField commentInput = new TextField(selectedLN.getCommentaire());
            commentInput.setPromptText("Nouveau Commentaire");
        // Suppose you have a java.util.Date
        // Supposons que vous avez un objet java.sql.Date
    java.sql.Date sqlDate = new java.sql.Date(selectedLN.getDateRdv().getTime());
            DatePicker datePicker = new DatePicker(sqlDate.toLocalDate());

            datePicker.setPromptText("Nouvelle Date de Réservation");

            grid.add(new Label("Commentaire:"), 0, 0);
            grid.add(commentInput, 1, 0);
            grid.add(new Label("Date de Réservation:"), 0, 1);
            grid.add(datePicker, 1, 1);

            dialog.getDialogPane().setContent(grid);

            // Convert the result to a pair of comment and date
            dialog.setResultConverter(dialogButton -> {
                if (dialogButton == saveButtonType) {
                    return new Pair<>(commentInput.getText(), datePicker.getValue());
                }
                return null;
            });

            // Show the dialog and handle the result
            Optional<Pair<String, LocalDate>> result = dialog.showAndWait();

            result.ifPresent(pair -> {
                // Update the comment and date with the new values
                String newComment = pair.getKey();
 
        selectedLN.setCommentaire(newComment);

        // Convertir LocalDate en java.sql.Date
     // Convertir LocalDate en java.sql.Date
           LocalDate newDate = pair.getValue();
        Date sqlDate1 = java.sql.Date.valueOf(newDate);
        selectedLN.setDateRdv(sqlDate1);


                // You can save the updated reservation or perform other actions as needed
                ReservationService reservationService = new ReservationService();
                reservationService.updateReservation(selectedLN);

                // Display a success message or refresh the UI
                showAlert("Success", "Commentaire et date de réservation mis à jour avec succès");
            });
 
        table.refresh();
        
    
        }}

               @FXML
    private void back(ActionEvent event) {
             try {
              Parent page1 = FXMLLoader.load(getClass().getResource("../gui/AcceuilM.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AcceuilController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }


   
        private void showAlert(String title, String message) {
            if (title.equals("Success")){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
       
            }
            else {
           Alert alert = new Alert(Alert.AlertType.WARNING);
        
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
            }
    }    }
 