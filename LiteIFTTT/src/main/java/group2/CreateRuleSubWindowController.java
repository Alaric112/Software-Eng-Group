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
import javafx.scene.control.CheckBox;
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
import java.time.DayOfWeek;

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
    private VBox timeTriggerBox;
    @FXML
    private VBox messageActionBox;
    @FXML
    private VBox playAudioBox;
    @FXML
    private TextField pathSound;
    @FXML
    private Spinner<Integer> spinnerHourTimeTrigger;
    @FXML
    private Spinner<Integer> spinnerMinuteTimeTrigger;
    @FXML
    private Button btnSetTimeTrigger;
    @FXML
    private TextArea textMessageArea;

    private Trigger lastTrigger;

    private Action lastAction;

    private Map<String, TextField> parameterTextFieldMap = new HashMap<>();

    private ControlRuleChecker checker = ControlRuleChecker.getInstance();

    private RuleCreator ruleCreator = RuleCreator.getInstance();

    private static Map<String, Runnable> actionVisibilityMap;
    private static Map<String, Runnable> triggerVisibilityMap;
    
    @FXML
    private CheckBox fireOnlyOnceCheckBox;
    @FXML
    private Spinner<Integer> spinnerDaySleepingPeriod;
    @FXML
    private Spinner<Integer> spinnerHourSleepingPeriod;
    @FXML
    private Spinner<Integer> spinnerMinuteSleepingPeriod;

    @FXML
    private TextField pathDelete;
    @FXML
    private VBox fileDeleteBox;
    @FXML
    private TextField pathFileToAppend;
    @FXML
    private VBox appendTextBox;
    @FXML
    private TextArea appendTextArea;
    @FXML
    private VBox moveActionBox;
    @FXML
    private TextField sourcePathTF;
    @FXML
    private TextField destinationPathTF;
    @FXML
    private VBox dayWeekBox;
    @FXML
    private ChoiceBox<DayOfWeek> choiceBoxDayWeek;
    @FXML
    private Button btnSetDayWeek;
    
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

        initActionVisibilityMap();
        initTriggerVisibilityMap();

        hideAllTriggerBoxes();
        hideAllActionBoxes();

        // Set values for Time trigger Spinner
        SpinnerValueFactory<Integer> hourValues = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23, 0);
        this.spinnerHourTimeTrigger.setValueFactory(hourValues);
        SpinnerValueFactory<Integer> minuteValues = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59, 0);
        this.spinnerMinuteTimeTrigger.setValueFactory(minuteValues);
        SpinnerValueFactory<Integer> dayValues  = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 30, 0);
        this.spinnerDaySleepingPeriod.setValueFactory(dayValues);
        SpinnerValueFactory<Integer> hourValues1 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23, 0);
        this.spinnerHourSleepingPeriod.setValueFactory(hourValues1);
        SpinnerValueFactory<Integer> minuteValues1 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59, 0);
        this.spinnerMinuteSleepingPeriod.setValueFactory(minuteValues1);

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
        choiceBoxDayWeek.getItems().addAll(DayOfWeek.values());
        btnSetDayWeek.disableProperty().bind(choiceBoxDayWeek.getSelectionModel().selectedItemProperty().isNull());
        
    }

    /**
     * Handles the event when the user confirms the creation of a rule.
     *
     * @param event the ActionEvent triggered by the user
     */
    @FXML
    private void confirmRuleCreationEvent(ActionEvent event) {

        String ruleName = ruleNameTF.getText();
        if(fireOnlyOnceCheckBox.isSelected())
            System.out.println("");
        Rule rule = ruleCreator.createRule(ruleName, lastTrigger, lastAction);

        RuleSet ruleSet = checker.getRuleSet();
        ruleSet.addRule(rule);
        System.out.println(ruleSet);
        System.out.println(rule);
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
        lastTrigger = ruleCreator.createTrigger(item.getValue());

        visibilityTrigger(item.getValue());

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
        lastAction = ruleCreator.createAction(item.getValue());

        visibilityAction(item.getValue());

    }

    /**
     * Selects a trigger item from the triggerTreeView and performs specific actions based on the selected item.
     */
    @FXML
    public void selectTriggerItem() {

        TreeItem<String> item = triggerTreeView.getSelectionModel().getSelectedItem();

        visibilityTrigger(item.getValue());
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

        TimeTrigger t = (TimeTrigger) lastTrigger;

        t.setTargetTime(spinnerHourTimeTrigger.getValue(), spinnerMinuteTimeTrigger.getValue());

        System.out.println(t.getTargetTime());

    }

    /**
     * Selects an action item from the actionTreeView and performs specific actions based on the selected item.
     */
    @FXML
    private void selectActionItem() {

        TreeItem<String> item = actionTreeView.getSelectionModel().getSelectedItem();

        visibilityAction(item.getValue());
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

        MessageAction m = (MessageAction) lastAction;
        m.setMessageInfo(messageInfo);

    }   
    
    private void initActionVisibilityMap() {
        actionVisibilityMap = new HashMap<>();
        actionVisibilityMap.put("Message", () -> {
                    hideAllActionBoxes();
                    messageActionBox.setVisible(true);
                    // Altre azioni specifiche per "message"
                });

                actionVisibilityMap.put("Sound", () -> {
                    hideAllActionBoxes();
                    playAudioBox.setVisible(true);
                });

                actionVisibilityMap.put("File Copy", () -> {
                    hideAllActionBoxes();
                    moveActionBox.setVisible(true);
                });

                actionVisibilityMap.put("File Delete", () -> {
                    hideAllActionBoxes();
                    fileDeleteBox.setVisible(true);
                });

                actionVisibilityMap.put("File Move", () -> {
                    hideAllActionBoxes();
                    moveActionBox.setVisible(true);
                });

                actionVisibilityMap.put("Text Append", () -> {
                    hideAllActionBoxes();
                    appendTextBox.setVisible(true);
                });

    }

    private void initTriggerVisibilityMap() {
        triggerVisibilityMap = new HashMap<>();
        triggerVisibilityMap.put("Time", () -> {
                    hideAllTriggerBoxes();
                    timeTriggerBox.setVisible(true);
                });

                triggerVisibilityMap.put("Day of Week", () -> {
                    hideAllTriggerBoxes();
                    dayWeekBox.setVisible(true);
                }); 
                
                triggerVisibilityMap.put("File Exist", () -> {
                    hideAllTriggerBoxes();
                });                 
                
                
    }
    
    private void hideAllActionBoxes() {
         
        messageActionBox.setVisible(false);
        playAudioBox.setVisible(false);
        fileDeleteBox.setVisible(false);
        appendTextBox.setVisible(false);
        moveActionBox.setVisible(false);
    }

    private void hideAllTriggerBoxes() {

        timeTriggerBox.setVisible(false);
        dayWeekBox.setVisible(false);
    } 

    private void visibilityAction(String value){

        Runnable visibilityAction = actionVisibilityMap.get(value);
        if (visibilityAction != null) {
            visibilityAction.run();
        }

    }

    private void visibilityTrigger(String value){

        Runnable visibilityTrigger = triggerVisibilityMap.get(value);
        if (visibilityTrigger != null) {
            visibilityTrigger.run();
        }

    }

    @FXML
    private void selectFilePathDelete(ActionEvent event) {
        FileDeleteAction deleteAction = (FileDeleteAction) lastAction;
        String path = getFilePath(pathDelete);
        deleteAction.setPath(path);
    }

    @FXML
    private void selectSoundPathEvent(ActionEvent event) {

        SoundAction soundAction = (SoundAction) lastAction;
        String path = getFilePath(pathSound);
        soundAction.setPath(path);

    }

    private String getFilePath(TextField txtField){

        if(txtField.getText().isEmpty()){

            FileChooser chooser = App.createFC("Open File");
            File file = chooser.showOpenDialog(new Stage());
            return (file != null) ? file.getPath() : "";

        } else{

            String path = txtField.getText();
            txtField.clear();
            return path;

        }
    }

    @FXML
    private void selectFileToAppendEvent(ActionEvent event) {

        TextAppendAction appendAction = (TextAppendAction) lastAction;
        String path = getFilePath(pathFileToAppend);
        appendAction.setFile(new File(path));

    }

    @FXML
    private void confirmAppendTextEvent(ActionEvent event) {

        TextAppendAction appendAction = (TextAppendAction) lastAction;
        appendAction.setTextAppend(appendTextArea.getText());

    }

    @FXML
    private void selectSourcePathEvent(ActionEvent event) {

        String path = getFilePath(sourcePathTF);

        if (lastAction instanceof FileMoveAction){
            FileMoveAction moveAction = (FileMoveAction) lastAction;
            moveAction.setSourcePath(path);

        } else{
            FileCopyAction copyAction = (FileCopyAction) lastAction;
            copyAction.setSourcePath(path);
        }

    }

    @FXML
    private void selectDestinationPathEvent(ActionEvent event) {

        String path = getFilePath(destinationPathTF);

        if (lastAction instanceof FileMoveAction){

            FileMoveAction moveAction = (FileMoveAction) lastAction;
            moveAction.setDestinationPath(path);
        } else{
            FileCopyAction copyAction = (FileCopyAction) lastAction;
            copyAction.setDestinationPath(path);
        }

    }

    @FXML
    private void setDayWeekEvent(ActionEvent event) {
        
        DayWeekTrigger weekTrigger = (DayWeekTrigger) lastTrigger;
        weekTrigger.setTargetDay(choiceBoxDayWeek.getValue());
         
    }
    
}
