/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group2;

import java.io.File;
import java.io.FileNotFoundException;
import javafx.scene.media.Media;
import javafx.scene.media.MediaException;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author Lore
 */
public class SoundAction implements Action{
    private String path;
    
    public SoundAction() {
        
        this.path= "";
        
    }
    
    @Override
    public void execute() {
        try {
            File musicFile = new File(this.path);

            // Check if the file 
            if (!musicFile.exists()) {
                throw new FileNotFoundException("File not found: " + this.path);
            }

            // Media creation
            Media media = new Media(musicFile.toURI().toString());
            final MediaPlayer mediaPlayer = new MediaPlayer(media);
            
            // Playing the media
            mediaPlayer.play();
            
            // File not found exception
        } catch (FileNotFoundException e) {
            System.err.println("Exception: " + e.getMessage());
            // File not supported
        } catch (MediaException e) {
            System.err.println("Exception: " + e.getMessage());
            
        }
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
     
    public void setPath(){
        
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Open File");
        File file = chooser.showOpenDialog(new Stage());
        path = file.getPath();
        
    }
}
        
    


    
  
