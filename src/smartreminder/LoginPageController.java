/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartreminder;

import classes.*;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;

/**
 * FXML Controller class
 *
 * @author 58010622
 */
public class LoginPageController implements Initializable {
    
    //attributes
    @FXML
    private TextField id_field;
    @FXML
    private PasswordField password_field;
    @FXML
    private Circle circle1;
    @FXML
    private Circle circle2;
    @FXML
    private Circle circle3;
    @FXML
    private Circle circle4;
    @FXML
    private Circle circle5;
    @FXML
    private Circle circle7;
    @FXML
    private Circle circle6;
    @FXML
    private Circle circle8;
    @FXML
    private Circle circle12;
    @FXML
    private Circle circle11;
    @FXML
    private Circle circle9;
    @FXML
    private Circle circle10;
    @FXML
    private Circle circle13;
    @FXML
    private Circle circle14;
    @FXML
    private Circle circle16;
    @FXML
    private Circle circle15;
    
    /**
     * Initializes the controller class.
     */
    //methods
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        SmartReminder.tmpId_field = id_field;
        SmartReminder.tmpPassword_field = password_field;
        
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
        
        circle.playCircleAnimation(circle13,1.0,0.1,1500);
        circle.playCircleAnimation(circle14,1.0,0.1,1500);
        circle.playCircleAnimation(circle15,1.0,0.1,1500);
        circle.playCircleAnimation(circle16,1.0,0.1,1500);
    }
    @FXML
    private void btnLogin(ActionEvent event) {
        List<UserAccount> users = SmartReminder.myUserAccountServices.getUserAccounts();
        for (int i = 0; i < users.size(); i++) {
            if(users.get(i).getUserName().equalsIgnoreCase(id_field.getText()) && users.get(i).getPassword().equalsIgnoreCase(password_field.getText())) {
                SmartReminder.myAccount = users.get(i);
                SmartReminder.pageController.next("HomePage");
                return;
            }
        }
        SmartReminder.pageController.next("LoginFailedPage");
    }
    @FXML
    private void onSignUp(MouseEvent event) {
        SmartReminder.pageController.next("SignUpPage");
    }
    
}
