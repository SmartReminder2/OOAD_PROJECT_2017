/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;
import javax.swing.Timer;
import smartreminder.*;

/**
 *
 * @author kan
 */
public class PlaySoundTimer {
    Boolean isAlarm;
    Timer t;

    public PlaySoundTimer() {
        t = new Timer(1000, new ActionListener() {
        @Override
        public void actionPerformed(java.awt.event.ActionEvent ae) {
            List<Schedule> schedules = SmartReminder.myCalendar.getSchedule(new Date(), SmartReminder.myAccount);
            for (int i = 0; i < schedules.size(); i++) {
                if (schedules.get(i).getBeginTime().getHours() == new Date().getHours()
                && schedules.get(i).getBeginTime().getMinutes() == new Date().getMinutes()
                && schedules.get(i).getIsAlert()) 
                {
                    System.out.println(schedules.get(i).getTitle());
                    if (!isAlarm) {
                        //playSound("alarm.wav");
                        SmartReminder.alarmSound.playSound();
                        isAlarm = true;
                    }
                }
                else {
                    if (isAlarm) {
                        //stopSound();
                        SmartReminder.alarmSound.stopSound();
                        isAlarm = false;
                    }
                }
            }
            System.out.println(new Date().getHours() + ":" + new Date().getMinutes() + ":" + new Date().getSeconds());
        }
       });
       isAlarm = false;
    }
    
    public void start() {
        t.start();
    }
    public void stop() {
        t.stop();
    }
}
