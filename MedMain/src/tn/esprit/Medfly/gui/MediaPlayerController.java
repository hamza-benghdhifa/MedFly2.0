/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TableView;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
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
import javafx.scene.control.ComboBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import static javafx.scene.media.MediaPlayer.Status.PLAYING;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import tools.DbConnect;

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class MediaPlayerController implements Initializable {

    @FXML
    private Button playy;
    @FXML
    private Button stopp;
    @FXML
    private MediaView mv;

    /**
     * Initializes the controller class.
     */
   String[] Referenc= {"Soins de santé mentale","Soins cardiovasculaires","Soins en pédiatrie","Soins en oncologie","Soins Dentaires Généraux","Soins Esthétiques Dentaires"};

     MediaPlayer mediaplayer;
    @FXML
    private ComboBox<String> regarder;
    String reff;
    @FXML
    private Button ret;
 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //String Vurl="C:\\Users\\Dell\\Desktop\\media\\derouich.mp4";
        
            regarder.getItems().addAll(Referenc);
               // String filePath = null;
            // AfficherController aff=new AfficherController();
            //reff = "Soins de santé mentale";
           /* String filePath = null;
            
        if (reff.equals("Soins de santé mentale")) {
            
                filePath = "C:\\Users\\Dell\\Desktop\\media\\soin_sante_mentale.mp4";
                 
       
        }else if (reff.equals("Soins en pédiatrie")){
                filePath = "C:\\Users\\Dell\\Desktop\\media\\pediatrie.mp4";
     
       
            }else if (reff.equals("Soins cardiovasculaires")){
                filePath = "C:\\Users\\Dell\\Desktop\\media\\soin_cardiovasculaire.mp4";
             
       
            }else if (reff.equals("Soins Dentaires Généraux")||reff.equals("Soins Esthétiques Dentaires")){
           
                filePath = "C:\\Users\\Dell\\Desktop\\media\\soin_dentaire.mp4";
           
       
        }
            
      
                
           
       // filePath = "C:\\Users\\Dell\\Desktop\\media\\derouich.mp4";
        String Vurl = new File(filePath).toURI().toString();

        Media media=new Media(Vurl);
         mediaplayer = new MediaPlayer(media);
        mv.setFitHeight(1100);
        mv.setFitWidth(1100);
        mv.setMediaPlayer(mediaplayer);
       
     */
 
    }    


   @FXML
    private void regarde(ActionEvent event) {
        reff=regarder.getSelectionModel().getSelectedItem().toString();
        String filePath = null;
            
        if (reff.equals("Soins de santé mentale")) {
            
                filePath = "C:\\Users\\Dell\\Desktop\\media\\soin_sante_mentale.mp4";
                 
       
        }else if (reff.equals("Soins en pédiatrie")){
                filePath = "C:\\Users\\Dell\\Desktop\\media\\pediatrie.mp4";
     
       
            }else if (reff.equals("Soins cardiovasculaires")){
                filePath = "C:\\Users\\Dell\\Desktop\\media\\soin_cardiovasculaire.mp4";
             
       
            }else if (reff.equals("Soins Dentaires Généraux")||reff.equals("Soins Esthétiques Dentaires")){
           
                filePath = "C:\\Users\\Dell\\Desktop\\media\\soin_dentaire.mp4";
           
       
        }else if (reff.equals("Soins en oncologie")){
           
                filePath = "C:\\Users\\Dell\\Desktop\\media\\soins_en_Oncologie.mp4";
           
       
        }
            
      
                
           
       // filePath = "C:\\Users\\Dell\\Desktop\\media\\derouich.mp4";
        String Vurl = new File(filePath).toURI().toString();

        Media media=new Media(Vurl);
         mediaplayer = new MediaPlayer(media);
        mv.setFitHeight(700);
        mv.setFitWidth(500);
        mv.setMediaPlayer(mediaplayer);
       
     
        
    }
        @FXML
    private void play(ActionEvent event) {
        if(mediaplayer.getStatus()==PLAYING){ 
            mediaplayer.stop();
             mediaplayer.play();
        }  else{
                    mediaplayer.play();

    }}

    @FXML
    private void stop(ActionEvent event) {
        mediaplayer.stop();
    }
private Stage stage;
    private Scene scene;
    private Parent Parent;
    @FXML
    private void recule(ActionEvent event) {
             try {
                  DbConnect cox=new DbConnect();
        Connection connect=cox.getConnection();
        Parent parent = FXMLLoader.load(getClass().getResource("afficher.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
        } catch (IOException ex) {
            Logger.getLogger(Ges_window.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
