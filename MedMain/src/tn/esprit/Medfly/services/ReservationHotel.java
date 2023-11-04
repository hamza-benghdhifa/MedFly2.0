/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.Medfly.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import tn.esprit.Medfly.entities.UserHotel;
import tn.esprit.Medfly.utilities.MyConnection;

/**
 *
 * @author 21653
 */
public class ReservationHotel implements IService<UserHotel> {
        Connection cnx;
 public ReservationHotel() {
        this.cnx= MyConnection.getInstance().getcnx();
    }

    @Override
    public void ajouter(UserHotel userHotel) {
 try {
            String req = "INSERT INTO userhotel (user_nom, user_prenom, numpassport, nom_hotel, pays, chambre_reservee, facture_hotel) VALUES (?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement preparedStatement = cnx.prepareStatement(req);
            preparedStatement.setString(1, userHotel.getUsernom());
            preparedStatement.setString(2, userHotel.getUserprenom());
            preparedStatement.setInt(3, userHotel.getNum_passport());
            preparedStatement.setString(4, userHotel.getNom_hotel());
            preparedStatement.setString(5, userHotel.getPays());
            preparedStatement.setInt(6, userHotel.getNbre_chambre());
            preparedStatement.setFloat(7, userHotel.getFacture_hotel());

            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }    

    @Override
    public void modifier(int id, UserHotel t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void supprimer(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public UserHotel getOne(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<UserHotel> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ArrayList<UserHotel> Advancedsearch(String d, String n, String k) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
