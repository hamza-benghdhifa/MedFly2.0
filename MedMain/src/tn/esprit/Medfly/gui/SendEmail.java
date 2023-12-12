/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TableView;

import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

/**
 *
 * @author Dell
 */
public class SendEmail {
    public SendEmail(){
}
    public void send(String to) throws AddressException {
        
        try {
            //String host="smtp.googlemail.com";
            String host="smtp.gmail.com";
            String user = "amir.hjiri@esprit.tn";
            String pass = "224JMT4975";
            String from ="amir.hjiri@esprit.tn";
            String subject ="Commentaire inaproprié";
            String messag ="votre compte est bloquer à cause de vous mentionnez un message  qui contients des mots inapropriés ";
            
            boolean sessionDebug=false;
            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable","true");
           // props.put("mail.smtp.ssl.protocols", "TLSv1.2"); 
           // props.put("mail.smtp.ssl.ciphersuites", "TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256"); 
            props.put("mail.smtp.stocketFactory.port","587");
           // props.put("mail.smtp.stocketFactory.class","javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.port","587");
            props.put("mail.smtp.host","smtp.gmail.com");
           // props.put("mail.smtp.port","587");
            //props.put("mail.smtp.starttls.required","true");
            //props.put("mail.smtp.ssl.enable","true");

           // java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
            Session mailsession=Session.getDefaultInstance(props, null);
            
            
            mailsession.setDebug(sessionDebug);
            Message msg=new MimeMessage(mailsession);
            msg.setFrom(new InternetAddress(from));
            InternetAddress[]address={new InternetAddress(to)};
            msg.setRecipients(Message.RecipientType.TO,address);
            msg.setSubject(subject);
            msg.setSentDate(new Date());
            msg.setText(messag);
             Transport transport=mailsession.getTransport("smtp");
            transport.connect(host,user,pass);
            transport.sendMessage(msg, msg.getAllRecipients());
            transport.close();
            System.out.println("Message_envoye");
           JOptionPane.showMessageDialog(null, "Message_envoye");
        } catch (MessagingException ex) {
            System.out.println(ex);
        }
       
    }}