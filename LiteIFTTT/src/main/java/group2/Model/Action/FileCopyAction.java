/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group2.Model.Action;

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
 * An implementation of the {@link Action} interface that performs
 * the action of copying a file from a source path to a destination path.
 */
public class FileCopyAction implements Action {
    
    private String sourcePath; 
    private String destinationPath;
    
    public FileCopyAction() {
        this.sourcePath= "";
        this.destinationPath= "";
    }

    public String getSourcePath() {
        return sourcePath;
    }

    public String getDestinationPath() {
        return destinationPath;
    }

    /**
     * Sets the source path of the file to be copied.
     *
     * @param sourcePath The new source path.
     */
    public void setSourcePath(String sourcePath) {
        this.sourcePath = sourcePath;
    }
    
    /**
     * Sets the destination path where the file will be copied to.
     *
     * @param destinationPath The new destination path.
     */
    public void setDestinationPath(String destinationPath) {
        this.destinationPath = destinationPath;
    }
    
    /**
     * Executes the file copy action by copying the file from the source
     * path to the destination path.
     */
    @Override
    public void execute() {
        try {
            Path srcPath = Paths.get(sourcePath);
            Path dstPath = Paths.get(destinationPath);
            Files.copy(srcPath, dstPath.resolve(srcPath.getFileName()), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            System.err.println("Error copying the file: " + e.getMessage());
        }
    }
}