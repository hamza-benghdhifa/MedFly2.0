/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Categories.Avis_Et_Comment;
import Categories.Categorie;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import tools.DbConnect;

/**
 *
 * @author Dell
 */
public class gestion_avis implements avis_management <Avis_Et_Comment>  {
Connection cnx;
    
    public gestion_avis(){
    this.cnx= DbConnect.getInstance().getConnection();
}
    @Override
    public void ajouter(Avis_Et_Comment a) {
       try {
            String req = "INSERT INTO `avisetcommentaires` ( `Nom_Service`, `Id_Patient`, `Note`, `Commentaire`, `Date_Avis`) VALUES ('" +a.getNom_Service() + "','" + a.getId_Patient()+ "','"+a.getNote()+ "','" + a.getCommentaire() + "','" + a.getDate_Avis()+ "')";
            
            Statement stm = cnx.createStatement();  
            stm.executeUpdate(req);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }  
    }

    @Override
    public void modifier(int id, Avis_Et_Comment a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void supprimer(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Avis_Et_Comment getOne(int id, Avis_Et_Comment a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Avis_Et_Comment> getAll(Avis_Et_Comment a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean chercher(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
