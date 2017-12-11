/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import smartreminder.*;

/**
 *
 * @author kan
 */
public class UserAccountServices {
    
    //attributes
    private static UserAccountServices instance = new UserAccountServices();
    private List<UserAccount> userAccounts;
    
    //constructors
    private UserAccountServices() {
        ObjectDBServices odb = new ObjectDBServices();
        EntityManager em = odb.openConnection();
        em.getTransaction().begin();
        TypedQuery<UserAccount> query = em.createQuery("SELECT user FROM UserAccount user", UserAccount.class);
        userAccounts = query.getResultList();
        odb.closeConnection();
    }
    
    //methods
    public static UserAccountServices getInstance(){
        return instance;
    }
    public boolean createUserAccounts(UserAccount userAccount, String confirmPassword){
        if(!userAccount.getUserName().equals("") && !userAccount.getPassword().equals("") && !userAccount.getPhoneNumber().equals("") && confirmPassword.equals(userAccount.getPassword())) {
            
            ObjectDBServices odb = new ObjectDBServices();
            EntityManager em = odb.openConnection();
            em.getTransaction().begin();
            em.persist(userAccount);
            em.getTransaction().commit();
            odb.closeConnection();
            
            userAccounts.add(userAccount);
            return true;
        }
        else {
            
            return false;
        }
    }
    public List getUserAccounts(){
        return userAccounts;
    }
    public void update(){
        ObjectDBServices odb = new ObjectDBServices();
        EntityManager em = odb.openConnection();
        em.getTransaction().begin();
        TypedQuery<UserAccount> query = em.createQuery("SELECT user FROM UserAccount user", UserAccount.class);
        userAccounts = query.getResultList();
        odb.closeConnection();
    }
}
