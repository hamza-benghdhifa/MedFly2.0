/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tn.esprit.Medfly.entities;

import java.util.Date;

public class MedecinM {
    private int Id;
    private String Nom;
    private String Prenom;
    private String Specialite;
    private String Pays;
    private Date DateGrad;
    private String NumberGrad;
    private String Email;
    private String MotDePasse;
    private String Image ;

    public String getImage() {
        return Image;
    }

    public void setImage(String Image) {
        this.Image = Image;
    }
    // Constructeur
    public MedecinM(int Id, String Nom, String Prenom, String Specialite, String Pays, Date DateGrad, String NumberGrad, String Email, String MotDePasse,String Image) {
        this.Id = Id;
        this.Nom = Nom;
        this.Prenom = Prenom;
        this.Specialite = Specialite;
        this.Pays = Pays;
        this.DateGrad = DateGrad;
        this.NumberGrad = NumberGrad;
        this.Email = Email;
        this.MotDePasse = MotDePasse;
         this.Image = Image;

    }

    // Getters et setters

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    // Ajoutez les getters et setters pour les autres attributs

    public String getPrenom() {
        return Prenom;
    }

    public void setPrenom(String Prenom) {
        this.Prenom = Prenom;
    }

    public String getSpecialite() {
        return Specialite;
    }

    public void setSpecialite(String Specialite) {
        this.Specialite = Specialite;
    }

    public String getPays() {
        return Pays;
    }

    public void setPays(String Pays) {
        this.Pays = Pays;
    }

    public Date getDateGrad() {
        return DateGrad;
    }

    public void setDateGrad(Date DateGrad) {
        this.DateGrad = DateGrad;
    }

    public String getNumberGrad() {
        return NumberGrad;
    }

    public void setNumberGrad(String NumberGrad) {
        this.NumberGrad = NumberGrad;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getMotDePasse() {
        return MotDePasse;
    }

    public void setMotDePasse(String MotDePasse) {
        this.MotDePasse = MotDePasse;
    }

    @Override
    public String toString() {
        return "Medecin{" + "Id=" + Id + ", Nom=" + Nom + ", Prenom=" + Prenom + ", Specialite=" + Specialite + ", Pays=" + Pays + ", DateGrad=" + DateGrad + ", NumberGrad=" + NumberGrad + ", Email=" + Email + ", MotDePasse=" + MotDePasse + '}';
    }
    
}
