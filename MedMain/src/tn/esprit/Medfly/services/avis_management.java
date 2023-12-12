/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import java.util.List;

/**
 *
 * @author Dell
 */
public interface avis_management<A> {
    public void ajouter(A a);
    public void modifier(int id,A a);
    public void supprimer(int id);
    public A getOne(int id,A a);
    public List<A> getAll(A a);
    public Boolean chercher(int id);
    
    
    
}
