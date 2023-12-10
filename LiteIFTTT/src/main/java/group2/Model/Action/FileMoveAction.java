/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group2.Model.Action;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 *
 * @author soniabruno
 */

/**
 * An implementation of the {@link Action} interface that represents
 * the action of moving a file from a source path to a destination path.
 * If the source file exists, it is moved to the specified destination path.
 */
public class FileMoveAction implements Action {

    private String sourcePath; 
    private String destinationPath;

    public FileMoveAction() {
        this.sourcePath = "";
        this.destinationPath = "";
    }  

    public String getSourcePath() {
        return sourcePath;
    }

    public String getDestinationPath() {
        return destinationPath;
    }
    
    /**
     * Sets the source path of the file to be moved.
     *
     * @param sourcePath The new source path.
     */
    public void setSourcePath(String sourcePath) {
        this.sourcePath = sourcePath;
    }

    /**
     * Sets the destination path where the file will be moved to.
     *
     * @param destinationPath The new destination path.
     */
    public void setDestinationPath(String destinationPath) {
        this.destinationPath = destinationPath;
    }

    
    /**
     * Executes the file move action by moving the file from the source
     * path to the destination path. If the source file exists, it is moved,
     * replacing any existing file at the destination.
     */
    @Override
    public void execute() {
        
        Path srcPath = Paths.get(sourcePath);
        Path dstPath = Paths.get(destinationPath);        
        
        try {
            if (Files.exists(srcPath)) {
                Files.move(srcPath, dstPath.resolve(srcPath.getFileName()), StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (IOException e) {
            System.err.println("Error : " + e.getMessage());
            e.printStackTrace();
        }
    }
    
        @Override
    public String toString() {
        return "File moved in: " + destinationPath;
    } 
}
