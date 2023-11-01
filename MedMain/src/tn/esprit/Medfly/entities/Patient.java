/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.Medfly.entities;

import java.util.Date;

/**
 *
 * @author hamza
 */
public class Patient {
    private int id ;
    private String name;
    private String prenom;
    private Date date_naissance;
    private int num_assurance;
    private String maladie;
    private String email;;
    private String password;

    public Patient(String name, String prenom, Date date_naissance, int num_assurance, String maladie, String email, String password) {
        this.name = name;
        this.prenom = prenom;
        this.date_naissance = date_naissance;
        this.num_assurance = num_assurance;
        this.maladie = maladie;
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
            
    
    

    
        public Patient(int id, String name, String prenom, Date date_naissance, int num_assurance, String maladie) {
        this.id = id;
        this.name = name;
        this.prenom = prenom;
        this.date_naissance = date_naissance;
        this.num_assurance = num_assurance;
        this.maladie = maladie;
    }
        
        public Patient( String name, String prenom, Date date_naissance, int num_assurance, String maladie) {
            
        this.name = name;
        this.prenom = prenom;
        this.date_naissance = date_naissance;
        this.num_assurance = num_assurance;
        this.maladie = maladie;
    }

         public Patient() {
            
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setDate_naissance(Date date_naissance) {
        this.date_naissance = date_naissance;
    }

    public void setNum_assurance(int num_assurance) {
        this.num_assurance = num_assurance;
    }

    public void setMaladie(String maladie) {
        this.maladie = maladie;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPrenom() {
        return prenom;
    }

    public Date getDate_naissance() {
        return date_naissance;
    }

    public int getNum_assurance() {
        return num_assurance;
    }

    public String getMaladie() {
        return maladie;
    }
    
    
}
