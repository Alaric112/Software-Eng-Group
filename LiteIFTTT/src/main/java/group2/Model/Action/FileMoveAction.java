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
public class FileMoveAction implements Action {

    private String sourcePath; 
    private String destinationPath;

    public FileMoveAction() {
        this.sourcePath = "";
        this.destinationPath = "";
    }  
    
    public void setSourcePath(String sourcePath) {
        this.sourcePath = sourcePath;
    }

    public void setDestinationPath(String destinationPath) {
        this.destinationPath = destinationPath;
    }

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
}
