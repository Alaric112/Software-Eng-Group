/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group2.Model.Trigger;

/**
 *
 * @author Lore
 */
import java.io.IOException;

public class ExitStatusTrigger implements Trigger {

    private String externalProgram;
    private int userExitStatus;

    public ExitStatusTrigger(String externalProgram, int userExitStatus) {
        this.externalProgram = externalProgram;
        this.userExitStatus = userExitStatus;
    }

    @Override
    public boolean evaluate() {
        try {
            // Configure the process
            ProcessBuilder processBuilder = new ProcessBuilder(externalProgram);
            Process process = processBuilder.start();

            //get the exit status
            int exitStatus = process.waitFor();
            
            // Check if the exit status matches the expected exit status
            return exitStatus == userExitStatus;

        } catch (IOException | InterruptedException e) {
            // Handle exceptions if the process configuration fails
            e.printStackTrace();
            return false;
        }
    }

    // Getter and setter methods for externalProgram
    public String getExternalProgram() {
        return externalProgram;
    }

    public void setExternalProgram(String externalProgram) {
        this.externalProgram = externalProgram;
    }
    
    // Getter and Setter methods for UserExitStatus
    public int getUserExitStatus() {
        return userExitStatus;
    }
    public void setUserExitStatus(int userExitStatus) {
        this.userExitStatus = userExitStatus;
    }
}
