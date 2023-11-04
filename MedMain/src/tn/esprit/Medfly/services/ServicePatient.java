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
import tn.esprit.Medfly.entities.Patient;

import java.sql.PreparedStatement;
import java.util.Date;
import tn.esprit.Medfly.utilities.MyConnection;

public  class ServicePatient implements IService<Patient> {
    Connection cnx = MyConnection.getInstance().getcnx();

    @Override
    public void ajouter(Patient patient) {
        try {
            String req = "INSERT INTO patient ( Nom, Prenom, DateNai, NumAssu, Maladie,EmailP,Password) VALUES ( ?, ?, ?, ?, ?,?,?)";

            PreparedStatement preparedStatement = cnx.prepareStatement(req);
            preparedStatement.setString(1, patient.getName());
            preparedStatement.setString(2, patient.getPrenom());
            preparedStatement.setDate(3, new java.sql.Date(patient.getDate_naissance().getTime()));
            preparedStatement.setInt(4, patient.getNum_assurance());
            preparedStatement.setString(5, patient.getMaladie());
            preparedStatement.setString(6, patient.getEmail());
            preparedStatement.setString(7, patient.getPassword());

            preparedStatement.executeUpdate();
        } catch (SQLException var4) {
            System.out.println(var4.getMessage());
        }
    }

    public void modifier(int id,Patient patient) {
        try {
            String req = "UPDATE patient SET Nom=?, Prenom=?, DateNai=?, NumAssu=?, Maladie=? WHERE id=?";

            PreparedStatement preparedStatement = cnx.prepareStatement(req);
            preparedStatement.setString(1, patient.getName());
            preparedStatement.setString(2, patient.getPrenom());
            preparedStatement.setDate(3, new java.sql.Date(patient.getDate_naissance().getTime()));
            preparedStatement.setInt(4, patient.getNum_assurance());
            preparedStatement.setString(5, patient.getMaladie());
            preparedStatement.setInt(6, patient.getId());

            preparedStatement.executeUpdate();

            System.out.println("Patient with ID " + patient.getId() + " updated successfully.");
        } catch (SQLException var4) {
            System.out.println("Error updating Patient: " + var4.getMessage());
        }
    }

    public void supprimer(int id) {
        try {
            String req = "DELETE FROM patient WHERE id=?";
            PreparedStatement preparedStatement = cnx.prepareStatement(req);
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException var4) {
            System.out.println(var4.getMessage());
        }
    }

    public List<Patient> getAll() {
        List<Patient> patients = new ArrayList<>();
        String req = "SELECT * FROM patient";

        try {
            Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(req);

            while (rs.next()) {
                Patient retrievedPatient = new Patient(
                    rs.getInt("Id"),
                    rs.getString("Nom"),
                    rs.getString("Prenom"),
                    rs.getDate("DateNai"),
                    rs.getInt("NumAssu"),
                    rs.getString("Maladie")
                );

                patients.add(retrievedPatient);
            }

            rs.close();
        } catch (SQLException var4) {
            System.out.println(var4.getMessage());
        }

        return patients;
    }
    
        public Patient getOne(int id) {
        Patient patient = null;
        try {
            
            String selectQuery = "SELECT * FROM patient WHERE id=?";
            
            
            PreparedStatement preparedStatement = cnx.prepareStatement(selectQuery);
            
         
            preparedStatement.setInt(1, id);
            
           
            ResultSet resultSet = preparedStatement.executeQuery();
            
         
            if (resultSet.next()) {
                
                patient = new Patient(
                    resultSet.getInt("Id"),
                    resultSet.getString("Nom"),
                    resultSet.getString("prenom"),
                    resultSet.getDate("DateNai"),
                    resultSet.getInt("NumAssu"),
                    resultSet.getString("Maladie")    
                );
            }/* private int id ;
    private String name;
    private String prenom;
    private Date date_naissance;
    private int num_assurance;
    private String maladie;
    */
            
            // Close the ResultSet and PreparedStatement
            resultSet.close();
            preparedStatement.close();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return patient;
    }
    
}
