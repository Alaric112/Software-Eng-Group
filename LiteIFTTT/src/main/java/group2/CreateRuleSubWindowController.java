/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package group2;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Control;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author patap
 */
public class CreateRuleSubWindowController implements Initializable {

    @FXML
    private Button closeButton;
    @FXML
    private Button confirmButton;
    @FXML
    private TextField ruleNameTF;
    
    RuleCreator ruleCreator = RuleCreator.getInstance();
    @FXML
    private Button addTriggerButton;
    @FXML
    private Button addActionButton;
    @FXML
    private ChoiceBox<String> triggerChoiceBox;
    @FXML
    private ChoiceBox<String> actionChoiceBox;
    @FXML
    private TreeView<String> triggerTreeView;
    @FXML
    private TreeView<String> actionTreeView;
    private VBox triggerParametersBox;
    
    private Trigger trigger;
    private Action action;
    
    private Map<String, TextField> parameterTextFieldMap = new HashMap<>();
    private VBox actionParametersBox;
    private ControlRuleChecker checker = ControlRuleChecker.getInstance();
    @FXML
    private Button buttParameters;
    @FXML
    private VBox timeTriggerBox;
    @FXML
    private TextField hourTrigger;
    @FXML
    private TextField minTrigger;
    @FXML
    private VBox messageActionBox;
    @FXML
    private VBox playAudioBox;
    @FXML
    private TextField pathSound;
    @FXML
    private TextArea textMessageArea;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {      
                
        triggerChoiceBox.getItems().addAll(ruleCreator.getAvailableTriggerTypes());
        actionChoiceBox.getItems().addAll(ruleCreator.getAvailableActionTypes());
        
        // Disattiva bottone conferma quando il text field del nome della regola e' vuoto
        BooleanBinding isTextFieldEmpty = Bindings.isEmpty(ruleNameTF.textProperty());
        
        // Disattiva quando il TreeView e' vuoto, ovvero NON ha una radice
        BooleanProperty triggerTreeViewHasRoot = new SimpleBooleanProperty();
     
        triggerTreeViewHasRoot.bind(Bindings.createBooleanBinding(() ->
                triggerTreeView.getRoot() != null, triggerTreeView.rootProperty()));

        BooleanProperty actionTreeViewHasRoot = new SimpleBooleanProperty();
     
        actionTreeViewHasRoot.bind(Bindings.createBooleanBinding(() ->
                actionTreeView.getRoot() != null, actionTreeView.rootProperty()));
        
        confirmButton.disableProperty().bind(isTextFieldEmpty.or(triggerTreeViewHasRoot.not()).or(actionTreeViewHasRoot.not()));
        
    }    

    @FXML
    private void confirmRuleCreationEvent(ActionEvent event) {
        
        String ruleName = ruleNameTF.getText();
        
        Rule rule = ruleCreator.createRule(ruleName, trigger, action);
        
        Ruleset ruleSet = checker.getRules();
        ruleSet.addRule(rule);
        System.out.println(ruleSet);
        closeWindowEvent(event);
        
    }

    @FXML
    private void closeWindowEvent(ActionEvent event) {
        
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void addTriggerEvent(ActionEvent event) {
    
          triggerParametersBox.getChildren().clear();
          TreeItem<String> item = new TreeItem<>(triggerChoiceBox.getValue());
          triggerTreeView.setRoot(item);
          trigger = ruleCreator.createTrigger(item.getValue());
   
    }

    @FXML
    private void addActionEvent(ActionEvent event) {
    
          actionParametersBox.getChildren().clear();
          TreeItem<String> item = new TreeItem<>(actionChoiceBox.getValue());
          actionTreeView.setRoot(item);
          action = ruleCreator.createAction(item.getValue());

    }
    
    @FXML
    public void selectTriggerItem(){
        
        TreeItem<String> item = triggerTreeView.getSelectionModel().getSelectedItem();
        System.out.println(item.getValue());
    }

    private void selectTriggerItem(ContextMenuEvent event) {
        
        selectTriggerItem();
    }

    private void selectTriggerItem(MouseEvent event) {
        
        selectTriggerItem();
    }
    
    
    @FXML
    private void setTimeEvent(ActionEvent event) {
        
    }
    
    @FXML
    private void selectActionItem(){     
        
        TreeItem<String> item = actionTreeView.getSelectionModel().getSelectedItem();
        System.out.println(item.getValue());
    }
    
    private void selectActionItem(ContextMenuEvent event) {
        
        selectActionItem();
    }

    private void selectActionItem(MouseEvent event) {
        
        selectActionItem();
    }

    @FXML
    private void insertMessageAction(ActionEvent event) {
        
        MessageAction message = new MessageAction();
        message.setMessageInfo(textMessageArea.getText());
        textMessageArea.clear();
                
    }

    @FXML
    private void selectPathEvent(ActionEvent event) {
        
        FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Upload File Path");
    fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("ALL FILES", "*.*"),
            new FileChooser.ExtensionFilter("ZIP", "*.zip"),
            new FileChooser.ExtensionFilter("PDF", "*.pdf"),
            new FileChooser.ExtensionFilter("TEXT", "*.txt"),
            new FileChooser.ExtensionFilter("IMAGE FILES", "*.jpg", "*.png", "*.gif")
    );


    File file = fileChooser.showOpenDialog(DialogPane.getScene().getWindow());

    if (file != null) {
        // pickUpPathField it's your TextField fx:id
       pathSound.setText(file.getPath());

    } else  {
        System.out.println("error"); // or something else
    }

    }

    
}
