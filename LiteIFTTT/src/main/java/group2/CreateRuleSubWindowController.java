/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package group2;

import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class 
 * 
 * The {@code CreateRuleSubWindowController} class is the controller for the
 * FXML document that defines the GUI for creating rules in the application.
 * It handles user interactions and manages the creation of rules, triggers,
 * and actions based on user inputs.
 *
 * <p>This controller initializes the UI components, binds properties to control
 * visibility and enable/disable states, and communicates with the {@code RuleCreator}
 * and {@code ControlRuleChecker} to create and validate rules.</p>
 * 
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
    @FXML
    private VBox triggerParametersBox;
    @FXML
    private VBox actionParametersBox;
    @FXML
    private VBox timeTriggerBox;
    @FXML
    private VBox messageActionBox;
    @FXML
    private VBox playAudioBox;
    @FXML
    private TextField pathSound;
    @FXML
    private TextField hourTriggerTF;
    @FXML
    private Spinner<Integer> spinnerHourTimeTrigger;
    @FXML
    private Spinner<Integer> spinnerMinuteTimeTrigger;
    @FXML
    private Button btnSetTimeTrigger;
    @FXML
    private TextArea textMessageArea;

    private Trigger trigger;

    private Action action;

    private Map<String, TextField> parameterTextFieldMap = new HashMap<>();

    private ControlRuleChecker checker = ControlRuleChecker.getInstance();

    RuleCreator ruleCreator = RuleCreator.getInstance();

    /**
     * Initializes the controller class. This method is automatically called
     * after the FXML file has been loaded.
     *
     * @param url the location used to resolve relative paths for the root object, or null if the location is not known
     * @param rb the resources used to localize the root object, or null if the root object was not localized
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        triggerChoiceBox.getItems().addAll(ruleCreator.getAvailableTriggerTypes());
        actionChoiceBox.getItems().addAll(ruleCreator.getAvailableActionTypes());

        timeTriggerBox.setVisible(false);
        messageActionBox.setVisible(false);
        playAudioBox.setVisible(false);

        // Set values for Time trigger Spinner
        SpinnerValueFactory<Integer> hourValues = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23, 0);
        this.spinnerHourTimeTrigger.setValueFactory(hourValues);
        SpinnerValueFactory<Integer> minuteValues = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59, 0);
        this.spinnerMinuteTimeTrigger.setValueFactory(minuteValues);

        // Disable the confirmation button when the rule name text field is empty
        BooleanBinding isTextFieldEmpty = Bindings.isEmpty(ruleNameTF.textProperty());

        // Disable when the TreeView is empty, i.e., it does NOT have a root
        BooleanProperty triggerTreeViewHasRoot = new SimpleBooleanProperty();

        triggerTreeViewHasRoot.bind(Bindings.createBooleanBinding(()
                -> triggerTreeView.getRoot() != null, triggerTreeView.rootProperty()));

        BooleanProperty actionTreeViewHasRoot = new SimpleBooleanProperty();

        actionTreeViewHasRoot.bind(Bindings.createBooleanBinding(()
                -> actionTreeView.getRoot() != null, actionTreeView.rootProperty()));

        confirmButton.disableProperty().bind(isTextFieldEmpty.or(triggerTreeViewHasRoot.not()).or(actionTreeViewHasRoot.not()));

    }

    /**
     * Handles the event when the user confirms the creation of a rule.
     *
     * @param event the ActionEvent triggered by the user
     */
    @FXML
    private void confirmRuleCreationEvent(ActionEvent event) {

        String ruleName = ruleNameTF.getText();

        Rule rule = ruleCreator.createRule(ruleName, trigger, action);

        RuleSet ruleSet = checker.getRuleSet();
        ruleSet.addRule(rule);
        System.out.println(ruleSet);
        closeWindowEvent(event);

    }

    /**
     * Handles the event when the user closes the rule creation window.
     *
     * @param event the ActionEvent triggered by the user
     */    
    @FXML
    private void closeWindowEvent(ActionEvent event) {

        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    /**
     * Handles the event when the user adds a trigger to the rule.
     *
     * @param event the ActionEvent triggered by the user
     */    
    @FXML
    private void addTriggerEvent(ActionEvent event) {

        TreeItem<String> item = new TreeItem<>(triggerChoiceBox.getValue());
        triggerTreeView.setRoot(item);
        trigger = ruleCreator.createTrigger(item.getValue());

    }

    /**
     * Handles the event when the user adds an action to the rule.
     *
     * @param event the ActionEvent triggered by the user
     */    
    @FXML
    private void addActionEvent(ActionEvent event) {

        TreeItem<String> item = new TreeItem<>(actionChoiceBox.getValue());
        actionTreeView.setRoot(item);
        action = ruleCreator.createAction(item.getValue());

    }

    /**
     * Selects a trigger item from the triggerTreeView and performs specific actions based on the selected item.
     */    
    @FXML
    public void selectTriggerItem() {

        TreeItem<String> item = triggerTreeView.getSelectionModel().getSelectedItem();

        if (item != null) {

            if (item.getValue().equals("Time")) {

                timeTriggerBox.setVisible(true);
            }
        }
    }

    private void selectTriggerItem(ContextMenuEvent event) {

        selectTriggerItem();
    }

    private void selectTriggerItem(MouseEvent event) {

        selectTriggerItem();
    }

    /**
     * Handles the event when the user sets the time for a time-trigger.
     *
     * @param event the ActionEvent triggered by the user
     */   
    @FXML
    private void setTimeEvent(ActionEvent event) {

        TimeTrigger t = (TimeTrigger) trigger;

        t.setTargetTime(spinnerHourTimeTrigger.getValue(), spinnerMinuteTimeTrigger.getValue());

        System.out.println(t.getTargetTime());

    }

    /**
     * Selects an action item from the actionTreeView and performs specific actions based on the selected item.
     */    
    @FXML
    private void selectActionItem() {

        TreeItem<String> item = actionTreeView.getSelectionModel().getSelectedItem();

        if (item != null) {

            if (item.getValue().equals("Message")) {

                messageActionBox.setVisible(true);
                playAudioBox.setVisible(false);
            } else {

                playAudioBox.setVisible(true);
                messageActionBox.setVisible(false);
            }

        }
    }

    private void selectActionItem(ContextMenuEvent event) {

        selectActionItem();
    }

    private void selectActionItem(MouseEvent event) {

        selectActionItem();
    }
    
    /**
     * Handles the event when the user inserts a message for a message action.
     *
     * @param event the ActionEvent triggered by the user
     */    
    @FXML
    private void insertMessageAction(ActionEvent event) {

        String messageInfo = textMessageArea.getText();
        textMessageArea.clear();

        MessageAction m = (MessageAction) action;
        m.setMessageInfo(messageInfo);

    }
    
    /**
     * Handles the event when the user selects a path for a sound action.
     *
     * @param event the ActionEvent triggered by the user
     */    
    @FXML
    private void selectPathEvent(ActionEvent event) {

        SoundAction soundAction = (SoundAction) action;
        
        soundAction.setPath();

    }

}
