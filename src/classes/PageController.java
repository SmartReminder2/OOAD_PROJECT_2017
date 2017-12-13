/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import smartreminder.*;

/**
 *
 * @author kan
 */
public class PageController {
    
    //attributes
    private static PageController instance = new PageController();
    private String currentPage;
    
    //constructors
    private PageController() {
        currentPage = "";
    }
    
    //methods
    public static PageController getInstance(){
        return instance;
    }
    public void next(String element) {
        if (element.equalsIgnoreCase("WelcomePage")) {
            setWelcomePage();
            goToWelcomePage();
            if( currentPage.equalsIgnoreCase("HomePage") 
                || currentPage.equalsIgnoreCase("ProfilePage") 
                || currentPage.equalsIgnoreCase("GroupPage")) 
            {
                currentPage = "LoginPage";
            }
            else {
                currentPage = "WelcomePage";
            }
        }
        else if (element.equalsIgnoreCase("LoginPage")) {
            setLoginPage();
            goToLoginPage();
            currentPage = "LoginPage";
        }
        else if (element.equalsIgnoreCase("SignUpPage")) {
            setSignUpPage();
            goToSignUpPage();
            currentPage = "SignUpPage";
        }
        else if (element.equalsIgnoreCase("LoginFailedPage")) {
            setLoginFailedPage();
            goToLoginFailedPage();
            currentPage = "LoginFailedPage";
        }
        else if (element.equalsIgnoreCase("HomePage")) {
            setHomePage();
            goToHomePage();
            currentPage = "HomePage";
        }
        else if (element.equalsIgnoreCase("ProfilePage")) {
            setProfilePage();
            goToProfilePage();
            currentPage = "ProfilePage";
        }
        else if (element.equalsIgnoreCase("GroupPage")) {
            setGroupPage();
            goToGroupPage();
            currentPage = "GroupPage";
        }
        else if (element.equalsIgnoreCase("SchedulePage")) {
            setSchedulePage();
            goToSchedulePage();
            currentPage = "SchedulePage";
        }
        else if (element.equalsIgnoreCase("TimeTablePage")) {
            setTimeTablePage();
            goToTimeTablePage();
            currentPage = "TimeTablePage";
        }
        else if (element.equalsIgnoreCase("ScheduleMakerPage")) {
            setScheduleMakerPage();
            goToScheduleMakerPage();
            currentPage = "ScheduleMakerPage";
        }
    }
    private void goToWelcomePage() {
        if(currentPage.equalsIgnoreCase("")) {
            Scene scene = new Scene(SmartReminder.welcomePage);
            SmartReminder.primaryStage.setScene(scene);
            SmartReminder.primaryStage.setTitle("Smart Reminder");
            SmartReminder.primaryStage.setResizable(false);
            SmartReminder.primaryStage.show();
        }
        else if( currentPage.equalsIgnoreCase("HomePage") 
            || currentPage.equalsIgnoreCase("ProfilePage") 
            || currentPage.equalsIgnoreCase("GroupPage")) 
        {
            SmartReminder.primaryStage.getScene().setRoot(SmartReminder.welcomePage);
        } 
    }
    private void goToLoginPage() {
        SmartReminder.tmpLogin_pane.getChildren().clear();
        SmartReminder.tmpLogin_pane.getChildren().add(SmartReminder.loginPage);
    }
    private void goToSignUpPage() {
        SmartReminder.tmpLogin_pane.getChildren().clear();
        SmartReminder.tmpLogin_pane.getChildren().add(SmartReminder.signUpPage);
    }
    private void goToLoginFailedPage() {
        SmartReminder.tmpLogin_pane.getChildren().clear();
        SmartReminder.tmpLogin_pane.getChildren().add(SmartReminder.loginFailedPage);
    }
    private void goToHomePage() {
        SmartReminder.primaryStage.getScene().setRoot(SmartReminder.homePage);
    }
    private void goToProfilePage() {
        SmartReminder.tmpMain_pane.getChildren().clear();
        SmartReminder.tmpMain_pane.getChildren().add(SmartReminder.profilePage);
    }
    private void goToGroupPage() {
        SmartReminder.tmpMain_pane.getChildren().clear();
        SmartReminder.tmpMain_pane.getChildren().add(SmartReminder.groupPage);
    }
    private void goToSchedulePage() {
        SmartReminder.primaryStage.getScene().setRoot(SmartReminder.schedulePage);
    }
    private void goToTimeTablePage() {
        SmartReminder.tmpTimeTable_pane.getChildren().clear();
        SmartReminder.tmpTimeTable_pane.getChildren().add(SmartReminder.timeTablePage);
    }
    private void goToScheduleMakerPage() {
        SmartReminder.primaryStage.getScene().setRoot(SmartReminder.scheduleMakerPage);
    }
    
