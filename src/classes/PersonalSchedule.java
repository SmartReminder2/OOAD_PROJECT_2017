/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.util.Date;
import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author kan
 */
@Entity
public class PersonalSchedule implements Serializable {
    
    //attributes
    private static final long serialVersionUID = 1L;
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    private long userId;
    private String title;
    private String detail;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date beginTime;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date finishTime;
    private int timeBeforeAlert;
    private Boolean isRepeat;
    private Boolean isAlert;
    
    //constructors
    public PersonalSchedule() {}
    public PersonalSchedule(long userId, String title, String message, Date start, Date finish, int timeBeforeAlert, Boolean isRepeat, Boolean isAlert) {
        this.userId = userId;
        this.title = title;
        this.detail = message;
        this.beginTime = start;
        this.finishTime = finish;
        this.timeBeforeAlert = timeBeforeAlert;
        this.isRepeat = isRepeat;
        this.isAlert = isAlert;
    }
    
    //methods
    public void setTitle(String title) {
        this.title = title;
    }
    public void setDetail(String detail) {
        this.detail = detail;
    }
    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }
    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }
    public void setTimeBeforeAlert(int timeBeforeAlert) {
        this.timeBeforeAlert = timeBeforeAlert;
    }
    public void setIsRepeat(Boolean isRepeat) {
        this.isRepeat = isRepeat;
    }
    public void setIsAlert(Boolean isAlert) {
        this.isAlert = isAlert;
    }
    public long getId() {
        return id;
    }
    public long getUserId() {
        return userId;
    }    
    public String getTitle() {
        return title;
    }
    public String getDetail() {
        return detail;
    }
    public Date getBeginTime() {
        return beginTime;
    }
    public Date getFinishTime() {
        return finishTime;
    }
    public int getTimeBeforeAlert() {
        return timeBeforeAlert;
    }
    public Boolean getIsRepeat() {
        return isRepeat;
    }    
    public Boolean getIsAlert() {
        return isAlert;
    }
}
