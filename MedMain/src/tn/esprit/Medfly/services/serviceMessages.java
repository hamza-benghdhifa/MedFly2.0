/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.Medfly.services;
import tn.esprit.Medfly.entities.messages;
import tn.esprit.Medfly.utilities.MahConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static java.sql.Statement.RETURN_GENERATED_KEYS;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;


/**
 *
 * @author khalf
 */
public class serviceMessages {
public void add(messages m) {
    Connection connection = MahConnection.getMyCnx().getCnx();
    int generatedId = -1;
    String query = "INSERT INTO messages (ObjetMessage, ContenuMessage, DateEnvoi, EmailDestinataire, Email) VALUES (?, ?, ?, ?, ?)";

    try (PreparedStatement pst = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
        pst.setString(1, m.getObjetMessage());
        pst.setString(2, m.getContenuMessage());
        pst.setTimestamp(3, m.getDateEnvoi());
        pst.setString(4, m.getEmailDestinataire());
        pst.setString(5, m.getEmail()); // Add the new Email attribute here

        pst.executeUpdate();

        ResultSet generatedKeys = pst.getGeneratedKeys();
        if (generatedKeys.next()) {
            generatedId = generatedKeys.getInt(1); // Get the generated ID
        }
        System.out.println("Message added successfully to the database");

        // Set the generated ID in the messages object
        m.setId(generatedId);
    } catch (SQLException ex) {
        System.err.println("Error adding message to the database: " + ex.getMessage());
    }
}

public List<messages> getFirst10MessagesByEmailDestinataire(String EmailDestinataire) {
    List<messages> messagesList = new ArrayList<>();
    String query = "SELECT * FROM messages WHERE EmailDestinataire=? ORDER BY DateEnvoi DESC LIMIT 10";
    System.out.println("SQL Query: " + query);
    try (PreparedStatement pst = MahConnection.getMyCnx().getCnx().prepareStatement(query)) {
        pst.setString(1, EmailDestinataire);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            messages message = new messages();
            message.setId(rs.getInt("id"));
            message.setObjetMessage(rs.getString("ObjetMessage"));
            message.setContenuMessage(rs.getString("ContenuMessage"));
            message.setDateEnvoi(rs.getTimestamp("DateEnvoi"));
            message.setEmailDestinataire(rs.getString("EmailDestinataire"));
            message.setEmail(rs.getString("Email")); // Add the new Email attribute here
            messagesList.add(message);
        }
        System.out.println("Number of messages retrieved: " + messagesList.size());
    } catch (Exception e) {
        System.out.println("Error retrieving messages: " + e);
    }
    return messagesList;
}
public List<messages> getMessagesByEmail(String emailToSearch, String userEmail) {
    List<messages> messagesList = new ArrayList<>();
    String query = "SELECT * FROM messages WHERE EmailDestinataire = ? AND Email = ? ORDER BY DateEnvoi DESC LIMIT 10";
    System.out.println("SQL Query: " + query);
    try (PreparedStatement pst = MahConnection.getMyCnx().getCnx().prepareStatement(query)) {
        pst.setString(1, userEmail);
        pst.setString(2, emailToSearch);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            messages message = new messages();
            message.setId(rs.getInt("id"));
            message.setObjetMessage(rs.getString("ObjetMessage"));
            message.setContenuMessage(rs.getString("ContenuMessage"));
            message.setDateEnvoi(rs.getTimestamp("DateEnvoi"));
            message.setEmailDestinataire(rs.getString("EmailDestinataire"));
            message.setEmail(rs.getString("Email"));
            messagesList.add(message);
        }
        System.out.println("Number of messages retrieved: " + messagesList.size());
    } catch (Exception e) {
        System.out.println("Error retrieving messages: " + e);
    }
    return messagesList;
}
}
