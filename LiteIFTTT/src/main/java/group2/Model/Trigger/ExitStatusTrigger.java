/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group2.Model.Trigger;

/**
 * The {@code ExitStatusTrigger} class implements the {@code Trigger} interface
 * and represents a trigger based on the exit status of an external program.
 * 
 * @author Lore
 */
import java.io.IOException;

/**
 * The ExitStatusTrigger class implements the Trigger interface and represents a trigger
 * that evaluates whether the exit status of an external program matches a user exit status.
 */
public class ExitStatusTrigger implements Trigger {

    /** The external program command to be executed. */
    private String externalProgram;

    /** The user-defined exit status to be compared with the external program's exit status. */
    private int userExitStatus;

    /**
     * Constructs a new {@code ExitStatusTrigger} with the default external program and a user exit status.
     */
    public ExitStatusTrigger() {
        this.externalProgram = "default";
        this.userExitStatus = -1;
    }

    /**
     * Evaluates whether the exit status of the external program matches the expected exit status.
     *
     * @return {@code true} if the exit status matches the expected exit status, {@code false} otherwise.
     */
    @Override
    public boolean evaluate() {
        try {
            // Configure the process
            ProcessBuilder processBuilder = new ProcessBuilder(externalProgram);
            Process process = processBuilder.start();

            // Get the exit status
            int exitStatus = process.waitFor();

            // Check if the exit status matches the expected exit status
            return exitStatus == userExitStatus;

        } catch (IOException | InterruptedException e) {
            // Handle exceptions if the process configuration fails
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Gets the external program command.
     *
     * @return The external program command.
     */
    public String getExternalProgram() {
        return externalProgram;
    }

    /**
     * Sets the external program command.
     *
     * @param externalProgram The new external program command.
     */
    public void setExternalProgram(String externalProgram) {
        this.externalProgram = externalProgram;
    }

    /**
     * Gets the user exit status.
     *
     * @return The user exit status.
     */
    public int getUserExitStatus() {
        return userExitStatus;
    }

    /**
     * Sets the user exit status.
     *
     * @param userExitStatus The new user exit status.
     */
    public void setUserExitStatus(int userExitStatus) {
        this.userExitStatus = userExitStatus;
    }

    /**
     * Returns a string representation of the ExitStatusTrigger object.
     *
     * @return A string representation of the ExitStatusTrigger object.
     */
    @Override
    public String toString() {
        return "ExitStatusTrigger";
    }
}
