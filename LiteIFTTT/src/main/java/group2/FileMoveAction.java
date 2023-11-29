/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group2;

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
    
    private Path sourcePath; 
    private Path destinationPath;

    public FileMoveAction() {
        this.sourcePath= Paths.get("not existing");
        this.destinationPath= Paths.get("not existing");
    }

    public Path setSourcePath(Path sourcePath) {
        return sourcePath;
    }

    public Path setDestinationPath(Path destinationPath) {
        return destinationPath;
    }
    
    @Override
    public void execute() {
        try {
            if (Files.exists(sourcePath)) {
                Files.move(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
            }
        }catch (IOException e) {
            System.err.println("Error copying the file: " + e.getMessage());
            e.printStackTrace();
        }
    }
}