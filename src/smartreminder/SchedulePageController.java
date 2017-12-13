/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartreminder;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author User
 */
public class SchedulePageController implements Initializable {

    //attributes
    @FXML
    private AnchorPane timeTable_pane;
    @FXML
    private Text headLabel;
    
    /**
     * Initializes the controller class.
     */
    //methods
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        SmartReminder.tmpTimeTable_pane = timeTable_pane;
        SmartReminder.tmpHeadLabel_add = headLabel ; 
    }    
    @FXML
    private void back(ActionEvent event) {
        SmartReminder.pageController.next("HomePage");
    }
    @FXML
    private void add(ActionEvent event) {
        SmartReminder.tmpId = 0;
        SmartReminder.pageController.next("ScheduleMakerPage");
    }
}