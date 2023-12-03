/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group2;

import java.io.IOException;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;

/**
 *
 * @author patap
 */
public class ExcProgrammAction implements Action {

    private String programPath;
    private String commandLineArg;

    public ExcProgrammAction() {
        this.programPath = "";
        this.commandLineArg = "";
    }    
    
    @Override
    public void execute() {
        
        try{
            ProcessBuilder processBuilder = new ProcessBuilder(programPath, commandLineArg);
            
            processBuilder.start();
            
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

    public String getProgramPath() {
        return programPath;
    }

    public void setProgramPath(String programPath) {
        this.programPath = programPath;
    }

    public String getCommandLineArg() {
        return commandLineArg;
    }

    public void setCommandLineArg(String commandLineArg) {
        this.commandLineArg = commandLineArg;
    }
            
}
