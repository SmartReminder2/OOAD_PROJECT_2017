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
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author 58010622
 */
public class LoginPageController implements Initializable {

    /**
     * Initializes the controller class.
     */ 
    //attributes
    @FXML
    private Pane login_pane;
    
    //methods
    @Override   
    public void initialize(URL url, ResourceBundle rb) {
        // TODO 
        SmartReminder.primaryPane = login_pane; //not yet
    }    
    @FXML
    private void click(ActionEvent event) {
        SmartReminder.primaryPane.getChildren().clear(); //not yet
        SmartReminder.primaryPane.getChildren().add(SmartReminder.fillIdPassword); //not yet
    }
}
