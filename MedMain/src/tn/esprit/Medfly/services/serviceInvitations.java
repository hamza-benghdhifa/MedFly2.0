/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.Medfly.services;
import tn.esprit.Medfly.entities.invitations;
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
public class serviceInvitations {
        public void addInvitation(invitations invitation) {
        Connection connection = MahConnection.getMyCnx().getCnx();
        String query = "INSERT INTO invitations (EmailDestinataireinv, Emailinv, Status) VALUES (?, ?, ?)";
        try (PreparedStatement pst = connection.prepareStatement(query)) {
            pst.setString(1, invitation.getEmailDestinataireinv());
            pst.setString(2, invitation.getEmailinv());
            pst.setString(3, invitation.getStatus());
            pst.executeUpdate();
            System.out.println("Invitation added to the database.");
        } catch (SQLException ex) {
            System.err.println("Error adding invitation to the database: " + ex.getMessage());
        }
    }
public List<invitations> getFirst10InvitationsForUser(String userEmail) {
    List<invitations> invitationsList = new ArrayList<>();
    String query = "SELECT * FROM invitations WHERE EmailDestinataireinv = ? ORDER BY idinvi DESC LIMIT 10";
    try (PreparedStatement pst = MahConnection.getMyCnx().getCnx().prepareStatement(query)) {
        pst.setString(1, userEmail);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            invitations invitation = new invitations();
            invitation.setIdinvi(rs.getInt("idinvi"));
            invitation.setEmailDestinataireinv(rs.getString("EmailDestinataireinv"));
            invitation.setEmailinv(rs.getString("Emailinv"));
            invitation.setStatus(rs.getString("Status"));
            invitationsList.add(invitation);
        }
        System.out.println("Number of invitations retrieved: " + invitationsList.size());
    } catch (SQLException ex) {
        System.err.println("Error retrieving invitations: " + ex.getMessage());
    }
    return invitationsList;
}
    public List<invitations> getInvitationsByEmail(String emailToSearch, String userEmail) {
    List<invitations> invitationsList = new ArrayList<>();
    String query = "SELECT Emailinv, Status FROM invitations WHERE EmailDestinataireinv = ? AND Emailinv = ? ORDER BY idinvi DESC LIMIT 10";
    System.out.println("SQL Query: " + query);
    try (PreparedStatement pst = MahConnection.getMyCnx().getCnx().prepareStatement(query)) {
        pst.setString(1, userEmail);
        pst.setString(2, emailToSearch);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            invitations invitation = new invitations();
            invitation.setEmailinv(rs.getString("Emailinv"));
            invitation.setStatus(rs.getString("Status"));
            invitationsList.add(invitation);
        }
        System.out.println("Number of invitations retrieved: " + invitationsList.size());
    } catch (Exception e) {
        System.out.println("Error retrieving invitations: " + e);
    }
    return invitationsList;
}
        public void updateInvitationStatus(int invitationId, String newStatus) {
        Connection connection = MahConnection.getMyCnx().getCnx();
        String query = "UPDATE invitations SET Status = ? WHERE idinvi = ?";

        try (PreparedStatement pst = connection.prepareStatement(query)) {
            pst.setString(1, newStatus);
            pst.setInt(2, invitationId);
            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Invitation status updated to " + newStatus);
            } else {
                System.out.println("No rows were updated. Invitation ID not found.");
            }
        } catch (SQLException ex) {
            System.err.println("Error updating invitation status: " + ex.getMessage());
        }
    }
public List<invitations> getApprovedInvitations(String emailDestinataire, String email) {
    List<invitations> approvedInvitations = new ArrayList<>();
    String query = "SELECT * FROM invitations WHERE EmailDestinataireinv = ? AND Emailinv = ? AND Status = 'accepted'";
    
    try (PreparedStatement pst = MahConnection.getMyCnx().getCnx().prepareStatement(query)) {
        pst.setString(1, emailDestinataire);
        pst.setString(2, email);
        ResultSet rs = pst.executeQuery();
        
        while (rs.next()) {
            invitations invitation = new invitations();
            invitation.setIdinvi(rs.getInt("idinvi"));
            invitation.setEmailDestinataireinv(rs.getString("EmailDestinataireinv"));
            invitation.setEmailinv(rs.getString("Emailinv"));
            invitation.setStatus(rs.getString("Status"));
            approvedInvitations.add(invitation);
        }
        
        System.out.println("Number of approved invitations retrieved: " + approvedInvitations.size());
    } catch (SQLException ex) {
        System.err.println("Error retrieving approved invitations: " + ex.getMessage());
    }
    
    return approvedInvitations;
}
        
        
}
    

