/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TableView;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

/**
 
 * @author Dell
 */
public class EnvoyerEmail {
    private String username = "amir.hjiri@esprit.tn";
private String password = "224JMT4975";

public void envoyer()  {
// Etape 1 : Création de la session
Properties props = new Properties();
props.put("mail.smtp.auth", "true");
props.put("mail.smtp.starttls.enable","true");
props.put("mail.smtp.host","smtp.gmail.com");
props.put("mail.smtp.port","587");
Session session = Session.getInstance(props,
new javax.mail.Authenticator() {
protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
//char[] passwordChars = password.toCharArray(); 
    return new javax.mail.PasswordAuthentication(username, password);
}
});
try {
// Etape 2 : Création de l'objet Message
Message message = new MimeMessage(session);
message.setFrom(new InternetAddress("amir.hjiri@esprit.tn"));
message.setRecipients(Message.RecipientType.TO,
InternetAddress.parse("amir.hjiri@esprit.tn"));
message.setSubject("Test email");
message.setText("Bonjour, ce message est un test ...");
// Etape 3 : Envoyer le message
Transport.send(message);
System.out.println("Message_envoye");
           JOptionPane.showMessageDialog(null, "Message_envoye");

} catch (MessagingException e) {
throw new RuntimeException(e);
} 
/*Properties properties =new Properties();
        
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        properties.put("mail.smtp.starttls.enable","true");
        properties.put("mail.smtp.host","smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        
        String myAccountEmail="amir.hjiri@esprit.tn";
        String password="224JMT4975";
         Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, password); //To change body of generated methods, choose Tools | Templates.
            }
            
}); 
         Message message =new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            Avis_Et_CommentaireController maile=new Avis_Et_CommentaireController();
           // String recipient ;
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
                message.setSubject("MEDFLY");
        Transport.send(message);
        System.out.println("message sent succesfully");*/
}
    
}

