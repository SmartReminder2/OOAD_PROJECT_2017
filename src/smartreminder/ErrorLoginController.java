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

/**
 * FXML Controller class
 *
 * @author 58010622
 */
public class ErrorLoginController implements Initializable {

    /**
     * Initializes the controller class.
     */
    //methods
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    @FXML
    private void btnBack(ActionEvent event) {
        SmartReminder.primaryPane.getChildren().clear();
        SmartReminder.primaryPane.getChildren().add(SmartReminder.fillIdPassword); 
    }
}
