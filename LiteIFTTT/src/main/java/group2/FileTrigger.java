/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group2;

import java.io.File;

/**
 * Constructs a new instance of the <code>FileTrigger</code> class with the
 * specified directory and file name.
 *
 * @author patap
 */
public class FileTrigger implements Trigger {

    private File targetFile;
    private String directory;
    private String fileName;

    public FileTrigger() {
        this.targetFile = null;
        this.directory = "";
        this.fileName = "";
    }

    /**
     * Evaluates whether the trigger condition is met (file exists).
     *
     * @return True if the file exists, false otherwise.
     */    
    @Override
    public boolean evaluate() {
        if (directory == null || fileName == null) {
            throw new IllegalStateException("Directory or fileName is null");
        }
        return targetFile.exists();
    }
    
    /**
     * Gets the target file.
     *
     * @return The target file.
     */
    public File getTargetFile() {
        return targetFile;
    }

    /**
     * Sets the target file with the specified directory and file name.
     *
     * @param directory The new target directory.
     * @param fileName  The new target file name.
     */
    public void setTargetFile(String directory, String fileName) {
        if (directory == null || fileName == null) {
            throw new IllegalArgumentException("Directory or fileName cannot be null");
        }
        this.directory = directory;
        this.fileName = fileName;        
        this.targetFile = new File(directory, fileName);
    }    
    
}
