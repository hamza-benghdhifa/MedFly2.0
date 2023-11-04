/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.Medfly.test;

import tn.esprit.Medfly.services.TwilloService;
import tn.esprit.Medfly.services.ReclamationService;
import com.mysql.jdbc.Connection;
//import edu.medfly.entity.Reclamation;
//import edu.medfly.services.ReclamationService;
//import edu.medfly.services.TwilloService;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import tn.esprit.Medfly.entities.Reclamation;
import tn.esprit.Medfly.entities.Reponse_rec;

import java.sql.Date;
import javafx.scene.control.Alert;



/**
 *
 * @author syrine
 */
public class Pidev1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws NoSuchAlgorithmException {
        // TODO code application logic here
Reclamation r = new Reclamation();
        ReclamationService s = new ReclamationService();
        s.ajouterReclamation(r);
 }
      
        
    }

   
    

