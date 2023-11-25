/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package group2;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
    private ImageView checkerImageView;
    @FXML
    private Button startCheckerBtn;
    @FXML
    private Button stopCheckerBtn;
    
    
    private ControlRuleChecker checker =ControlRuleChecker.getInstance();
    private Ruleset ruleSet;
    private BooleanProperty isThreadRunning = new SimpleBooleanProperty(false);
    @FXML
    private TableColumn<Rule, String> nameRule;
    @FXML
    private TableColumn<Rule, Boolean> stateRule;
    @FXML
    private TableView<Rule> ruleTable;
    //private ObservableList<Rule> observableRuleList = FXCollections.observableArrayList();   
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        startCheckerBtn.disableProperty().bind(isThreadRunning);
        stopCheckerBtn.disableProperty().bind(isThreadRunning.not());
        checkerImageView.visibleProperty().bind(isThreadRunning);
        
        nameRule.setCellValueFactory(new PropertyValueFactory("name"));
        stateRule.setCellValueFactory(new PropertyValueFactory("active"));
        
        ruleTable.setItems(checker.getRules().getRules());
        
        // TODO
        ruleSet= checker.getRules();
        ruleSetLabel.setText(ruleSet.getName());
                
    }    

    @FXML
    private void createRuleAction(ActionEvent event) {
        
        if(isThreadRunning.getValue()){
            
            stopCheckerEvent(event);   
        }
        
        App.createSubWindow("CreateRuleSubWindow", "Rule Creator");
        //observableRuleList.addAll(checker.getRules().getRules());
    }

    @FXML
    private void startCheckerEvent(ActionEvent event) {
        
        checker.startPeriodicCheck();
        isThreadRunning.set(true);        
        
    }

    @FXML
    private void stopCheckerEvent(ActionEvent event) {
        
        checker.stopPeriodicCheck();
        isThreadRunning.set(false);
    }
    
}
