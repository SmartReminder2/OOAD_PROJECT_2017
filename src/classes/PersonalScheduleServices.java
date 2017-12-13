/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import smartreminder.SmartReminder;

/**
 *
 * @author kan
 */
public class PersonalScheduleServices {
    
    //attributes
    private static PersonalScheduleServices instance = new PersonalScheduleServices();
    private List<PersonalSchedule> schedules;
    private boolean isAdding = true;
    private long tmpSchId;
    
    //constructors
    private PersonalScheduleServices(){
        ObjectDBServices odb = new ObjectDBServices();
        EntityManager em = odb.openConnection();
        em.getTransaction().begin();
        TypedQuery<PersonalSchedule> query = em.createQuery("SELECT sch FROM PersonalSchedule sch", PersonalSchedule.class);
        schedules = query.getResultList();
        odb.closeConnection();
    }
    
    //methods
    public static PersonalScheduleServices getInstance(){
        return instance;
    }
    private boolean isAvailable(PersonalSchedule schedule) {
        boolean returnVal = true;
        int[] newSchedulePhase = new int[48];
        for (int i = getPhase(schedule.getBeginTime()); i < getPhase(schedule.getFinishTime()); i++) {
            newSchedulePhase[i] = 1;
        }
        for (int i = 0; i < schedules.size(); i++) {
            if (schedules.get(i).getUserId() == SmartReminder.myAccount.getId()) {
                int date = schedule.getBeginTime().getDate();
                int month = schedule.getBeginTime().getMonth();
                int year = schedule.getBeginTime().getYear();
                if (schedules.get(i).getBeginTime().getDate() == date && schedules.get(i).getBeginTime().getMonth() == month && schedules.get(i).getBeginTime().getYear() == year) {
                    if (!isAdding && schedules.get(i).getId() == tmpSchId) {
                        
                    }
                    else if ( isAdding || (!isAdding && schedules.get(i).getId() != tmpSchId) ) {
                        int[] schedulePhase = new int[48];
                        for (int j = 0; j < schedulePhase.length; j++) {
                            if(j==0)
                            {
                                
                            }
                            if (j >= getPhase(schedules.get(i).getBeginTime()) && j < getPhase(schedules.get(i).getFinishTime())) {
                                schedulePhase[j] = 1;
                            }
                        }
                        for (int j = 0; j < schedulePhase.length; j++) {
                            if(newSchedulePhase[j] == 1 & schedulePhase[j] == 1) {
                                returnVal = false;
                                break;
                            }
                        }
                        if(returnVal == false) {
                            break;
                        }
                    }
                }
            }
        }
        return returnVal;
    }    
    public static int getPhase(Date date) {
        int phase;
        int hrs = date.getHours();
        int mins = date.getMinutes();
        if((hrs == 0) & (mins == 0)) {
            phase = 0;
        }
        else if((hrs == 0) & (mins == 30)) {
            phase = 1;
        }
        else if((hrs == 1) & (mins == 0)) {
            phase = 2;
        }
        else if((hrs == 1) & (mins == 30)) {
            phase = 3;
        }
        else if((hrs == 2) & (mins == 0)) {
            phase = 4;
        }
        else if((hrs == 2) & (mins == 30)) {
            phase = 5;
        }
        else if((hrs == 3) & (mins == 0)) {
            phase = 6;
        }
        else if((hrs == 3) & (mins == 30)) {
            phase = 7;
        }
        else if((hrs == 4) & (mins == 0)) {
            phase = 8;
        }
        else if((hrs == 4) & (mins == 30)) {
            phase = 9;
        }
        else if((hrs == 5) & (mins == 0)) {
            phase = 10;
        }
        else if((hrs == 5) & (mins == 30)) {
            phase = 11;
        }
        else if((hrs == 6) & (mins == 0)) {
            phase = 12;
        }
        else if((hrs == 6) & (mins == 30)) {
            phase = 13;
        }
        else if((hrs == 7) & (mins == 0)) {
            phase = 14;
        }
        else if((hrs == 7) & (mins == 30)) {
            phase = 15;
        }
        else if((hrs == 8) & (mins == 0)) {
            phase = 16;
        }
        else if((hrs == 8) & (mins == 30)) {
            phase = 17;
        }
        else if((hrs == 9) & (mins == 0)) {
            phase = 18;
        }
        else if((hrs == 9) & (mins == 30)) {
            phase = 19;
        }
        else if((hrs == 10) & (mins == 0)) {
            phase = 20;
        }
        else if((hrs == 10) & (mins == 30)) {
            phase = 21;
        }
        else if((hrs == 11) & (mins == 0)) {
            phase = 22;
        }
        else if((hrs == 11) & (mins == 30)) {
            phase = 23;
        }
        else if((hrs == 12) & (mins == 0)) {
            phase = 24;
        }
        else if((hrs == 12) & (mins == 30)) {
            phase = 25;
        }
        else if((hrs == 13) & (mins == 0)) {
            phase = 26;
        }
        else if((hrs == 13) & (mins == 30)) {
            phase = 27;
        }
        else if((hrs == 14) & (mins == 0)) {
            phase = 28;
        }
        else if((hrs == 14) & (mins == 30)) {
            phase = 29;
        }
        else if((hrs == 15) & (mins == 0)) {
            phase = 30;
        }
        else if((hrs == 15) & (mins == 30)) {
            phase = 31;
        }
        else if((hrs == 16) & (mins == 0)) {
            phase = 32;
        }
        else if((hrs == 16) & (mins == 30)) {
            phase = 33;
        }
        else if((hrs == 17) & (mins == 0)) {
            phase = 34;
        }
        else if((hrs == 17) & (mins == 30)) {
            phase = 35;
        }
        else if((hrs == 18) & (mins == 0)) {
            phase = 36;
        }
        else if((hrs == 18) & (mins == 30)) {
            phase = 37;
        }
        else if((hrs == 19) & (mins == 0)) {
            phase = 38;
        }
        else if((hrs == 19) & (mins == 30)) {
            phase = 39;
        }
        else if((hrs == 20) & (mins == 0)) {
            phase = 40;
        }
        else if((hrs == 20) & (mins == 30)) {
            phase = 41;
        }
        else if((hrs == 21) & (mins == 0)) {
            phase = 42;
        }
        else if((hrs == 21) & (mins == 30)) {
            phase = 43;
        }
        else if((hrs == 22) & (mins == 0)) {
            phase = 44;
        }
        else if((hrs == 22) & (mins == 30)) {
            phase = 45;
        }
        else if((hrs == 23) & (mins == 0)) {
            phase = 46;
        }
        else if((hrs == 23) & (mins == 30)) {
            phase = 47;
        }
        else {
            phase = 0;
        }
        return phase;
    }    
    public void addSchedule(PersonalSchedule schedule) {
        isAdding = true;
        tmpSchId = 0;
        if(!schedules.isEmpty()) {
            if(isAvailable(schedule)) {
                ObjectDBServices odb = new ObjectDBServices();
                EntityManager em = odb.openConnection();
                em.getTransaction().begin();
                em.persist(schedule);
                em.getTransaction().commit();
                odb.closeConnection();
                schedules.add(schedule);
            }   
        }
        else {
            ObjectDBServices odb = new ObjectDBServices();
            EntityManager em = odb.openConnection();
            em.getTransaction().begin();
            em.persist(schedule);
            em.getTransaction().commit();
            odb.closeConnection();
            schedules.add(schedule);
        }
    }    
    public void editSchedule() {
        isAdding = false;
        ObjectDBServices odb = new ObjectDBServices();
        EntityManager em = odb.openConnection();
        PersonalSchedule sch = em.find(PersonalSchedule.class, SmartReminder.tmpId);
        em.getTransaction().begin();
        String[] tmpStr = SmartReminder.tmpStartTime.getSelectionModel().getSelectedItem().split("\\.");
        int tmpBeginHrs = Integer.parseInt(tmpStr[0]);
        int tmpBeginMins = Integer.parseInt(tmpStr[1]);
        tmpStr = SmartReminder.tmpFinishTime.getSelectionModel().getSelectedItem().split("\\.");
        int tmpFinishHrs = Integer.parseInt(tmpStr[0]);
        int tmpFinishMins = Integer.parseInt(tmpStr[1]);
        Date tmpBegin = sch.getBeginTime();
        tmpBegin.setHours(tmpBeginHrs);
        tmpBegin.setMinutes(tmpBeginMins);
        Date tmpFinish = sch.getFinishTime();
        tmpFinish.setHours(tmpFinishHrs);
        tmpFinish.setMinutes(tmpFinishMins);
        tmpSchId = sch.getId();        
        PersonalSchedule tmpSch = new PersonalSchedule(sch.getUserId(), SmartReminder.tmpScheduleName.getText(), SmartReminder.tmpDetail.getText(), tmpBegin, tmpFinish, Integer.parseInt(SmartReminder.selectedPreAlarm), SmartReminder.tmpCheckRepeat.isSelected(), SmartReminder.tmpCheckAlarm.isSelected());
        if(!schedules.isEmpty()) {
            if(isAvailable(tmpSch)) {
                String[] str = SmartReminder.tmpStartTime.getSelectionModel().getSelectedItem().split("\\.");
                int beginHrs = Integer.parseInt(str[0]);
                int beginMins = Integer.parseInt(str[1]);
                str = SmartReminder.tmpFinishTime.getSelectionModel().getSelectedItem().split("\\.");
                int finishHrs = Integer.parseInt(str[0]);
                int finishMins = Integer.parseInt(str[1]);
                sch.setTitle(SmartReminder.tmpScheduleName.getText());
                sch.setDetail(SmartReminder.tmpDetail.getText());
                sch.getBeginTime().setHours(beginHrs);
                sch.getBeginTime().setMinutes(beginMins);
                sch.getFinishTime().setHours(finishHrs);
                sch.getFinishTime().setMinutes(finishMins);
                sch.setTimeBeforeAlert(Integer.parseInt(SmartReminder.tmpPreAlarmList.getSelectionModel().getSelectedItem()));
                sch.setIsRepeat(SmartReminder.tmpCheckRepeat.isSelected());
                sch.setIsAlert(SmartReminder.tmpCheckAlarm.isSelected());
                long temporarySchId = sch.getId();
                List<PersonalSchedule> list = SmartReminder.myScheduleServices.getSchedule(sch.getBeginTime(), SmartReminder.myAccount);
                for (int i = 0; i < list.size(); i++) {
                    if(list.get(i).getId() == temporarySchId) {
                        list.get(i).setTitle(SmartReminder.tmpScheduleName.getText());
                        list.get(i).setDetail(SmartReminder.tmpDetail.getText());
                        list.get(i).getBeginTime().setHours(beginHrs);
                        list.get(i).getBeginTime().setMinutes(beginMins);
                        list.get(i).getFinishTime().setHours(finishHrs);
                        list.get(i).getFinishTime().setMinutes(finishMins);
                        list.get(i).setTimeBeforeAlert(Integer.parseInt(SmartReminder.tmpPreAlarmList.getSelectionModel().getSelectedItem()));
                        list.get(i).setIsRepeat(SmartReminder.tmpCheckRepeat.isSelected());
                        list.get(i).setIsAlert(SmartReminder.tmpCheckAlarm.isSelected());
                    }
                }
                em.getTransaction().commit();
                odb.closeConnection();
            }
            else {
                odb.closeConnection();
            }
        }
    }    
    public List<PersonalSchedule> getSchedule(Date schDate, UserAccount user) {
        int date = schDate.getDate();
        int month = schDate.getMonth();
        int year = schDate.getYear();
        ArrayList<PersonalSchedule> list = new ArrayList<>();
        for (int i = 0; i < schedules.size(); i++) {
            if (schedules.get(i).getBeginTime().getDate()== date & schedules.get(i).getBeginTime().getMonth() == month & schedules.get(i).getBeginTime().getYear() == year) {
                if (schedules.get(i).getUserId() == user.getId()) {
                    list.add(schedules.get(i));
                }
            }
        }
        return (List)list;
    }    
    public void update(){
        ObjectDBServices odb = new ObjectDBServices();
        EntityManager em = odb.openConnection();
        em.getTransaction().begin();
        TypedQuery<PersonalSchedule> query = em.createQuery("SELECT sch FROM PersonalSchedule sch", PersonalSchedule.class);
        schedules = query.getResultList();
        odb.closeConnection();
    }
}
