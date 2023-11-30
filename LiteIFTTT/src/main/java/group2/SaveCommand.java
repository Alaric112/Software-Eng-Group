package group2;

import java.io.File;
import java.io.IOException;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

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

    /**
     * Constructs a new SaveCommand with the specified RuleSet.
     *
     * @param ruleSet The RuleSet to be saved.
     */    
    public SaveCommand(RuleSet ruleSet) {
        this.ruleSet = ruleSet;

    }    

    /**
     * Executes the SaveCommand by displaying a file save dialog and saving the RuleSet to the selected file.
     * If an IOException occurs during the save operation, it is printed to the standard error stream.
     */    
    @Override
public void execute() {
    // Create a file chooser for selecting the save location        
    FileChooser chooser = new FileChooser();
    chooser.setTitle("Save RuleSet");

    // Set a filter to allow only .dat files
    FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Data Files (*.dat)", "*.dat");
    chooser.getExtensionFilters().add(extFilter);

    // Set the initial file name (Automatically sets the file name to the ruleSet's name)
    chooser.setInitialFileName(ruleSet.getName());
    
    // Show the save file dialog and get the selected file
    File file = chooser.showSaveDialog(new Stage());

    // Save the RuleSet to the selected file using FileIOManager
    FileIOManager.saveToFileAsync(file, ruleSet);
}
    
}
