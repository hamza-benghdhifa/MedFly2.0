/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Categories;

import java.util.Objects;

/**
 *
 * @author Dell
 */
public class Avis_Et_Comment {
    int Id_Avis;
    String Nom_Service;
    int Id_Patient;
    double Note;
    String Commentaire;
    String Date_Avis;

    public Avis_Et_Comment() {
    }

    public Avis_Et_Comment(String Nom_Service, int Id_Patient, double Note, String Commentaire, String Date_Avis) {
        this.Nom_Service = Nom_Service;
        this.Id_Patient = Id_Patient;
        this.Note = Note;
        this.Commentaire = Commentaire;
        this.Date_Avis = Date_Avis;
    }

    public Avis_Et_Comment(int Id_Avis, String Nom_Service, int Id_Patient, double Note, String Commentaire, String Date_Avis) {
        this.Id_Avis = Id_Avis;
        this.Nom_Service = Nom_Service;
        this.Id_Patient = Id_Patient;
        this.Note = Note;
        this.Commentaire = Commentaire;
        this.Date_Avis = Date_Avis;
    }

    public int getId_Avis() {
        return Id_Avis;
    }

    public void setId_Avis(int Id_Avis) {
        this.Id_Avis = Id_Avis;
    }

    public String getNom_Service() {
        return Nom_Service;
    }

    public void setNom_Service(String Nom_Service) {
        this.Nom_Service = Nom_Service;
    }

    public int getId_Patient() {
        return Id_Patient;
    }

    public void setId_Patient(int Id_Patient) {
        this.Id_Patient = Id_Patient;
    }

    public double getNote() {
        return Note;
    }

    public void setNote(double Note) {
        this.Note = Note;
    }

    public String getCommentaire() {
        return Commentaire;
    }

    public void setCommentaire(String Commentaire) {
        this.Commentaire = Commentaire;
    }

    public String getDate_Avis() {
        return Date_Avis;
    }

    public void setDate_Avis(String Date_Avis) {
        this.Date_Avis = Date_Avis;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + this.Id_Avis;
        hash = 47 * hash + Objects.hashCode(this.Nom_Service);
        hash = 47 * hash + this.Id_Patient;
        hash = 47 * hash + (int) (Double.doubleToLongBits(this.Note) ^ (Double.doubleToLongBits(this.Note) >>> 32));
        hash = 47 * hash + Objects.hashCode(this.Commentaire);
        hash = 47 * hash + Objects.hashCode(this.Date_Avis);
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
        final Avis_Et_Comment other = (Avis_Et_Comment) obj;
        if (this.Id_Avis != other.Id_Avis) {
            return false;
        }
        if (this.Id_Patient != other.Id_Patient) {
            return false;
        }
        if (Double.doubleToLongBits(this.Note) != Double.doubleToLongBits(other.Note)) {
            return false;
        }
        if (!Objects.equals(this.Nom_Service, other.Nom_Service)) {
            return false;
        }
        if (!Objects.equals(this.Commentaire, other.Commentaire)) {
            return false;
        }
        if (!Objects.equals(this.Date_Avis, other.Date_Avis)) {
            return false;
        }
        return true;
    }
    
    

}
