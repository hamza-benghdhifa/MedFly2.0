/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.Medfly.entities;

/**
 *
 * @author 21653
 */
public class Facture {
    private int passport;
    private String nom;
    private String prenom;
    private String destiation;
    private String nom_hotel;
    private String nom_compagnie;
    private float montant;

    public Facture(int passport, String nom, String prenom, String destiation, String nom_hotel, String nom_compagnie, float montant) {
        this.passport = passport;
        this.nom = nom;
        this.prenom = prenom;
        this.destiation = destiation;
        this.nom_hotel = nom_hotel;
        this.nom_compagnie = nom_compagnie;
        this.montant = montant;
    }

    public Facture(int numPassport, String nomUtilisateur, String prenomUtilisateur, String nomHotel, String pays, Object object, Facture factureHotel) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getPassport() {
        return passport;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getDestiation() {
        return destiation;
    }

    public String getNom_hotel() {
        return nom_hotel;
    }

    public String getNom_compagnie() {
        return nom_compagnie;
    }

    public float getMontant() {
        return montant;
    }

    public void setPassport(int passport) {
        this.passport = passport;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setDestiation(String destiation) {
        this.destiation = destiation;
    }

    public void setNom_hotel(String nom_hotel) {
        this.nom_hotel = nom_hotel;
    }

    public void setNom_compagnie(String nom_compagnie) {
        this.nom_compagnie = nom_compagnie;
    }

    public void setMontant(float montant) {
        this.montant = montant;
    }

    @Override
    public String toString() {
        return "Facture{" + "passport=" + passport + ", nom=" + nom + ", prenom=" + prenom + ", destiation=" + destiation + ", nom_hotel=" + nom_hotel + ", nom_compagnie=" + nom_compagnie + ", montant=" + montant + '}';
    }
    
    
}
