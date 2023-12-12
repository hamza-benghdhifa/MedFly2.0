/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package tn.esprit.Medfly.gui;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tn.esprit.Medfly.entities.MedecinM;
import tn.esprit.Medfly.services.ReservationService;
 
public class AcceuilMController implements Initializable {
  @FXML
    private TextField t1;
      @FXML
    private ComboBox<String> specialiteComboBox;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    // Liste des spécialités existantes (à remplacer par vos données réelles)
        List<String> specialites = Arrays.asList("Cardiologue", "Dermatologue", "Pédiatre", "Ophtalmologiste", "Dentiste");

        // Spécialités supplémentaires
        List<String> specialitesSupplementaires = Arrays.asList(
            "La médecine préventive",
            "La néonatologie",
            "La néphrologie",
            "La neurologie",
            "L’odontologie",
            "L’oncologie",
            "L’obstétrique",
            "L’ophtalmologie",
            "L’orthopédie",
            "L’Oto-rhino-laryngologie",
            "La pédiatrie",
            "La pneumologie",
            "La psychiatrie",
            "La radiologie",
            "La radiothérapie",
            "La rhumatologie",
            "L’urologie"
        );

        // Ajoutez les spécialités existantes et supplémentaires au ComboBox
        specialiteComboBox.getItems().addAll(specialites);
        specialiteComboBox.getItems().addAll(specialitesSupplementaires);
       
    }
   
              @FXML
    private void rechercher(ActionEvent event) {
       // Récupérez les valeurs des champs de recherche
        String Pays = t1.getText();
        String specialite = specialiteComboBox.getValue();

        // Vérifiez si les champs sont vides
        if (Pays.isEmpty() && specialite == null) {
            afficherAlerte("Veuillez spécifier au moins un critère de recherche.");
        } else {
            // Utilisez les valeurs pour effectuer la recherche
            ReservationService serviceMedecin = new ReservationService(); // Remplacez ServiceMedecin par votre classe de service réelle
            ObservableList<MedecinM> resultats = serviceMedecin.getAllMedecinForPays(Pays, specialite);
            boolean ok =false;
            if (resultats.isEmpty()) {
                afficherAlerte("Aucun médecin correspondant aux critères de recherche.");
            } 
           
            if (!resultats.isEmpty()) {
                ok=true;
           try {
   System.out.println(ok +"size="+resultats.size());
    FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/ConsulterMedecin.fxml"));
    Parent page2 = loader.load();
    ConsulterMedecinController controller =  loader.getController();
    controller.setMedecin(resultats);
   
					Scene scene = new Scene(page2);
					Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
					stage.setScene(scene);
					stage.show();
} catch (IOException ex) {
    Logger.getLogger(ConsulterMedecinController.class.getName()).log(Level.SEVERE, null, ex);
}
            }
        }
    }

        
    

    private void afficherAlerte(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Avertissement");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
