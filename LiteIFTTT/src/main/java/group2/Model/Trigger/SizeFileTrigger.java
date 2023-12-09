/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group2.Model.Trigger;

import java.io.File;

/**
 * The {@code SizeFileTrigger} class extends the {@code FileTrigger} class
 * and represents a trigger based on the size of a specified file.
 *
 * @author Faust
 */
public class SizeFileTrigger extends FileTrigger{

    private long sizeTarget;
    private File fileTarget;
    private String filename;
    private String directory;

    public SizeFileTrigger() {
        super();
        this.sizeTarget = 0;
    }   
    
    /**
     * Evaluates whether the size of the target file exceeds the specified size target.
     * 
     * @return {@code true} if the size of the target file exceeds the specified size target, 
     * {@code false} otherwise.
     */
    @Override
    public boolean evaluate() {
        
        System.out.println(getTargetFile().length());
        return fileTarget.length()>sizeTarget;
        
    }

    public long getSizeFile() {
        return sizeTarget;
    }
    
    /**
     * Sets the target size for the file trigger.
     * 
     * @param sizeFile The target size to set.
     */
    public void setSizeFile(long sizeFile) {
        this.sizeTarget = sizeFile;
    }
    
    /**
     * Sets the target file for the size trigger.
     * 
     * @param file The target file to set.
     */
    public void setTargetFile(File file) {
         fileTarget = file;   
    }    
    
        @Override
    public String toString() {
        return "SizeTrigger";
    }
}
