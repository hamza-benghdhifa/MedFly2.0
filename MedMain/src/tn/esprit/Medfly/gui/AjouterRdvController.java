/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package tn.esprit.Medfly.gui;
 import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.IOException;
 import java.sql.Date;
import java.time.LocalDate;
import java.net.URL;
import java.time.LocalDate;
 import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tn.esprit.Medfly.entities.Reservation;
import tn.esprit.Medfly.services.ReservationService;
public class AjouterRdvController implements Initializable {
        @FXML
    private TextField t1;
                @FXML
    private TextField t2;
        @FXML
    private DatePicker d1;
                @FXML
    private TextArea c1;
int id;
 
    void setId(int id) {
        this.id= id;
     }

    /**
     * Initializes the controller class.
     */
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    public Date localDateToDate(LocalDate localDate) {
    if (localDate != null) {
        return Date.valueOf(localDate);
    }
    return null; // Handle null LocalDate, if needed
}
    
      @FXML
    private void res(ActionEvent event) {
              String nom = t1.getText();
        String prenom = t2.getText();
        String comment =c1.getText();
        LocalDate dateValue = d1.getValue(); // Assuming you want a LocalDate
        
        if (nom.isEmpty() || prenom.isEmpty() ||comment.isEmpty() || dateValue == null) {
            // Show an error alert if any of the fields is empty
            showAlert("Error", "Please fill in all fields !!!!!.");
        } else {
                Date sqlDate = localDateToDate(dateValue);

            // Call your service method here and handle success/failure
            Reservation r =new Reservation();
            r.setMedecinId(id);
            r.setDateRdv(sqlDate);
            r.setCommentaire(comment);
            boolean success =  false;
            ReservationService a =new ReservationService();
            a.createReservation(r,nom,prenom);
            success=true;
            if (success) {
                showAlert("Success", "Operation was successful.");
                   try {
               Parent page2 = FXMLLoader.load(getClass().getResource("../gui/ConsulterMyReservation.fxml"));
                 Scene scene = new Scene(page2);
                 Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                 stage.setScene(scene);
                 stage.show();
             } catch (IOException ex) {
                 Logger.getLogger(ConsulterMyReservationController.class.getName()).log(Level.SEVERE, null, ex);
             }
            } else {
                showAlert("Error", "Operation failed.");
            }
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
    }
        @FXML
    private void back(ActionEvent event) {
        try {
               Parent page1 = FXMLLoader.load(getClass().getResource("../gui/ConsulterMedecin.fxml"));
                 Scene scene = new Scene(page1);
                 Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                 stage.setScene(scene);
                 stage.show();
             } catch (IOException ex) {
                 Logger.getLogger(ConsulterMedecinController.class.getName()).log(Level.SEVERE, null, ex);
             }
    }

    

}
