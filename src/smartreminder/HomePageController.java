/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartreminder;

import classes.*;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.time.Month;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.shape.Rectangle;
import java.util.Date;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author 58010622
 */

public class HomePageController implements Initializable {
    
    //attributes
    @FXML
    private ComboBox year_list;
    @FXML
    private ComboBox month_list;
    @FXML
    private GridPane calendarPane;
    @FXML
    private ListView<String> friend_list;
    @FXML
    private TextField idFriend_field;
    @FXML
    private Label nameDelete_label;
    @FXML
    private Pane main_pane;
    @FXML
    private Pane deleteFriend_pane;
    @FXML
    private Pane friendListPane;
    @FXML
    private Menu username_menu;
    @FXML
    private ListView<String> searchedUser_list;
    @FXML
    private ListView<String> friendRequest_list;
    @FXML
    private JFXButton currentYearDisplay;
    @FXML
    private JFXButton currentMonthDisplay;
    @FXML
    private JFXButton currentDateDisplay;
    @FXML
    private ImageView picLogout;
    
    private String selectedFriendName;
    private ObservableList<String> yearList = FXCollections.observableArrayList();
    private ObservableList<String> monthList = FXCollections.observableArrayList("January","February","March","April","May","June","July","August","September","October","November","December");
    
    /**
     * Initializes the controller class.
     */
    //methods
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        SmartReminder.tmpUsername_menu = username_menu;
        SmartReminder.tmpMain_pane = main_pane;
        SmartReminder.tmpMonth_list = month_list;
        SmartReminder.tmpYear_list = year_list;
        SmartReminder.tmpCalendarPane = calendarPane;
        SmartReminder.tmpCurrentDateDisplay = currentDateDisplay;
        SmartReminder.tmpCurrentMonthDisplay = currentMonthDisplay;
        SmartReminder.tmpCurrentYearDisplay = currentYearDisplay;
        Image img = new Image("file:src/Image/logout.png");
        picLogout.setImage(img);
        for (int i = 1900; i < 2100; i++) {
           yearList.add(i + "");
        }
        SmartReminder.tmpMonth_list.setItems(monthList);
        SmartReminder.tmpYear_list.setItems(yearList); 
        friend_list.setItems(SmartReminder.myFriends);
        searchedUser_list.setItems(SmartReminder.searchedUsernames);
        friendRequest_list.setItems(SmartReminder.myFriendRequests);
    }
    @FXML
    private void selectYear(ActionEvent event) {
        SmartReminder.selectedYear = Integer.parseInt(SmartReminder.tmpYear_list.getValue().toString());
        SmartReminder.calendarGenerator.generateCalendar();
    }
    @FXML
    private void selectMonth(ActionEvent event) {
        String monthName = SmartReminder.tmpMonth_list.getValue().toString();
        SmartReminder.selectedMonth = Month.valueOf(monthName.toUpperCase()).getValue() - 1;
        SmartReminder.calendarGenerator.generateCalendar();
    }
    @FXML
    private void signOut(ActionEvent event) {
        friendListPane.setVisible(true);
        SmartReminder.pageController.next("WelcomePage");
    }
    @FXML
    private void addFriend(ActionEvent event) {
        if (searchedUser_list.getSelectionModel().getSelectedItem() != null) {
            ArrayList<UserAccount> userList = SmartReminder.myFriendServices.searchUser(searchedUser_list.getSelectionModel().getSelectedItem());
            Friend fnd = new Friend(SmartReminder.myAccount, userList.get(0));
            SmartReminder.myFriendServices.add(fnd);
            updateFriendList();
        }
    }
    @FXML
    private void selectFriend(MouseEvent event) {
        if (!friend_list.getSelectionModel().isEmpty()) {
            if(event.getClickCount() > 1){
                selectedFriendName = friend_list.getSelectionModel().getSelectedItem();
                nameDelete_label.setText(selectedFriendName); 
                deleteFriend_pane.setVisible(true);
            }
        }
    }
    @FXML
    private void deleteFriend(ActionEvent event) {
        ArrayList<UserAccount> userList = SmartReminder.myFriendServices.searchUser(selectedFriendName);
        ArrayList<Friend> friendList = SmartReminder.myFriendServices.getFriendList();
        for (int i = 0; i < friendList.size(); i++) {
            if (friendList.get(i).getFriendAccount().getId() == userList.get(0).getId()) {
                SmartReminder.myFriendServices.delete(friendList.get(i));
                SmartReminder.myFriends.remove(selectedFriendName);
                updateFriendList();
                deleteFriend_pane.setVisible(false);
            }
        }
    }
    @FXML
    private void cancleDeleteFriend(ActionEvent event) {
        deleteFriend_pane.setVisible(false);
    }
    @FXML
    private void viewProfile(ActionEvent event) {
        friendListPane.setVisible(false);
        SmartReminder.pageController.next("ProfilePage");
    }
    @FXML
    private void viewPersonalInfo(ActionEvent event) {
        friendListPane.setVisible(true);
        SmartReminder.pageController.next("HomePage");
    }
    @FXML
    private void viewGroupInfo(ActionEvent event) {
        friendListPane.setVisible(false);
        SmartReminder.pageController.next("GroupPage");
    }
    @FXML
    private void selectDate(MouseEvent event) {
        Rectangle rect = (Rectangle)event.getSource();
        String[] str = rect.getId().split("dayBlock");
        SmartReminder.selectedDate = Integer.parseInt(SmartReminder.dayLabel[Integer.parseInt(str[1])-1].getText());
        if(!rect.getFill().toString().equals("0xf0ffffff"))
        {
            Date current = new Date(SmartReminder.currentYear-1900, SmartReminder.currentMonth, SmartReminder.currentDate);
            Date selected = new Date(SmartReminder.selectedYear-1900, SmartReminder.selectedMonth, SmartReminder.selectedDate);
            if(selected.after(current) || selected.equals(current)) {
                SmartReminder.beginTime = new Date(SmartReminder.selectedYear-1900, SmartReminder.selectedMonth, SmartReminder.selectedDate, 0, 0);
                SmartReminder.finishTime = new Date(SmartReminder.selectedYear-1900, SmartReminder.selectedMonth, SmartReminder.selectedDate, 0, 0);
                if (SmartReminder.isPersonalCalendar) {
                    SmartReminder.pageController.next("SchedulePage");
                }
                else {
                    if (SmartReminder.tmpGroupDetail == null) {
                        
                    }
                    else {
                        SmartReminder.pageController.next("SchedulePage");
                    }
                }
            }
        }
    }
    @FXML
    private void searchUser(ActionEvent event) {
        SmartReminder.searchedUsernames.clear();
        ArrayList<UserAccount> userList = SmartReminder.myFriendServices.searchUser(idFriend_field.getText());
        for (int i = 0; i < userList.size(); i++) {
            SmartReminder.searchedUsernames.add(userList.get(i).getUserName());
        }
    }
    @FXML
    private void acceptFriendRequest(ActionEvent event) {
        if (friendRequest_list.getSelectionModel().getSelectedItem() != null) {
            ArrayList<UserAccount> userList = SmartReminder.myFriendServices.searchUser(friendRequest_list.getSelectionModel().getSelectedItem());
            Friend fnd = new Friend(SmartReminder.myAccount, userList.get(0));
            SmartReminder.myFriendServices.add(fnd);
            updateFriendList();
            updateFriendRequest();
        }
    }
    @FXML
    private void declineFriendRequest(ActionEvent event) {
        
    }
    @FXML
    private void refresh(ActionEvent event) {
        SmartReminder.myScheduleServices.update();
        SmartReminder.groupScheduleServices.update();
        SmartReminder.myFriendServices.update();
        SmartReminder.myGroupServices.update();
        SmartReminder.myUserAccountServices.update();
        GroupPageController.updateGroupList();
        updateFriendRequest();
    }
    
    public static void updateFriendList() {
        SmartReminder.myFriends.clear();
        ArrayList<Friend> accountList = SmartReminder.myFriendServices.getFriendList();
        for (int i = 0; i < accountList.size(); i++) {
           SmartReminder.myFriends.add(accountList.get(i).getFriendAccount().getUserName());
        }
    }
    public static void clearSearchedUserList() {
        SmartReminder.searchedUsernames.clear();
    }
    public static void updateFriendRequest() {
        SmartReminder.myFriendRequests.clear();
        ArrayList<String> nameList = SmartReminder.myFriendServices.getFriendRequestList();
        for (int i = 0; i < nameList.size(); i++) {
            SmartReminder.myFriendRequests.add(nameList.get(i));
        }
    }
}
