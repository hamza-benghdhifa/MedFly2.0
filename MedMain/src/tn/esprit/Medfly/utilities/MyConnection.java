/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.Medfly.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;



public class MyConnection {
    String url = "jdbc:mysql://localhost:3308/medfly";
    String login = "root";
    String password = "";
    
    private Connection cnx ;
    private static MyConnection instance;
    
    
    private MyConnection(){
        try {
            cnx =   DriverManager.getConnection(url, login, password);
            System.out.println("connected to DB");
        } catch (SQLException ex) {
            Logger.getLogger(MyConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
      public static MyConnection getInstance() {
        if (instance == null) {
            instance = new MyConnection();
        }

        return instance;
    }
    public Connection getcnx(){
            return cnx;
    }
}

    
