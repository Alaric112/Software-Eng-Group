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
import javafx.scene.image.ImageView;

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
    @FXML
    private ListView<?> ruleListView;
    @FXML
    private ImageView checkerImageView;
    @FXML
    private Button startCheckerBtn;
    @FXML
    private Button stopCheckerBtn;
    
    
    private ControlRuleChecker checker =ControlRuleChecker.getInstance();
    private Ruleset ruleSet;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ruleSet= checker.getRules();
        ruleSetLabel.setText(ruleSet.getName());
        checkerImageView.setVisible(false);
       
    }    

    //private visualizeRules(){
    
    //}
    
    @FXML
    private void createRuleAction(ActionEvent event) {
        
        stopCheckerEvent(event);
        App.createSubWindow("CreateRuleSubWindow", "Rule Creator");
        
    }

    @FXML
    private void startCheckerEvent(ActionEvent event) {
        
        checker.startPeriodicCheck();
        checkerImageView.setVisible(true);        
        
    }

    @FXML
    private void stopCheckerEvent(ActionEvent event) {
        
        checker.stopPeriodicCheck();
        checkerImageView.setVisible(false);
    }
    
}
