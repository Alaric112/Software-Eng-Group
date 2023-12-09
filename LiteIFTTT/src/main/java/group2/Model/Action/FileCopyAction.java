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

    
    public void setSourcePath(String sourcePath) {
        this.sourcePath = sourcePath;
    }

    public void setDestinationPath(String destinationPath) {
        this.destinationPath = destinationPath;
    }
    
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