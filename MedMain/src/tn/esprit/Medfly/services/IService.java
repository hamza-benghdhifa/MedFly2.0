package tn.esprit.Medfly.services;

import java.util.List;
/**
 *
 * @author hamza
 * @param <E>
 */
public interface IService<E> {
   


    void ajouter(E var1);

    void modifier(int id,E var1);

    void supprimer(int var1);

     E getOne(int id );

    List<E> getAll();
}
    

