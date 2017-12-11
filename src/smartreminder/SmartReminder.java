/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartreminder;

import classes.*;
import java.util.Date;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.swing.Timer;

/**
 *
 * @author 58010622
 */
public class SmartReminder extends Application {
    
    //attributes
    //pages
    public static Parent loginPage;
    public static Parent fillIdPassword;
    public static Parent signUpPage;
    public static Parent errorLogin;
    public static Parent homePage;
    public static Parent profilePage;
    public static Parent groupPage;
    public static Parent addSchedulePage;
    public static Parent timeTablePage;
    public static Parent addingSchedulePage;
    
    public static Stage primaryStage;
    public static Pane primaryPane;
    public static Pane secondaryPane;
    
    //services
    public static PersonalCalendar myCalendar = PersonalCalendar.getInstance();
    public static GroupCalendar groupCalendar = GroupCalendar.getInstance();
    public static FriendServices myFriendServices = FriendServices.getInstance();
    public static GroupServices myGroupServices = GroupServices.getInstance();
    public static UserAccountServices myUserAccountServices = UserAccountServices.getInstance();
    public static Sound alarmSound = new Sound("alarm.wav");
    public static PlaySoundTimer timer = new PlaySoundTimer();
    public static UserAccount myAccount;
    public static Date beginTime;
    public static Date finishTime;

    //FillIdPasswordController
    public static TextField tmpId_field; //use in FillIdPasswordController, HomePageController
    public static PasswordField tmpPassword_field; //use in FillIdPasswordController, HomePageController
    
    
    
    //methods
    @Override
    public void start(Stage stage) throws Exception {
        //myCalendar.showSchedule();
        primaryStage = stage;
        loginPage = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
        fillIdPassword = FXMLLoader.load(getClass().getResource("FillIdPassword.fxml"));
        signUpPage = FXMLLoader.load(getClass().getResource("SignUp.fxml"));
        errorLogin = FXMLLoader.load(getClass().getResource("ErrorLogin.fxml"));
        homePage = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
        profilePage = FXMLLoader.load(getClass().getResource("ProfilePage.fxml"));
        groupPage = FXMLLoader.load(getClass().getResource("GroupPage.fxml"));
        addSchedulePage = FXMLLoader.load(getClass().getResource("AddSchedule.fxml"));
        timeTablePage = FXMLLoader.load(getClass().getResource("TimeTable.fxml"));
        addingSchedulePage = FXMLLoader.load(getClass().getResource("AddingSchedule.fxml"));

        Scene scene = new Scene(loginPage);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Smart Reminder");
        primaryStage.show();
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
}
