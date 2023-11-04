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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author hamza
 */
public class ModMedController implements Initializable {

    
        @FXML
    private TextField IdField;
     
   @FXML
    private TextField tfmDate;

    @FXML
    private TextField tfmName;

    @FXML
    private TextField tfmNum;

    @FXML
    private TextField tfmPays;

    @FXML
    private TextField tfmPrenom;

    @FXML
    private TextField tfmSpecialite;

    @FXML
    void Apply(ActionEvent event) {
        
 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    Date gradDate = null;

    try {
        gradDate = dateFormat.parse(tfmDate.getText());
    } catch (ParseException e) {
        // Handle parsing errors
        e.printStackTrace();
        // You should display an error message to the user in a real application
        return;
    }

    int gradNum = 0;
    int idm = 0;

    try {
        gradNum = Integer.parseInt(tfmNum.getText());
        idm = Integer.parseInt(IdField.getText());
    } catch (NumberFormatException e) {
        
        e.printStackTrace();
       
        return;
    }

    Medecin newMedecin = new Medecin(idm,this.tfmName.getText(), this.tfmPrenom.getText(), this.tfmSpecialite.getText(), this.tfmPays.getText(), gradDate, gradNum);

    ServiceMedecin serviceMedecin = new ServiceMedecin();
    serviceMedecin.modifier(idm, newMedecin);

    System.out.println("Modification réussie.");
  
}
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        // TODO
    }    

         public void initData(Medecin selectedMedecin) {
        // Fill the text fields with the information of the selected Medecin.
        tfmName.setText(selectedMedecin.getName());
        tfmPrenom.setText(selectedMedecin.getPrenom());
        tfmSpecialite.setText(selectedMedecin.getSpecialite());
        tfmPays.setText(selectedMedecin.getPays());

        // Format the date and set it to the grad_date TextField
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        tfmDate.setText(dateFormat.format(selectedMedecin.getGrad_date()));

        tfmNum.setText(String.valueOf(selectedMedecin.getGrad_num()));
    }
    @FXML
    private void backtomain(ActionEvent event) throws IOException {
       
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("MedAffich.fxml")));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        
    }

    @FXML
    private void searchforid(ActionEvent event) {
        
    int medecinId = Integer.parseInt(IdField.getText());

    ServiceMedecin service = new ServiceMedecin();
    Medecin medecin = service.getOne(medecinId);

    tfmName.setText(medecin.getName());
    tfmPrenom.setText(medecin.getPrenom());
    tfmSpecialite.setText(medecin.getSpecialite());
    tfmPays.setText(medecin.getPays());

    // Format the date and set it to the grad_date TextField
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    tfmDate.setText(dateFormat.format(medecin.getGrad_date()));

    tfmNum.setText(String.valueOf(medecin.getGrad_num()));
    }
    
}
