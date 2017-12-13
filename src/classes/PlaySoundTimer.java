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
    
    //attributes
    private Boolean isAlarm;
    private Timer t;
    
    //constructors
    public PlaySoundTimer() {
        t = new Timer(1000, new ActionListener() {
        @Override
        public void actionPerformed(java.awt.event.ActionEvent ae) {
            List<PersonalSchedule> schedules = SmartReminder.myScheduleServices.getSchedule(new Date(), SmartReminder.myAccount);
            for (int i = 0; i < schedules.size(); i++) {
                if (schedules.get(i).getBeginTime().getHours() == new Date().getHours()
                && schedules.get(i).getBeginTime().getMinutes() == new Date().getMinutes()
                && schedules.get(i).getIsAlert()) 
                {
                    if (!isAlarm) {
                        SmartReminder.alarmSound.playSound();
                        isAlarm = true;
                    }
                }
                else {
                    if (isAlarm) {
                        SmartReminder.alarmSound.stopSound();
                        isAlarm = false;
                    }
                }
            }
        }
       });
       isAlarm = false;
    }
    
    //methods
    public void start() {
        t.start();
    }
    public void stop() {
        t.stop();
    }
}
