/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package group2.Controller;

import group2.App;
import group2.Model.Rule.*;
import group2.Model.Rule.FileManager.*;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
/**
 * FXML Controller class
 *
 * @author patap
 */
public class SecondaryController implements Initializable, Observer {

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
    @FXML
    private TableColumn<Rule, String> nameRule;
    @FXML
    private TableColumn<Rule, String> stateRule;
    @FXML
    private TableView<Rule> ruleTable;
    @FXML
    private MenuItem deleteRuleItemMenu;
    @FXML
    private MenuItem switchStatusRule;      
    @FXML
    private Button exitBtn;
    @FXML
    private Button deleteBtn;
    @FXML
    private MenuItem deleteEditMenuBar;
    @FXML
    private Button homeBtn;
    
    private BooleanProperty isThreadRunning = new SimpleBooleanProperty(false);
    private ControlRuleChecker checker =ControlRuleChecker.getInstance();
    
    private RuleSet ruleSet = checker.getRuleSet();    
    
    private ObservableList<Rule> observableRules;    
    @FXML
    private MenuItem editRule;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ruleSet = checker.getRuleSet();        
        observableRules = FXCollections.observableArrayList();
        observableRules.setAll(ruleSet.getRules());
        ruleSet.addObserver(this);
        checker.addObserver(this);
        startCheckerBtn.disableProperty().bind(isThreadRunning);
        stopCheckerBtn.disableProperty().bind(isThreadRunning.not());
        checkerImageView.visibleProperty().bind(isThreadRunning);
        
        nameRule.setCellValueFactory(new PropertyValueFactory("name"));
        stateRule.setCellValueFactory(cellData -> {
            Rule rule = cellData.getValue();
            String status = rule.isActive() ? "active" : "disabled";
            return new SimpleStringProperty(status);
        });

        initItemSelecteBinding();
        
        ruleTable.setItems(observableRules);
        
        ruleSetLabel.setText(ruleSet.getName());
         
        ruleSet= checker.getRuleSet();
        
        ruleTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
                
    }    

    private void initItemSelecteBinding(){
           
        // Disable Delete and Switch status context menu if there is no selecte element in the table
        BooleanProperty isItemSelected = new SimpleBooleanProperty();
        isItemSelected.bind(Bindings.isNull(ruleTable.getSelectionModel().selectedItemProperty()));
        deleteRuleItemMenu.disableProperty().bind(isItemSelected);
        editRule.disableProperty().bind(isItemSelected);
        deleteBtn.disableProperty().bind(isItemSelected);
        switchStatusRule.disableProperty().bind(isItemSelected);
        deleteEditMenuBar.disableProperty().bind(isItemSelected);
        
    } 
    
    private void AutoSave(){
        File file = new File("backup.dat");
        FileIOManager.saveToFileAsync(file, ruleSet);        
    }
    
    @FXML
    private void createRuleAction(ActionEvent event) {
        
        boolean wasCheckerRunning = isThreadRunning.get();
        
        // Se il checker è in esecuzione, fermalo prima di aprire la finestra di creazione delle regole
        if (wasCheckerRunning)
            stopCheckerEvent(event);
              
        App.createSubWindow("CreateRuleSubWindow", "Rule Creator");
        
        if (wasCheckerRunning)
            startCheckerEvent(event);
    
    }

    @FXML
    private void startCheckerEvent(ActionEvent event) {
        
        isThreadRunning.set(true);
        checker.startPeriodicCheck();                
        
    }

    @FXML
    private void stopCheckerEvent(ActionEvent event) {
        
        isThreadRunning.set(false); 
        checker.stopPeriodicCheck();
        
    }

    @FXML
    private void createNewRuleSetEvent(ActionEvent event) {
        
        App.createSubWindow("SubWindowsCreationRuleSet", "New Ruleset");
    }

    @FXML
    private void deleteRuleEvent(ActionEvent event) {

        List<Rule> rules = new ArrayList();
        rules.addAll(ruleTable.getSelectionModel().getSelectedItems());

        if (!rules.isEmpty()) {
            String title = "Confirm Deletion";
            String contentText = "Are you sure you want to delete this rule?";
            // Mostra una finestra di dialogo di conferma
            Alert alert = App.createPopUP(Alert.AlertType.WARNING, title, contentText);
            
            // Configura i pulsanti della finestra di dialogo
            ButtonType buttonTypeYes = new ButtonType("Yes", ButtonData.YES);
            ButtonType buttonTypeNo = new ButtonType("No", ButtonData.NO);
            alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

            Optional<ButtonType> result = alert.showAndWait();

            // Se l'utente ha premuto "Yes", procedi con la cancellazione
            if (result.isPresent() && result.get() == buttonTypeYes) {
                    
                for(Rule rule : rules){
                    ruleSet.removeRule(rule);
                }
                ruleTable.getSelectionModel().clearSelection();
            }
        }
    }

    @FXML
    private void switchStatusRuleEvent(ActionEvent event) {
        
        List<Rule> rules = new ArrayList();
        rules.addAll(ruleTable.getSelectionModel().getSelectedItems());
        
        if (!rules.isEmpty()){
            for(Rule rule : rules){
                ruleSet.switchRuleStatus(rule);
            }
            ruleTable.refresh();
        }
    }

    @FXML
    private void exitEvent(ActionEvent event) {
        
        Platform.exit();
    }

    @FXML
    private void SaveRuleSetEvent(ActionEvent event) {
       
        File file = App.createFCSave(ruleSet.getName());
        Command saveCommand = new SaveCommand(ruleSet, file);
        saveCommand.execute();
        System.out.println("Save button pressed");
    }

    @FXML
    private void loadRuleSetEvent(ActionEvent event) {
        
        File file = App.createFCLoad();
        Command loadCommand = new LoadCommand(null, file);
        loadCommand.execute();
        
    }

    @FXML
    private void returnToHomeEvent(ActionEvent event) {
        
        App.switchTo("primary");
    }

    @Override
    public void update(Observable o, Object arg) {
        
        if (o instanceof ControlRuleChecker){
            
            // When it change the Ruleset it must execute this code
            ruleSet = checker.getRuleSet();
            observableRules.setAll(ruleSet.getRules());
            ruleSetLabel.setText(ruleSet.getName());
            ruleTable.refresh();
        } else if (o instanceof RuleSet) {
            RuleSet updatedRuleSet = (RuleSet) o;
            observableRules.setAll(updatedRuleSet.getRules());
            ruleTable.refresh();
            AutoSave();
        }
    }

    public void cleanup() {
        // Rimuovi il controller come osservatore quando non è più necessario
        ruleSet.deleteObserver(this);
    }

    @FXML
    private void editRuleEvent(ActionEvent event) {
        
        if(isThreadRunning.getValue()){           
            stopCheckerEvent(event);   
        }
        Rule rule = ruleTable.getSelectionModel().getSelectedItem();       
        App.createSubWindow("CreateRuleSubWindow", "Rule Creator");             
    }
    
}
