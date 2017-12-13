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
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
public class ScheduleMakerPageController implements Initializable {
    
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
    private Text headLabel;
    
    private boolean checkRepeatValue; 
    
    /**
     * Initializes the controller class.
     */
    //methods
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        SmartReminder.tmpDetail = detail;
        SmartReminder.tmpScheduleName = scheduleName;
        SmartReminder.tmpFinishTime = finishTime;
        SmartReminder.tmpStartTime = startTime;
        SmartReminder.tmpPreAlarmList = preAlarmList;
        SmartReminder.tmpCheckAlarm = checkAlarm;
        SmartReminder.tmpCheckRepeat = checkRepeat;
        SmartReminder.tmpHeadLabel_adding = headLabel;
        SmartReminder.tmpStartTime.setItems(SmartReminder.STARTTIMES);
        SmartReminder.tmpFinishTime.setItems(SmartReminder.FINISHTIMES);
        SmartReminder.tmpPreAlarmList.setItems(SmartReminder.PREALARMS);
    }      
    @FXML
    private void Clicking(ActionEvent event) {
        String[] str = SmartReminder.tmpStartTime.getSelectionModel().getSelectedItem().split("\\.");
        int beginHrs = Integer.parseInt(str[0]);
        int beginMins = Integer.parseInt(str[1]);
        str = SmartReminder.tmpFinishTime.getSelectionModel().getSelectedItem().split("\\.");
        int finishHrs = Integer.parseInt(str[0]);
        int finishMins = Integer.parseInt(str[1]);
        boolean titleCheck = true;
        if (SmartReminder.tmpScheduleName.getText().equals("")) {
            titleCheck = false;
        }
        if ( (finishHrs == 0 && finishMins == 0) || (beginHrs < finishHrs) || (beginHrs == finishHrs && beginMins < finishMins) ) {
            if (titleCheck) {
                //Edit
                if (SmartReminder.tmpId != 0) {
                    if (SmartReminder.isPersonalCalendar) {
                        SmartReminder.myScheduleServices.editSchedule();
                    }
                    else {
                        SmartReminder.groupScheduleServices.editSchedule();
                    }
                }
                //Add
                else {
                    str = SmartReminder.selectedStartTime.split("\\.");
                    SmartReminder.beginTime.setHours(Integer.parseInt(str[0]));
                    SmartReminder.beginTime.setMinutes(Integer.parseInt(str[1]));
                    str = SmartReminder.selectedFinishTime.split("\\.");
                    SmartReminder.finishTime.setHours(Integer.parseInt(str[0]));
                    SmartReminder.finishTime.setMinutes(Integer.parseInt(str[1]));
                    //wait isAlarm argument
                    if(SmartReminder.isPersonalCalendar) {
                        PersonalSchedule schedule = new PersonalSchedule(SmartReminder.myAccount.getId(), scheduleName.getText(), detail.getText(), SmartReminder.beginTime, SmartReminder.finishTime, Integer.parseInt(SmartReminder.selectedPreAlarm), SmartReminder.tmpCheckRepeat.isSelected(), SmartReminder.tmpCheckAlarm.isSelected());
                        SmartReminder.myScheduleServices.addSchedule(schedule);
                    }
                    else {
                        ArrayList<GroupMember> list = SmartReminder.myGroupServices.getMyGroupList();
                        long tmpGroupId = 0;
                        for (int i = 0; i < list.size(); i++) {
                            if (list.get(i).getGroupDetail().getGroupName().equals(SmartReminder.selectedGroup) && 
                                    list.get(i).getGroupDetail().getCreaterAccount().getUserName().equals(SmartReminder.tmpCreaterUsername)) 
                            {
                                tmpGroupId = list.get(i).getGroupDetail().getId();
                            }
                        }
                        GroupSchedule schedule = new GroupSchedule(tmpGroupId, scheduleName.getText(), detail.getText(), SmartReminder.beginTime, SmartReminder.finishTime, Integer.parseInt(SmartReminder.selectedPreAlarm), SmartReminder.tmpCheckRepeat.isSelected(), SmartReminder.tmpCheckAlarm.isSelected());
                        SmartReminder.groupScheduleServices.addSchedule(schedule);
                    }
                }
                SmartReminder.beginTime = new Date(SmartReminder.selectedYear-1900, SmartReminder.selectedMonth, SmartReminder.selectedDate, 0, 0);
                SmartReminder.finishTime = new Date(SmartReminder.selectedYear-1900, SmartReminder.selectedMonth, SmartReminder.selectedDate, 0, 0);
                SmartReminder.pageController.next("SchedulePage");
            }
        }
    }
    @FXML
    private void checkBoxOnClick(ActionEvent event) {
         checkRepeatValue = checkRepeat.isSelected();
    }
    @FXML
    private void backOnclick(ActionEvent event) {
        SmartReminder.pageController.next("SchedulePage");
    }
    @FXML
    private void SelectStartTime(MouseEvent event) {
        if(event.getClickCount() == 1){
            SmartReminder.selectedStartTime = startTime.getSelectionModel().getSelectedItem();
        }    
    }
    @FXML
    private void SelectFinishTime(MouseEvent event) {
        if(event.getClickCount() == 1){
            SmartReminder.selectedFinishTime = finishTime.getSelectionModel().getSelectedItem();
        }
    }
    @FXML
    private void preAlarming(ActionEvent event) {
        SmartReminder.selectedPreAlarm = preAlarmList.getValue(); 
    }
}
