package group2.Controller;

import group2.App;
import group2.Model.Rule.ControlRuleChecker;
import group2.Model.Rule.RuleList;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class for the sub-window used to create a new rule set.
 * Manages the actions and interactions related to the creation of a new rule set,
 * including setting the control time and specifying the rule set name.
 *
 * @author patap
 */
public class SubWindowsCreationRuleSetController implements Initializable {

    @FXML
    private Button closeButton;
    @FXML
    private Button confirmButton;
    @FXML
    private Spinner<Integer> spinnerControlTime;
    @FXML
    private TextField ruleSetNameTextField;
    
    private ControlRuleChecker checker;

    /**
     * Initializes the controller class. Configures the spinner for control time
     * and binds the confirmation button's disable property to the rule set name text field.
     *
     * @param url The location used to resolve relative paths for the root object.
     * @param rb The resources used to localize the root object.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
       checker = ControlRuleChecker.getInstance();
       
       confirmButton.disableProperty().bind(ruleSetNameTextField.textProperty().isEmpty()); 
       SpinnerValueFactory<Integer> gradesValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(5, 60, 5);
       this.spinnerControlTime.setValueFactory(gradesValueFactory);
    }    

    /**
     * Handles the action event for closing the rule set creation sub-window.
     * Closes the sub-window when the "Close" button is clicked.
     *
     * @param event The ActionEvent triggered by the user.
     */    
    @FXML
    private void closeWindowAction(ActionEvent event) {
        
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();       
    }

    /**
     * Handles the action event for confirming the creation of a new rule set.
     * Creates a new rule set based on the user-specified control time and rule set name,
     * changes the current rule set in the application's control, switches to the secondary view,
     * and closes the rule set creation sub-window.
     *
     * @param event The ActionEvent triggered by the user.
     */    
    @FXML
    private void creationRuleSetConfirmationAction(ActionEvent event) {

        RuleList rules=new RuleList(spinnerControlTime.getValue(), ruleSetNameTextField.getText());
        checker.changeRuleset(rules);

        App.switchTo("secondary");
        closeWindowAction(event);
        
    } 

    /**
     * Handles the action event for opening a file chooser to select a save path.
     * Opens a file chooser dialog for the user to select a save path when the "Save" button is clicked.
     *
     * @param event The ActionEvent triggered by the user.
     */    
    @FXML
    private void openSavePathEvent(ActionEvent event) {
        
        FileChooser fc = new FileChooser();
        
        File f = fc.showSaveDialog(null);
        
        if(f==null) return;
             
    }
    
}
