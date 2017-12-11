/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import javafx.animation.FadeTransition;
import javafx.animation.Timeline;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

/**
 *
 * @author kan
 */
public class Animation {
    
    public void playCircleAnimation(Circle c, double startFadeVal, double stopFadeVal, int duration){
        FadeTransition ft = new FadeTransition(Duration.millis(duration), c);
        ft.setFromValue(startFadeVal);
        ft.setToValue(stopFadeVal);
        ft.setCycleCount(Timeline.INDEFINITE);
        ft.setAutoReverse(true);
        ft.play();
    }
}
