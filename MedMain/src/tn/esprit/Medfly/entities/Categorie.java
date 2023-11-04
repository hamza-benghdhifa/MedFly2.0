/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Categories;

import java.sql.Date;
import java.util.Objects;


/**
 *
 * @author Dell
 */
public class Categorie {
    int id;
    String NomCategorie;
    int Tarification;
    String Description;
    String Disponibilite;
    String Ref_Services;
    String date;

    public Categorie() {
    }
    public Categorie(String NomCategorie, int Tarification, String Description, String Disponibilite, String Ref_Services, String date) {
        this.NomCategorie = NomCategorie;
        this.Tarification = Tarification;
        this.Description = Description;
        this.Disponibilite = Disponibilite;
        this.Ref_Services = Ref_Services;
        this.date = date;
    }

    public Categorie(int id, String NomCategorie, int Tarification, String Description, String Disponibilite, String Ref_Services, String date) {
        this.id = id;
        this.NomCategorie = NomCategorie;
        this.Tarification = Tarification;
        this.Description = Description;
        this.Disponibilite = Disponibilite;
        this.Ref_Services = Ref_Services;
        this.date = date;
    }

    /*public Categorie(String nom, String tarif, String description, String disponibilite, String s, java.sql.Date date) {
        this.NomCategorie = NomCategorie;
        this.Tarification = Tarification;
        this.Description = Description;
        this.Disponibilite = Disponibilite;
        this.Ref_Services = Ref_Services;
        this.date = date;
    }*/

   

   

   

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomCategorie() {
        return NomCategorie;
    }

    public void setNomCategorie(String NomCategorie) {
        this.NomCategorie = NomCategorie;
    }

    public int getTarification() {
        return Tarification;
    }

    public void setTarification(int Tarification) {
        this.Tarification = Tarification;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getDisponibilite() {
        return Disponibilite;
    }

    public void setDisponibilite(String Disponibilite) {
        this.Disponibilite = Disponibilite;
    }

    public String getRef_Services() {
        return Ref_Services;
    }

    public void setRef_Services(String Ref_Services) {
        this.Ref_Services = Ref_Services;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String Date) {
        this.date = Date;
    }

    @Override
    public String toString() {
        return "Categorie{" + "id=" + id + ", NomCategorie=" + NomCategorie + ", Tarification=" + Tarification + ", Description=" + Description + ", Disponibilite=" + Disponibilite + ", Ref_Services=" + Ref_Services + ", Date=" + date + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + this.id;
        hash = 37 * hash + Objects.hashCode(this.NomCategorie);
        hash = 37 * hash + Objects.hashCode(this.Tarification);
        hash = 37 * hash + Objects.hashCode(this.Description);
        hash = 37 * hash + Objects.hashCode(this.Disponibilite);
        hash = 37 * hash + Objects.hashCode(this.Ref_Services);
        hash = 37 * hash + Objects.hashCode(this.date);
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
        final Categorie other = (Categorie) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.NomCategorie, other.NomCategorie)) {
            return false;
        }
        if (!Objects.equals(this.Tarification, other.Tarification)) {
            return false;
        }
        if (!Objects.equals(this.Description, other.Description)) {
            return false;
        }
        if (!Objects.equals(this.Disponibilite, other.Disponibilite)) {
            return false;
        }
        if (!Objects.equals(this.Ref_Services, other.Ref_Services)) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        return true;
    }
    
    
}
