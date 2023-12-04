/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group2;

import java.io.File;

/**
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
       
    @Override
    public boolean evaluate() {
        
        System.out.println(getTargetFile().length());
        return fileTarget.length()>sizeTarget;
        
    }

    public long getSizeFile() {
        return sizeTarget;
    }

    public void setSizeFile(long sizeFile) {
        this.sizeTarget = sizeFile;
    }
    
    public void setTargetFile(File file) {
         fileTarget = file;   
    }    
}
