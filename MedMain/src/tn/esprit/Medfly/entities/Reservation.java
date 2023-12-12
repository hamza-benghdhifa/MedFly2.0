/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tn.esprit.Medfly.entities;


import java.util.Date;

public class Reservation {
    private int id;
    private int PatientId ;
    private int MedecinId ;
    private Date DateRdv ;
    private String Commentaire ;
private Boolean payement ;
private String Pays ;
private String NameDoctor ;

    public Reservation(int id, int PatientId, int MedecinId, Date DateRdv, String Commentaire, Boolean payement, String Pays, String NameDoctor) {
        this.id = id;
        this.PatientId = PatientId;
        this.MedecinId = MedecinId;
        this.DateRdv = DateRdv;
        this.Commentaire = Commentaire;
        this.payement = payement;
        this.Pays = Pays;
        this.NameDoctor = NameDoctor;
    }

    public Reservation(int PatientId, int MedecinId, Date DateRdv, String Commentaire, Boolean payement, String Pays, String NameDoctor) {
        this.PatientId = PatientId;
        this.MedecinId = MedecinId;
        this.DateRdv = DateRdv;
        this.Commentaire = Commentaire;
        this.payement = payement;
        this.Pays = Pays;
        this.NameDoctor = NameDoctor;
    }
    

    public String getPays() {
        return Pays;
    }

    public void setPays(String Pays) {
        this.Pays = Pays;
    }

    public String getNameDoctor() {
        return NameDoctor;
    }

    public void setNameDoctor(String NameDoctor) {
        this.NameDoctor = NameDoctor;
    }


    public Boolean getPayement() {
        return payement;
    }

    public void setPayement(Boolean payement) {
        this.payement = payement;
    }
    public String getCommentaire() {
        return Commentaire;
    }

    public void setCommentaire(String Commentaire) {
        this.Commentaire = Commentaire;
    }

    // Constructeur
    public Reservation(int id, int idPatient, int idMedecin, Date dateRdv,String Commentaire,Boolean ok) {
 
        this.id = id;
        this.PatientId = idPatient;
        this.MedecinId = idMedecin;
        this.DateRdv = dateRdv;
        this.Commentaire=Commentaire;
          this.payement =ok;
    }

       public Reservation(  int idPatient, int idMedecin, Date dateRdv,String Commentaire,Boolean ok) {
      
        this.PatientId = idPatient;
        this.MedecinId = idMedecin;
        this.DateRdv = dateRdv;
        this.Commentaire=Commentaire;
        this.payement =ok;
    }
       
    public int getPatientId() {
        return PatientId;
    }

    public void setPatientId(int PatientId) {
        this.PatientId = PatientId;
    }

    public int getMedecinId() {
        return MedecinId;
    }

    public void setMedecinId(int MedecinId) {
        this.MedecinId = MedecinId;
    }

    public Reservation() {
     }

    // Getters et Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

   
    public Date getDateRdv() {
        return DateRdv;
    }

    public void setDateRdv(Date dateRdv) {
        this.DateRdv = dateRdv;
    }

    @Override
    public String toString() {
        return "Reservation{" + "id=" + id + ", PatientId=" + PatientId + ", MedecinId=" + MedecinId + ", DateRdv=" + DateRdv + ", Commentaire=" + Commentaire + ", payement=" + payement + '}';
    }

 
  
    
}

