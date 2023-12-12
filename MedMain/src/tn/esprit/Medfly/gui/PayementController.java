/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package tn.esprit.Medfly.gui;

/*import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;*/

import java.util.Collections;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
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
import tn.esprit.Medfly.services.ReservationPayement;
import tn.esprit.Medfly.services.ReservationService;

public class PayementController implements Initializable {
    int id ;
     public  void setId(int id) {
       this.id=id;
     }
 @FXML
    private TextField t1;
  @FXML
    private TextField t2;
   @FXML
    private TextField t3;
    @FXML
    private TextField t4;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
    
 @FXML
private void valider(ActionEvent event) {
    /*String numCarte = t1.getText();
    String CVC = t2.getText();
    String dateExpiration = t3.getText();
    String email = t4.getText();
    Stripe.apiKey = "sk_test_51O4vooJwXHbglbmRzWgX6G2N6gLkjrzwEhhtC0kV8xM87a4L0NLH6q6rLb97pdV9nLL6TZN0R9X5ff3dkpUzDFtt0096KV2aFJ";

    // Vérifiez si tous les champs sont remplis
    if (numCarte.isEmpty() || CVC.isEmpty() || dateExpiration.isEmpty() || email.isEmpty()) {
        showAlert("Erreur", "Veuillez remplir tous les champs.");
    } 
    if (!isValidEmail(email)) {
        showAlert("Erreur", "L'adresse e-mail n'est pas valide.");
    } 
    else {
        // Tous les champs sont valides, appelez le service pour créer le paiement
 boolean paymentSuccess =false ;
try {
            // Create a PaymentIntent
            PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
            .setAmount(1000L) // Set the amount as a Long value
  .setCurrency("usd")
    .setPaymentMethod("pm_card_visa") // Remplacez par la méthode de paiement réelle
     .setAutomaticPaymentMethods(
      PaymentIntentCreateParams.AutomaticPaymentMethods.builder()
        .setEnabled(true)
        .setAllowRedirects(
          PaymentIntentCreateParams.AutomaticPaymentMethods.AllowRedirects.NEVER
        )
        .build()
    )
    .build();

            PaymentIntent paymentIntent = PaymentIntent.create(params);
            paymentSuccess=true;
            // Handle the payment response
            if ("succeeded".equals(paymentIntent.getStatus())) {
               
                System.out.println(paymentSuccess);

                // Payment was successful
                // Show a success message or perform further actions
            } else {
                // Payment failed
                // Show an error message to the user
            }
        } catch (StripeException e) {
            e.printStackTrace();
            // Handle the Stripe exception and show an error message to the user
        }
    

        if (paymentSuccess) {
              ReservationPayement a =new ReservationPayement();
                          Reservation r =new Reservation();
                          r.setId(this.id);
                          r.setPayement(true);
                    a.updatepayemnt(r);
      // Vous pouvez créer une instance de GeneratePDFReport ici et appeler la méthode main si nécessaire
        GeneratePDFReport generatePDFReport = new GeneratePDFReport();
        // Appeler la méthode main
        generatePDFReport.main(null);
            showAlert("Succès", "Paiement créé avec succès.");
              try {
               Parent page1 = FXMLLoader.load(getClass().getResource("../gui/ConsulterMyReservation.fxml"));
                 Scene scene = new Scene(page1);
                 Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                 stage.setScene(scene);
                 stage.show();
             } catch (IOException ex) {
                 Logger.getLogger(ConsulterMyReservationController.class.getName()).log(Level.SEVERE, null, ex);
             }
        } else {
            showAlert("Erreur", "Échec de la création du paiement.");
        }
    }*/
}

 
private boolean isValidEmail(String email) {
    // Expression régulière pour valider une adresse e-mail
       /* String regex = "[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}";

    // Créez un objet Pattern
    Pattern pattern = Pattern.compile(regex);

    // Créez un objet Matcher
    Matcher matcher = pattern.matcher(email);

    // Vérifiez si l'adresse e-mail correspond à l'expression régulière
    return matcher.matches();
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



      
 
         @FXML*/
    /*private void back(ActionEvent event) {
        try {
           /*    Parent page1 = FXMLLoader.load(getClass().getResource("../gui/ConsulterMedecin.fxml"));
                 Scene scene = new Scene(page1);
                 Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                 stage.setScene(scene);
                 stage.show();
             } catch (IOException ex) {
                 Logger.getLogger(ConsulterMedecinController.class.getName()).log(Level.SEVERE, null, ex);
             }
    }   

  
}
