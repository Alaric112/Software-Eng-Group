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
 * Controller class for the secondary view.
 * This class manages the behavior and interaction of GUI elements in the secondary view.
 *
 * @author Alessandro Accarino
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
    @FXML
    private MenuItem editRule;
   
    private BooleanProperty isThreadRunning = new SimpleBooleanProperty(false);
    private ControlRuleChecker checker = ControlRuleChecker.getInstance();
    
    private RuleList ruleSet;     
    private ObservableList<Rule> observableRules;    

    
    /**
     * Initializes the controller class.
     * This method is called automatically when the FXML file is loaded.
     * It sets up the initial state and bindings for GUI elements.
     * 
     * @param url The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param rb The resources used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ruleSet = checker.getRuleSet();
        
        observableRules = FXCollections.observableArrayList(ruleSet.getRules());

        ruleSet.addObserver(this);
        checker.addObserver(this);
        
        startCheckerBtn.disableProperty().bind(isThreadRunning);
        stopCheckerBtn.disableProperty().bind(isThreadRunning.not());
        checkerImageView.visibleProperty().bind(isThreadRunning);
        
        nameRule.setCellValueFactory(new PropertyValueFactory("name"));
        stateRule.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().isActive() ? "active" : "disabled"));

        initItemSelecteBinding();
        
        ruleTable.setItems(observableRules);
        
        ruleSetLabel.setText(ruleSet.getName());
         
        ruleSet= checker.getRuleSet();        
        ruleTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        
        startCheckerEvent(new ActionEvent());
    }    

    /**
     * Initializes the bindings for disabling/enabling various GUI elements based on the selection in the ruleTable.
     */    
    private void initItemSelecteBinding(){
           
        BooleanProperty isItemSelected = new SimpleBooleanProperty();        
        isItemSelected.bind(Bindings.isNull(ruleTable.getSelectionModel().selectedItemProperty()));
        
        deleteRuleItemMenu.disableProperty().bind(isItemSelected);
        editRule.disableProperty().bind(isItemSelected);
        deleteBtn.disableProperty().bind(isItemSelected);
        switchStatusRule.disableProperty().bind(isItemSelected);
        deleteEditMenuBar.disableProperty().bind(isItemSelected);
        
    } 

    /**
     * Autosaves the ruleSet to a backup file.
     */    
    private void AutoSave(){
        File file = new File("backup.dat");
        saveRuleSet(ruleSet, file);       
    }

    /**
     * Handles the action event for creating a new rule.
     * If the checker is running, it stops it before opening the rule creation window and restarts it afterward.
     *
     * @param event The ActionEvent triggered by the user.
     */    
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
    
    /**
     * Handles the action event for starting the rule checker.
     * Sets the isThreadRunning property to true and starts the periodic rule check.
     *
     * @param event The ActionEvent triggered by the user.
     */    
    @FXML
    private void startCheckerEvent(ActionEvent event) {
        
        isThreadRunning.set(true);
        checker.startPeriodicCheck();                
        
    }

    /**
     * Handles the action event for stopping the rule checker.
     * Sets the isThreadRunning property to false and stops the periodic rule check.
     *
     * @param event The ActionEvent triggered by the user.
     */    
    @FXML
    private void stopCheckerEvent(ActionEvent event) {
        
        isThreadRunning.set(false); 
        checker.stopPeriodicCheck();
        
    }

    /**
     * Handles the action event for creating a new rule set.
     *
     * @param event The ActionEvent triggered by the user.
     */    
    @FXML
    private void createNewRuleSetEvent(ActionEvent event) {
        
        App.createSubWindow("SubWindowsCreationRuleSet", "New Ruleset");
    }

    /**
     * Handles the action event for deleting a rule.
     * Gets the selected rule(s) from the table and prompts the user for confirmation before deleting.
     *
     * @param event The ActionEvent triggered by the user.
     */    
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

    /**
     * Handles the action event for switching the status of selected rules.
     * Gets the selected rule(s) from the table and switches their status.
     *
     * @param event The ActionEvent triggered by the user.
     */    
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

    /**
     * Handles the action event for exiting the application.
     * Closes the application when the user clicks the exit button.
     *
     * @param event The ActionEvent triggered by the user.
     */    
    @FXML
    private void exitEvent(ActionEvent event) {
        
        Platform.exit();
    }

    /**
     * Handles the action event for saving the rule set to a file.
     * Prompts the user to select a location and saves the rule set to a file.
     *
     * @param event The ActionEvent triggered by the user.
     */    
    @FXML
    private void SaveRuleSetEvent(ActionEvent event) {
       
        File file = App.createFCSave(ruleSet.getName());
        saveRuleSet(ruleSet, file);
    }

    private void saveRuleSet(RuleList ruleSet, File file){
        
        Command saveCommand = new SaveCommand(ruleSet, file);
        saveCommand.execute();
        
    }
    
    /**
     * Handles the action event for loading a rule set from a file.
     * Prompts the user to select a file and loads the rule set from that file.
     *
     * @param event The ActionEvent triggered by the user.
     */    
    @FXML
    private void loadRuleSetEvent(ActionEvent event) {
        
        File file = App.createFCLoad();
        Command loadCommand = new LoadCommand(null, file);
        Platform.runLater(() -> {
            loadCommand.execute();
        });
    }

    /**
     * Handles the action event for returning to the home view.
     * Switches the view back to the primary view (Home view).
     *
     * @param event The ActionEvent triggered by the user.
     */    
    @FXML
    private void returnToHomeEvent(ActionEvent event) {
        
        App.switchTo("primary");
    }

    /**
     * Updates the view when the observed objects (ruleSet or checker) change.
     * Refreshes the rule table and other relevant GUI elements.
     *
     * @param o   The observable object.
     * @param arg An argument passed to the notifyObservers method (not used in this implementation).
     */    
    @Override
    public void update(Observable o, Object arg) {
        
        if (o instanceof ControlRuleChecker){        
            
            cleanup();
            ruleSet = checker.getRuleSet();
            ruleSet.addObserver(this);
            Platform.runLater(() -> {            
                observableRules.setAll(ruleSet.getRules());
                ruleSetLabel.setText(ruleSet.getName());
                ruleTable.refresh();
            });

        } else if (o instanceof RuleList) {
            RuleList updatedRuleSet = (RuleList) o;
                observableRules.setAll(updatedRuleSet.getRules());
                ruleTable.refresh();
                AutoSave();
        }
    }
    
    public void cleanup() {
        // Rimuovi il controller come osservatore quando non è più necessario
        ruleSet.deleteObserver(this);
    }

    // FAUSTO ???
    @FXML
    private void editRuleEvent(ActionEvent event) {
        
        if(isThreadRunning.getValue()){           
            stopCheckerEvent(event);   
        }
        Rule rule = ruleTable.getSelectionModel().getSelectedItem();       
        App.createSubWindow("CreateRuleSubWindow", "Rule Creator");             
    }
    
}
