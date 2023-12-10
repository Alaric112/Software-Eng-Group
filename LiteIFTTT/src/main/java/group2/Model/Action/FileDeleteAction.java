/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group2.Model.Action;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author soniabruno
 */


/**
 * An implementation of the {@link Action} interface that represents
 * the action of deleting a file specified by its path.
 * If the file exists, it is deleted, and an exception is thrown if the
 * deletion fails.
 */
public class FileDeleteAction implements Action {

    private String path;

    public FileDeleteAction() {
        this.path = "";
    }

    public void setPath(String filePath) {
        this.path = filePath;
    }

    /**
     * Executes the file delete action by attempting to delete the specified file.
     * If the file exists, it is deleted, and an exception is thrown if the deletion fails.
     */
    @Override
    public void execute() {
        
        Path filePath = Paths.get(path);
        try {
            if (Files.exists(filePath)) {
                if (!Files.deleteIfExists(filePath)) {
                    throw new IOException("Unable to delete the file: " + filePath.toAbsolutePath());
                }
            }
        } catch (SecurityException | IOException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public String toString() {
        return "File deleted : "+path;
    } 
}

