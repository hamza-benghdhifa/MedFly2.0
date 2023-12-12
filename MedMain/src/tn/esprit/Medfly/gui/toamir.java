/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TableView;

import java.net.PasswordAuthentication;
import java.util.Date;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

/**
 *
 * @author Dell
 */
public class toamir {
    public toamir(){
    }
    public void tomir(){
    try {
    String host = "smtp.gmail.com";
    String user = "hjiriamir2020@gmail.com";
    String pass = "amir1998*";
    String from = "hjiriamir2020@gmail.com";
    String to = "jihedafli2017@gmail.com"; 

    boolean sessionDebug = false;
    Properties props = new Properties();
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.smtp.ssl.protocols", "TLSv1.2");
    props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
    props.put("mail.smtp.socketFactory.fallback", "false");
    props.put("mail.smtp.port", "465");
    props.put("mail.smtp.host", host);
    props.put("mail.smtp.starttls.required", "true");

    javax.mail.Authenticator authenticator = new javax.mail.Authenticator() {
        protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
            return new javax.mail.PasswordAuthentication(user, pass);
        }
    };
    
    Session mailSession = Session.getInstance(props, authenticator);
    mailSession.setDebug(sessionDebug);

    Message msg = new MimeMessage(mailSession);
    msg.setFrom(new InternetAddress(from));
    InternetAddress[] address = { new InternetAddress(to) };
    msg.setRecipients(Message.RecipientType.TO, address);
    msg.setSubject("our subject");
    msg.setSentDate(new Date());
    msg.setText("our message");

    Transport transport = mailSession.getTransport("smtp");
    transport.connect(host, user, pass);
    transport.sendMessage(msg, msg.getAllRecipients());
    transport.close();
    System.out.println("Message_envoye");
    JOptionPane.showMessageDialog(null, "Message_envoye");
} catch (MessagingException ex) {
    System.out.println(ex);
}

}}
