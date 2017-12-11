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
import smartreminder.AddingScheduleController;
import smartreminder.SmartReminder;

/**
 *
 * @author kan
 */
public class PersonalCalendar implements Calendar {
    
    //attributes
    private static PersonalCalendar instance = new PersonalCalendar();
    private List<Schedule> schedules;
    private boolean isAdding = true;
    private long tmpSchId;
    
    //constructors
    private PersonalCalendar(){
        
        ObjectDBServices odb = new ObjectDBServices();
        EntityManager em = odb.openConnection();
        //EntityManagerFactory emf = Persistence.createEntityManagerFactory("./db/database.odb");
        //EntityManager em = emf.createEntityManager();
 
        // Store 1000 Point objects in the database:
        em.getTransaction().begin();
        
        TypedQuery<Schedule> query = em.createQuery("SELECT sch FROM Schedule sch", Schedule.class);
        schedules = query.getResultList();
        
        odb.closeConnection();
        //SmartReminder.emf.close();
        
        System.out.println("Construction Success!!");
    }
    
    //methods
    public static PersonalCalendar getInstance(){
        return instance;
    }
    private boolean isAvailable(Schedule schedule) {
        
        boolean returnVal = true;
        int[] newSchedulePhase = new int[48];
        for (int i = getPhase(schedule.getBeginTime()); i < getPhase(schedule.getFinishTime()); i++) {
            newSchedulePhase[i] = 1;
        }
        System.out.println("New Sch");
        for (int i = 0; i < newSchedulePhase.length; i++) {
            System.out.print(newSchedulePhase[i]);
        }
        System.out.println("id: " + tmpSchId);
        System.out.println("BeginPhase: " + getPhase(schedule.getBeginTime()));
        System.out.println("FinPhase: " + getPhase(schedule.getFinishTime()));
        System.out.println("");
        
        for (int i = 0; i < schedules.size(); i++) {
            if (schedules.get(i).getUserId() == SmartReminder.myAccount.getId()) {
                int date = schedule.getBeginTime().getDate();
                int month = schedule.getBeginTime().getMonth();
                int year = schedule.getBeginTime().getYear();
                if (schedules.get(i).getBeginTime().getDate() == date && schedules.get(i).getBeginTime().getMonth() == month && schedules.get(i).getBeginTime().getYear() == year) {
                    if (!isAdding && schedules.get(i).getId() == tmpSchId) {
                        System.out.println("skip this overlab for editting");
                    }
                    else if ( isAdding || (!isAdding && schedules.get(i).getId() != tmpSchId) ) {
                        int[] schedulePhase = new int[48];
                        for (int j = 0; j < schedulePhase.length; j++) {
                            if(j==0)
                            {
                                //System.out.println("Begin"+getPhase(schedules.get(i).getBeginTime()));
                                //System.out.println("Fin"+getPhase(schedules.get(i).getFinishTime()));
                                //System.out.println("J=0 title: " + schedules.get(i).getTitle());
                            }
                            if (j >= getPhase(schedules.get(i).getBeginTime()) && j < getPhase(schedules.get(i).getFinishTime())) {
                                schedulePhase[j] = 1;
                                //System.out.println("title: " + schedules.get(i).getTitle());
                                //System.out.println("kan eiei: " + j + " I:"+i);

                            }
                        }
                        System.out.println("Old Sch " + i);
                        for (int j = 0; j < schedulePhase.length; j++) {
                            System.out.print(schedulePhase[j]);
                        }
                        System.out.println("id: " + schedules.get(i).getId());
                        System.out.println("BeginPhase: " + getPhase(schedules.get(i).getBeginTime()));
                        System.out.println("FinPhase: " + getPhase(schedules.get(i).getFinishTime()));
                        /*System.out.println("");
                        System.out.println("");
                        for (int j = 0; j < newSchedulePhase.length; j++) {
                            System.out.print(newSchedulePhase[j]);
                        }
                        System.out.println("");*/

                        for (int j = 0; j < schedulePhase.length; j++) {
                            if(newSchedulePhase[j] == 1 & schedulePhase[j] == 1) {
                                System.out.println(j+" : Beam eieieie : Old "+schedulePhase[j] +" New : "+newSchedulePhase[j]+" I:"+i);
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
    public void addSchedule(Schedule schedule) {
        isAdding = true;
        tmpSchId = 0;
        if(!schedules.isEmpty()) {
            if(isAvailable(schedule)) {
                ObjectDBServices odb = new ObjectDBServices();
                EntityManager em = odb.openConnection();
                //EntityManagerFactory emf = Persistence.createEntityManagerFactory("./db/database.odb");
                //EntityManager em = emf.createEntityManager();
                em.getTransaction().begin();
                em.persist(schedule);
                em.getTransaction().commit();
                // Close the database connection:
                odb.closeConnection();
                //SmartReminder.emf.close();
                schedules.add(schedule);
            }
            else {
                System.out.println(schedule.getTitle() + " schedule is Overlap.");
                
            }
                
        }
        else {
            System.out.println(schedule.getTitle() + " is the first schedule.");
            ObjectDBServices odb = new ObjectDBServices();
            EntityManager em = odb.openConnection();
            //EntityManagerFactory emf = Persistence.createEntityManagerFactory("./db/database.odb");
            //EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            em.persist(schedule);
            em.getTransaction().commit();
            // Close the database connection:
            odb.closeConnection();
            //SmartReminder.emf.close();
            schedules.add(schedule);
            System.out.println("Adding success!!!");
        }
    }    
    public void editSchedule() {
        isAdding = false;
        ObjectDBServices odb = new ObjectDBServices();
        EntityManager em = odb.openConnection();
        //EntityManagerFactory emf = Persistence.createEntityManagerFactory("./db/database.odb");
        //EntityManager em = emf.createEntityManager();
        Schedule sch = em.find(Schedule.class, AddingScheduleController.tmpId);
        em.getTransaction().begin();
        
        String[] tmpStr = AddingScheduleController.tmpStartTime.getSelectionModel().getSelectedItem().split("\\.");
        int tmpBeginHrs = Integer.parseInt(tmpStr[0]);
        int tmpBeginMins = Integer.parseInt(tmpStr[1]);
                
        tmpStr = AddingScheduleController.tmpFinishTime.getSelectionModel().getSelectedItem().split("\\.");
        int tmpFinishHrs = Integer.parseInt(tmpStr[0]);
        int tmpFinishMins = Integer.parseInt(tmpStr[1]);
        
        Date tmpBegin = sch.getBeginTime();
        tmpBegin.setHours(tmpBeginHrs);
        tmpBegin.setMinutes(tmpBeginMins);
        Date tmpFinish = sch.getFinishTime();
        tmpFinish.setHours(tmpFinishHrs);
        tmpFinish.setMinutes(tmpFinishMins);
        tmpSchId = sch.getId();
        //System.out.println("IDDDDDDDDDDD: " + sch.getId());
        
        Schedule tmpSch = new Schedule(sch.getUserId(), AddingScheduleController.tmpScheduleName.getText(), AddingScheduleController.tmpDetail.getText(), tmpBegin, tmpFinish, Integer.parseInt(AddingScheduleController.select_pre), AddingScheduleController.tmpCheckRepeat.isSelected(), AddingScheduleController.tmpCheckAlarm.isSelected());
        
        if(!schedules.isEmpty()) {
            if(isAvailable(tmpSch)) {
                System.out.println("Able to edit schedule!!");
                String[] str = AddingScheduleController.tmpStartTime.getSelectionModel().getSelectedItem().split("\\.");
                int beginHrs = Integer.parseInt(str[0]);
                int beginMins = Integer.parseInt(str[1]);

                str = AddingScheduleController.tmpFinishTime.getSelectionModel().getSelectedItem().split("\\.");
                int finishHrs = Integer.parseInt(str[0]);
                int finishMins = Integer.parseInt(str[1]);

                sch.setTitle(AddingScheduleController.tmpScheduleName.getText());
                sch.setDetail(AddingScheduleController.tmpDetail.getText());

                sch.getBeginTime().setHours(beginHrs);
                sch.getBeginTime().setMinutes(beginMins);

                sch.getFinishTime().setHours(finishHrs);
                sch.getFinishTime().setMinutes(finishMins);

                sch.setTimeBeforeAlert(Integer.parseInt(AddingScheduleController.tmpPreAlarmList.getSelectionModel().getSelectedItem()));
                sch.setIsRepeat(AddingScheduleController.tmpCheckRepeat.isSelected());
                sch.setIsAlert(AddingScheduleController.tmpCheckAlarm.isSelected());

                long tmpSchId = sch.getId();
                List<Schedule> list = SmartReminder.myCalendar.getSchedule(sch.getBeginTime(), SmartReminder.myAccount);
                for (int i = 0; i < list.size(); i++) {
                    if(list.get(i).getId() == tmpSchId) {
                        list.get(i).setTitle(AddingScheduleController.tmpScheduleName.getText());
                        list.get(i).setDetail(AddingScheduleController.tmpDetail.getText());
                        list.get(i).getBeginTime().setHours(beginHrs);
                        list.get(i).getBeginTime().setMinutes(beginMins);
                        list.get(i).getFinishTime().setHours(finishHrs);
                        list.get(i).getFinishTime().setMinutes(finishMins);
                        list.get(i).setTimeBeforeAlert(Integer.parseInt(AddingScheduleController.tmpPreAlarmList.getSelectionModel().getSelectedItem()));
                        list.get(i).setIsRepeat(AddingScheduleController.tmpCheckRepeat.isSelected());
                        list.get(i).setIsAlert(AddingScheduleController.tmpCheckAlarm.isSelected());
                    }
                }

                em.getTransaction().commit();
                odb.closeConnection();
            }
            else {
                odb.closeConnection();
                System.out.println(sch.getTitle() + " schedule is Overlap. (updating)");
            }
        }
        
        
    }    
    public List<Schedule> getSchedule(Date schDate, UserAccount user) {
        int date = schDate.getDate();
        int month = schDate.getMonth();
        int year = schDate.getYear();
        ArrayList<Schedule> list = new ArrayList<>();
        //System.out.println(schedules.size());
        for (int i = 0; i < schedules.size(); i++) {
            //System.out.println(schedules.get(i).getBeginTime().getDay() + " " + date);
            if (schedules.get(i).getBeginTime().getDate()== date & schedules.get(i).getBeginTime().getMonth() == month & schedules.get(i).getBeginTime().getYear() == year) {
                if (schedules.get(i).getUserId() == user.getId()) {
                    list.add(schedules.get(i));
                }
            }
        }
        //System.out.println(list.toString());
         
        return (List)list;
    }    
    public void showSchedule() {
        
        /*EntityManager em = SmartReminder.emf.createEntityManager();
 
        // Store 1000 Point objects in the database:
        em.getTransaction().begin();
        
        TypedQuery<Schedule> query = em.createQuery("SELECT sch FROM Schedule sch", Schedule.class);
        schedules = query.getResultList();
        
        em.close();
        emf.close();
        //SmartReminder.emf.close();*/
        
        for (int i = 0; i < schedules.size(); i++) {
            System.out.println(schedules.get(i).getTitle());
            System.out.println(schedules.get(i).getDetail());
        }
    }    
    public void update(){
        ObjectDBServices odb = new ObjectDBServices();
        EntityManager em = odb.openConnection();
        //EntityManagerFactory emf = Persistence.createEntityManagerFactory("./db/database.odb");
        //EntityManager em = emf.createEntityManager();
 
        // Store 1000 Point objects in the database:
        em.getTransaction().begin();
        
        TypedQuery<Schedule> query = em.createQuery("SELECT sch FROM Schedule sch", Schedule.class);
        schedules = query.getResultList();
        
        odb.closeConnection();
    }
}
