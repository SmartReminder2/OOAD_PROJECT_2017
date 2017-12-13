/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartreminder;

import classes.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

/**
 * FXML Controller class
 *
 * @author User
 */
public class SignUpPageController implements Initializable {

    //attributes
    @FXML
    private TextField username_fill;
    @FXML
    private PasswordField password_fill;
    @FXML
    private PasswordField password_confirm;
    @FXML
    private TextField tel_fill;
    @FXML
    private Pane error_pane;
    @FXML
    private Circle circle8;
    @FXML
    private Circle circle5;
    @FXML
    private Circle circle3;
    @FXML
    private Circle circle1;
    @FXML
    private Circle circle6;
    @FXML
    private Circle circle7;
    @FXML
    private Circle circle4;
    @FXML
    private Circle circle2;
    @FXML
    private Circle circle10;
    @FXML
    private Circle circle11;
    @FXML
    private Circle circle12;
    @FXML
    private Circle circle9;
    
    /**
     * Initializes the controller class.
     */
    //methods
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Animation circle = new Animation();
        circle.playCircleAnimation(circle1,1.0,0.1,1800);
        circle.playCircleAnimation(circle2,1.0,0.1,1800);
        circle.playCircleAnimation(circle3,1.0,0.1,1800);
        circle.playCircleAnimation(circle4,1.0,0.1,1800);
        
        circle.playCircleAnimation(circle5,1.0,0.1,1200);
        circle.playCircleAnimation(circle6,1.0,0.1,1200);
        circle.playCircleAnimation(circle7,1.0,0.1,1200);
        circle.playCircleAnimation(circle8,1.0,0.1,1200);
        
        circle.playCircleAnimation(circle9,1.0,0.1,800);
        circle.playCircleAnimation(circle10,1.0,0.1,800);
        circle.playCircleAnimation(circle11,1.0,0.1,800);
        circle.playCircleAnimation(circle12,1.0,0.1,800);
    }    
    @FXML
    private void onSignUpClick(ActionEvent event) {
        String username = username_fill.getText();
        String password = password_fill.getText();
        String tel = tel_fill.getText();
        UserAccount user = new UserAccount(username, password, tel);
        if(SmartReminder.myUserAccountServices.createUserAccounts(user, password_confirm.getText())) {
            error_pane.setVisible(false);
            SmartReminder.pageController.next("LoginPage");
        }
        else {
            error_pane.setVisible(true);
        }
    }
    @FXML
    private void onBackClick(ActionEvent event) {
        error_pane.setVisible(false);
        SmartReminder.pageController.next("LoginPage");
    }
}
