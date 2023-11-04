/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import java.util.List;
import Categories.Categorie;
import static java.awt.PageAttributes.MediaType.C;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import tools.DbConnect;
/**
 *
 * @author Dell
 */
public class gestion_service implements service_management <Categorie> {
    Connection cnx;
    
    public gestion_service(){
    this.cnx= DbConnect.getInstance().getConnection();
}
    @Override
    public void ajouter(Categorie c) {
/*try {
            String req = "INSERT INTO `gestion_categories` ( `NomCategorie`, `Description`, `Tarification`, `Ref_Services`, `Disponibilite`, `Date`) VALUES ('" +c.getNomCategorie() + "','" + c.getDescription()+ "','"+c.getTarification()+ "','" + c.getRef_Services() + "','" + c.getDisponibilite() + "','" + c.getDate()+ "')";
            
            Statement stm = cnx.createStatement();  
            stm.executeUpdate(req);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }   */ 
    
    try {
        String req = "INSERT INTO gestion_categories (NomCategorie, Description, Tarification, Ref_Services, Disponibilite, Date) VALUES (?, ?, ?, ?, ?, ?)";
        
        PreparedStatement pstmt = cnx.prepareStatement(req);
        pstmt.setString(1, c.getNomCategorie());
        pstmt.setString(2, c.getDescription());
        pstmt.setInt(3, c.getTarification());
        pstmt.setString(4, c.getRef_Services());
        pstmt.setString(5, c.getDisponibilite());
        pstmt.setString(6,   c.getDate());
        
        pstmt.executeUpdate();
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}

    
    
   

    @Override
    public void modifier(int id ,Categorie c) {
try {
        // Préparez la requête SQL pour mettre à jour une catégorie en fonction de l'ID
        String req = "UPDATE gestion_categories SET NomCategorie=?, Description=?, Tarification=?, Ref_Services=?, Disponibilite=?, Date=? WHERE id=?";
        
        // Créez une PreparedStatement pour éviter les injections SQL et passer les paramètres de manière sécurisée
        PreparedStatement preparedStatement = cnx.prepareStatement(req);
        preparedStatement.setString(1, c.getNomCategorie());
        preparedStatement.setString(2, c.getDescription());
        preparedStatement.setInt(3, c.getTarification());
        preparedStatement.setString(4, c.getRef_Services());
        preparedStatement.setString(5, c.getDisponibilite());
        preparedStatement.setString(6,   c.getDate());
        preparedStatement.setInt(7, id); // L'ID de la catégorie à mettre à jour

        // Exécutez la requête
        preparedStatement.executeUpdate();
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }    }

    @Override
    public void supprimer(int id ) {
  try {
        String req = "DELETE FROM gestion_categories WHERE id=?";
        PreparedStatement preparedStatement = cnx.prepareStatement(req);
        preparedStatement.setInt(1, id); 
        preparedStatement.executeUpdate();
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }    }

    @Override
    public Categorie getOne(int id,Categorie c) {
         try {
                String req = "SELECT* FROM gestion_categories WHERE id=?";
 PreparedStatement preparedStatement = cnx.prepareStatement(req);
        preparedStatement.setInt(1, id); 
        preparedStatement.executeUpdate();
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
         return c;
    }

    @Override
    public List<Categorie> getAll(Categorie c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public Boolean chercher(int id){
        String sql = "SELECT COUNT(*) FROM gestion_categories WHERE id = ?";
        try {
            PreparedStatement statement = cnx.prepareStatement(sql);
            statement.setInt(1, id);
              try (ResultSet resultSet = statement.executeQuery()) {
            resultSet.next();
            int count = resultSet.getInt(1);
            
            return count > 0; // Si count est supérieur à zéro, l'ID existe, donc on retourne true, sinon false.
        }
        } catch (SQLException ex) {
            return false;
        }

        
    }
}
