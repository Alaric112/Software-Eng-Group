/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package group2;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
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
        // TODO
     
    }    

    @FXML
    private void createRuleSetAction(ActionEvent event) {
     
        
     App.createSubWindow("SubWindowsCreationRuleSet", "New Ruleset");
    }

    @FXML
    private void loadRuleSetAction(ActionEvent event) {
        
        
        Command loadCommand = new LoadCommand();
        loadCommand.execute();                
        App.switchTo("secondary");        
    }           
            
}
