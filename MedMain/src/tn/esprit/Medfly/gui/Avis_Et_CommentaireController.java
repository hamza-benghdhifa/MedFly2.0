/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TableView;

import Categories.Avis_Et_Comment;
import Categories.Categorie;
import Service.gestion_avis;
import Service.gestion_service;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.PasswordAuthentication;
//import javax.mail.PasswordAuthentication;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;
import static jdk.nashorn.internal.objects.NativeRegExp.test;
import org.controlsfx.control.Rating;
import tools.DbConnect;

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class Avis_Et_CommentaireController implements Initializable {

    @FXML
    private Button confirmer_comment;
    @FXML
    private Rating star;
    @FXML
    private TextField txt_id;
    @FXML
    private DatePicker txt_date;
    @FXML
    private TextArea txtcomment;
    @FXML
    private ComboBox<String> comb_services;
    @FXML
    private TextField mail;
    @FXML
    private Button send;


    /**
     * Initializes the controller class.
     */
   // String top =mail.getText();
      public static boolean isValidEmail(String email) {
       
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try {
            DbConnect cox=new DbConnect();
             Connection connect=cox.getConnection();
            Statement statement = connect.createStatement();
            
       

            // Exécution d'une requête SQL pour récupérer les noms des services
            ResultSet resultSet = statement.executeQuery("SELECT NomCategorie FROM `gestion_categories`");

            while (resultSet.next()) {
                String serviceName = resultSet.getString("NomCategorie");
                comb_services.getItems().add(serviceName);
            }

            //connect.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }    
    String date;
     private void getDate(ActionEvent event) {
        LocalDate  MonDate = txt_date.getValue();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); 
         date = MonDate.format(formatter);
        
    }
     String nom_Services;
       @FXML
    private void select_service(ActionEvent event) {
         nom_Services=comb_services.getSelectionModel().getSelectedItem().toString();
         
    }
    public boolean isCommentInappropriate(String comment) {
    String[] inappropriateWords = {"mot1","mot2","mot3"}; 

    for (String word : inappropriateWords) {
        if (comment.toLowerCase().contains(word.toLowerCase())) {
            return true; 
        }
    }
    return false; 
}


    @FXML
    private void commenter(ActionEvent event) throws AddressException, MessagingException, Exception {
        String NomService = nom_Services;
        String Comment = txtcomment.getText();
        String texte = txt_id.getText();
        int id=0;
        try{
         id = Integer.parseInt(texte);     
        } catch (NumberFormatException e) { 
           
        }
        String Date_av =date;
        String email=mail.getText();
        //System.out.println("votre avis est :"+star.getRating());
        double etoile=star.getRating();
        gestion_avis ga = new gestion_avis();
         Avis_Et_Comment Com = new Avis_Et_Comment(nom_Services,id,etoile,Comment,Date_av);
            if (isValidEmail(email)) {
            //System.out.println("L'adresse e-mail est valide.");
            if(isCommentInappropriate(Comment) ==true){
           JOptionPane.showMessageDialog(null, "votre commentaire contient des mot inappropriés");
       
 SendEmail test=new SendEmail();
        
 test.send(email);
         }else{
       
         ga.ajouter(Com);
       
        
             JOptionPane.showMessageDialog(null, "commentaire et avis ajouter ");}
        } else {
            System.out.println("L'adresse e-mail n'est pas valide.");
             JOptionPane.showMessageDialog(null, "L'adresse e-mail n'est pas valide.");
        }
        /* if(isCommentInappropriate(Comment) ==true){
           JOptionPane.showMessageDialog(null, "votre commentaire contient des mot inappropriés");
       
 SendEmail test=new SendEmail();
        
 test.send(email);
         }else{
       
         ga.ajouter(Com);
       
        
             JOptionPane.showMessageDialog(null, "commentaire et avis ajouter ");}*/

    }

    @FXML
    private void email(ActionEvent event) {
    }

    @FXML
    private void sendd(ActionEvent event) throws DocumentException {
        
       /* Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.stocketFactory.port","465");
            props.put("mail.smtp.stocketFactory.class","javax.net.ssl.SSLSocketFactory");
             props.put("mail.smtp.auth", "true");
             props.put("mail.smtp.port","465");
              Session session=Session.getDefaultInstance(props, 
                      new javax.mail.Authenticator(){
                      protected  javax.mail.PasswordAuthentication getPasswordAuthentication(){
                          char[] passwordChars = password.toCharArray(); 
                      return new java.net.PasswordAuthentication("hjiriamir2020@gmail.com","password");
                      }
                      }s
                      );
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

    Paragraph para=new Paragraph("*************************Listes des ervices****************************");
    table.addCell("Nom de la catégorie");
    table.addCell(rs.getString("NomCategorie"));

    table.addCell("Tarification");
    table.addCell(tarif);

    table.addCell("Description");
    table.addCell(rs.getString("Description"));

    table.addCell("Référence des services");
    table.addCell(rs.getString("Ref_Services"));

    table.addCell("Disponibilité");
    table.addCell(rs.getString("Disponibilite"));

    table.addCell("Date");
    table.addCell(rs.getString("Date"));
            
document.add(table);
                document.add(new Paragraph(""));
                
                }
                document.close();
            } catch (SQLException ex) {
                Logger.getLogger(Avis_Et_CommentaireController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Ges_window.class.getName()).log(Level.SEVERE, null, ex);
        }*/

    }
private Stage stage;
    private Scene scene;
    private Parent Parent;
    @FXML
    private void recul(ActionEvent event) {
             try {
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
