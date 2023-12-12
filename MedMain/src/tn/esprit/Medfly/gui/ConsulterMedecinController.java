/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package tn.esprit.Medfly.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import tn.esprit.Medfly.entities.MedecinM;
 
import tn.esprit.Medfly.services.ReservationService;

 
public class ConsulterMedecinController implements Initializable {
         @FXML
    private TilePane medecinTilePane;
    private ObservableList<MedecinM> resultats = FXCollections.observableArrayList();

    @Override
public void initialize(URL urlFrF, ResourceBundle rb) {
    
    }

public void setMedecin(ObservableList<MedecinM> resultats1) {
    resultats = resultats1;
    initializeUI(); // Call a separate method to initialize the UI
}
private void initializeUI() {
    System.out.println("size: " + resultats.size());

    for (MedecinM event1 : resultats) {
        Card card = new Card(event1);
        medecinTilePane.getChildren().add(card);
    }
}
 
  



     
   
    
    public class Card extends VBox {
    public Card(MedecinM event1) {
        // Create an Image object from the file path
        Image image = new Image("file:" +event1.getImage());
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(150);
        imageView.setFitHeight(150);
        Label nomLabel = new Label("Nom & prenom : "+event1.getNom()+" "+event1.getPrenom());
        Label descriptionLabel = new Label("Pays : " + event1.getPays());
         nomLabel.setTextFill(Color.GREEN);
        VBox.setMargin(imageView, new Insets(10, 0, 0, 0));
        VBox.setMargin(nomLabel, new Insets(10, 0, 0, 0));
        VBox.setMargin(descriptionLabel, new Insets(10, 0, 0, 0));
 
       // Create a reservation button
        Button reserveButton = new Button("Reserver ");
        reserveButton.getStyleClass().add("reserver-button");

        reserveButton.setOnAction(event -> {
        
 
  try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/AjouterRdv.fxml"));
                Parent page1 = loader.load();

            // Accédez au contrôleur de l'interface cible
            AjouterRdvController controller =loader.getController();

            // Définissez le contrôleur pour le FXMLLoader
 
                controller.setId(event1.getId());
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }
            catch (IOException ex) {
            Logger.getLogger(ConsulterMedecinController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
 
 
});

 

       

        HBox buttonBox = new HBox(reserveButton);
        buttonBox.setAlignment(Pos.CENTER_RIGHT);
        VBox.setMargin(buttonBox, new Insets(0, 10, 0, 0));

        // Add all nodes to the card
        getChildren().addAll(imageView, nomLabel, descriptionLabel, buttonBox);
        setStyle("-fx-background-color: white; -fx-border-color: black; -fx-padding: 10;");
        setPrefWidth(200);
        setPrefHeight(250);
        getStyleClass().add("product-card");
    }

        
    }
  
    
}
