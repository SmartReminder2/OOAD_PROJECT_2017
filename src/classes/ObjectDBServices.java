/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import javax.persistence.*;

/**
 *
 * @author kan
 */
public class ObjectDBServices {
    
    //attributes
    private EntityManagerFactory emf;
    private EntityManager em;
    
    //constructor
    public ObjectDBServices() {}
    
    //methods
    public EntityManager openConnection(){
        emf = Persistence.createEntityManagerFactory("./db/database.odb");
        //emf = Persistence.createEntityManagerFactory("objectdb://161.246.6.212:80/database.odb;user=admin;password=admin");
        em = emf.createEntityManager();
        return em;
    }
    public void closeConnection(){
        em.close();
        emf.close();
    }
}
