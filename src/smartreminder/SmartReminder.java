/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartreminder;

import classes.*;
import com.jfoenix.controls.JFXButton;
import java.util.Date;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author 58010622
 */
public class SmartReminder extends Application {
    
    //attributes
    //pages
    public static Stage primaryStage;
    public static Parent welcomePage;
    public static Parent loginPage;
    public static Parent signUpPage;
    public static Parent loginFailedPage;
    public static Parent homePage;
    public static Parent profilePage;
    public static Parent groupPage;
    public static Parent schedulePage;
    public static Parent timeTablePage;
    public static Parent scheduleMakerPage;
    
    //services
    public static PersonalScheduleServices myScheduleServices = PersonalScheduleServices.getInstance();
    public static GroupScheduleServices groupScheduleServices = GroupScheduleServices.getInstance();
    public static FriendServices myFriendServices = FriendServices.getInstance();
    public static GroupServices myGroupServices = GroupServices.getInstance();
    public static UserAccountServices myUserAccountServices = UserAccountServices.getInstance();
    
    public static Sound alarmSound = new Sound("alarm.wav");
    public static PlaySoundTimer timer = new PlaySoundTimer();
    public static UserAccount myAccount;
    public static Date beginTime;
    public static Date finishTime;
    public static PageController pageController = PageController.getInstance();
    public static CalendarGenerator calendarGenerator = CalendarGenerator.getInstance();
    
    //WelcomePageController
    public static Pane tmpLogin_pane;

    //LoginPageController
    public static TextField tmpId_field;
    public static PasswordField tmpPassword_field;
    
    //HomePageController
    public static ComboBox tmpMonth_list;
    public static ComboBox tmpYear_list;
    public static Pane tmpMain_pane;
    public static ObservableList<String> myFriends = FXCollections.observableArrayList ();
    public static ObservableList<String> searchedUsernames = FXCollections.observableArrayList();
    public static ObservableList<String> myFriendRequests = FXCollections.observableArrayList();
    public static Menu tmpUsername_menu;
    public static boolean isPersonalCalendar = true;
    public static Circle tmpImageProfile;
    public static Label tmpUsername_label;
    public static int selectedDate;
    public static int selectedMonth;
    public static int selectedYear;
    public static int currentDate;
    public static int currentMonth;
    public static int currentYear;
    public static String monthString;
    public static GridPane tmpCalendarPane;
    public static Label[] dayLabel = new Label[42];
    public static Rectangle[] dayBlock = new Rectangle[42];
    public static JFXButton tmpCurrentYearDisplay;
    public static JFXButton tmpCurrentMonthDisplay;
    public static JFXButton tmpCurrentDateDisplay;
    
    //GroupPageController
    public static ListView<String> tmpFriend_list;
    public static ListView<String> tmpFriendInGroup_list;
    public static ListView<String> tmpGroup_list;
    public static ObservableList<String> myGroups = FXCollections.observableArrayList ();
    public static ObservableList<String> friendsInGroup = FXCollections.observableArrayList ();
    public static String selectedGroup;
    public static String selectedFriend;
    public static String selectedFriendInGroup;
    public static GroupDetail tmpGroupDetail;
    public static String tmpCreaterUsername;
    
    //SchedulePageController
    public static AnchorPane tmpTimeTable_pane;
    public static Text tmpHeadLabel_add;
    
    //ScheduleMakerPageController
    public static Text tmpHeadLabel_adding;
    public static String selectedStartTime;
    public static String selectedFinishTime;
    public static String selectedPreAlarm;
    public static long tmpId;
    public static TextField tmpScheduleName;
    public static TextField tmpDetail;
    public static ListView<String> tmpStartTime;
    public static ListView<String> tmpFinishTime;
    public static ComboBox<String> tmpPreAlarmList;
    public static CheckBox tmpCheckAlarm;
    public static CheckBox tmpCheckRepeat;
    public static final ObservableList<String> STARTTIMES = FXCollections.observableArrayList("00.00","00.30","01.00","01.30","02.00","02.30","03.00","03.30","04.00","04.30","05.00","05.30","06.00","06.30","07.00","07.30","08.00","08.30","09.00","09.30","10.00","10.30","11.00","11.30","12.00","12.30","13.00","13.30","14.00","14.30","15.00","15.30","16.00","16.30","17.00","17.30","18.00","18.30","19.00","19.30","20.00","20.30","21.00","21.30","22.00","22.30","23.00","23.30");
    public static final ObservableList<String> FINISHTIMES = FXCollections.observableArrayList("00.30","01.00","01.30","02.00","02.30","03.00","03.30","04.00","04.30","05.00","05.30","06.00","06.30","07.00","07.30","08.00","08.30","09.00","09.30","10.00","10.30","11.00","11.30","12.00","12.30","13.00","13.30","14.00","14.30","15.00","15.30","16.00","16.30","17.00","17.30","18.00","18.30","19.00","19.30","20.00","20.30","21.00","21.30","22.00","22.30","23.00","23.30","00.00");
    public static final ObservableList<String> PREALARMS = FXCollections.observableArrayList("0","5","10","15","20","25","30");
    
    //methods
    @Override
    public void start(Stage stage) throws Exception {
        primaryStage = stage;
        setPages();
        pageController.next("WelcomePage");
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            public void run() {
                // Do what you want when the application is stopping
                timer.stop();
            }
        }));
        
    }
    private void setPages() throws Exception {
        welcomePage = FXMLLoader.load(getClass().getResource("WelcomePage.fxml"));
        loginPage = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
        signUpPage = FXMLLoader.load(getClass().getResource("SignUpPage.fxml"));
        loginFailedPage = FXMLLoader.load(getClass().getResource("LoginFailedPage.fxml"));
        homePage = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
        profilePage = FXMLLoader.load(getClass().getResource("ProfilePage.fxml"));
        groupPage = FXMLLoader.load(getClass().getResource("GroupPage.fxml"));
        schedulePage = FXMLLoader.load(getClass().getResource("SchedulePage.fxml"));
        timeTablePage = FXMLLoader.load(getClass().getResource("TimeTablePage.fxml"));
        scheduleMakerPage = FXMLLoader.load(getClass().getResource("ScheduleMakerPage.fxml"));
    }
}
