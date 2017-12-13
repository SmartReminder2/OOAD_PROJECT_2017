/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 *
 * @author kan
 */
public class Sound {
    
    //attributes
    private Media soundM;
    private MediaPlayer sound;
    
    //constructors
    public Sound(String url) {
        soundM = new Media(new File("src/Sound/"+url).toURI().toString());
        sound = new MediaPlayer(soundM);
    }
    
    //methods
    public void playSound(){ 
        sound.setVolume(1.0);
        sound.setCycleCount(MediaPlayer.INDEFINITE);
        sound.play();   
    }
    public void stopSound() {
        sound.stop();
    }
}
