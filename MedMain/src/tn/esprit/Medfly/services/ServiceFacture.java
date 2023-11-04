/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.Medfly.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import tn.esprit.Medfly.entities.Facture;
import tn.esprit.Medfly.utilities.MyConnection;

/**
 *
 * @author 21653
 */
public class ServiceFacture implements IService<Facture> {
    Connection cnx;

    public ServiceFacture() {
        this.cnx = MyConnection.getInstance().getcnx();
    }

    @Override
    public void ajouter(Facture facture) {
       try {
            String req = "INSERT INTO facture (num_passport, nom, prenom, destination, nom_hotel, nom_compagnie, montant) VALUES (?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement preparedStatement = cnx.prepareStatement(req);
            preparedStatement.setInt(1, facture.getPassport());
            preparedStatement.setString(2, facture.getNom());
            preparedStatement.setString(3, facture.getPrenom());
            preparedStatement.setString(4, facture.getDestiation());
            preparedStatement.setString(5, facture.getNom_hotel());
            preparedStatement.setString(6, facture.getNom_compagnie());
            preparedStatement.setFloat(7, facture.getMontant());

            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
       
    }
    }
    @Override
    public void modifier(int id, Facture t) {
 try {
            String req = "UPDATE facture SET nom=?, prenom=?, destination=?, nom_hotel=?, nom_compagnie=?, montant=? WHERE num_passport=?";
        
            PreparedStatement preparedStatement = cnx.prepareStatement(req);
            preparedStatement.setString(1,t.getNom());
            preparedStatement.setString(2, t.getPrenom());
            preparedStatement.setString(3, t.getDestiation());
            preparedStatement.setString(4, t.getNom_hotel());
            preparedStatement.setString(5, t.getNom_compagnie());
            preparedStatement.setFloat(6, t.getMontant());
            preparedStatement.setInt(7, t.getPassport());

            int rowsAffected = preparedStatement.executeUpdate();
        
            if (rowsAffected > 0) {
                System.out.println("Enregistrement modifié avec succès.");
            } else {
                System.out.println("La modification de l'enregistrement a échoué.");
            }
        
            preparedStatement.close();
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la modification de l'enregistrement : " + ex.getMessage());
        }
    }    

    @Override
    public void supprimer(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

public Facture getOne(int passport) {
    String query = "SELECT * FROM facture WHERE num_passport = ?";
    Facture facture = null;

    try (PreparedStatement preparedStatement = cnx.prepareStatement(query)) {
        preparedStatement.setInt(1, passport);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            // Remplissez les propriétés de l'objet Facture avec les données de la base de données
            int numPassport = resultSet.getInt("num_passport");
            String nom = resultSet.getString("nom");
            String prenom = resultSet.getString("prenom");
            String destination = resultSet.getString("destination");
            String nomHotel = resultSet.getString("nom_hotel");
            String nomCompagnie = resultSet.getString("nom_compagnie");
            float montantTotal = resultSet.getFloat("montant");

            // Créez un nouvel objet Facture avec les données récupérées
            facture = new Facture(numPassport, nom, prenom, destination, nomHotel, nomCompagnie, montantTotal);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }

    return facture;

}
    @Override
    public List<Facture> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ArrayList<Facture> Advancedsearch(String d, String n, String k) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}