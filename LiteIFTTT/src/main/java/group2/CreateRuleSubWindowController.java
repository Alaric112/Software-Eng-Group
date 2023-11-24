/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package group2;

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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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
    private TreeView actionTreeView;
    @FXML
    private VBox triggerParametersBox;
    
    private Trigger trigger;
    private Action action;
    
    private Map<String, TextField> parameterTextFieldMap = new HashMap<>();

    
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
        
        // TO DO 
        //Prendere rule set da controlRuleChecker
        // Fare add della rule creata nel ruleset preso
        
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
//        ruleCreator.setLastSelectedType("trigger");
//        showTypeSelectionPopup("Trigger Selection", ruleCreator.getAvailableTriggerTypes());
//        System.out.println(ruleCreator.getAvailableTriggerTypes());
   
    }

    @FXML
    private void addActionEvent(ActionEvent event) {
    
          TreeItem<String> item = new TreeItem<>(actionChoiceBox.getValue());
          actionTreeView.setRoot(item);
          action = ruleCreator.createAction(item.getValue());
//        ruleCreator.setLastSelectedType("action");
//        showTypeSelectionPopup("Action Selection", ruleCreator.getAvailableActionTypes());

    }
    
    @FXML
    public void selectItem(){
        
        TreeItem<String> item = triggerTreeView.getSelectionModel().getSelectedItem();
        
        updateTriggerParameters(item);
    }
    
    
    private void updateTriggerParameters(TreeItem<String> selectedItem) {
        
        triggerParametersBox.getChildren().clear(); // Rimuovi tutti i controlli precedenti

        if (selectedItem != null) {
            String selectedType = selectedItem.getValue();
   
            List<Control> parameterControls = ruleCreator.createTriggerControl(selectedType);
            triggerParametersBox.getChildren().addAll(parameterControls);
            
             // Aggiungi i controlli dei parametri al triggerParametersBox e alla mappa
            for (Control control : parameterControls) {
                if (control instanceof TextField) {
                    TextField textField = (TextField) control;
                    String parameterName = getParameterNameFromControl(textField); // Implementa questa funzione
                    parameterTextFieldMap.put(parameterName, textField);
                }

                triggerParametersBox.getChildren().add(control);
            }
            
        }
        
    }
    
    private String getParameterNameFromControl(TextField textField) {
    // Implementa la logica per ottenere il nome del parametro associato al TextField
    // Può essere il testo dell'etichetta accanto, un attributo associato al TextField, ecc.
    return textField.getPromptText(); // Esempio: il testo dell'etichetta è considerato come il nome del parametro
    }
    
    private void processTriggerParameters() {
    for (Map.Entry<String, TextField> entry : parameterTextFieldMap.entrySet()) {
        String parameterName = entry.getKey();
        TextField textField = entry.getValue();
        String parameterValue = textField.getText();
        // Fai qualcosa con il nome e il valore del parametro
        System.out.println(parameterName + ": " + parameterValue);
    }
}
    
//    private void showTypeSelectionPopup(String title, List<String> types) {
//        
//        App.createSubWindow("TypeSelectionPopup", title);
//               
//    }

    
}
