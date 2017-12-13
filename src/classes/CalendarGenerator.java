/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.util.Calendar;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import smartreminder.SmartReminder;

/**
 *
 * @author kan
 */
public class CalendarGenerator {
    
    //attributes
    private static CalendarGenerator instance = new CalendarGenerator();
    
    //constructors
    private CalendarGenerator(){}
    
    //methods
    public static CalendarGenerator getInstance(){
        return instance;
    }
    public void generateCalendar(){
        
        int count_day = 1;
        int rectangle_loop = 1;
        
        Calendar c = Calendar.getInstance();
        //month in Calendar class start at 0(0 = january)
        c.set(SmartReminder.selectedYear, SmartReminder.selectedMonth, 1);
        //day of week started at 1 (1 = sunday)
        int day_of_week = c.get(Calendar.DAY_OF_WEEK);
        // Get the number of days in that month
        int daysInMonth = c.getActualMaximum(Calendar.DAY_OF_MONTH); 
        int previousMonth = (SmartReminder.selectedMonth == 0) ? 11 : SmartReminder.selectedMonth-1;
        c.set(SmartReminder.selectedYear, previousMonth, 1);
        //Get the number of days in that previous month
        int daysInPreviousMonth = c.getActualMaximum(Calendar.DAY_OF_MONTH);
        
        int startDate;
        if(day_of_week == 1){
            startDate = 1;           
        }
        else {
            startDate = (daysInPreviousMonth - (day_of_week - 1)) + 1;
        }
        //int dayInMonth = 1;
        for (Node child : SmartReminder.tmpCalendarPane.getChildren()) {
            if(child.getId()!=null){
                if(child.getId().contains("dayBlock")){
                    SmartReminder.dayBlock[rectangle_loop-1] = (Rectangle) child;
                    rectangle_loop++;
                }
                if(child.getId().contains("dayLabel")){
                    SmartReminder.dayLabel[count_day-1] = (Label) child;
                    if(count_day < day_of_week){
                        SmartReminder.dayLabel[count_day-1].setText(String.valueOf(startDate++));
                        SmartReminder.dayBlock[count_day-1].setFill(Color.AZURE);
                        startDate = (count_day+1==day_of_week) ? 1 : startDate;
                    }
                    else if(count_day < daysInMonth+day_of_week)
                    {
                        if(startDate == SmartReminder.currentDate && SmartReminder.selectedYear == SmartReminder.currentYear && SmartReminder.selectedMonth == SmartReminder.currentMonth)
                        {
                           SmartReminder.dayBlock[count_day-1].setFill(Color.CORAL);
                        }  
                        else
                        {   
                            if(SmartReminder.selectedYear != SmartReminder.currentYear||SmartReminder.selectedMonth != SmartReminder.currentMonth) {
                                SmartReminder.dayBlock[count_day-1].setFill(Color.WHITE);
                            }
                        }
                       SmartReminder.dayLabel[count_day-1].setText(String.valueOf(startDate++));
                       startDate = (startDate-1==daysInMonth) ? 1 : startDate;
                    }
                    else
                    {
                       SmartReminder.dayBlock[count_day-1].setFill(Color.AZURE);
                       SmartReminder.dayLabel[count_day-1].setText(String.valueOf(startDate++));
                    }
                    count_day++;
                }
            }
        }
    }
}
