package group2;

import java.io.File;

/**
 * The {@code SaveCommand} class represents a command to save a {@link RuleSet} to a file.
 * It implements the {@link Command} interface, allowing it to be used in a command pattern.
 *
 * <p>This class provides functionality to display a file save dialog using JavaFX's {@link FileChooser},
 * allowing the user to specify a location to save the RuleSet. The saved file is in a binary format with
 * the extension ".dat".
 *
 *
 * @author patap
 */
public class SaveCommand implements Command {

    private RuleSet ruleSet;
    private File file;
    /**
     * Constructs a new SaveCommand with the specified RuleSet.
     *
     * @param ruleSet The RuleSet to be saved.
     * @param file
     */    
    public SaveCommand(RuleSet ruleSet, File file) {
        this.ruleSet = ruleSet;
        this.file = file;

    }    

    /**
     * Executes the SaveCommand by displaying a file save dialog and saving the RuleSet to the selected file.
     * If an IOException occurs during the save operation, it is printed to the standard error stream.
     */    
    @Override
    public void execute() {

        // Save the RuleSet to the selected file using FileIOManager
        FileIOManager.saveToFileAsync(file, ruleSet);
    }

    public RuleSet getRuleSet() {
        return ruleSet;
    }

    public void setRuleSet(RuleSet ruleSet) {
        this.ruleSet = ruleSet;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
            
}
