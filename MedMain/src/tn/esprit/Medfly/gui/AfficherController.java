/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TableView;

import Categories.Categorie;
import Service.gestion_service;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;


import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import tools.DbConnect;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class AfficherController implements Initializable {

    @FXML
    private TableColumn<Categorie, String> nom;
    @FXML
    private TableColumn<Categorie, String> description;
    @FXML
    private TableColumn<Categorie, String> tarifica;
    @FXML
    private TableColumn<Categorie, String> reff;
    @FXML
    private TableColumn<Categorie, String> diponi;
    @FXML
    private TableColumn<Categorie, String> mirdate;
    @FXML
    private TableView<Categorie> tablee;
    String VideoSelectionner;
    ObservableList<Categorie> listcateg = FXCollections.observableArrayList(); 
    @FXML
    private TextField chercher;
    @FXML
    private Button avis;
    @FXML
    private Button ouvrer;
    private ComboBox<String> vid;
    
 private String[] Reference= {"","Soins de santé mentale"," Soins cardiovasculaires","Soins en pédiatrie","Soins en oncologie","Soins de réadaptation","Soins Dentaires Généraux","Soins Esthétiques Dentaires","Soins en Chirurgie Buccale","Soins de Chirurgie Esthétique Buccale"};
    @FXML
    private Button generate;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //estion_service gs = new gestion_service();
                   

        DbConnect cox=new DbConnect();
        Connection connect=cox.getConnection();
        String viewtable ="SELECT * FROM `gestion_categories`";
         try {
            Statement statement=connect.createStatement();
            ResultSet queryOutput=statement.executeQuery(viewtable);
            while(queryOutput.next()){
                String name=queryOutput.getString("NomCategorie");
                String descriptions=queryOutput.getString("Description");
                int tarification=queryOutput.getInt("Tarification");
                String ref_services=queryOutput.getString("Ref_Services");
                String disponibilite=queryOutput.getString("Disponibilite");
                String date=queryOutput.getString("Date");
                
                listcateg.add(new Categorie(name,tarification,descriptions,ref_services,disponibilite,date));
                
                nom.setCellValueFactory(new PropertyValueFactory<>("NomCategorie"));
                description.setCellValueFactory(new PropertyValueFactory<>("Description"));
                tarifica.setCellValueFactory(new PropertyValueFactory<>("Tarification"));
                reff.setCellValueFactory(new PropertyValueFactory<>("Ref_Services"));
                diponi.setCellValueFactory(new PropertyValueFactory<>("Disponibilite"));
                mirdate.setCellValueFactory(new PropertyValueFactory<>("Date"));

                tablee.setItems(listcateg);
                
             FilteredList<Categorie> filteredData = new FilteredList<>(listcateg, b -> true);
        
                chercher.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(categorie -> {
        if (newValue == null || newValue.isEmpty()) {
            return true;
        }
        String search = newValue.toLowerCase();
        if (categorie.getNomCategorie().toLowerCase().contains(search) || categorie.getDescription().toLowerCase().contains(search)) {
            return true;
        } else {
            return false;
        }
    });
});
    
                
            SortedList<Categorie>sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(tablee.comparatorProperty());
            tablee.setItems(sortedData);
                
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
       //vid.getItems().addAll(Reference); 
    }    
 private Stage stage;
    private Scene scene;
    private Parent Parent;
    @FXML
    private void acc(ActionEvent event) {
           try {
        Parent parent = FXMLLoader.load(getClass().getResource("window_acceuil.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
        } catch (IOException ex) {
            Logger.getLogger(Ges_window.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void aviss(ActionEvent event) {
           try {
        Parent parent = FXMLLoader.load(getClass().getResource("Avis_Et_Commentaire.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
        } catch (IOException ex) {
            Logger.getLogger(Ges_window.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void ouvrir(ActionEvent event) {
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

    private void sequences(ActionEvent event) {
        VideoSelectionner=vid.getSelectionModel().getSelectedItem().toString();
    }

    @FXML
    private void generating(ActionEvent event)throws DocumentException  {
         try {
            String file_name="C:\\Users\\Dell\\Documents\\NetBeansProjects\\generate_pdf\\Services_PDF";
            
    Document document =new Document();
            PdfWriter.getInstance(document, new FileOutputStream(file_name));
                document.open();
                DbConnect cox=new DbConnect();
             Connection connect=cox.getConnection();
            PreparedStatement ps = null;
            ResultSet rs=null;
            String query ="Select * from `gestion_categories`";
            try {
                ps=connect.prepareStatement(query);
                rs=ps.executeQuery();
                while(rs.next()){
                    int tarification = rs.getInt("Tarification");
                    String tarif = String.valueOf(tarification);
                     PdfPTable table = new PdfPTable(6); 
                     //table.getDefaultCell().setBackgroundColor(BaseColor.LIGHT_GRAY);

Font headerFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.BLUE);
   

   table.addCell(new Phrase("Nom de la catégorie", headerFont));
table.addCell(new Phrase("Tarification", headerFont));
table.addCell(new Phrase("Description", headerFont));
table.addCell(new Phrase("Référence des services", headerFont));
table.addCell(new Phrase("Disponibilité", headerFont));
table.addCell(new Phrase("Date", headerFont));
    while (rs.next()) {
    table.addCell(rs.getString("NomCategorie"));
    table.addCell(tarif);
    table.addCell(rs.getString("Description"));
    table.addCell(rs.getString("Ref_Services"));
    table.addCell(rs.getString("Disponibilite"));
    table.addCell(rs.getString("Date"));
}
document.add(table);
                document.add(new Paragraph(""));
                
                }
                document.close();
            } catch (SQLException ex) {
                Logger.getLogger(Avis_Et_CommentaireController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Ges_window.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    }
    
    
    

