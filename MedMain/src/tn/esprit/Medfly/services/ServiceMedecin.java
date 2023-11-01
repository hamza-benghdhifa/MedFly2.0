/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.Medfly.services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import tn.esprit.Medfly.entities.Medecin;
import tn.esprit.Medfly.utilities.MyConnection;
import java.sql.PreparedStatement;


/**
 *
 * @author hamza
 */
public  class ServiceMedecin implements IService<Medecin>{

 
        Connection cnx = MyConnection.getInstance().getcnx();
       
        
        public ServiceMedecin(){
        } 
        
     
public void ajouter(Medecin medecin) {
    try {
        String req = "INSERT INTO medecin (Nom, Prenom, Specialite, Pays, DateGrad, NumberGrad,Email,MotDePasse) VALUES (?, ?, ?, ?, ?, ?,?,?)";
        PreparedStatement preparedStatement = cnx.prepareStatement(req);
        preparedStatement.setString(1, medecin.getName());
        preparedStatement.setString(2, medecin.getPrenom());
        preparedStatement.setString(3, medecin.getSpecialite());
        preparedStatement.setString(4, medecin.getPays());
        preparedStatement.setDate(5, new java.sql.Date(medecin.getGrad_date().getTime()));
        preparedStatement.setInt(6, medecin.getGrad_num());
        preparedStatement.setString(7, medecin.getEmail());
        preparedStatement.setString(8, medecin.getMot_passe());
       
        
        
        preparedStatement.executeUpdate();
        
        System.out.println("Medecin added successfully to the database.");
    } catch (SQLException ex) {
        System.out.println("Error adding Medecin: " + ex.getMessage());
    }
}
     
         

  
        
       public void modifier(int id ,Medecin medecin) {
          try {
        String req = "UPDATE medecin SET Nom=?, Prenom=?, Specialite=?, Pays=?, DateGrad=?, NumberGrad=? WHERE id=?";
        PreparedStatement preparedStatement = this.cnx.prepareStatement(req);
        preparedStatement.setString(1, medecin.getName());
        preparedStatement.setString(2, medecin.getPrenom());
        preparedStatement.setString(3, medecin.getSpecialite());
        preparedStatement.setString(4, medecin.getPays());
        preparedStatement.setDate(5, new java.sql.Date(medecin.getGrad_date().getTime()));
        preparedStatement.setInt(6, medecin.getGrad_num());
        preparedStatement.setInt(7, medecin.getId());

        preparedStatement.executeUpdate();
        
        System.out.println("Medecin with ID " + medecin.getId() + " updated successfully.");
    } catch (SQLException ex) {
        System.out.println("Error updating Medecin: " + ex.getMessage());
    }
        
    }
        public void supprimer(int id) {
            try {
        String req = "DELETE FROM medecin WHERE id=?";
        PreparedStatement preparedStatement = cnx.prepareStatement(req);
        preparedStatement.setInt(1, id);
        
        preparedStatement.executeUpdate();
    } catch (SQLException var4) {
        System.out.println(var4.getMessage());
    }
    }
        
  public  List<Medecin> getAll() {
    List<Medecin> medecins = new ArrayList<>();
    String req = "SELECT * FROM medecin";

    try {
        Statement stm = cnx.createStatement();
        ResultSet rs = stm.executeQuery(req);

        while (rs.next()) {
            Medecin retrievedMedecin = new Medecin();
             retrievedMedecin.setName(rs.getString("Id"));
            retrievedMedecin.setName(rs.getString("Nom"));
            retrievedMedecin.setPrenom(rs.getString("Prenom"));
            retrievedMedecin.setSpecialite(rs.getString("Specialite"));
            retrievedMedecin.setPays(rs.getString("Pays"));
            retrievedMedecin.setGrad_date(rs.getDate("DateGrad"));
            retrievedMedecin.setGrad_num(rs.getInt("NumberGrad"));

            
            medecins.add(retrievedMedecin);
        }

        rs.close();
    } catch (SQLException var4) {
        System.out.println(var4.getMessage());
    }

    return medecins;
}
    public Medecin getOne(int id) {
        Medecin medecin = null;
        try {
            
            String selectQuery = "SELECT * FROM medecin WHERE id=?";
            
            
            PreparedStatement preparedStatement = cnx.prepareStatement(selectQuery);
            
         
            preparedStatement.setInt(1, id);
            
           
            ResultSet resultSet = preparedStatement.executeQuery();
            
         
            if (resultSet.next()) {
                
                medecin = new Medecin(
                    resultSet.getInt("Id"),
                    resultSet.getString("Nom"),
                    resultSet.getString("Prenom"),
                    resultSet.getString("Specialite"),
                    resultSet.getString("Pays"),
                    resultSet.getDate("DateGrad"),
                    resultSet.getInt("NumberGrad")
                );
            }
            
            // Close the ResultSet and PreparedStatement
            resultSet.close();
            preparedStatement.close();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return medecin;
    }

public ArrayList<Medecin> AdvancedSearchByName(String name) {
    String req = "SELECT * FROM medecin WHERE Nom LIKE ?";

    ArrayList<Medecin> medecins = new ArrayList<>();
    try {
        PreparedStatement preparedStatement = cnx.prepareStatement(req);
        preparedStatement.setString(1, "%" + name + "%");

        ResultSet rs = preparedStatement.executeQuery();

        while (rs.next()) {
            Medecin medecin = new Medecin();
            medecin.setName(rs.getString("Nom"));
            medecin.setPrenom(rs.getString("Prenom"));
            medecin.setSpecialite(rs.getString("Specialite"));
            medecin.setPays(rs.getString("Pays"));
            medecin.setGrad_date(rs.getDate("DateGrad"));
            medecin.setGrad_num(rs.getInt("NumberGrad"));
            medecins.add(medecin);
        }

        rs.close();
        preparedStatement.close();
    } catch (SQLException ex) {
        System.out.println("Erreur lors de la recherche avancée : " + ex.getMessage());
    }

    return medecins;
}












 }




    







