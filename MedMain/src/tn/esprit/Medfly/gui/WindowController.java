/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TableView;

import Categories.Categorie;
import Service.gestion_service;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import static java.time.temporal.TemporalQueries.localDate;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class WindowController implements Initializable {

    @FXML
    private RadioButton Button_Disponible;
    @FXML
    private ToggleGroup dispo;
    @FXML
    private RadioButton Button_Indisponible;
    @FXML
    private TextField txtNom;
    @FXML
    private TextField txtDescription;
    @FXML
    private TextField txtTarif;
    @FXML
    private Label MyLabel;
    @FXML
    private Label MyLabel1;
    @FXML
    private ComboBox<String> myChoiceBox;
    
    private String[] References= {"","Soins de santé mentale"," Soins cardiovasculaires","Soins en pédiatrie","Soins en oncologie","Soins de réadaptation","Soins Dentaires Généraux","Soins Esthétiques Dentaires","Soins en Chirurgie Buccale","Soins de Chirurgie Esthétique Buccale"};
    @FXML
    private Label date_label;
    private DatePicker MyDate;
    @FXML
    private Button ajout;
    
    String Ref_Services;
    @FXML
    private AnchorPane MyChoiceBox;
    @FXML
    private DatePicker Mydat;
    @FXML
    private Button modif;
    @FXML
    private Button supprim;
    @FXML
    private Button re;
    @FXML
    private Button vid;
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       myChoiceBox.getItems().addAll(References);
    }  
     
    @FXML
    public void selected(ActionEvent event) {
       
        Ref_Services=myChoiceBox.getSelectionModel().getSelectedItem().toString();
    }
    @FXML
    private void getDispo(ActionEvent event) {
        if(Button_Disponible.isSelected()){
        MyLabel.setText(Button_Disponible.getText());
    }
        else if(Button_Indisponible.isSelected())
         MyLabel.setText(Button_Indisponible.getText());

    }
  
    String dateStr;
    @FXML
    private void getDate(ActionEvent event) {
        LocalDate  MonDate = Mydat.getValue();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); 
         dateStr = MonDate.format(formatter);
        
    }
    @FXML
    private void AddCategorie(ActionEvent event) {
        
        String NomCategorie = txtNom.getText();
        String Description = txtDescription.getText();
        String texte = txtTarif.getText();
         int tarification=0;
        try {
         tarification = Integer.parseInt(texte); 
            
            
        } catch (NumberFormatException e) { 
            //System.err.println("La saisie n'est pas un entier valide !");
           // JOptionPane.showMessageDialog(null, "Tarification non valide ");
        }
        String Disponibilite = MyLabel.getText();
         String ref=Ref_Services;
         String Date_aj =dateStr;
         //JOptionPane.showMessageDialog(null,NomCategorie);
        gestion_service gs = new gestion_service();
        Categorie Cat = new Categorie(NomCategorie,tarification,Description,Disponibilite,ref, Date_aj);
       if (NomCategorie.equals("") || Description.equals("")) {
    JOptionPane.showMessageDialog(null, "Veuillez remplir toutes les champs ");
    
} else if(tarification<0 || tarification==0  ) {
        JOptionPane.showMessageDialog(null, "Tarification non valide ");
}else {
     try {
         tarification = Integer.parseInt(texte); 
            
            gs.ajouter(Cat);
                    JOptionPane.showMessageDialog(null, " Categorie Ajouter avec succés ");

        //System.out.println("Categorie Ajouter!"); 
        } catch (NumberFormatException e) { 
            //System.err.println("La saisie n'est pas un entier valide !");
            JOptionPane.showMessageDialog(null, "Tarification non valide ");
        }
         
       }
        
        myChoiceBox.getItems().add(txtNom.getText());
     txtNom.clear();
    }
    private Stage stage;
    private Scene scene;
    private Parent Parent;
    
    @FXML
    private void UpdateCategorie(ActionEvent event) {
         try {
        Parent parent = FXMLLoader.load(getClass().getResource("UpdateWindow.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
        } catch (IOException ex) {
            Logger.getLogger(Ges_window.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void DeleteCategorie(ActionEvent event) {
         try {
        Parent parent = FXMLLoader.load(getClass().getResource("DeleteWindow.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
        } catch (IOException ex) {
            Logger.getLogger(Ges_window.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    @FXML
    private void ret(ActionEvent event) {
         try {
        Parent parent = FXMLLoader.load(getClass().getResource("Acceuil.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
        } catch (IOException ex) {
            Logger.getLogger(Ges_window.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void video(ActionEvent event) {
          try {
        Parent parent = FXMLLoader.load(getClass().getResource("MediaPlayer.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
        } catch (IOException ex) {
            Logger.getLogger(Ges_window.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    }

    
    

