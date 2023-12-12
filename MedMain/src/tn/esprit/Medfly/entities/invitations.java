/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.Medfly.entities;

/**
 *
 * @author khalf
 */
public class invitations {
    private int idinvi;
    private String EmailDestinataireinv;
    private String Emailinv;
    private String Status; // Should be "pending" or "accepted"

    // Constructors
    public invitations() {
    }

    public invitations(int idinvi, String EmailDestinataireinv, String Emailinv, String Status) {
        this.idinvi = idinvi;
        this.EmailDestinataireinv = EmailDestinataireinv;
        this.Emailinv = Emailinv;
        this.Status = Status;
    }

    // Getters and Setters
    public int getIdinvi() {
        return idinvi;
    }

    public void setIdinvi(int idinvi) {
        this.idinvi = idinvi;
    }

    public String getEmailDestinataireinv() {
        return EmailDestinataireinv;
    }

    public void setEmailDestinataireinv(String EmailDestinataireinv) {
        this.EmailDestinataireinv = EmailDestinataireinv;
    }

    public String getEmailinv() {
        return Emailinv;
    }

    public void setEmailinv(String Emailinv) {
        this.Emailinv = Emailinv;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }
}
