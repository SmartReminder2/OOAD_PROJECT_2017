/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartreminder;

import classes.*;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.text.DateFormatSymbols;
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
import java.util.Calendar;
import java.util.Date;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

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
    private Circle mark_day;
    @FXML
    private Label dayLabel1;
    @FXML
    private Label dayLabel2;
    @FXML
    private Label dayLabel3;
    @FXML
    private Label dayLabel4;
    @FXML
    private Label dayLabel5;
    @FXML
    private Label dayLabel6;
    @FXML
    private Label dayLabel7;
    @FXML
    private Label dayLabel8;
    @FXML
    private Label dayLabel9;
    @FXML
    private Label dayLabel10;
    @FXML
    private Label dayLabel11;
    @FXML
    private Label dayLabel12;
    @FXML
    private Label dayLabel13;
    @FXML
    private Label dayLabel14;
    @FXML
    private Label dayLabel15;
    @FXML
    private Label dayLabel16;
    @FXML
    private Label dayLabel17;
    @FXML
    private Label dayLabel18;
    @FXML
    private Label dayLabel19;
    @FXML
    private Label dayLabel20;
    @FXML
    private Label dayLabel21;
    @FXML
    private Label dayLabel22;
    @FXML
    private Label dayLabel23;
    @FXML
    private Label dayLabel24;
    @FXML
    private Label dayLabel25;
    @FXML
    private Label dayLabel26;
    @FXML
    private Label dayLabel27;
    @FXML
    private Label dayLabel28;
    @FXML
    private Label dayLabel29;
    @FXML
    private Label dayLabel30;
    @FXML
    private Label dayLabel31;
    @FXML
    private Label dayLabel32;
    @FXML
    private Label dayLabel33;
    @FXML
    private Label dayLabel34;
    @FXML
    private Label dayLabel35;
    @FXML
    private Label dayLabel36;
    @FXML
    private Label dayLabel37;
    @FXML
    private Label dayLabel38;
    @FXML
    private Label dayLabel39;
    @FXML
    private Label dayLabel40;
    @FXML
    private Label dayLabel41;
    @FXML
    private Label dayLabel42;
    @FXML
    private Rectangle dayBlock4;
    @FXML
    private Rectangle dayBlock1;
    @FXML
    private Rectangle dayBlock2;
    @FXML
    private Rectangle dayBlock3;
    @FXML
    private Rectangle dayBlock5;
    @FXML
    private Rectangle dayBlock6;
    @FXML
    private Rectangle dayBlock7;
    @FXML
    private Rectangle dayBlock8;
    @FXML
    private Rectangle dayBlock9;
    @FXML
    private Rectangle dayBlock10;
    @FXML
    private Rectangle dayBlock11;
    @FXML
    private Rectangle dayBlock12;
    @FXML
    private Rectangle dayBlock13;
    @FXML
    private Rectangle dayBlock14;
    @FXML
    private Rectangle dayBlock15;
    @FXML
    private Rectangle dayBlock16;
    @FXML
    private Rectangle dayBlock17;
    @FXML
    private Rectangle dayBlock18;
    @FXML
    private Rectangle dayBlock19;
    @FXML
    private Rectangle dayBlock20;
    @FXML
    private Rectangle dayBlock21;
    @FXML
    private Rectangle dayBlock22;
    @FXML
    private Rectangle dayBlock23;
    @FXML
    private Rectangle dayBlock24;
    @FXML
    private Rectangle dayBlock25;
    @FXML
    private Rectangle dayBlock26;
    @FXML
    private Rectangle dayBlock27;
    @FXML
    private Rectangle dayBlock28;
    @FXML
    private Rectangle dayBlock29;
    @FXML
    private Rectangle dayBlock30;
    @FXML
    private Rectangle dayBlock31;
    @FXML
    private Rectangle dayBlock32;
    @FXML
    private Rectangle dayBlock33;
    @FXML
    private Rectangle dayBlock34;
    @FXML
    private Rectangle dayBlock35;
    @FXML
    private Rectangle dayBlock36;
    @FXML
    private Rectangle dayBlock37;
    @FXML
    private Rectangle dayBlock38;
    @FXML
    private Rectangle dayBlock39;
    @FXML
    private Rectangle dayBlock40;
    @FXML
    private Rectangle dayBlock41;
    @FXML
    private Rectangle dayBlock42;
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
    private JFXButton showCurrentYear;
    @FXML
    private JFXButton showCurrentMonth;
    @FXML
    private JFXButton showCurrenDate;
    @FXML
    private Pane selectCalPane;
    @FXML
    private ImageView picLogout;
    
    //private Label labelToday;
    //private Label label_Today;
    
    private String selectedFriendName;
    
    //private Pane selectCal_Pane;
    
    private ObservableList<String> list = FXCollections.observableArrayList();
    private ObservableList<String> list2 = FXCollections.observableArrayList("January","February","March","April","May","June","July","August","September","October","November","December");
    
    // friendList_name load form database
    public static ObservableList<String> friendList_name = FXCollections.observableArrayList (); 
    public static ObservableList<String> userNameList = FXCollections.observableArrayList();
    public static ObservableList<String> friendReqNameList = FXCollections.observableArrayList();
    //private Label month_label;
    
    private Label[] dayLabel = new Label[42];
    private Rectangle[] dayBlock = new Rectangle[42];
    int year = Calendar.getInstance().get(Calendar.YEAR);
    int month;
    int current_day;
    public static int current_month;
    public static int current_year;
    public static int selectedDay;
    String monthName;
   
    public static Menu tmpUsernameMenu;

    public static boolean isPersonal = true;
    
    String defaultMonth;
 
    /**
     * Initializes the controller class.
     */
    //methods
    @Override
    public void initialize(URL url, ResourceBundle rb) {  
       
        Image img = new Image("file:src/Image/logout.png");
        picLogout.setImage(img);
        for (int i = 1900; i < 2100; i++) {
           list.add(i + "");
        }
        tmpUsernameMenu = username_menu;
        year_list.setItems(list); 
        month_list.setItems(list2); 
        SmartReminder.secondaryPane = main_pane;
        setInit();      
        showCurrentMonth.setText(defaultMonth.substring(0, 3));
        showCurrentYear.setText(year+"");
        showCurrenDate.setText(current_day+"");
        
    
    }
    @FXML
    private void list_Action(ActionEvent event) {
        year = Integer.parseInt(year_list.getValue().toString());    
        generateCalendar(month,year);
    }
    @FXML
    private void mlist_Action(ActionEvent event) {
        
        monthName = month_list.getValue().toString();
        // month(String) --> month(Int)
        month = Month.valueOf(monthName.toUpperCase()).getValue();
        generateCalendar(--month,year);
    }
    @FXML
    private void signOut(ActionEvent event) {
        //setInit();
        SmartReminder.myAccount = new UserAccount();
        friendList_name.clear();
        friendReqNameList.clear();
        friend_list.setItems(friendList_name);
        friendRequest_list.setItems(friendReqNameList);
        SmartReminder.tmpId_field.setText("");
        SmartReminder.tmpPassword_field.setText("");
        SmartReminder.secondaryPane.getChildren().clear();
        friendListPane.setVisible(true);
        SmartReminder.alarmSound.stopSound();
        SmartReminder.primaryStage.getScene().setRoot(SmartReminder.loginPage);
    }
    @FXML
    private void addFriend(ActionEvent event) {
        ArrayList<UserAccount> userList = SmartReminder.myFriendServices.searchUser(searchedUser_list.getSelectionModel().getSelectedItem());
        Friend fnd = new Friend(SmartReminder.myAccount, userList.get(0));
        SmartReminder.myFriendServices.add(fnd);
        updateFriendList();
    }
    @FXML
    private void dubbleClickedFriendList(MouseEvent event) {
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
                friendList_name.remove(selectedFriendName);
                updateFriendList();
                deleteFriend_pane.setVisible(false);
                System.out.println(friendList.get(i).getFriendAccount().getUserName() + " is deleted");
            }
        }
    }
    @FXML
    private void cancleDelete(ActionEvent event) {
        deleteFriend_pane.setVisible(false);
    }
    @FXML
    private void profileMenu(ActionEvent event) {
        setPane(SmartReminder.profilePage); 
        ProfilePageController.setInit();     
    }
    @FXML
    private void personalMenu(ActionEvent event) {
        isPersonal = true;
        SmartReminder.secondaryPane.getChildren().clear();
        friendListPane.setVisible(true);
        
        SmartReminder.myCalendar.update();
        SmartReminder.groupCalendar.update();
        SmartReminder.myFriendServices.update();
        SmartReminder.myGroupServices.update();
        SmartReminder.myUserAccountServices.update();
        
        setInit();
    }
    @FXML
    private void groupMenu(ActionEvent event) {
        isPersonal = false;
        
        SmartReminder.myCalendar.update();
        SmartReminder.groupCalendar.update();
        SmartReminder.myFriendServices.update();
        SmartReminder.myGroupServices.update();
        SmartReminder.myUserAccountServices.update();
        
        GroupPageController.updateGroupList();
        GroupPageController.friendInList_name.clear();
        updateFriendList();
        setInit();
        GroupPageController.tmpGroupDetail = null;
        setPane(SmartReminder.groupPage);
    }
    @FXML
    private void onclickCalendar(MouseEvent event) {
     
        Rectangle rect = (Rectangle)event.getSource();
        System.out.println(rect.getId());
        String[] str = rect.getId().split("dayBlock");
        System.out.println(str[1]);
        System.out.println(dayLabel[Integer.parseInt(str[1])-1].getText());
        System.out.println(rect.getFill().toString());
        selectedDay = Integer.parseInt(dayLabel[Integer.parseInt(str[1])-1].getText());
        if(!rect.getFill().toString().equals("0xf0ffffff"))
        {
            if(selectedDay >= current_day) {
                SmartReminder.beginTime = new Date(current_year-1900, current_month, selectedDay, 0, 0);
                SmartReminder.finishTime = new Date(current_year-1900, current_month, selectedDay, 0, 0);
                System.out.println("open");
                if (isPersonal) {
                    AddScheduleController.setTimeTable();
                    SmartReminder.primaryStage.getScene().setRoot(SmartReminder.addSchedulePage);
                }
                else {
                    if (GroupPageController.tmpGroupDetail == null) {
                        System.out.println("Pleaseeeeeee select group first!!");
                    }
                    else {
                        AddScheduleController.setTimeTable();
                        SmartReminder.primaryStage.getScene().setRoot(SmartReminder.addSchedulePage);
                    }
                }
                
            }
        }
           
    }
    @FXML
    private void searchUser(ActionEvent event) {
        userNameList.clear();
        ArrayList<UserAccount> userList = SmartReminder.myFriendServices.searchUser(idFriend_field.getText());
        for (int i = 0; i < userList.size(); i++) {
            userNameList.add(userList.get(i).getUserName());
        }
    }
    @FXML
    private void acceptFriendRequest(ActionEvent event) {
        ArrayList<UserAccount> userList = SmartReminder.myFriendServices.searchUser(friendRequest_list.getSelectionModel().getSelectedItem());
        Friend fnd = new Friend(SmartReminder.myAccount, userList.get(0));
        SmartReminder.myFriendServices.add(fnd);
        updateFriendList();
        updateFriendRequest();
    }
    @FXML
    private void declineFriendRequest(ActionEvent event) {
    }
    @FXML
    private void update(ActionEvent event) {
        SmartReminder.myCalendar.update();
        SmartReminder.groupCalendar.update();
        SmartReminder.myFriendServices.update();
        SmartReminder.myGroupServices.update();
        SmartReminder.myUserAccountServices.update();
        GroupPageController.updateGroupList();
        updateFriendRequest();
        System.out.println("Refresh!!");
    }
    
    public void setInit() {  
       //Set Friend List
        friend_list.setItems(friendList_name);
        searchedUser_list.setItems(userNameList);
        friendRequest_list.setItems(friendReqNameList);
        
       //Generate Calendar
        Calendar c = Calendar.getInstance();
        month = c.get(Calendar.MONTH);
        current_day = c.get(Calendar.DATE);
        year = c.get(Calendar.YEAR);
        current_year = year;
        current_month = month;
        defaultMonth = new DateFormatSymbols().getMonths()[month];
        generateCalendar(month,year);
        month_list.setValue(defaultMonth);
        year_list.setValue(year);
        String date = "Today is "+current_day+" / "+defaultMonth+" / "+year ;
      //  label_Today.setText(date);
    }
    public void generateCalendar(int month,int year){
       
        int count_day = 1;
        int rectangle_loop = 1;
      
         //month in Calendar class start at 0(0 = january) 
         Calendar c = Calendar.getInstance();
         c.set(year, month, 1);
         int day_of_week = c.get(Calendar.DAY_OF_WEEK);
         //day of week started at 1 (1 = sunday)
        
        // Get the number of days in that month
         int daysInMonth = c.getActualMaximum(Calendar.DAY_OF_MONTH); 
         
         //Get the number of days in that previous month
         month = (month == 0) ? 11 : month-1;
         c.set(year,month,1);
         int daysInPreviousMonth = c.getActualMaximum(Calendar.DAY_OF_MONTH);
         int day = daysInPreviousMonth+2-day_of_week;
         int dayinMonth = 1;
         if(day_of_week == 1){
             day = 1;           
         }
         for (Node child : calendarPane.getChildren()) {
            if(child.getId()!=null){
                if(child.getId().contains("dayBlock")){
                    dayBlock[rectangle_loop-1] = (Rectangle) child;
                    rectangle_loop++;
                }
                if(child.getId().contains("dayLabel")){
                    dayLabel[count_day-1] = (Label) child;
                    if(count_day < day_of_week){
                        dayLabel[count_day-1].setText(String.valueOf(day++));
                        dayBlock[count_day-1].setFill(Color.AZURE);
                        day = (count_day+1==day_of_week) ? 1 : day;
                    }
                    else if(count_day < daysInMonth+day_of_week)
                    {
                        if(day == current_day &&year==current_year && month+1 == current_month)
                        {
                           mark_day.setVisible(false);
                           dayBlock[count_day-1].setFill(Color.CORAL);
                        }  
                        else
                        {   if(year!=current_year||month+1!=current_month)
                                mark_day.setVisible(false);
                            dayBlock[count_day-1].setFill(Color.WHITE);
                        }
                       dayLabel[count_day-1].setText(String.valueOf(day++));
                       day = (day-1==daysInMonth) ? 1 : day;
                    }
                    else
                    {
                       dayBlock[count_day-1].setFill(Color.AZURE);
                       dayLabel[count_day-1].setText(String.valueOf(day++));
                    }
                    count_day++;
                }
            }
        }
    }
    public void setPane(Parent page) {
        SmartReminder.secondaryPane.getChildren().clear();
        friendListPane.setVisible(false);
        SmartReminder.secondaryPane.getChildren().add(page);
    }
    public static void updateFriendList() {
        friendList_name.clear();
        ArrayList<Friend> accountList = SmartReminder.myFriendServices.getFriendList();
        for (int i = 0; i < accountList.size(); i++) {
           friendList_name.add(accountList.get(i).getFriendAccount().getUserName());
        }
    }
    public static void updateSearchedUserList() {
        userNameList.clear();
    }
    public static void updateFriendRequest() {
        friendReqNameList.clear();
        ArrayList<String> nameList = SmartReminder.myFriendServices.getFriendRequestList();
        for (int i = 0; i < nameList.size(); i++) {
            friendReqNameList.add(nameList.get(i));
        }
    }
}
