/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;
import Categories.Categorie;
import Service.gestion_service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/**
 *
 * @author Dell
 */
public class DbConnect {
    private Connection cnx;
    private static DbConnect instance;
    
    private String url = "jdbc:mysql://localhost:3306/projet_pi";
    private String user = "root";
    private String password = "";
    
    public DbConnect(){
        try {
            cnx = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to DB successfully !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public static DbConnect getInstance(){
        if(instance == null){
            instance = new DbConnect();
        }
        return instance;
    }
    
    public Connection getConnection(){
        return this.cnx;
        
    }
    
        public  ObservableList<Categorie> getDatausers(){
        //Connection conn = DbConnect();
        ObservableList<Categorie> list = FXCollections.observableArrayList();
        try {
           // DbConnect con = this.instance;
            //Connection con =getConnection();
            PreparedStatement ps = cnx.prepareStatement("select * from gestion_categories");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){   
                list.add(new Categorie(rs.getString("NomCategorie"), rs.getInt("Tarification"), rs.getString("Description"),rs.getString("Ref_Services"), rs.getString("Disponibilite"), rs.getString("Date")));               
            }
        } catch (SQLException ex ) {
        }
        return list;
    }
}
