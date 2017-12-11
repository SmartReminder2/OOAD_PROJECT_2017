/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import smartreminder.*;

/**
 *
 * @author kan
 */
public class FriendServices {
    
    //attributes
    private static FriendServices instance = new FriendServices();
    private List<Friend> friendList = new ArrayList<>();
    
    //constructors
    private FriendServices(){
        
        ObjectDBServices odb = new ObjectDBServices();
        EntityManager em = odb.openConnection();
        //EntityManagerFactory emf = Persistence.createEntityManagerFactory("./db/database.odb");
        //EntityManager em = emf.createEntityManager();
 
        // Store 1000 Point objects in the database:
        em.getTransaction().begin();
        
        TypedQuery<Friend> query = em.createQuery("SELECT fnd FROM Friend fnd", Friend.class);
        friendList = query.getResultList();
        
        odb.closeConnection();
    }
    
    //methods
    public static FriendServices getInstance(){
        return instance;
    }
    public void add(Friend friend) {
        boolean isValid = true;
        
        for (int i = 0; i < friendList.size(); i++) {
            if ( (friendList.get(i).getMyAccount().getId() == friend.getMyAccount().getId()) && (friendList.get(i).getFriendAccount().getId() == friend.getFriendAccount().getId()) ) {
                isValid = false;
                break;
            }
        }
        
        if (isValid) {
            ObjectDBServices odb = new ObjectDBServices();
            EntityManager em = odb.openConnection();
            //EntityManagerFactory emf = Persistence.createEntityManagerFactory("./db/database.odb");
            //EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            em.persist(friend);
            em.getTransaction().commit();
            // Close the database connection:
            odb.closeConnection();

            friendList.add(friend);
            System.out.println("Adding friend success!!");
        }
        else {
            System.out.println("This friend, \"" + friend.getFriendAccount().getUserName() + "\"," + " is already exist");
        }
    }
    public void delete(Friend friend) {
        ObjectDBServices odb = new ObjectDBServices();
        EntityManager em = odb.openConnection();
        //EntityManagerFactory emf = Persistence.createEntityManagerFactory("./db/database.odb");
        //EntityManager em = emf.createEntityManager();
        Friend fnd = em.find(Friend.class, friend.getId());
 
        em.getTransaction().begin();
        em.remove(fnd);
        em.getTransaction().commit();
        // Close the database connection:
        odb.closeConnection();
        friendList.remove(friend);
    }
    public ArrayList searchUser(String userName) {
        
        ArrayList<UserAccount> returnList = new ArrayList<>();
        
        ObjectDBServices odb = new ObjectDBServices();
        EntityManager em = odb.openConnection();
        //EntityManagerFactory emf = Persistence.createEntityManagerFactory("./db/database.odb");
        //EntityManager em = emf.createEntityManager();
 
        // Store 1000 Point objects in the database:
        em.getTransaction().begin();
        
        TypedQuery<UserAccount> query = em.createQuery("SELECT user FROM UserAccount user", UserAccount.class);
        List<UserAccount> userList = query.getResultList();
        
        odb.closeConnection();
        
        for (int i = 0; i < userList.size(); i++) {
            if(userList.get(i).getUserName().equals(userName)) {
                returnList.add(userList.get(i));
            }
        }
        return returnList;
    }
    public ArrayList getFriendList() {
        ArrayList<Friend> list = new ArrayList<>();
        for (int i = 0; i < friendList.size(); i++) {
            if (friendList.get(i).getMyAccount().getId() == SmartReminder.myAccount.getId()) {
                list.add(friendList.get(i));
            }
        }
        return list;
    }
    public ArrayList getFriendRequestList() {
        
        ArrayList<String> list = new ArrayList<>();
        ArrayList<Friend> myFndList = getFriendList();
        
        boolean check = true;
        for (int i = 0; i < friendList.size(); i++) {
            if (friendList.get(i).getFriendAccount().getId() == SmartReminder.myAccount.getId()) {
                for (int j = 0; j < myFndList.size(); j++) {
                    if (myFndList.get(j).getFriendAccount().getId() == friendList.get(i).getMyAccount().getId()) {
                        check = false;
                        break;
                    }
                }
                if (check) {
                    list.add(friendList.get(i).getMyAccount().getUserName());
                }
                
            }
            check = true;
        }
        return list;
    }
    public void update(){
        ObjectDBServices odb = new ObjectDBServices();
        EntityManager em = odb.openConnection();
        //EntityManagerFactory emf = Persistence.createEntityManagerFactory("./db/database.odb");
        //EntityManager em = emf.createEntityManager();
 
        // Store 1000 Point objects in the database:
        em.getTransaction().begin();
        
        TypedQuery<Friend> query = em.createQuery("SELECT fnd FROM Friend fnd", Friend.class);
        friendList = query.getResultList();
        
        odb.closeConnection();
    }
}
