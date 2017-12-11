/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartreminder;

import classes.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author User
 */
public class AddingScheduleController implements Initializable {
    
    //attributes
    @FXML
    private TextField detail;
    @FXML
    private TextField scheduleName;
    @FXML
    private ComboBox<String> preAlarmList;
    @FXML
    private CheckBox checkRepeat;
    @FXML
    private ListView<String> startTime;
    @FXML
    private ListView<String> finishTime;
    @FXML
    private CheckBox checkAlarm;
    @FXML
    private Text labelHead;
    
    public static ObservableList<String> startTimelist = FXCollections.observableArrayList("00.00","00.30","01.00","01.30","02.00","02.30","03.00","03.30","04.00","04.30","05.00","05.30","06.00","06.30","07.00","07.30","08.00","08.30","09.00","09.30","10.00","10.30","11.00","11.30","12.00","12.30","13.00","13.30","14.00","14.30","15.00","15.30","16.00","16.30","17.00","17.30","18.00","18.30","19.00","19.30","20.00","20.30","21.00","21.30","22.00","22.30","23.00","23.30");
    public static ObservableList<String> finishTimelist = FXCollections.observableArrayList("00.30","01.00","01.30","02.00","02.30","03.00","03.30","04.00","04.30","05.00","05.30","06.00","06.30","07.00","07.30","08.00","08.30","09.00","09.30","10.00","10.30","11.00","11.30","12.00","12.30","13.00","13.30","14.00","14.30","15.00","15.30","16.00","16.30","17.00","17.30","18.00","18.30","19.00","19.30","20.00","20.30","21.00","21.30","22.00","22.30","23.00","23.30","00.00");
    public static ObservableList<String> preAlarmlist = FXCollections.observableArrayList("0","5","10","15","20","25","30");
    
    boolean checkRepeatValue; 
    public static String select_startTime;
    public static String select_finishTime;
    public static String select_pre;
    
    public static long tmpId;
    
    public static ListView<String> tmpFinishTime;
    public static ListView<String> tmpStartTime;
    public static ComboBox<String> tmpPreAlarmList;
    
    public static TextField tmpDetail;
    public static TextField tmpScheduleName;
    
    public static CheckBox tmpCheckAlarm;
    public static CheckBox tmpCheckRepeat;
    
    public static Text label;
    
