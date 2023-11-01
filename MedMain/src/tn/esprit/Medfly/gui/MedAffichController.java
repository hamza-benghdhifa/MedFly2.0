/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.Medfly.gui;

import tn.esprit.Medfly.entities.Medecin;
import tn.esprit.Medfly.services.ServiceMedecin;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import static javafx.collections.FXCollections.observableArrayList;
import static javafx.collections.FXCollections.observableList;
import static javafx.collections.FXCollections.observableList;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.converter.BooleanStringConverter;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import org.apache.poi.ss.usermodel.Row;




public class MedAffichController implements Initializable {

    

    @FXML
    private TreeTableColumn<Medecin, Date> CLdate;


    @FXML
    private TreeTableColumn<Medecin,String> CLnom;

    @FXML
    private TreeTableColumn<Medecin,Integer> CLnum;

    @FXML
    private TreeTableColumn<Medecin,String> CLpays;

    @FXML
    private TreeTableColumn<Medecin,String> CLprenom;

    @FXML
    private TreeTableColumn<Medecin,String> CLspecialite;

    @FXML
    private TreeTableView<Medecin> TableMed;
  
    private final TreeItem<Medecin> toor = new TreeItem<>();

    @FXML
    private TextField tfsearch;

        private Stage stage;
        
        private Scene scene;
        
        private Parent root;
        
       private ObservableList<Medecin> medecinList = FXCollections.observableArrayList();
 
    
    @FXML
    void Delete(ActionEvent event) {
        // Retrieve the selected item in the TreeTableView
    TreeItem<Medecin> selectedMedecinItem = TableMed.getSelectionModel().getSelectedItem();

    if (selectedMedecinItem != null) {
        Medecin selectedMedecin = selectedMedecinItem.getValue();
        int id = selectedMedecin.getId(); // Assuming you have a method to get the Medecin's ID

        // Display a confirmation dialog
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de suppression");
        alert.setHeaderText("Voulez-vous supprimer ce médecin ?");
        alert.setContentText("Confirmez la suppression du médecin sélectionné.");

        // Add "Yes" and "No" buttons to the dialog
        ButtonType buttonTypeYes = new ButtonType("Oui");
        ButtonType buttonTypeNo = new ButtonType("Non");
        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

        // Wait for the user's response
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == buttonTypeYes) {
            // The user has chosen to delete the Medecin
            ServiceMedecin serviceMedecin = new ServiceMedecin();
            serviceMedecin.supprimer(id);

            // Remove the item from the visual tree as well
            toor.getChildren().remove(selectedMedecinItem);
        }
    }

  
}

