package group2.Controller;

import group2.App;
import group2.Model.Rule.FileManager.LoadCommand;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

/**
 * FXML Controller class for the primary view of the application. Manages the
 * actions and interactions related to the primary view, which includes creating
 * a new rule set and loading an existing rule set.
 *
 * @author patap
 */
public class PrimaryController implements Initializable {

    @FXML
    private Button openRuleSetButton;
    @FXML
    private Button newRuleSetButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    /**
     * Handles the action event for creating a new rule set. Opens a sub-window
     * for creating a new rule set when the "New Rule Set" button is clicked.
     *
     * @param event The ActionEvent triggered by the user.
     */
    @FXML
    private void createRuleSetAction(ActionEvent event) {

        App.createSubWindow("SubWindowsCreationRuleSet", "New Ruleset");
    }

    /**
     * Handles the action event for loading an existing rule set. Prompts the
     * user to select a file, loads the rule set from that file, and switches to
     * the secondary view.
     *
     * @param event The ActionEvent triggered by the user.
     */
    @FXML
    private void loadRuleSetAction(ActionEvent event) {

        Thread myThread = new Thread(new Runnable() {
            @Override
            public void run() {
                App.switchTo("secondary");
            }
        });

        File file = App.createFCLoad();

        LoadCommand loadCommand = new LoadCommand(myThread, file);
        Platform.runLater(() -> {
            loadCommand.execute();
        });

    }

}
