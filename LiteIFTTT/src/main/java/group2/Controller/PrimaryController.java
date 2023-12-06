/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package group2.Controller;

import group2.App;
import group2.App;
import group2.Model.Rule.FileManager.LoadCommand;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

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
    @FXML
    private Button sdsdsdsdsdsdsdsdsdd;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void createRuleSetAction(ActionEvent event) {
             
        App.createSubWindow("SubWindowsCreationRuleSet", "New Ruleset");
    }

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
       
        loadCommand.execute();                
        
                
    }        
            
}
