/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.Medfly.entities;
import java.sql.Date;
import tn.esprit.Medfly.utilities.MahConnection;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.List;
import java.time.LocalDateTime;
/**
 *
 * @author khalf
 */
public class messages {
    private int id;
    private String ObjetMessage;
    private String ContenuMessage;
    private Timestamp DateEnvoi;
    private String EmailDestinataire;
    private String Email;

    public messages() {
        // Default constructor
    }


    public messages(int id, String ObjetMessage, String ContenuMessage, String EmailDestinataire, String Email) {
        this.id = id;
        this.ObjetMessage = ObjetMessage;
        this.ContenuMessage = ContenuMessage;
        this.EmailDestinataire = EmailDestinataire;
        this.Email = Email;
    }    
// Constructor for creating a new message with the current date
    public messages(String ObjetMessage, String ContenuMessage, String EmailDestinataire, String Email) {
        this.id = 0; // Auto-incremented ID will be generated by the database
        this.ObjetMessage = ObjetMessage;
        this.ContenuMessage = ContenuMessage;
        this.DateEnvoi = new Timestamp(System.currentTimeMillis()); // Set to the current time
        this.EmailDestinataire = EmailDestinataire;
        this.Email = Email;
    }

    // Getters and setters for the attributes (id, ObjetMessage, ContenuMessage, DateEnvoi, EmailDestinataire, and Email)

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getObjetMessage() {
        return ObjetMessage;
    }

    public void setObjetMessage(String ObjetMessage) {
        this.ObjetMessage = ObjetMessage;
    }

    public String getContenuMessage() {
        return ContenuMessage;
    }

    public void setContenuMessage(String ContenuMessage) {
        this.ContenuMessage = ContenuMessage;
    }

    public Timestamp getDateEnvoi() {
        return DateEnvoi;
    }

    public void setDateEnvoi(Timestamp DateEnvoi) {
        this.DateEnvoi = DateEnvoi;
    }

    public String getEmailDestinataire() {
        return EmailDestinataire;
    }

    public void setEmailDestinataire(String EmailDestinataire) {
        this.EmailDestinataire = EmailDestinataire;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }
    

/*hashCode*/    
    @Override
    public int hashCode() {
        return super.hashCode(); //To change body of generated methods, choose Tools | Templates.
    }
}


/*Constructor*/
    
        