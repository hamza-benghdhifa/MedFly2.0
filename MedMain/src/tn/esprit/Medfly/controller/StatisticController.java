/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.Medfly.controller;

import tn.esprit.Medfly.services.ReclamationService;
import tn.esprit.Medfly.services.ReponseRecService;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author syrine
 */
public class StatisticController implements Initializable {

    @FXML
    private BarChart<?, ?> recchart;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
  XYChart.Series set1= new XYChart.Series<>();
        ReclamationService S = new ReclamationService();
             ReponseRecService Se = new ReponseRecService();

            set1.getData().add(new XYChart.Data("nombre de reclamation",S.nbrec()));
            set1.getData().add(new XYChart.Data("nombre de reponse",Se.nbreprec()));
           recchart.setBarGap(40);
        recchart.setCategoryGap(10);

             
      
    recchart.getData().setAll(set1);

    }    

    @FXML
    private void goback(ActionEvent event) throws IOException {
        
         FXMLLoader load = new FXMLLoader(getClass().getResource("/edu/medfly/Interface/reclamationview.fxml"));
                           Parent root =load.load();
                           ReclamationviewController c2=  load.getController();
                           Scene ss= new Scene(root);
                           Stage se= new Stage();
                           se=(Stage)((Node)event.getSource()).getScene().getWindow();
                           se.setScene(ss);
                           se.show();
    }
    
}
