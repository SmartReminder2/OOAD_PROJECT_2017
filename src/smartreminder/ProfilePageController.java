/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartreminder;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;

/**
 * FXML Controller class
 *
 * @author 58010622
 */
public class ProfilePageController implements Initializable {
    
    //attributes
    @FXML
    private Circle imageProfile;
    @FXML
    private Label username_label;
    
    /**
     * Initializes the controller class.
     */
    //methods
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        SmartReminder.tmpUsername_label = username_label;
        SmartReminder.tmpImageProfile = imageProfile;
    }   
}