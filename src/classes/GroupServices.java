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
public class GroupServices {
    
    //attributes
    private static GroupServices instance = new GroupServices();
    private List<GroupMember> groupList = new ArrayList<>();
    
    //constructors
    private GroupServices(){
        
        ObjectDBServices odb = new ObjectDBServices();
        EntityManager em = odb.openConnection();
        em.getTransaction().begin();
        TypedQuery<GroupMember> query = em.createQuery("SELECT gMem FROM GroupMember gMem", GroupMember.class);
        groupList = query.getResultList();
        odb.closeConnection();
    }
    
    //methods
    public static GroupServices getInstance(){
        return instance;
    }
    public void create(String groupName) {
        boolean isValid = true;
        if (groupName.matches("[a-zA-Z0-9]+")) {
            for (int i = 0; i < groupList.size(); i++) {
                if ( (groupList.get(i).getGroupDetail().getGroupName().equals(groupName)) && 
                    (groupList.get(i).getGroupDetail().getCreaterAccount().getId() == SmartReminder.myAccount.getId())
                    ) {
                    isValid = false;
                    break;
                }
            }
            if (isValid) {
                GroupDetail newGroup = new GroupDetail(groupName, SmartReminder.myAccount);
                ObjectDBServices odb = new ObjectDBServices();
                EntityManager em = odb.openConnection();
                em.getTransaction().begin();
                em.persist(newGroup);
                em.getTransaction().commit();
                odb.closeConnection();
                
                GroupMember newMember = new GroupMember(SmartReminder.myAccount, newGroup);
                odb = new ObjectDBServices();
                em = odb.openConnection();
                em.getTransaction().begin();
                em.persist(newMember);
                em.getTransaction().commit();
                odb.closeConnection();
                groupList.add(newMember);
            }
        }        
    }
    public void delete(String groupName, String createrUserName) {
        if (createrUserName.equals(SmartReminder.myAccount.getUserName())) {
            GroupDetail group = new GroupDetail();
            for (int i = 0; i < groupList.size(); i++) {
                if ( (groupList.get(i).getGroupDetail().getGroupName().equals(groupName)) && 
                    (groupList.get(i).getGroupDetail().getCreaterAccount().getUserName().equals(createrUserName)) 
                ) {
                    group = groupList.get(i).getGroupDetail();
                }
            }            
            ArrayList<GroupMember> members = getMembers(groupName, createrUserName);
            for (int i = 0; i < members.size(); i++) {
                ObjectDBServices odb = new ObjectDBServices();
                EntityManager em = odb.openConnection();
                GroupMember gMem = em.find(GroupMember.class, members.get(i).getId());
                em.getTransaction().begin();
                em.remove(gMem);
                em.getTransaction().commit();
                odb.closeConnection();
                groupList.remove(members.get(i));
            }
            ObjectDBServices odb = new ObjectDBServices();
            EntityManager em = odb.openConnection();
            GroupDetail g = em.find(GroupDetail.class, group.getId());
            em.getTransaction().begin();
            em.remove(g);
            em.getTransaction().commit();
            odb.closeConnection();
        }
    }
    public void addMember (String username, String groupName, String createrUserName) {
        boolean isValid = true;
        GroupDetail group = null;
        ArrayList<GroupMember> gList = getMyGroupList();
        for (int i = 0; i < gList.size(); i++) {
            if ( (gList.get(i).getGroupDetail().getGroupName().equals(groupName)) && 
                (gList.get(i).getGroupDetail().getCreaterAccount().getUserName().equals(createrUserName)) 
            ) 
            {
                group = gList.get(i).getGroupDetail();
                ArrayList<GroupMember> memberList = getMembers(group.getGroupName(), group.getCreaterAccount().getUserName());
                for (int j = 0; j < memberList.size(); j++) {
                    if (memberList.get(j).getUserAccount().getUserName().equals(username)) {
                        isValid = false;
                        break;
                    }
                }
            }
        }
        if (isValid) {
            UserAccount account = null;
            ArrayList<Friend> fndList = SmartReminder.myFriendServices.getFriendList();
            for (int i = 0; i < fndList.size(); i++) {
                if (fndList.get(i).getFriendAccount().getUserName().equals(username)) {
                    account = fndList.get(i).getFriendAccount();
                }
            }
            GroupMember newMember = new GroupMember(account, group);
            ObjectDBServices odb = new ObjectDBServices();
            EntityManager em = odb.openConnection();
            em.getTransaction().begin();
            em.persist(newMember);
            em.getTransaction().commit();
            odb.closeConnection();
            groupList.add(newMember);
        }
    }
    public void deleteMember (String userName, String groupName, String createrUserName) {
        if (createrUserName.equals(SmartReminder.myAccount.getUserName())) {
            ArrayList<GroupMember> members = getMembers(groupName, createrUserName);
            for (int i = 0; i < members.size(); i++) {
                if (userName.equals(members.get(i).getUserAccount().getUserName())) {
                    ObjectDBServices odb = new ObjectDBServices();
                    EntityManager em = odb.openConnection();
                    GroupMember gMem = em.find(GroupMember.class, members.get(i).getId());
                    em.getTransaction().begin();
                    em.remove(gMem);
                    em.getTransaction().commit();
                    odb.closeConnection();
                    groupList.remove(members.get(i));
                }
            }
        }
    }
    public ArrayList getMyGroupList() {
        ArrayList<GroupMember> list = new ArrayList<>();
        for (int i = 0; i < groupList.size(); i++) {
            if (groupList.get(i).getUserAccount().getId() == SmartReminder.myAccount.getId()) {
                list.add(groupList.get(i));
            }
        }
        return list;
    }    
    public ArrayList getMembers(String groupName, String createrUserName) {
        ArrayList<GroupMember> members = new ArrayList<>();
        for (int i = 0; i < groupList.size(); i++) {
            if ( (groupList.get(i).getGroupDetail().getGroupName().equals(groupName)) && 
                (groupList.get(i).getGroupDetail().getCreaterAccount().getUserName().equals(createrUserName)) 
            ) 
            {
                members.add(groupList.get(i));
            }
            
        }
        return members;
    }
    public void update(){
        ObjectDBServices odb = new ObjectDBServices();
        EntityManager em = odb.openConnection();
        em.getTransaction().begin();
        TypedQuery<GroupMember> query = em.createQuery("SELECT gMem FROM GroupMember gMem", GroupMember.class);
        groupList = query.getResultList();
        odb.closeConnection();
    }
}
