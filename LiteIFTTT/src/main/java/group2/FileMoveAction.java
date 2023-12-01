/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

/**
 *
 * @author soniabruno
 */
public class FileMoveAction implements Action {

    private Path sourcePath; 
    private Path destinationPath;

    public void setSourcePath(Path sourcePath) {
        this.sourcePath = sourcePath;
    }

    public void setDestinationPath(Path destinationPath) {
        this.destinationPath = destinationPath;
    }

    @Override
    public void execute() {
        try {
            if (Files.exists(sourcePath)) {
                Files.move(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (IOException e) {
            System.err.println("Error : " + e.getMessage());
            e.printStackTrace();
        }
    }
}