private void showAlert(String title, String message) {
    Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
}


    

    @FXML
    void ToAjouter(ActionEvent event)  throws IOException {
       
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AjoutMed.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
   
    

    @FXML
    void ToModifier(ActionEvent event) throws IOException {
        
  // Retrieve the selected Medecin from the TreeTableView
    TreeItem<Medecin> selectedMedecinItem = TableMed.getSelectionModel().getSelectedItem();

    if (selectedMedecinItem != null) {
        Medecin selectedMedecin = selectedMedecinItem.getValue();

        // Load the ModifierMedecin.fxml window
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ModMed.fxml"));
        Parent root = loader.load();

        // Get the ModifierMedecinController of the window
        ModMedController modifierMedecinController = loader.getController();

        // Pass the information of the selected Medecin to the ModifierMedecinController
        modifierMedecinController.initData(selectedMedecin);

        // Switch to the ModifierMedecin window
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    
}
   
    @FXML
    void search(ActionEvent event) {
                  String idText =  tfsearch.getText();

    // Check if the input is a valid integer
    if (idText.matches("\\d+")) {
        int id = Integer.parseInt(idText);

        // Retrieve the patient with the specified ID using ServicePatient
        ServiceMedecin servicemedecin = new ServiceMedecin();
        Medecin medecin = servicemedecin.getOne(id);

        if (medecin != null) {
            // Clear the existing data
            toor.getChildren().clear();

            // Add the retrieved patient to the TreeTableView
            TreeItem<Medecin> patientItem = new TreeItem<>(medecin);
            toor.getChildren().add(patientItem);
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
private void RechercheAvancee(ActionEvent event) {
    // Retrieve the text entered in the txtRecherche field
    String recherche = tfsearch.getText().trim();

    // Make sure the search is not empty
    if (!recherche.isEmpty()) {
        // Use the new method for advanced search for Medecin
        ServiceMedecin serviceMedecin = new ServiceMedecin();
        ArrayList<Medecin> result = serviceMedecin.AdvancedSearchByName(recherche);

        // Clear the current content of your TreeTableView (toor)
        toor.getChildren().clear();

        // Add the search results to the TreeTableView
        for (Medecin medecin : result) {
            TreeItem<Medecin> medecinItem = new TreeItem<>(medecin);
            toor.getChildren().add(medecinItem);
        }
    } else {
        // If the search field is empty, you can reload all Medecin items
        loadMed();
    }
}

    


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Associate TableColumn with Medecin properties
        
        CLnom.setCellValueFactory(new TreeItemPropertyValueFactory<>("name"));
        CLprenom.setCellValueFactory(new TreeItemPropertyValueFactory<>("prenom"));
        CLspecialite.setCellValueFactory(new TreeItemPropertyValueFactory<>("specialite"));
        CLpays.setCellValueFactory(new TreeItemPropertyValueFactory<>("pays"));
        CLdate.setCellValueFactory(new TreeItemPropertyValueFactory<>("grad_date"));
        CLnum.setCellValueFactory(new TreeItemPropertyValueFactory<>("grad_num"));

        // Set the root node for the TreeTableView
        TableMed.setRoot(toor);

        // Load Medecin data
        loadMed();
        
    
        
        
                // Mettre en place la recherche avancée en écoutant les modifications du champ de recherche
            tfsearch.textProperty().addListener((observable, oldValue, newValue) -> {
            // Appeler la méthode RechercheAvancee lorsque le texte change
             RechercheAvancee(null); });
            
            
                    }
    

    private void loadMed() {
        // Fetch Medecin data from your ServiceMedecin  
        ServiceMedecin service = new ServiceMedecin();
        List<Medecin> medecinData = service.getAll();

        // Create TreeItems for each Medecin and add them to the root
        for (Medecin medecin : medecinData) {
            TreeItem<Medecin> medecinItem = new TreeItem<>(medecin);
            toor.getChildren().add(medecinItem);
        }
    }

    @FXML
    private void refref(ActionEvent event) {
            // Clear the existing data
    toor.getChildren().clear();

    // Load Medecin data again
    loadMed();
    }
public void exportToExcelMedecin() {
    // Create a new Excel workbook and sheet
       // Create a new Excel workbook and sheet
    Workbook workbook = new XSSFWorkbook();
    Sheet sheet = workbook.createSheet("Medecin Data");

    // Get the data from the TableView or wherever you have it
    List<Medecin> data = getMedecinData(); // You should implement this method to fetch the data

    // Create headers for the Excel file
    Row headerRow = sheet.createRow(0);
    headerRow.createCell(0).setCellValue("Name");
    headerRow.createCell(1).setCellValue("Prenom");
    headerRow.createCell(2).setCellValue("Specialite");
    headerRow.createCell(3).setCellValue("Pays");
    headerRow.createCell(4).setCellValue("Grad Date");
    headerRow.createCell(5).setCellValue("Grad Num");
    headerRow.createCell(6).setCellValue("Email");

    // Populate the Excel sheet with data
    for (int i = 0; i < data.size(); i++) {
        Row row = sheet.createRow(i + 1);
        Medecin medecin = data.get(i);
        row.createCell(0).setCellValue(medecin.getName());
        row.createCell(1).setCellValue(medecin.getPrenom());
        row.createCell(2).setCellValue(medecin.getSpecialite());
        row.createCell(3).setCellValue(medecin.getPays());
        
        // Format the date (grad_date) to a string before adding it to the cell
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        row.createCell(4).setCellValue(dateFormat.format(medecin.getGrad_date()));
        
        row.createCell(5).setCellValue(medecin.getGrad_num());
        row.createCell(6).setCellValue(medecin.getEmail());
    }

    // Save the workbook to a file
    try {
        FileOutputStream fileOut = new FileOutputStream("medecin_data.xlsx");
        workbook.write(fileOut);
        fileOut.close();
        System.out.println("Excel file for Medecin data has been created!");
    } catch (Exception e) {
        e.printStackTrace();
    }
}
private List<Medecin> getMedecinData() {
        List<Medecin> medecinList = new ArrayList<>();

        // Traverse the TreeTableView structure to get the data
        for (TreeItem<Medecin> topLevelItem : toor.getChildren()) {
            Medecin medecin = topLevelItem.getValue();
            medecinList.add(medecin);

            // If your TreeTableView has nested items, you can traverse them here
            for (TreeItem<Medecin> nestedItem : topLevelItem.getChildren()) {
                Medecin nestedMedecin = nestedItem.getValue();
                medecinList.add(nestedMedecin);
            }
        }

        return medecinList;
    }

    // Other methods and code here



    @FXML
    private void EXCELBUTTON(ActionEvent event) {
        exportToExcelMedecin();
    }

    @FXML
    private void back(ActionEvent event) throws IOException {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AdminMainMenu.fxml")));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
    }

    }

    
