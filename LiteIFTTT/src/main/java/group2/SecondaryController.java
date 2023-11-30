/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package group2;

import java.io.File;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
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
    
    private ObjectProperty<RuleSet> ruleSetProperty = checker.getRuleSetProperty();    
    private RuleSet ruleSet;
    @FXML
    private ProgressBar progressBar;
    
    private ObservableList<Rule> observableRules;    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ruleSet = checker.getRuleSet();        
        observableRules = FXCollections.observableArrayList();
        observableRules.setAll(ruleSet.getRules());
        ruleSet.addObserver(this);
        
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
        
        // When it change the Ruleset it must execute this code
        ruleSetProperty.addListener((observable, oldRuleSet, newRuleSet) -> {
            // Aggiorna la tabella con la nuova lista di regole
            ruleSet = newRuleSet;
            observableRules.setAll(ruleSet.getRules());
            ruleTable.refresh();
            //ruleTable.setItems(FXCollections.observableList(observableRules));
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
                
        observableRules.addListener((ListChangeListener<Rule>) change -> {
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
        // Ottieni la regola selezionata dalla tabella
        Rule rule = ruleTable.getSelectionModel().getSelectedItem();

        if (rule != null) {
            String title = "Confirm Deletion";
            String contentText = "Are you sure you want to delete this rule?";
            // Mostra una finestra di dialogo di conferma
            Alert alert = App.createPopUP(Alert.AlertType.WARNING, title, contentText);
            
            // Configura i pulsanti della finestra di dialogo
            ButtonType buttonTypeYes = new ButtonType("Yes", ButtonData.YES);
            ButtonType buttonTypeNo = new ButtonType("No", ButtonData.NO);
            alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

            // Mostra la finestra di dialogo e attendi la risposta dell'utente
            Optional<ButtonType> result = alert.showAndWait();

            // Se l'utente ha premuto "Yes", procedi con la cancellazione
            if (result.isPresent() && result.get() == buttonTypeYes) {
                // Esegui l'operazione di cancellazione
                ruleSet.getRules().remove(rule);

                // Aggiorna la visualizzazione della tabella
                ruleTable.getItems().remove(rule);
            }
        }
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

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof RuleSet) {
            RuleSet updatedRuleSet = (RuleSet) o;
            System.out.println(updatedRuleSet);
            System.out.println("viva la cipolla");
            observableRules.setAll(updatedRuleSet.getRules());
            // Aggiorna la tabella con la nuova lista di regole
            //ruleTable.setItems(observableRules);
            ruleTable.refresh();
            AutoSave();
        }
    }

    public void cleanup() {
        // Rimuovi il controller come osservatore quando non è più necessario
        ruleSet.deleteObserver(this);
    }
    
}
