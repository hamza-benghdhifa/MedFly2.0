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
public class UserVol {
    private String unom;
    private String uprenom;
    private int passport;
    private String nom_compagnie;
    private int nbre_billet;
    private String destination;
    private String depart;
    private float facture;

    public UserVol(String unom, String uprenom, int passport, String nom_compagnie, int nbre_billet, String destination, String depart, float facture) {
        this.unom = unom;
        this.uprenom = uprenom;
        this.passport = passport;
        this.nom_compagnie = nom_compagnie;
        this.nbre_billet = nbre_billet;
        this.destination = destination;
        this.depart = depart;
        this.facture = facture;
    }

    public UserVol() {
    }

    public String getUnom() {
        return unom;
    }

    public String getUprenom() {
        return uprenom;
    }

    public int getPassport() {
        return passport;
    }

    public String getNom_compagnie() {
        return nom_compagnie;
    }

    public int getNbre_billet() {
        return nbre_billet;
    }

    public String getDestination() {
        return destination;
    }

    public String getDepart() {
        return depart;
    }

    public float getFacture() {
        return facture;
    }

    public void setUnom(String unom) {
        this.unom = unom;
    }

    public void setUprenom(String uprenom) {
        this.uprenom = uprenom;
    }

    public void setPassport(int passport) {
        this.passport = passport;
    }

    public void setNom_compagnie(String nom_compagnie) {
        this.nom_compagnie = nom_compagnie;
    }

    public void setNbre_billet(int nbre_billet) {
        this.nbre_billet = nbre_billet;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public void setFacture(float facture) {
        this.facture = facture;
    }

    @Override
    public String toString() {
        return "UserVol{" + "unom=" + unom + ", uprenom=" + uprenom + ", passport=" + passport + ", nom_compagnie=" + nom_compagnie + ", nbre_billet=" + nbre_billet + ", destination=" + destination + ", depart=" + depart + ", facture=" + facture + '}';
    }
    
            
                


}
