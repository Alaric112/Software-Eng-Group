/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group2.Model.Action;


import java.util.Observable;



/**
 *
 * @author Lore
 *
 * 
 * Implementation of the Action interface for playing sound files.
 * This class allows the execution of sound-related actions, such as playing audio files.
 * It supports setting the path of the sound file and playing the specified media.
 */
public class SoundAction extends Observable implements Action {

    /** The path to the sound file. */
    private String path;
    private boolean hasObserver = false;
    
    /**
     * Default constructor for SoundAction.
     * Initializes the path to an empty string.
     */
    public SoundAction() {
        this.path = "Asset//AtDoom'sGate.mp3";
    }

    /**
     * Executes the sound action by playing the specified audio file.
     * It checks if the file exists, creates a media instance, and plays the media.
     * If the file is not found or there is an issue with the media, exceptions are caught and printed.
     * @see SoundActionController
     */
    @Override
    public void execute() {
        
        addWathcer();
        setChanged();
        notifyObservers(path);
    }

    private void addWathcer(){
        if(!hasObserver){
            this.addObserver(new MessageActionController());
            hasObserver = true;
        }
    }    
    
    /**
     * Gets the current path to the sound file.
     *
     * @return The path to the sound file.
     */
    public String getPath() {
        return path;
    }

    /**
     * Sets the path to the sound file.
     *
     * @param path The new path to the sound file.
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * Sets the path to the sound file using a FileChooser dialog.
     * Opens a file chooser dialog for the user to select a sound file.
     * Sets the path to the selected file.
     */

        @Override
    public String toString() {
        return "SoundAction";
    } 
}   
    


    
  