    /**
     * Initializes the controller class.
     */
    //methods
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tmpDetail = detail;
        tmpScheduleName = scheduleName;
        tmpFinishTime = finishTime;
        tmpStartTime = startTime;
        tmpPreAlarmList = preAlarmList;
        tmpCheckAlarm = checkAlarm;
        tmpCheckRepeat = checkRepeat;
        label = labelHead;
    }      
    @FXML
    private void Clicking(ActionEvent event) {
        
        String[] str = tmpStartTime.getSelectionModel().getSelectedItem().split("\\.");
        int beginHrs = Integer.parseInt(str[0]);
        int beginMins = Integer.parseInt(str[1]);
                
        str = tmpFinishTime.getSelectionModel().getSelectedItem().split("\\.");
        int finishHrs = Integer.parseInt(str[0]);
        int finishMins = Integer.parseInt(str[1]);
        
        boolean titleCheck = true;
        if (tmpScheduleName.getText().equals("")) {
            titleCheck = false;
            System.out.println("Invalid schedule title, try again");
        }
        
        if ( (finishHrs == 0 && finishMins == 0) || (beginHrs < finishHrs) || (beginHrs == finishHrs && beginMins < finishMins) ) {
            if (titleCheck) {
                if (tmpId != 0) {
                    if (HomePageController.isPersonal) {
                        SmartReminder.myCalendar.editSchedule();
                    }
                    else {
                        SmartReminder.groupCalendar.editSchedule();
                    }
                    
                }
                else {

                    str = select_startTime.split("\\.");
                    System.out.println(str[0]);
                    System.out.println(str[1]);
                    SmartReminder.beginTime.setHours(Integer.parseInt(str[0]));
                    SmartReminder.beginTime.setMinutes(Integer.parseInt(str[1]));

                    System.out.println(SmartReminder.beginTime.getHours() + " " + SmartReminder.beginTime.getMinutes());

                    str = select_finishTime.split("\\.");
                    SmartReminder.finishTime.setHours(Integer.parseInt(str[0]));
                    SmartReminder.finishTime.setMinutes(Integer.parseInt(str[1]));

                    System.out.println(SmartReminder.finishTime.getHours() + " " + SmartReminder.finishTime.getMinutes());

                    //wait isAlarm argument
                    if(HomePageController.isPersonal) {
                        Schedule schedule = new Schedule(SmartReminder.myAccount.getId(), scheduleName.getText(), detail.getText(), SmartReminder.beginTime, SmartReminder.finishTime, Integer.parseInt(select_pre), tmpCheckRepeat.isSelected(), tmpCheckAlarm.isSelected());

                        SmartReminder.myCalendar.addSchedule(schedule);

                        SmartReminder.myCalendar.showSchedule();
                    }
                    else {
                        ArrayList<GroupMember> list = SmartReminder.myGroupServices.getMyGroupList();
                        long tmpGroupId = 0;
                        for (int i = 0; i < list.size(); i++) {
                            if (list.get(i).getGroupDetail().getGroupName().equals(GroupPageController.select_GroupName) && 
                                    list.get(i).getGroupDetail().getCreaterAccount().getUserName().equals(GroupPageController.createrUsername)) 
                            {
                                tmpGroupId = list.get(i).getGroupDetail().getId();
                            }
                            
                        }
                        GroupSchedule schedule = new GroupSchedule(tmpGroupId, scheduleName.getText(), detail.getText(), SmartReminder.beginTime, SmartReminder.finishTime, Integer.parseInt(select_pre), tmpCheckRepeat.isSelected(), tmpCheckAlarm.isSelected());
                        SmartReminder.groupCalendar.addSchedule(schedule);
                        SmartReminder.groupCalendar.showSchedule();
                    }
                }

                System.out.println(scheduleName.getText());
                System.out.println(detail.getText());
                System.out.println(select_startTime);
                System.out.println(select_finishTime);
                System.out.println(select_pre);   
                System.out.println(tmpCheckRepeat.isSelected());
                System.out.println(tmpCheckAlarm.isSelected());

                SmartReminder.beginTime = new Date(HomePageController.current_year-1900, HomePageController.current_month, HomePageController.selectedDay, 0, 0);
                SmartReminder.finishTime = new Date(HomePageController.current_year-1900, HomePageController.current_month, HomePageController.selectedDay, 0, 0);
                AddScheduleController.setTimeTable();
                SmartReminder.primaryStage.getScene().setRoot(SmartReminder.addSchedulePage);
            }
        }
        else {
            System.out.println("Invalid time, try again!!");
        }
    }
    @FXML
    private void checkBoxOnClick(ActionEvent event) {
         checkRepeatValue = checkRepeat.isSelected();
    }
    @FXML
    private void backOnclick(ActionEvent event) {
        tmpId = 0;
        AddScheduleController.setTimeTable();
        System.out.println("BACKKKKKKKKK");
        SmartReminder.primaryStage.getScene().setRoot(SmartReminder.addSchedulePage);
    }
    @FXML
    private void SelectStartTime(MouseEvent event) {
        if(event.getClickCount() == 1){
            select_startTime = startTime.getSelectionModel().getSelectedItem();
        }    
    }
    @FXML
    private void SelectFinishTime(MouseEvent event) {
        if(event.getClickCount() == 1){
            select_finishTime = finishTime.getSelectionModel().getSelectedItem();
        }
    }
    @FXML
    private void preAlarming(ActionEvent event) {
        select_pre = preAlarmList.getValue(); 
    }
    
    static void setInit() {
        tmpStartTime.setItems(startTimelist);
        tmpFinishTime.setItems(finishTimelist);
        tmpPreAlarmList.setItems(preAlarmlist);
        if(HomePageController.isPersonal)
        {
            label.setText("DAILY SCHEDULE");
        }
        else
        {
            label.setText("GROUP SCHEDULE");
        } 
        //for personal
        if (HomePageController.isPersonal) {
            List<Schedule> list = SmartReminder.myCalendar.getSchedule(SmartReminder.beginTime, SmartReminder.myAccount);
            //Edit
            if(tmpId != 0) {
                for (int i = 0; i < list.size(); i++) {
                    if(list.get(i).getId() == tmpId) {
                        tmpScheduleName.setText(list.get(i).getTitle());
                        tmpDetail.setText(list.get(i).getDetail());

                        String startTimeStr;
                        if(list.get(i).getBeginTime().getHours() < 10) {
                            startTimeStr = "0".concat(list.get(i).getBeginTime().getHours()+"");
                        }
                        else
                            startTimeStr = list.get(i).getBeginTime().getHours()+"";
                        startTimeStr = startTimeStr.concat(".");
                        if(list.get(i).getBeginTime().getMinutes()< 10) {
                            startTimeStr = startTimeStr.concat("0".concat(list.get(i).getBeginTime().getMinutes()+""));
                        }
                        else {
                            startTimeStr = startTimeStr.concat(list.get(i).getBeginTime().getMinutes()+"");
                        }
                        System.out.println(startTimeStr);
                        
                        String finishTimeStr;
                        if(list.get(i).getFinishTime().getHours() < 10) {
                            finishTimeStr = "0".concat(list.get(i).getFinishTime().getHours()+"");
                        }
                        else
                            finishTimeStr = list.get(i).getFinishTime().getHours()+"";
                        finishTimeStr = finishTimeStr.concat(".");
                        if(list.get(i).getFinishTime().getMinutes()< 10) {
                            finishTimeStr = finishTimeStr.concat("0".concat(list.get(i).getFinishTime().getMinutes()+""));
                        }
                        else {
                            finishTimeStr = finishTimeStr.concat(list.get(i).getFinishTime().getMinutes()+"");
                        }
                        System.out.println(finishTimeStr);
                        
                        tmpStartTime.getSelectionModel().select(startTimeStr);
                        tmpStartTime.scrollTo(startTimeStr);
                        
                        tmpFinishTime.getSelectionModel().select(finishTimeStr);
                        tmpFinishTime.scrollTo(finishTimeStr);
                        
                        select_pre = list.get(i).getTimeBeforeAlert() + "";
                        tmpPreAlarmList.getSelectionModel().select(select_pre);
                        
                        tmpCheckAlarm.setSelected(list.get(i).getIsAlert());
                        tmpCheckRepeat.setSelected(list.get(i).getIsRepeat());
                        
                    }
                }

            }
            //create
            else {
                tmpScheduleName.setText("");
                tmpDetail.setText("");
                tmpPreAlarmList.setPromptText(preAlarmlist.get(0)); 
                tmpPreAlarmList.setValue(preAlarmlist.get(0));
                tmpPreAlarmList.getSelectionModel().select(0);
                select_pre = tmpPreAlarmList.getSelectionModel().getSelectedItem();

                tmpStartTime.getSelectionModel().select(0);
                tmpFinishTime.getSelectionModel().select(0);
                tmpStartTime.scrollTo(0);
                tmpFinishTime.scrollTo(0);
                select_startTime = tmpStartTime.getSelectionModel().getSelectedItem();
                select_finishTime = tmpFinishTime.getSelectionModel().getSelectedItem();
                tmpCheckAlarm.setSelected(false);
                tmpCheckRepeat.setSelected(false);
            }
        }
        //for Group
        else {
            List<GroupSchedule> list = SmartReminder.groupCalendar.getSchedule(SmartReminder.beginTime, GroupPageController.tmpGroupDetail);
            if(tmpId != 0) {
                for (int i = 0; i < list.size(); i++) {
                    if(list.get(i).getId() == tmpId) {
                        tmpScheduleName.setText(list.get(i).getTitle());
                        tmpDetail.setText(list.get(i).getDetail());

                        String startTimeStr;
                        if(list.get(i).getBeginTime().getHours() < 10) {
                            startTimeStr = "0".concat(list.get(i).getBeginTime().getHours()+"");
                        }
                        else
                            startTimeStr = list.get(i).getBeginTime().getHours()+"";
                        startTimeStr = startTimeStr.concat(".");
                        if(list.get(i).getBeginTime().getMinutes()< 10) {
                            startTimeStr = startTimeStr.concat("0".concat(list.get(i).getBeginTime().getMinutes()+""));
                        }
                        else {
                            startTimeStr = startTimeStr.concat(list.get(i).getBeginTime().getMinutes()+"");
                        }
                        System.out.println(startTimeStr);

                        String finishTimeStr;
                        if(list.get(i).getFinishTime().getHours() < 10) {
                            finishTimeStr = "0".concat(list.get(i).getFinishTime().getHours()+"");
                        }
                        else
                            finishTimeStr = list.get(i).getFinishTime().getHours()+"";
                        finishTimeStr = finishTimeStr.concat(".");
                        if(list.get(i).getFinishTime().getMinutes()< 10) {
                            finishTimeStr = finishTimeStr.concat("0".concat(list.get(i).getFinishTime().getMinutes()+""));
                        }
                        else {
                            finishTimeStr = finishTimeStr.concat(list.get(i).getFinishTime().getMinutes()+"");
                        }
                        System.out.println(finishTimeStr);
                        
                        tmpStartTime.getSelectionModel().select(startTimeStr);
                        tmpStartTime.scrollTo(startTimeStr);
                        
                        tmpFinishTime.getSelectionModel().select(finishTimeStr);
                        tmpFinishTime.scrollTo(finishTimeStr);
                        
                        select_pre = list.get(i).getTimeBeforeAlert() + "";
                        tmpPreAlarmList.getSelectionModel().select(select_pre);
                        
                        tmpCheckAlarm.setSelected(list.get(i).getIsAlert());
                        tmpCheckRepeat.setSelected(list.get(i).getIsRepeat());
                    }
                }

            }
            else {
                tmpScheduleName.setText("");
                tmpDetail.setText("");
                tmpPreAlarmList.setPromptText(preAlarmlist.get(0)); 
                tmpPreAlarmList.setValue(preAlarmlist.get(0));
                tmpPreAlarmList.getSelectionModel().select(0);
                select_pre = tmpPreAlarmList.getSelectionModel().getSelectedItem();

                tmpStartTime.getSelectionModel().select(0);
                tmpFinishTime.getSelectionModel().select(0);
                tmpStartTime.scrollTo(0);
                tmpFinishTime.scrollTo(0);
                select_startTime = tmpStartTime.getSelectionModel().getSelectedItem();
                select_finishTime = tmpFinishTime.getSelectionModel().getSelectedItem();

            }
        }
        
        
    }
}
