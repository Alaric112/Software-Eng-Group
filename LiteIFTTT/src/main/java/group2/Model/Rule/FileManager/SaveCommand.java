package group2.Model.Rule.FileManager;

import group2.Model.Rule.RuleList;
import java.io.File;

/**
/**
 * The {@code SaveCommand} class represents a command to save a {@link RuleList} to a file.
 * It implements the {@link Command} interface, allowing it to be used in a command pattern.
 *
 * <p>
 * This class encapsulates the logic for saving a RuleList to a specified file asynchronously.
 * It utilizes the {@link FileIOManager} to perform the asynchronous save operation.
 * </p>
 *
 * @author Alessandro Accarino
 */
public class SaveCommand implements Command {

    private RuleList ruleSet;
    private File file;
    
    /**
     * Constructs a new SaveCommand with the specified RuleList.
     *
     * @param ruleSet The RuleList to be saved.
     * @param file
     */    
    public SaveCommand(RuleList ruleSet, File file) {
        this.ruleSet = ruleSet;
        this.file = file;

    }    
 
    @Override
    public void execute() {

        // Save the RuleList to the selected file using FileIOManager
        FileIOManager.saveToFileAsync(file, ruleSet);
    }

    /**
     * Gets the RuleList associated with this SaveCommand.
     *
     * @return The RuleList to be saved.
     */    
    public RuleList getRuleSet() {
        return ruleSet;
    }

    /**
     * Sets the RuleList for this SaveCommand.
     *
     * @param ruleSet The RuleList to be saved.
     */    
    public void setRuleSet(RuleList ruleSet) {
        this.ruleSet = ruleSet;
    }

    /**
     * Gets the file associated with this SaveCommand.
     *
     * @return The file to which the RuleList will be saved.
     */    
    public File getFile() {
        return file;
    }

    /**
     * Sets the file for this SaveCommand.
     *
     * @param file The file to which the RuleList will be saved.
     */    
    public void setFile(File file) {
        this.file = file;
    }
            
}
