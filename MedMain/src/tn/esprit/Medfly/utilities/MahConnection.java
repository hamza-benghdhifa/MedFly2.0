/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.Medfly.utilities;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author khalf
 */
public class MahConnection {
    String url ="jdbc:mysql://localhost:3306/medfly4";
    String login ="root";
    String password="";
    
    private Connection cnx;
    private static MahConnection mycnx;
    
    private MahConnection(){
        try {
            cnx=DriverManager.getConnection(url, login , password);
            System.out.println("Conncetion to DB !");
        } catch (SQLException ex) {
            Logger.getLogger(MyConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public Connection getCnx(){
        return cnx;
    }
    public static MahConnection getMyCnx(){
        if(mycnx == null)
        mycnx = new MahConnection();
        return mycnx;
    }
}
