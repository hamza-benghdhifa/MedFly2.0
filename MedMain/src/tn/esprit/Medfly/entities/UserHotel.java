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
public class UserHotel {
    private String usernom;
    private String userprenom;
    private int num_passport;
    private String nom_hotel;
    private String pays;
    private int nbre_chambre;
    private float facture_hotel;

    public UserHotel(String usernom, String userprenom, int num_passport, String nom_hotel, String pays, int nbre_chambre, float facture_hotel) {
        this.usernom = usernom;
        this.userprenom = userprenom;
        this.num_passport = num_passport;
        this.nom_hotel = nom_hotel;
        this.pays = pays;
        this.nbre_chambre = nbre_chambre;
        this.facture_hotel = facture_hotel;
    }

    public UserHotel() {
    }

    public String getUsernom() {
        return usernom;
    }

    public String getUserprenom() {
        return userprenom;
    }

    public int getNum_passport() {
        return num_passport;
    }

    public String getNom_hotel() {
        return nom_hotel;
    }

    public String getPays() {
        return pays;
    }

    public int getNbre_chambre() {
        return nbre_chambre;
    }

    public float getFacture_hotel() {
        return facture_hotel;
    }

    public void setUsernom(String usernom) {
        this.usernom = usernom;
    }

    public void setUserprenom(String userprenom) {
        this.userprenom = userprenom;
    }

    public void setNum_passport(int num_passport) {
        this.num_passport = num_passport;
    }

    public void setNom_hotel(String nom_hotel) {
        this.nom_hotel = nom_hotel;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public void setNbre_chambre(int nbre_chambre) {
        this.nbre_chambre = nbre_chambre;
    }

    public void setFacture_hotel(float facture_hotel) {
        this.facture_hotel = facture_hotel;
    }

    @Override
    public String toString() {
        return "UserHotel{" + "usernom=" + usernom + ", userprenom=" + userprenom + ", num_passport=" + num_passport + ", nom_hotel=" + nom_hotel + ", pays=" + pays + ", nbre_chambre=" + nbre_chambre + ", facture_hotel=" + facture_hotel + '}';
    }
    

}
