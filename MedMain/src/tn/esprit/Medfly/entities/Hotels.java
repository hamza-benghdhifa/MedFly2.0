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
public class Hotels {
    
    private int id_hotel;
    private String nom_hotel;
    private String etoile;
    private String pays;
    private int nbre_chambre;
    private float prix_nuit;

    public Hotels(int id_hotel, String nom_hotel, String etoile, String pays, int nbre_chambre, float prix_nuit) {
        this.id_hotel = id_hotel;
        this.nom_hotel = nom_hotel;
        this.etoile = etoile;
        this.pays = pays;
        this.nbre_chambre = nbre_chambre;
        this.prix_nuit = prix_nuit;
    }

    
    

    public Hotels() {
    }

    public Hotels(String nom_hotel, String etoile, String pays, int nbre_chambre, float prix_nuit) {
        this.nom_hotel = nom_hotel;
        this.etoile = etoile;
        this.pays = pays;
        this.nbre_chambre = nbre_chambre;
        this.prix_nuit = prix_nuit;
    }

   

    public void setId_hotel(int id_hotel) {
        this.id_hotel = id_hotel;
    }

    public int getId_hotel() {
        return id_hotel;
    }

    public String getNom_hotel() {
        return nom_hotel;
    }

    public String getEtoile() {
        return etoile;
    }

    public String getPays() {
        return pays;
    }

    public int getNbre_chambre() {
        return nbre_chambre;
    }

    public float getPrix_nuit() {
        return prix_nuit;
    }

    public void setNom_hotel(String nom_hotel) {
        this.nom_hotel = nom_hotel;
    }

    public void setEtoile(String etoile) {
        this.etoile = etoile;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public void setNbre_chambre(int nbre_chambre) {
        this.nbre_chambre = nbre_chambre;
    }

    public void setPrix_nuit(float prix_nuit) {
        this.prix_nuit = prix_nuit;
    }

    @Override
    public String toString() {
        return "Hotels{" + "id_hotel=" + id_hotel + ", nom_hotel=" + nom_hotel + ", etoile=" + etoile + ", pays=" + pays + ", nbre_chambre=" + nbre_chambre + ", prix_nuit=" + prix_nuit + '}';
    }

    
    
    
}
