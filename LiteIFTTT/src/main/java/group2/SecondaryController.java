/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package group2;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

/**
 * FXML Controller class
 *
 * @author patap
 */
public class SecondaryController implements Initializable {

    @FXML
    private Button createRuleButton;
    @FXML
    private Label ruleSetLabel;
    private ControlRuleChecker checker =ControlRuleChecker.getInstance();
    private Ruleset ruleSet;
    @FXML
    private ListView<?> ruleListView;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ruleSet= checker.getRules();
        ruleSetLabel.setText(ruleSet.getName());
    }    

    //private visualizeRules(){
    
    //}
    
    @FXML
    private void createRuleAction(ActionEvent event) {
        
        App.createSubWindow("CreateRuleSubWindow", "Rule Creator");
        
    }
    
}
