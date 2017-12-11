/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author kan
 */

@Entity
public class Friend implements Serializable {
    
    //attributes
    private static final long serialVersionUID = 1L;
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    private UserAccount myAccount;
    private UserAccount friendAccount;
    
    //constructors
    public Friend() {}
    public Friend(UserAccount myAccount, UserAccount friendAccount) {
        this.myAccount = myAccount;
        this.friendAccount = friendAccount;
    }
    
    //methods
    public long getId() {
        return id;
    }
    public UserAccount getMyAccount() {
        return myAccount;
    }
    public UserAccount getFriendAccount() {
        return friendAccount;
    }
    
}
