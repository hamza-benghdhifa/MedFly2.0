/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.Medfly.services;

import tn.esprit.Medfly.utilities.DB;
/*import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;*/
 import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import tn.esprit.Medfly.entities.Reservation;
/*import com.twilio.type.PhoneNumber;*/

/**
 *
 * @author 21655
 */
public class ReservationPayement {
       private Connection conn; // Initialisez cette connexion dans le constructeur

    public ReservationPayement() {
        conn = DB.getInstance().getConnection();
    }
     /* public void updatepayemnt(Reservation reservation) {
       String updateQuery = "UPDATE reservation SET payement=? WHERE Id = ?";
         try   {
             PreparedStatement statement = conn.prepareStatement(updateQuery);
            
            statement.setBoolean(1, reservation.getPayement());

            statement.setInt(2, reservation.getId());
            int rowsUpdated = statement.executeUpdate();
              String ACCOUNT_SID = "AC0deb9e5eb708b44a85ca0da67f293fb5";
                String AUTH_TOKEN = "6b12c343ed5625f4209e517f91d5e3af";

     
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
String fromPhoneNumber = "+12296290970";
String toPhoneNumber = "+21655343510";
String messageBody = "Reservation a été payée.";

Message message = Message.creator(
    new PhoneNumber(toPhoneNumber), // Laissez-le tel quel
    new PhoneNumber(fromPhoneNumber), // Laissez-le tel quel
    messageBody
).create();


        System.out.println("Message SID: " + message.getSid());
            if (rowsUpdated == 0) {
                // Aucune réservation correspondante trouvée
                throw new SQLException("La réservation n'a pas été trouvée dans la base de données.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Gérer l'exception selon vos besoins (par exemple, générer une exception personnalisée)
        }
         }*/
}
