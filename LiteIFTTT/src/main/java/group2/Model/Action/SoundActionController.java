/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group2.Model.Action;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Observable;
import java.util.Observer;
import javafx.scene.media.Media;
import javafx.scene.media.MediaException;
import javafx.scene.media.MediaPlayer;

/**
 *
 * @author Alessandro Accarino
 */
public class SoundActionController implements Observer {

    @Override
    public void update(Observable o, Object arg) {
        try {
            File musicFile = new File((String) arg);

            // Check if the file exists
            if (!musicFile.exists()) {
                throw new FileNotFoundException("File not found: " + (String) arg);
            }

            // Media creation
            Media media = new Media(musicFile.toURI().toString());
            final MediaPlayer mediaPlayer = new MediaPlayer(media);

            // Playing the media
            mediaPlayer.play();

        } catch (FileNotFoundException e) {
            System.err.println("Exception: " + e.getMessage()); // File not found exception
        } catch (MediaException e) {
            System.err.println("Exception: " + e.getMessage()); // File not supported
        }
    } 
    
}
