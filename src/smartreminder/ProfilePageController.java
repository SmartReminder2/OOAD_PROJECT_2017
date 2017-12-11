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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;
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
    private ImageView imageView;
    @FXML
    private Label username_label;
    @FXML
    private Label twi_label;
    @FXML
    private Label ig_label;
    @FXML
    private Label email_label;
    @FXML
    private Label tal_label;
    @FXML
    private Label facebook_label;
    
    public static Circle tmpImageProfile;
    public static Image img;   
    public static String username;
    //public static ImageView imageViewTemp;
    public static Label tmpUsername_label;
    /**
     * Initializes the controller class.
     */
    //methods
    @Override
    public void initialize(URL url, ResourceBundle rb) {
 
        //imageViewTemp = imageView;
        tmpUsername_label = username_label;
        tmpImageProfile = imageProfile;
        
        
    }   
    public static void setInit() {   
        username = SmartReminder.myAccount.getUserName();
        //img = imageViewTemp.getImage();
        
        img = new Image("file:src/Image/defaultUser.png");
 
        tmpImageProfile.setFill(new ImagePattern(img));
        tmpUsername_label.setText(username);
    }
}
