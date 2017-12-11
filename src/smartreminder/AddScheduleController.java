/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartreminder;

import classes.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author User
 */
public class AddScheduleController implements Initializable {

    //attributes
    @FXML
    private AnchorPane timeTable_pane;
    @FXML
    private Text headLabel;
    
    public static  AnchorPane timeTable_paneTemp;
    public static Text label;
    public static int xPos = 208;
    public static int yPos = 3;
    
    /**
     * Initializes the controller class.
     */
    //methods
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            
        timeTable_paneTemp = timeTable_pane;
        label = headLabel ; 
        // TODO
    }    
    @FXML
    private void adding(ActionEvent event) {
        AddingScheduleController.tmpId = 0;
        AddingScheduleController.setInit();
        SmartReminder.primaryStage.getScene().setRoot(SmartReminder.addingSchedulePage);  
    }
    @FXML
    private void backBtn(ActionEvent event) {
        SmartReminder.primaryStage.getScene().setRoot(SmartReminder.homePage);  
        GroupPageController.tmpGroupDetail = null;
        GroupPageController.tmpGroup_list.getSelectionModel().clearSelection();
    }
    
    static void setTimeTable() {

        timeTable_paneTemp.getChildren().clear();
        timeTable_paneTemp.getChildren().add(SmartReminder.timeTablePage);
        if(HomePageController.isPersonal)
        {
            label.setText("DAILY SCHEDULE");
        }
        else
        {
            label.setText("GROUP SCHEDULE");
        } 
        ArrayList<Color> colorList = new ArrayList();
        colorList.add(Color.SKYBLUE);
        colorList.add(Color.ORANGE);
        colorList.add(Color.PALEGREEN);
        System.out.println("setTimeTable");
        
        //personal schedules
        if (HomePageController.isPersonal) {
            SmartReminder.myCalendar.update();
            System.out.println("BeginTime:" + SmartReminder.beginTime.toString());
            List<Schedule> list = new ArrayList<>();
            list.addAll(SmartReminder.myCalendar.getSchedule(SmartReminder.beginTime, SmartReminder.myAccount));
            
            for (int i = 0; i < list.size(); i++) {
                System.out.println(list.get(i).getTitle());
                System.out.println("222");
            }

            for (int j = 0; j < list.size(); j++) {

                int beginPhase = PersonalCalendar.getPhase(list.get(j).getBeginTime());
                int finishPhase = PersonalCalendar.getPhase(list.get(j).getFinishTime());
                if(finishPhase == 0) {
                    finishPhase = 48;
                }

                int[] num = new int[48];
                for (int i = beginPhase; i < finishPhase; i++) {
                    num[i] = 1;
                }
                /*for (int i = 0; i < num.length; i++) {
                    System.out.println(num[i]);
                }*/

                int firstPos = beginPhase;
                int lastPos = finishPhase - 1;
                System.out.println(firstPos + " " + lastPos);
                int height = 62;
                for (int i = 0; i < 48; i++) {

                    if(num[i] == 1) {
                        System.out.println(i);
                        Rectangle rect = new Rectangle(xPos, yPos, 600, height);
                        rect.setFill(colorList.get(j % 3));
                        rect.setVisible(true);
                        timeTable_paneTemp.getChildren().add(rect);
                    }

                    if(i == Math.ceil((lastPos - firstPos)/2.0) + firstPos) {
                        /*String text = list.get(0).getTitle().substring(0, 8);
                        text = text.concat(" ..");
                        System.out.println(text);*/
                        Button btn = new Button("EDIT");
                        Label label = new Label(list.get(j).getTitle());
                        label.setLayoutX(xPos + 10);
                        label.setLayoutY(yPos - 20);
                        label.setVisible(true);

                        btn.setLayoutX(xPos + 10);
                        btn.setLayoutY(yPos);
                        btn.setVisible(true);

                        timeTable_paneTemp.getChildren().add(btn);
                        timeTable_paneTemp.getChildren().add(label);

                        final long tmpI = list.get(j).getId();

                        btn.setOnAction(new EventHandler<ActionEvent>()
                        {
                            @Override
                            public void handle(ActionEvent e)
                            {
                                System.out.println("Hello World" + tmpI);
                                AddingScheduleController.tmpId = tmpI;
                                AddingScheduleController.setInit();
                                SmartReminder.primaryStage.getScene().setRoot(SmartReminder.addingSchedulePage);
                            }

                        }

                        );


                        System.out.println(firstPos + " " + lastPos);
                        System.out.println("btn" + i);
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
            SmartReminder.groupCalendar.update();
            List<GroupSchedule> list = new ArrayList<>();
            list.addAll(SmartReminder.groupCalendar.getSchedule(SmartReminder.beginTime, GroupPageController.tmpGroupDetail));
            
            SmartReminder.myCalendar.update();
            List<Schedule> memberSchedules = new ArrayList<>();
            memberSchedules.addAll(SmartReminder.groupCalendar.getMemberSchedules(GroupPageController.select_GroupName, GroupPageController.createrUsername));
            
            //for member schedules
            for (int i = 0; i < memberSchedules.size(); i++) {
                System.out.println(memberSchedules.get(i).getTitle());
                System.out.println("222");
            }

            for (int j = 0; j < memberSchedules.size(); j++) {

                int beginPhase = PersonalCalendar.getPhase(memberSchedules.get(j).getBeginTime());
                int finishPhase = PersonalCalendar.getPhase(memberSchedules.get(j).getFinishTime());
                if(finishPhase == 0) {
                    finishPhase = 48;
                }

                int[] num = new int[48];
                for (int i = beginPhase; i < finishPhase; i++) {
                    num[i] = 1;
                }
                /*for (int i = 0; i < num.length; i++) {
                    System.out.println(num[i]);
                }*/

                int firstPos = beginPhase;
                int lastPos = finishPhase - 1;
                System.out.println(firstPos + " " + lastPos);
                int height = 62;
                for (int i = 0; i < 48; i++) {

                    if(num[i] == 1) {
                        System.out.println(i);
                        Rectangle rect = new Rectangle(xPos, yPos, 600, height);
                        rect.setFill(Color.GREY);
                        rect.setVisible(true);
                        timeTable_paneTemp.getChildren().add(rect);
                    }

                    /*if(i == Math.ceil((lastPos - firstPos)/2.0) + firstPos) {

                        Button btn = new Button("EDIT");
                        Label label = new Label(memberSchedules.get(j).getTitle());
                        label.setLayoutX(xPos + 10);
                        label.setLayoutY(yPos - 20);
                        label.setVisible(true);

                        btn.setLayoutX(xPos + 10);
                        btn.setLayoutY(yPos);
                        btn.setVisible(true);

                        timeTable_paneTemp.getChildren().add(btn);
                        timeTable_paneTemp.getChildren().add(label);

                        final long tmpI = memberSchedules.get(j).getId();

                        btn.setOnAction(new EventHandler<ActionEvent>()
                        {
                            @Override
                            public void handle(ActionEvent e)
                            {
                                System.out.println("Hello World" + tmpI);
                                AddingScheduleController.tmpId = tmpI;
                                AddingScheduleController.setInit();
                                SmartReminder.primaryStage.getScene().setRoot(SmartReminder.addingSchedulePage);
                            }

                        }

                        );


                        System.out.println(firstPos + " " + lastPos);
                        System.out.println("btn" + i);
                    }*/

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
            for (int i = 0; i < list.size(); i++) {
                System.out.println(list.get(i).getTitle());
                System.out.println("222");
            }

            for (int j = 0; j < list.size(); j++) {

                int beginPhase = PersonalCalendar.getPhase(list.get(j).getBeginTime());
                int finishPhase = PersonalCalendar.getPhase(list.get(j).getFinishTime());
                if(finishPhase == 0) {
                    finishPhase = 48;
                }

                int[] num = new int[48];
                for (int i = beginPhase; i < finishPhase; i++) {
                    num[i] = 1;
                }
                /*for (int i = 0; i < num.length; i++) {
                    System.out.println(num[i]);
                }*/

                int firstPos = beginPhase;
                int lastPos = finishPhase - 1;
                System.out.println(firstPos + " " + lastPos);
                int height = 62;
                for (int i = 0; i < 48; i++) {

                    if(num[i] == 1) {
                        System.out.println(i);
                        Rectangle rect = new Rectangle(xPos, yPos, 600, height);
                        rect.setFill(colorList.get(j % 3));
                        rect.setVisible(true);
                        timeTable_paneTemp.getChildren().add(rect);
                    }

                    if(i == Math.ceil((lastPos - firstPos)/2.0) + firstPos) {
                        /*String text = list.get(0).getTitle().substring(0, 8);
                        text = text.concat(" ..");
                        System.out.println(text);*/
                        Button btn = new Button("EDIT");
                        Label label = new Label(list.get(j).getTitle());
                        label.setLayoutX(xPos + 10);
                        label.setLayoutY(yPos - 20);
                        label.setVisible(true);

                        btn.setLayoutX(xPos + 10);
                        btn.setLayoutY(yPos);
                        btn.setVisible(true);

                        timeTable_paneTemp.getChildren().add(btn);
                        timeTable_paneTemp.getChildren().add(label);

                        final long tmpI = list.get(j).getId();

                        btn.setOnAction(new EventHandler<ActionEvent>()
                        {
                            @Override
                            public void handle(ActionEvent e)
                            {
                                System.out.println("Hello World" + tmpI);
                                AddingScheduleController.tmpId = tmpI;
                                AddingScheduleController.setInit();
                                SmartReminder.primaryStage.getScene().setRoot(SmartReminder.addingSchedulePage);
                            }

                        }

                        );


                        System.out.println(firstPos + " " + lastPos);
                        System.out.println("btn" + i);
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
}
