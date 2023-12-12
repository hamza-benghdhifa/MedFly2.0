/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tn.esprit.Medfly.gui;
import besttrip.tools.DB;
/*import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;*/
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
 import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.SQLException;
public class GeneratePDFReport {
 
    
     public static void main(String[] args) {
         /*        Connection conn; // Initialisez cette connexion dans le constructeur
             conn = DB.getInstance().getConnection();

        try {
            // Établir une connexion à la base de données
              Statement statement =conn.createStatement();
 
  // Créer un document PDF
            Document document = new Document(PageSize.A4);
            PdfWriter.getInstance(document, new FileOutputStream("rapport_reservation.pdf"));
            document.open();

            // Titre du rapport
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            String currentDate = dateFormat.format(new Date());
            Paragraph title = new Paragraph("Statistiques Pour Toutes les Réservations", new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.TIMES_ROMAN, 14));
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            Paragraph dateTime = new Paragraph("Date et heure : " + currentDate, new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.TIMES_ROMAN, 12));
            dateTime.setAlignment(Element.ALIGN_CENTER);
            document.add(dateTime);

            // Tableau des statistiques
            PdfPTable table = new PdfPTable(2);
            table.setWidthPercentage(100);
            table.setSpacingBefore(20f);
            table.setSpacingAfter(20f);

            // Extraire les données de la base de données
            ResultSet totalReservationsResult = statement.executeQuery("SELECT COUNT(*) AS TotalReservations FROM reservation");
            totalReservationsResult.next();
            int totalReservations = totalReservationsResult.getInt("TotalReservations");

            ResultSet reservationsPayeesResult = statement.executeQuery("SELECT COUNT(*) AS ReservationsPayees FROM reservation WHERE payement = true");
            reservationsPayeesResult.next();
            int reservationsPayees = reservationsPayeesResult.getInt("ReservationsPayees");

            ResultSet reservationsNonPayeesResult = statement.executeQuery("SELECT COUNT(*) AS ReservationsNonPayees FROM reservation WHERE payement = false");
            reservationsNonPayeesResult.next();
            int reservationsNonPayees = reservationsNonPayeesResult.getInt("ReservationsNonPayees");

            ResultSet specialitePopulaireResult = statement.executeQuery(
                    "SELECT m.Specialite, COUNT(r.Id) AS SpecialiteCount " +
                            "FROM medecin m " +
                            "INNER JOIN reservation r ON m.Id = r.MedecinId " +
                            "GROUP BY m.Specialite " +
                            "ORDER BY SpecialiteCount DESC " +
                            "LIMIT 1"
            );
            specialitePopulaireResult.next();
            String specialitePopulaire = specialitePopulaireResult.getString("Specialite");

            ResultSet reservationsParMoisResult = statement.executeQuery(
                    "SELECT DATE_FORMAT(DateRdv, '%Y-%m') AS Mois, COUNT(*) AS ReservationsMois " +
                            "FROM reservation " +
                            "GROUP BY Mois"
            );

            // Remplir le tableau
            table.addCell(new Phrase("Nombre total de réservations"));
            table.addCell(new Phrase(Integer.toString(totalReservations)));
            table.addCell(new Phrase("Nombre de réservations payées"));
            table.addCell(new Phrase(Integer.toString(reservationsPayees)));
            table.addCell(new Phrase("Nombre de réservations non payées"));
            table.addCell(new Phrase(Integer.toString(reservationsNonPayees)));
            table.addCell(new Phrase("Spécialité la plus populaire"));
            table.addCell(new Phrase(specialitePopulaire));
            table.addCell(new Phrase("Nombre de réservations par mois"));
            StringBuilder moisInfo = new StringBuilder();
            while (reservationsParMoisResult.next()) {
                String mois = reservationsParMoisResult.getString("Mois");
                int reservationsMois = reservationsParMoisResult.getInt("ReservationsMois");
                moisInfo.append(mois).append(" : ").append(reservationsMois).append(" réservations\n");
            }
            table.addCell(new Phrase(moisInfo.toString()));

            document.add(table);
             // Fermer le document PDF
            document.close();
// Ouvrir le fichier PDF avec l'application par défaut
    try {
        File pdfFile = new File("rapport_reservation.pdf");
        if (pdfFile.exists()) {
            Desktop.getDesktop().open(pdfFile);
        } else {
            System.out.println("Le fichier PDF n'a pas été trouvé : " +"rapport_reservation.pdf");
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
           

            // Fermer la connexion à la base de données
         } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
}
}