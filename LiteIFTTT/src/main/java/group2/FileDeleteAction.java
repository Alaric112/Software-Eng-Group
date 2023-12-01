/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group2;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author soniabruno
 */

public class FileDeleteAction implements Action {

    private Path filePath;

    public FileDeleteAction() {
        this.filePath = Paths.get("not existing");
    }

    public void setPath(Path filePath) {
        this.filePath = filePath;
    }

    @Override
    public void execute() {
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
}

