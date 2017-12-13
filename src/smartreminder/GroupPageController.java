/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartreminder;

import classes.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author 58010622
 */
public class GroupPageController implements Initializable {
    
    //attributes
    @FXML
    private TextField groupName;
    @FXML
    private Pane deleteGroup_pane;
    @FXML
    private Pane deleteFriend_pane;
    @FXML
    private ListView<String> friend_list;
    @FXML
    private ListView<String> group_list;
    @FXML
    private ListView<String> friendInGroup_list;
    @FXML
    private Label groupNameDelete_label;
    @FXML
    private Label FndInGpNameDelete_label;
    
    /**
     * Initializes the controller class.
     */
    //methods
    @Override
    public void initialize(URL url, ResourceBundle rb) { 
        SmartReminder.tmpFriendInGroup_list = friendInGroup_list;
        SmartReminder.tmpFriend_list = friend_list;
        SmartReminder.tmpGroup_list = group_list;
        SmartReminder.tmpFriend_list.setItems(SmartReminder.myFriends);
        SmartReminder.tmpFriendInGroup_list.setItems(SmartReminder.friendsInGroup);
        SmartReminder.tmpGroup_list.setItems(SmartReminder.myGroups);
    }   
    @FXML
    private void createGroup(ActionEvent event) {
        SmartReminder.myGroupServices.create(groupName.getText());
        updateGroupList();
    }
    @FXML
    private void deleteGroup(ActionEvent event) {
        SmartReminder.myGroupServices.delete(SmartReminder.selectedGroup, SmartReminder.tmpCreaterUsername);
        updateGroupList();
        updateFriendInList();
        deleteGroup_pane.setVisible(false);
    }
    @FXML
    private void cancleDeleteGroup(ActionEvent event) {
        deleteGroup_pane.setVisible(false);
    }
    @FXML
    private void deleteFriend(ActionEvent event) {
        SmartReminder.myGroupServices.deleteMember(SmartReminder.selectedFriendInGroup, SmartReminder.selectedGroup, SmartReminder.tmpCreaterUsername);
        updateFriendInList();
        deleteFriend_pane.setVisible(false);
    }
    @FXML
    private void cancleDeleteFriend(ActionEvent event) {
        deleteFriend_pane.setVisible(false);
    }
    @FXML
    private void selectFriendInGroup(MouseEvent event) {
        int index=0;
        // Double Click
        if (!SmartReminder.tmpFriendInGroup_list.getSelectionModel().isEmpty()) {
            SmartReminder.selectedFriendInGroup = SmartReminder.tmpFriendInGroup_list.getSelectionModel().getSelectedItem();
        }
        if(event.getClickCount() > 1 && !SmartReminder.tmpFriendInGroup_list.getSelectionModel().isEmpty()){
            FndInGpNameDelete_label.setText(SmartReminder.selectedFriendInGroup);
            deleteFriend_pane.setVisible(true);
        }
    }
    @FXML
    private void selectGroup(MouseEvent event) {
        if (!SmartReminder.tmpGroup_list.getSelectionModel().isEmpty()) {
            String[] str = SmartReminder.tmpGroup_list.getSelectionModel().getSelectedItem().split(" by:");
            SmartReminder.selectedGroup = str[0];
            SmartReminder.tmpCreaterUsername = str[str.length-1];
            ArrayList<Friend> fndList = SmartReminder.myFriendServices.getFriendList();
            UserAccount account = null;
            for (int i = 0; i < fndList.size(); i++) {
                if (fndList.get(i).getFriendAccount().getUserName().equals(SmartReminder.tmpCreaterUsername)) {
                    account = fndList.get(i).getFriendAccount();
                }
            }
            ArrayList<GroupMember> groups = SmartReminder.myGroupServices.getMyGroupList();
            for (int i = 0; i < groups.size(); i++) {
                if (groups.get(i).getGroupDetail().getGroupName().equals(SmartReminder.selectedGroup) && 
                    groups.get(i).getGroupDetail().getCreaterAccount().getUserName().equals(SmartReminder.tmpCreaterUsername)) 
                {
                    SmartReminder.tmpGroupDetail = groups.get(i).getGroupDetail();
                }
            }
            if (SmartReminder.tmpGroupDetail != null) {
                updateFriendInList();
            }
        }
        if(event.getClickCount() > 1){
             // if Double click Delete Group
            if (!SmartReminder.tmpGroup_list.getSelectionModel().isEmpty()) {
                groupNameDelete_label.setText(SmartReminder.selectedGroup);
                deleteGroup_pane.setVisible(true);
            }
        }
    }
    @FXML
    private void selectFriend(MouseEvent event) {
        SmartReminder.selectedFriend = SmartReminder.tmpFriend_list.getSelectionModel().getSelectedItem();
        if(event.getClickCount() > 1){
            if (!SmartReminder.tmpFriend_list.getSelectionModel().isEmpty() && SmartReminder.tmpGroup_list.getSelectionModel().getSelectedItem() != null) {
                SmartReminder.myGroupServices.addMember(SmartReminder.selectedFriend, SmartReminder.selectedGroup, SmartReminder.tmpCreaterUsername);
                if (SmartReminder.tmpGroupDetail != null) {
                    updateFriendInList();
                }
            }
        }
    }
    public static void updateGroupList() {
        SmartReminder.myGroups.clear();
        ArrayList<GroupMember> list = SmartReminder.myGroupServices.getMyGroupList();
        for (int i = 0; i < list.size(); i++) {
            SmartReminder.myGroups.add(list.get(i).getGroupDetail().getGroupName() + " by:" + list.get(i).getGroupDetail().getCreaterAccount().getUserName());
        }
    }
    public static void updateFriendInList() {
        SmartReminder.friendsInGroup.clear();
        ArrayList<GroupMember> list = SmartReminder.myGroupServices.getMembers(SmartReminder.selectedGroup, SmartReminder.tmpCreaterUsername);
        for (int i = 0; i < list.size(); i++) {
            if (!list.get(i).getUserAccount().getUserName().equals(SmartReminder.myAccount.getUserName())) {
                SmartReminder.friendsInGroup.add(list.get(i).getUserAccount().getUserName());
            }
        }
    }
}
