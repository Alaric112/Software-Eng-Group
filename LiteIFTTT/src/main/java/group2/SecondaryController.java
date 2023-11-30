/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package group2;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
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
    
    private ObjectProperty<RuleSet> ruleSetProperty = checker.getRuleSetProperty();    
    private RuleSet ruleSet = checker.getRuleSet();
    @FXML
    private ProgressBar progressBar;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                
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
        
        ruleTable.setItems(ruleSetProperty.get().getRules());

        // When it change the Ruleset it must execute this code
        ruleSetProperty.addListener((observable, oldRuleSet, newRuleSet) -> {
            // Aggiorna la tabella con la nuova lista di regole
            ruleSet = newRuleSet;
            ruleTable.setItems(ruleSet.getRules());
            AutoSave();
        });

        AutoSave();
        
        // Dynamic bindig 
        ruleSetLabel.textProperty().bind(Bindings.createStringBinding(() ->
                ruleSetProperty.get().getName(), ruleSetProperty));
         
        ruleSet= checker.getRuleSet();
                
    }    

    private void initItemSelecteBinding(){
           
        // Disable Delete and Switch status context menu if there is no selecte element in the table
        BooleanProperty isItemSelected = new SimpleBooleanProperty();
        isItemSelected.bind(Bindings.isNull(ruleTable.getSelectionModel().selectedItemProperty()));
        deleteRuleItemMenu.disableProperty().bind(isItemSelected);
        deleteBtn.disableProperty().bind(isItemSelected);
        switchStatusRule.disableProperty().bind(isItemSelected);
        deleteEditMenuBar.disableProperty().bind(isItemSelected);
        
    } 
    
    private void AutoSave(){
        
        ruleSet.getRules().addListener((ListChangeListener<Rule>) change -> {
            while (change.next()) {
                if (change.wasAdded() || change.wasRemoved() || change.wasUpdated()) {
                    // Se ci sono state modifiche nella lista di regole, esegui il salvataggio automatico
                    File file = new File("backup.dat");
                    FileIOManager.saveToFileAsync(file, ruleSet);
                }
            }
        });
        
    }
    
    @FXML
    private void createRuleAction(ActionEvent event) {
        
        if(isThreadRunning.getValue()){
            
            stopCheckerEvent(event);   
        }
        
        App.createSubWindow("CreateRuleSubWindow", "Rule Creator");
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

    @FXML
    private void createNewRuleSetEvent(ActionEvent event) {
        
        App.createSubWindow("SubWindowsCreationRuleSet", "New Ruleset");
    }

    @FXML
    private void deleteRuleEvent(ActionEvent event) {
        
        // TO REFACTOR        
        Rule rule = ruleTable.getSelectionModel().getSelectedItem();

        // Mostra una finestra di dialogo di conferma
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Confirm cancellation");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to cancel this rule?");

        // Configura i pulsanti della finestra di dialogo
        ButtonType buttonTypeYes = new ButtonType("Yes");
        ButtonType buttonTypeNo = new ButtonType("No");
        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

        // Attendi la risposta dell'utente
        alert.showAndWait().ifPresent(buttonType -> {
            if (buttonType == buttonTypeYes) {
                // L'utente ha cliccato su "Yes", procedi con la cancellazione
                ruleSet.getRules().remove(rule);
            }
        });
    }

    @FXML
    private void switchStatusRuleEvent(ActionEvent event) {
        
        Rule rule = ruleTable.getSelectionModel().getSelectedItem();
        
        ruleSet.switchRuleStatus(rule);
        ruleTable.refresh();
    }

    @FXML
    private void exitEvent(ActionEvent event) {
        
        Platform.exit();
    }

    @FXML
    private void SaveRuleSetEvent(ActionEvent event) {
          
        Command saveCommand = new SaveCommand(ruleSet);
        saveCommand.execute();
        System.out.println("Save button pressed");
    }

    @FXML
    private void loadRuleSetEvent(ActionEvent event) {
                
        Command loadCommand = new LoadCommand();
        loadCommand.execute();
        System.out.println("load button pressed");
        
    }

    @FXML
    private void returnToHomeEvent(ActionEvent event) {
        
        App.switchTo("primary");
    }
    
}
