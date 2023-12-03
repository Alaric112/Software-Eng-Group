/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group2;

import java.io.IOException;

/**
 *
 * @author patap
 */
public class exitStatusTrigger implements Trigger {
    
    private String programPath;
    private int expectedExitStatus;

    public exitStatusTrigger() {
        this.programPath = "";
        this.expectedExitStatus = 0;
    }

    @Override
    public boolean evaluate() {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder(programPath);
            
            
            int exitStatus = processBuilder.start().waitFor();

            return exitStatus == expectedExitStatus;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return false; 
        }
    }

    public String getProgramPath() {
        return programPath;
    }

    public void setProgramPath(String programPath) {
        this.programPath = programPath;
    }

    public int getExpectedExitStatus() {
        return expectedExitStatus;
    }

    public void setExpectedExitStatus(int expectedExitStatus) {
        this.expectedExitStatus = expectedExitStatus;
    }      
    
}