    private void setWelcomePage() {
        if(currentPage.equalsIgnoreCase("HomePage") 
            || currentPage.equalsIgnoreCase("ProfilePage") 
            || currentPage.equalsIgnoreCase("GroupPage")) 
        {
            SmartReminder.myAccount = null;
            SmartReminder.myFriends.clear();
            SmartReminder.myFriendRequests.clear();
            SmartReminder.tmpId_field.setText("");
            SmartReminder.tmpPassword_field.setText("");
            SmartReminder.tmpMain_pane.getChildren().clear();
            SmartReminder.alarmSound.stopSound();
            SmartReminder.timer.stop();
        }
    }
    private void setLoginPage() {
        
    }
    private void setSignUpPage() {
        
    }
    private void setLoginFailedPage() {
        
    }
    private void setHomePage() {
        if(currentPage.equalsIgnoreCase("LoginPage")) {
            SmartReminder.tmpUsername_menu.setText(SmartReminder.myAccount.getUserName());
            HomePageController.updateFriendList();
            HomePageController.clearSearchedUserList();
            HomePageController.updateFriendRequest();
            SmartReminder.timer.start();
            SmartReminder.isPersonalCalendar = true;
            SmartReminder.myScheduleServices.update();
            SmartReminder.groupScheduleServices.update();
            SmartReminder.myFriendServices.update();
            SmartReminder.myGroupServices.update();
            SmartReminder.myUserAccountServices.update();
            Date date = new Date();
            SmartReminder.currentDate = date.getDate();
            SmartReminder.currentMonth = date.getMonth();
            SmartReminder.currentYear = date.getYear() + 1900;
            SmartReminder.selectedMonth = SmartReminder.currentMonth;
            SmartReminder.selectedYear = SmartReminder.currentYear;
            SmartReminder.monthString = new DateFormatSymbols().getMonths()[SmartReminder.selectedMonth];
            SmartReminder.tmpMonth_list.setValue(SmartReminder.monthString);
            SmartReminder.tmpYear_list.setValue(SmartReminder.selectedYear);
            SmartReminder.tmpMonth_list.getSelectionModel().select(SmartReminder.monthString);
            SmartReminder.tmpYear_list.getSelectionModel().select(SmartReminder.selectedYear + "");
            SmartReminder.tmpCurrentDateDisplay.setText(SmartReminder.currentDate+"");
            SmartReminder.tmpCurrentMonthDisplay.setText(SmartReminder.monthString.substring(0, 3));
            SmartReminder.tmpCurrentYearDisplay.setText(SmartReminder.selectedYear+"");
            SmartReminder.calendarGenerator.generateCalendar();
        }
        else if(currentPage.equalsIgnoreCase("SchedulePage")) {
            SmartReminder.tmpGroupDetail = null;
            SmartReminder.tmpGroup_list.getSelectionModel().clearSelection();
            SmartReminder.friendsInGroup.clear();
        }
        else {
            SmartReminder.isPersonalCalendar = true;
            SmartReminder.tmpMain_pane.getChildren().clear();
            SmartReminder.myScheduleServices.update();
            SmartReminder.groupScheduleServices.update();
            SmartReminder.myFriendServices.update();
            SmartReminder.myGroupServices.update();
            SmartReminder.myUserAccountServices.update();
            Date date = new Date();
            SmartReminder.currentDate = date.getDate();
            SmartReminder.currentMonth = date.getMonth();
            SmartReminder.currentYear = date.getYear() + 1900;
            SmartReminder.selectedMonth = SmartReminder.currentMonth;
            SmartReminder.selectedYear = SmartReminder.currentYear;
            SmartReminder.monthString = new DateFormatSymbols().getMonths()[SmartReminder.selectedMonth];
            SmartReminder.tmpMonth_list.setValue(SmartReminder.monthString);
            SmartReminder.tmpYear_list.setValue(SmartReminder.selectedYear);
            SmartReminder.tmpMonth_list.getSelectionModel().select(SmartReminder.monthString);
            SmartReminder.tmpYear_list.getSelectionModel().select(SmartReminder.selectedYear + "");
            SmartReminder.calendarGenerator.generateCalendar();
        }
    }
    private void setProfilePage() {
        String username = SmartReminder.myAccount.getUserName();
        SmartReminder.tmpUsername_label.setText(username);
        Image img = new Image("file:src/Image/defaultUser.png");
        SmartReminder.tmpImageProfile.setFill(new ImagePattern(img));
    }
    private void setGroupPage() {
        SmartReminder.isPersonalCalendar = false;
        SmartReminder.myScheduleServices.update();
        SmartReminder.groupScheduleServices.update();
        SmartReminder.myFriendServices.update();
        SmartReminder.myGroupServices.update();
        SmartReminder.myUserAccountServices.update();
        GroupPageController.updateGroupList();
        SmartReminder.friendsInGroup.clear();
        HomePageController.updateFriendList();
        Date date = new Date();
        SmartReminder.currentDate = date.getDate();
        SmartReminder.currentMonth = date.getMonth();
        SmartReminder.currentYear = date.getYear() + 1900;
        SmartReminder.selectedMonth = SmartReminder.currentMonth;
        SmartReminder.selectedYear = SmartReminder.currentYear;
        SmartReminder.monthString = new DateFormatSymbols().getMonths()[SmartReminder.selectedMonth];
        SmartReminder.tmpMonth_list.setValue(SmartReminder.monthString);
        SmartReminder.tmpYear_list.setValue(SmartReminder.selectedYear);
        SmartReminder.tmpMonth_list.getSelectionModel().select(SmartReminder.monthString);
        SmartReminder.tmpYear_list.getSelectionModel().select(SmartReminder.selectedYear + "");
        SmartReminder.calendarGenerator.generateCalendar();
        SmartReminder.tmpGroupDetail = null;
    }
    private void setSchedulePage() {
        if (currentPage.equalsIgnoreCase("ScheduleMakerPage")) {
            SmartReminder.tmpId = 0;
        }
        int xPos = 208;
        int yPos = 3;
        SmartReminder.pageController.next("TimeTablePage");
        if(SmartReminder.isPersonalCalendar)
        {
            SmartReminder.tmpHeadLabel_add.setText("DAILY SCHEDULE");
        }
        else
        {
            SmartReminder.tmpHeadLabel_add.setText("GROUP SCHEDULE");
        } 
        ArrayList<Color> colorList = new ArrayList();
        colorList.add(Color.SKYBLUE);
        colorList.add(Color.ORANGE);
        colorList.add(Color.PALEGREEN);
        //personal schedules
        if (SmartReminder.isPersonalCalendar) {
            SmartReminder.myScheduleServices.update();
            List<PersonalSchedule> list = new ArrayList<>();
            list.addAll(SmartReminder.myScheduleServices.getSchedule(SmartReminder.beginTime, SmartReminder.myAccount));
            for (int j = 0; j < list.size(); j++) {
                int beginPhase = PersonalScheduleServices.getPhase(list.get(j).getBeginTime());
                int finishPhase = PersonalScheduleServices.getPhase(list.get(j).getFinishTime());
                if(finishPhase == 0) {
                    finishPhase = 48;
                }
                int[] num = new int[48];
                for (int i = beginPhase; i < finishPhase; i++) {
                    num[i] = 1;
                }
                int firstPos = beginPhase;
                int lastPos = finishPhase - 1;
                int height = 62;
                for (int i = 0; i < 48; i++) {
                    if(num[i] == 1) {
                        System.out.println(i);
                        Rectangle rect = new Rectangle(xPos, yPos, 600, height);
                        rect.setFill(colorList.get(j % 3));
                        rect.setVisible(true);
                        SmartReminder.tmpTimeTable_pane.getChildren().add(rect);
                    }
                    if(i == Math.ceil((lastPos - firstPos)/2.0) + firstPos) {
                        Button btn = new Button("EDIT");
                        Label label = new Label(list.get(j).getTitle());
                        label.setLayoutX(xPos + 10);
                        label.setLayoutY(yPos - 20);
                        label.setVisible(true);
                        btn.setLayoutX(xPos + 10);
                        btn.setLayoutY(yPos);
                        btn.setVisible(true);
                        SmartReminder.tmpTimeTable_pane.getChildren().add(btn);
                        SmartReminder.tmpTimeTable_pane.getChildren().add(label);
                        final long tmpI = list.get(j).getId();
                        btn.setOnAction(new EventHandler<ActionEvent>()
                        {
                            @Override
                            public void handle(ActionEvent e)
                            {
                                SmartReminder.tmpId = tmpI;
                                SmartReminder.pageController.next("ScheduleMakerPage");
                            }
                        }
                        );
                    }
                    if(i < 22) {
                        if(i % 2 == 0) {
                            yPos += height;
                            height = 64;
                        }
                        else {
                            yPos += height;
                            height = 62;
                        }
                    }
                    else {
                        if(i % 2 == 0) {
                            yPos += height;
                            height = 63;
                        }
                        else {
                            yPos += height;
                            height = 61;
                        }
                    }
                }
                xPos = 208;
                yPos = 3;
            }
        }
        //group schedules
        else {
            SmartReminder.groupScheduleServices.update();
            List<GroupSchedule> list = new ArrayList<>();
            list.addAll(SmartReminder.groupScheduleServices.getSchedule(SmartReminder.beginTime, SmartReminder.tmpGroupDetail));
            SmartReminder.myScheduleServices.update();
            List<PersonalSchedule> memberSchedules = new ArrayList<>();
            memberSchedules.addAll(SmartReminder.groupScheduleServices.getMemberSchedules(SmartReminder.selectedGroup, SmartReminder.tmpCreaterUsername));
            for (int j = 0; j < memberSchedules.size(); j++) {
                int beginPhase = PersonalScheduleServices.getPhase(memberSchedules.get(j).getBeginTime());
                int finishPhase = PersonalScheduleServices.getPhase(memberSchedules.get(j).getFinishTime());
                if(finishPhase == 0) {
                    finishPhase = 48;
                }
                int[] num = new int[48];
                for (int i = beginPhase; i < finishPhase; i++) {
                    num[i] = 1;
                }
                int firstPos = beginPhase;
                int lastPos = finishPhase - 1;
                int height = 62;
                for (int i = 0; i < 48; i++) {
                    if(num[i] == 1) {
                        Rectangle rect = new Rectangle(xPos, yPos, 600, height);
                        rect.setFill(Color.GREY);
                        rect.setVisible(true);
                        SmartReminder.tmpTimeTable_pane.getChildren().add(rect);
                    }
                    if(i < 22) {
                        if(i % 2 == 0) {
                            yPos += height;
                            height = 64;
                        }
                        else {
                            yPos += height;
                            height = 62;
                        }
                    }
                    else {
                        if(i % 2 == 0) {
                            yPos += height;
                            height = 63;
                        }
                        else {
                            yPos += height;
                            height = 61;
                        }
                    }
                }
                xPos = 208;
                yPos = 3;
            }
            
            //for group schedules
            for (int j = 0; j < list.size(); j++) {
                int beginPhase = PersonalScheduleServices.getPhase(list.get(j).getBeginTime());
                int finishPhase = PersonalScheduleServices.getPhase(list.get(j).getFinishTime());
                if(finishPhase == 0) {
                    finishPhase = 48;
                }
                int[] num = new int[48];
                for (int i = beginPhase; i < finishPhase; i++) {
                    num[i] = 1;
                }
                int firstPos = beginPhase;
                int lastPos = finishPhase - 1;
                int height = 62;
                for (int i = 0; i < 48; i++) {
                    if(num[i] == 1) {
                        Rectangle rect = new Rectangle(xPos, yPos, 600, height);
                        rect.setFill(colorList.get(j % 3));
                        rect.setVisible(true);
                        SmartReminder.tmpTimeTable_pane.getChildren().add(rect);
                    }

                    if(i == Math.ceil((lastPos - firstPos)/2.0) + firstPos) {
                        Button btn = new Button("EDIT");
                        Label label = new Label(list.get(j).getTitle());
                        label.setLayoutX(xPos + 10);
                        label.setLayoutY(yPos - 20);
                        label.setVisible(true);
                        btn.setLayoutX(xPos + 10);
                        btn.setLayoutY(yPos);
                        btn.setVisible(true);
                        SmartReminder.tmpTimeTable_pane.getChildren().add(btn);
                        SmartReminder.tmpTimeTable_pane.getChildren().add(label);
                        final long tmpI = list.get(j).getId();
                        btn.setOnAction(new EventHandler<ActionEvent>()
                        {
                            @Override
                            public void handle(ActionEvent e)
                            {
                                SmartReminder.tmpId = tmpI;
                                SmartReminder.pageController.next("ScheduleMakerPage");
                            }
                        }
                        );
                    }
                    if(i < 22) {
                        if(i % 2 == 0) {
                            yPos += height;
                            height = 64;
                        }
                        else {
                            yPos += height;
                            height = 62;
                        }
                    }
                    else {
                        if(i % 2 == 0) {
                            yPos += height;
                            height = 63;
                        }
                        else {
                            yPos += height;
                            height = 61;
                        }
                    }
                }
                xPos = 208;
                yPos = 3;
            }
        }
    }
    private void setTimeTablePage() {
        
    }
    private void setScheduleMakerPage() {
        if(SmartReminder.isPersonalCalendar)
        {
            SmartReminder.tmpHeadLabel_adding.setText("DAILY SCHEDULE");
        }
        else
        {
            SmartReminder.tmpHeadLabel_adding.setText("GROUP SCHEDULE");
        }
        //for personal
        if (SmartReminder.isPersonalCalendar) {
            List<PersonalSchedule> list = SmartReminder.myScheduleServices.getSchedule(SmartReminder.beginTime, SmartReminder.myAccount);
            //Edit
            if(SmartReminder.tmpId != 0) {
                for (int i = 0; i < list.size(); i++) {
                    if(list.get(i).getId() == SmartReminder.tmpId) {
                        SmartReminder.tmpScheduleName.setText(list.get(i).getTitle());
                        SmartReminder.tmpDetail.setText(list.get(i).getDetail());
                        String startTimeStr;
                        if(list.get(i).getBeginTime().getHours() < 10) {
                            startTimeStr = "0".concat(list.get(i).getBeginTime().getHours()+"");
                        }
                        else {
                            startTimeStr = list.get(i).getBeginTime().getHours()+"";
                        }
                        startTimeStr = startTimeStr.concat(".");
                        if(list.get(i).getBeginTime().getMinutes()< 10) {
                            startTimeStr = startTimeStr.concat("0".concat(list.get(i).getBeginTime().getMinutes()+""));
                        }
                        else {
                            startTimeStr = startTimeStr.concat(list.get(i).getBeginTime().getMinutes()+"");
                        }
                        String finishTimeStr;
                        if(list.get(i).getFinishTime().getHours() < 10) {
                            finishTimeStr = "0".concat(list.get(i).getFinishTime().getHours()+"");
                        }
                        else {
                            finishTimeStr = list.get(i).getFinishTime().getHours()+"";
                        }
                        finishTimeStr = finishTimeStr.concat(".");
                        if(list.get(i).getFinishTime().getMinutes()< 10) {
                            finishTimeStr = finishTimeStr.concat("0".concat(list.get(i).getFinishTime().getMinutes()+""));
                        }
                        else {
                            finishTimeStr = finishTimeStr.concat(list.get(i).getFinishTime().getMinutes()+"");
                        }
                        SmartReminder.tmpStartTime.getSelectionModel().select(startTimeStr);
                        SmartReminder.tmpStartTime.scrollTo(startTimeStr);
                        SmartReminder.tmpFinishTime.getSelectionModel().select(finishTimeStr);
                        SmartReminder.tmpFinishTime.scrollTo(finishTimeStr);
                        SmartReminder.selectedPreAlarm = list.get(i).getTimeBeforeAlert() + "";
                        SmartReminder.tmpPreAlarmList.getSelectionModel().select(SmartReminder.selectedPreAlarm);
                        SmartReminder.tmpCheckAlarm.setSelected(list.get(i).getIsAlert());
                        SmartReminder.tmpCheckRepeat.setSelected(list.get(i).getIsRepeat());
                    }
                }
            }
            //create
            else {
                SmartReminder.tmpScheduleName.setText("");
                SmartReminder.tmpDetail.setText("");
                SmartReminder.tmpPreAlarmList.setPromptText(SmartReminder.PREALARMS.get(0)); 
                SmartReminder.tmpPreAlarmList.setValue(SmartReminder.PREALARMS.get(0));
                SmartReminder.tmpPreAlarmList.getSelectionModel().select(0);
                SmartReminder.selectedPreAlarm = SmartReminder.tmpPreAlarmList.getSelectionModel().getSelectedItem();
                SmartReminder.tmpStartTime.getSelectionModel().select(0);
                SmartReminder.tmpFinishTime.getSelectionModel().select(0);
                SmartReminder.tmpStartTime.scrollTo(0);
                SmartReminder.tmpFinishTime.scrollTo(0);
                SmartReminder.selectedStartTime = SmartReminder.tmpStartTime.getSelectionModel().getSelectedItem();
                SmartReminder.selectedFinishTime = SmartReminder.tmpFinishTime.getSelectionModel().getSelectedItem();
                SmartReminder.tmpCheckAlarm.setSelected(false);
                SmartReminder.tmpCheckRepeat.setSelected(false);
            }
        }
        //for Group
        else {
            List<GroupSchedule> list = SmartReminder.groupScheduleServices.getSchedule(SmartReminder.beginTime, SmartReminder.tmpGroupDetail);
            if(SmartReminder.tmpId != 0) {
                for (int i = 0; i < list.size(); i++) {
                    if(list.get(i).getId() == SmartReminder.tmpId) {
                        SmartReminder.tmpScheduleName.setText(list.get(i).getTitle());
                        SmartReminder.tmpDetail.setText(list.get(i).getDetail());
                        String startTimeStr;
                        if(list.get(i).getBeginTime().getHours() < 10) {
                            startTimeStr = "0".concat(list.get(i).getBeginTime().getHours()+"");
                        }
                        else {
                            startTimeStr = list.get(i).getBeginTime().getHours()+"";
                        }
                        startTimeStr = startTimeStr.concat(".");
                        if(list.get(i).getBeginTime().getMinutes()< 10) {
                            startTimeStr = startTimeStr.concat("0".concat(list.get(i).getBeginTime().getMinutes()+""));
                        }
                        else {
                            startTimeStr = startTimeStr.concat(list.get(i).getBeginTime().getMinutes()+"");
                        }
                        String finishTimeStr;
                        if(list.get(i).getFinishTime().getHours() < 10) {
                            finishTimeStr = "0".concat(list.get(i).getFinishTime().getHours()+"");
                        }
                        else {
                            finishTimeStr = list.get(i).getFinishTime().getHours()+"";
                        }
                        finishTimeStr = finishTimeStr.concat(".");
                        if(list.get(i).getFinishTime().getMinutes()< 10) {
                            finishTimeStr = finishTimeStr.concat("0".concat(list.get(i).getFinishTime().getMinutes()+""));
                        }
                        else {
                            finishTimeStr = finishTimeStr.concat(list.get(i).getFinishTime().getMinutes()+"");
                        }
                        SmartReminder.tmpStartTime.getSelectionModel().select(startTimeStr);
                        SmartReminder.tmpStartTime.scrollTo(startTimeStr);
                        SmartReminder.tmpFinishTime.getSelectionModel().select(finishTimeStr);
                        SmartReminder.tmpFinishTime.scrollTo(finishTimeStr);
                        SmartReminder.selectedPreAlarm = list.get(i).getTimeBeforeAlert() + "";
                        SmartReminder.tmpPreAlarmList.getSelectionModel().select(SmartReminder.selectedPreAlarm);
                        SmartReminder.tmpCheckAlarm.setSelected(list.get(i).getIsAlert());
                        SmartReminder.tmpCheckRepeat.setSelected(list.get(i).getIsRepeat());
                    }
                }
            }
            else {
                SmartReminder.tmpScheduleName.setText("");
                SmartReminder.tmpDetail.setText("");
                SmartReminder.tmpPreAlarmList.setPromptText(SmartReminder.PREALARMS.get(0)); 
                SmartReminder.tmpPreAlarmList.setValue(SmartReminder.PREALARMS.get(0));
                SmartReminder.tmpPreAlarmList.getSelectionModel().select(0);
                SmartReminder.selectedPreAlarm = SmartReminder.tmpPreAlarmList.getSelectionModel().getSelectedItem();
                SmartReminder.tmpStartTime.getSelectionModel().select(0);
                SmartReminder.tmpFinishTime.getSelectionModel().select(0);
                SmartReminder.tmpStartTime.scrollTo(0);
                SmartReminder.tmpFinishTime.scrollTo(0);
                SmartReminder.selectedStartTime = SmartReminder.tmpStartTime.getSelectionModel().getSelectedItem();
                SmartReminder.selectedFinishTime = SmartReminder.tmpFinishTime.getSelectionModel().getSelectedItem();
                SmartReminder.tmpCheckAlarm.setSelected(false);
                SmartReminder.tmpCheckRepeat.setSelected(false);
            }
        }
    }
}
