
package group2.Model.Action;

import java.io.IOException;

/**
 * The `ExcProgrammAction` class represents an implementation of the `Action` interface
 * for executing external programs with specified command-line arguments.
 *
 * @author patap
 * @version 1.0
 */
public class ExcProgrammAction implements Action {

    private String programPath;
    private String commandLineArg;

    public ExcProgrammAction() {
        this.programPath = "";
        this.commandLineArg = "";
    }    

    /**
     * Executes the external program with the specified command-line argument.
     * Uses `ProcessBuilder` to start the external process.
     */    
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
