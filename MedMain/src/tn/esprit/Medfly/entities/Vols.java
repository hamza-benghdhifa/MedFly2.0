/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.Medfly.entities;

import java.util.Objects;

/**
 *
 * @author 21653
 */
public class Vols {
     private String date_depart;
     private String destination;
     private float prix_billet;
     private int nb_billet;
     private int id_vol;
     private String nom_airways;

    public Vols() {
    }

    public Vols(String nom_airways, int nb_billet, float prix_billet,String date_depart, String destination) {
        this.date_depart = date_depart;
        this.destination = destination;
        this.prix_billet = prix_billet;
        this.nb_billet = nb_billet;
        this.nom_airways = nom_airways;
    }

    public Vols( int id_vol,String nom_airways, int nb_billet, float prix_billet,String date_depart, String destination) {
        this.date_depart = date_depart;
        this.destination = destination;
        this.prix_billet = prix_billet;
        this.nb_billet = nb_billet;
        this.id_vol = id_vol;
        this.nom_airways = nom_airways;
    }

 

    public int getId_vol() {
        return id_vol;
    }

    public String getDate_depart() {
        return date_depart;
    }

   

  
    public String getNom_airways() {
        return nom_airways;
    }

    public void setDate_depart(String date_depart) {
        this.date_depart = date_depart;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

   
    public float getPrix_billet() {
        return prix_billet;
    }

    public void setPrix_billet(float prix_billet) {
        this.prix_billet = prix_billet;
    }

     public void setId_vol(int id_vol) {
        this.id_vol = id_vol;
    }
    public void setNb_billet(int nb_billet) {
        this.nb_billet = nb_billet;
    }

    public void setNom_airways(String nom_airways) {
        this.nom_airways = nom_airways;
    }

    public int getNb_billet() {
        return nb_billet;
    }

    @Override
    public String toString() {
        return "vols{" + "date_depart=" + date_depart + ", destination=" + destination + ", prix_billet=" + prix_billet + ", nb_billet=" + nb_billet + ", id_vol=" + id_vol + ", nom_airways=" + nom_airways + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + this.id_vol;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Vols other = (Vols) obj;
        return true;
    }

    
    
     
    
    
}
