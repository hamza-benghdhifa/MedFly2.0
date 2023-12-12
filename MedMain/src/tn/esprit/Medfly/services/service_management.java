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
public interface service_management <C> {
    public void ajouter(C c);
    public void modifier(int id,C c);
    public void supprimer(int id);
    public C getOne(int id,C c);
    public List<C> getAll(C c);
    public Boolean chercher(int id);
    
}
