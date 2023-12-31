package group2.Controller;

import group2.App;
import group2.Model.Action.*;
import group2.Model.Trigger.*;
import group2.Model.Rule.*;
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
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.stage.DirectoryChooser;

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
 * @author Alessandro Accarino
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
    @FXML
    private VBox FileExistBox;
    @FXML
    private TextField FileExistNameTF;
    @FXML
    private Button btnFileExist;
    @FXML
    private CheckBox sleepRuleCheckBox;
    @FXML
    private VBox sleepRuleBox;
    @FXML
    private CheckBox fireOnceCheckBox;
    @FXML
    private VBox dateTriggerBox;
    @FXML
    private DatePicker datePickTrigger;
    @FXML
    private Button btnSetDateTrigger;
    @FXML
    private VBox excProgrammActionBox;
    @FXML
    private TextField programmPathTF;
    @FXML
    private TextField excArgumentsTF;
    @FXML
    private Button setExcArgsBtn;
    @FXML
    private VBox dayMonthBox;
    @FXML
    private ChoiceBox<Month> choiceBoxMonths;
    @FXML
    private Spinner<Integer> dayMonthSpinner;
    @FXML
    private Button btnSetDayMonthTrigger;
    @FXML
    private VBox sizeFileBox;
    @FXML
    private TextField sizeField;
    @FXML
    private Button insertSizeButton;
    @FXML
    private ListView<String> actionListView;  
    private RuleCreator ruleCreator = RuleCreator.getInstance();
    private File folderPath;
    private Trigger lastTrigger;
    private static Map<String, Runnable> actionVisibilityMap;
    private static Map<String, Runnable> triggerVisibilityMap;
    private long millisecond;
    private Action lastAction;
    private List<Action> sequenceAction;
    @FXML
    private CheckBox checkRegisterAction;
    @FXML
    private TextField actionRegisterTF;
    @FXML
    private HBox macroActionBox;
    @FXML
    private Button addMacroActionBtn;
    @FXML
    private ChoiceBox<String> macroActionChoiceBox;
    @FXML
    private VBox exitStatusTriggerBox;
    @FXML
    private Spinner<Integer> exitStatusPicker;
    /**
     * Initializes the controller class. This method is automatically called
     * after the FXML file has been loaded.
     *
     * @param url the location used to resolve relative paths for the root object, or null if the location is not known
     * @param rb the resources used to localize the root object, or null if the root object was not localized
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        millisecond = 0;
        triggerChoiceBox.getItems().addAll(ruleCreator.getAvailableTriggerTypes());
        actionChoiceBox.getItems().addAll(ruleCreator.getAvailableActionTypes());

        sequenceAction = new ArrayList();
        
        initActionVisibilityMap();
        initTriggerVisibilityMap();

        hideAllTriggerBoxes();
        hideAllActionBoxes();

        // Set values for Time trigger Spinner
        setupSpinner(spinnerHourTimeTrigger, 0, 23, 0);
        setupSpinner(spinnerMinuteTimeTrigger, 0, 59, 0);
        setupSpinner(spinnerDaySleepingPeriod, 0, 30, 0);
        setupSpinner(spinnerHourSleepingPeriod, 0, 23, 0);
        setupSpinner(spinnerMinuteSleepingPeriod, 0, 59, 0);
        setupSpinner(dayMonthSpinner, 1, 31, 1);
        setupSpinner(exitStatusPicker, -100, 100, 1);        
        
        bindButtonToTextField(insertSizeButton, sizeField);                
                
        // Imposta la selezione predefinita (puoi scegliere quale impostare come predefinita)
        fireOnceCheckBox.setSelected(true);
        
        // Disabilita checkBox2 quando checkBox1 è selezionata e viceversa
        fireOnceCheckBox.disableProperty().bind(sleepRuleCheckBox.selectedProperty());
        sleepRuleCheckBox.disableProperty().bind(fireOnceCheckBox.selectedProperty());        
        
        // Lega la visibilità della VBox allo stato selezionato della checkBox2
        sleepRuleBox.visibleProperty().bind(sleepRuleCheckBox.selectedProperty());        
        
        // Disable the confirmation button when the rule name text field is empty
        BooleanBinding isTextFieldEmpty = Bindings.isEmpty(ruleNameTF.textProperty());

        // Disable when the TreeView is empty, i.e., it does NOT have a root
        BooleanProperty triggerTreeViewHasRoot = new SimpleBooleanProperty();

        triggerTreeViewHasRoot.bind(Bindings.createBooleanBinding(()
                -> triggerTreeView.getRoot() != null, triggerTreeView.rootProperty()));

        confirmButton.disableProperty().bind(isTextFieldEmpty.or(triggerTreeViewHasRoot.not()).or(Bindings.isEmpty(actionListView.getItems())));        
        choiceBoxDayWeek.getItems().addAll(DayOfWeek.values());
        choiceBoxMonths.getItems().addAll(Month.values());
        
        bindButtonToTextField(btnFileExist, FileExistNameTF);
        bindButtonToChoiceBox(btnSetDayWeek, choiceBoxDayWeek);
        bindButtonToDatePicker(btnSetDateTrigger, datePickTrigger);
        bindButtonToTextField(setExcArgsBtn, excArgumentsTF);
        bindButtonToChoiceBox(btnSetDayMonthTrigger, choiceBoxMonths);
        bindButtonToChoiceBox(addActionButton, actionChoiceBox);
        bindButtonToChoiceBox(addTriggerButton, triggerChoiceBox);
        bindButtonToChoiceBox(addMacroActionBtn, macroActionChoiceBox);
        bindButtonToCheckBox(addMacroActionBtn, checkRegisterAction);
        
        // Binding della visibilità del TextField al valore della CheckBox
        actionRegisterTF.visibleProperty().bind(checkRegisterAction.selectedProperty());        
        
        macroActionBox.setVisible(!ruleCreator.macroActionEmpty());
        setUpActionMacroBox();
    }
    
    /**
     * Handles the event when the user confirms the creation of a rule.
     *
     * @param event the ActionEvent triggered by the user
     */
    @FXML
    private void confirmRuleCreationEvent(ActionEvent event) {
        
        if(sequenceAction.size() != 1){            
            lastAction = ruleCreator.createCompositeAction(sequenceAction);
        }        

        String ruleName = ruleNameTF.getText();
        String ruleType = "OnlyOnce";      
        if (sleepRuleCheckBox.isSelected())
            ruleType = "SleepingTime";               
                    
        Rule rule = ruleCreator.createRule(ruleName, lastTrigger, lastAction, ruleType);
        if (rule instanceof SleepingRuleDecorator){
            SleepingRuleDecorator s = (SleepingRuleDecorator) rule;
            s.setMinSleepTime(millisecond);
        
        }

        if(checkRegisterAction.isSelected()){
            ruleCreator.registerActionMacro(actionRegisterTF.getText(), lastAction);        
        }

        closeWindowEvent(event);
    }

    private void setUpActionMacroBox(){
        
        if(!ruleCreator.macroActionEmpty()){
        actionChoiceBox.getItems().addAll(ruleCreator.getAvailableActionTypes());            
            macroActionChoiceBox.getItems().addAll(ruleCreator.getAvailableMacroAction());
        }        
        
    }
   
    // Method to set up spinner values
    private void setupSpinner(Spinner<Integer> spinner, int min, int max, int initialValue) {
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(min, max, initialValue);
        spinner.setValueFactory(valueFactory);
    }    

    // Method to set up BooleanBinding for disabling a button based on TextField empty property
    private void bindButtonToTextField(Button button, TextField textField) {
        button.disableProperty().bind(Bindings.isEmpty(textField.textProperty()));
    }

    // Method to set up BooleanBinding for disabling a button based on ChoiceBox selectedItemProperty
    private <T> void bindButtonToChoiceBox(Button button, ChoiceBox<T> choiceBox) {
        button.disableProperty().bind(choiceBox.getSelectionModel().selectedItemProperty().isNull());
    }

    // Method to set up BooleanBinding for disabling a button based on DatePicker valueProperty
    private void bindButtonToDatePicker(Button button, DatePicker datePicker) {
        button.disableProperty().bind(datePicker.valueProperty().isNull());
    }

    // Method to set up BooleanBinding for disabling a button based on CheckBox selectedProperty
    private void bindButtonToCheckBox(Button button, CheckBox checkBox) {
        button.disableProperty().bind(checkBox.selectedProperty());
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
        
        String item = actionChoiceBox.getValue();

        actionListView.getItems().add(item);
        Action action = ruleCreator.createAction(item);
        sequenceAction.add(action);
        lastAction = action;
        visibilityAction(item);                        

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

    }

    /**
     * Selects an action item from the actionListView and performs specific actions based on the selected item.
     */
    private void selectActionItem() {

        String item = actionListView.getSelectionModel().getSelectedItem();
        int selectedIndex = actionListView.getSelectionModel().getSelectedIndex();
        lastAction = sequenceAction.get(selectedIndex);
        
        visibilityAction(item);
    }

    @FXML
    private void selectActionItemEvent(MouseEvent event) {
        
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
                
                actionVisibilityMap.put("Execute Programm", () -> {
                    hideAllActionBoxes();
                    excProgrammActionBox.setVisible(true);
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
                    FileExistBox.setVisible(true);
                });

                triggerVisibilityMap.put("Date", () -> {
                    hideAllTriggerBoxes();
                    dateTriggerBox.setVisible(true);
                });                

                triggerVisibilityMap.put("Day of Month", () -> {
                    hideAllTriggerBoxes();
                    dayMonthBox.setVisible(true);
                });   
                
                triggerVisibilityMap.put("File Size", () -> {
                    hideAllTriggerBoxes();
                    sizeFileBox.setVisible(true);
                });
                
                triggerVisibilityMap.put("Exit Status", () -> {
                    hideAllTriggerBoxes();
                    exitStatusTriggerBox.setVisible(true);
                });
                
                
    }
    
    private void hideAllActionBoxes() {
         
        messageActionBox.setVisible(false);
        playAudioBox.setVisible(false);
        fileDeleteBox.setVisible(false);
        appendTextBox.setVisible(false);
        moveActionBox.setVisible(false);
        excProgrammActionBox.setVisible(false);
        
    }

    private void hideAllTriggerBoxes() {

        timeTriggerBox.setVisible(false);
        dayWeekBox.setVisible(false);
        FileExistBox.setVisible(false);
        dateTriggerBox.setVisible(false);
        dayMonthBox.setVisible(false);
        sizeFileBox.setVisible(false);
        exitStatusTriggerBox.setVisible(false);
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
        String path = getFilePath(pathDelete, false);
        deleteAction.setPath(path);
    }

    @FXML
    private void selectSoundPathEvent(ActionEvent event) {

        SoundAction soundAction = (SoundAction) lastAction;
        String path = getFilePath(pathSound, false);
        soundAction.setPath(path);

    }

    private String getFilePath(TextField txtField, boolean isFolder){
        File file;
        if(txtField.getText().isEmpty()){
            if(isFolder){
                
                DirectoryChooser directoryChooser = new DirectoryChooser();
                directoryChooser.setTitle("Select a directory");
                file = directoryChooser.showDialog(new Stage());
                
            } else{
                FileChooser chooser = App.createFC("Open File");
                file = chooser.showOpenDialog(new Stage());

            }
            return (file != null) ? file.getPath() : "";

        } else{

            String path = txtField.getText();
            txtField.clear();
            return path;

        }

    }

    @FXML
    private void addMacroActionEvent(ActionEvent event) {
        
        String item = macroActionChoiceBox.getValue();
        actionListView.getItems().add(item);
        Action action = ruleCreator.getMacroAction(item);
        sequenceAction.add(action);
        lastAction = action;
        
    }    
    
    @FXML
    private void selectFileToAppendEvent(ActionEvent event) {

        TextAppendAction appendAction = (TextAppendAction) lastAction;
        String path = getFilePath(pathFileToAppend, false);
        appendAction.setFile(new File(path));

    }

    @FXML
    private void confirmAppendTextEvent(ActionEvent event) {

        TextAppendAction appendAction = (TextAppendAction) lastAction;
        appendAction.setTextAppend(appendTextArea.getText());

    }

    @FXML
    private void selectSourcePathEvent(ActionEvent event) {

        String path = getFilePath(sourcePathTF, false);

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

        String path = getFilePath(destinationPathTF, true);

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

    @FXML
    private void selectFolderPathEvent(ActionEvent event) {
        
        DirectoryChooser directoryChooser = new DirectoryChooser();

        directoryChooser.setTitle("Select a directory");

        folderPath = directoryChooser.showDialog(new Stage());
    }

    @FXML
    private void confirmFileExistEvent(ActionEvent event) {
        
        FileTrigger fileTrigger = (FileTrigger) lastTrigger;
        fileTrigger.setTargetFile(folderPath.getAbsolutePath(), FileExistNameTF.getText());
        
    }

    @FXML
    private void setDateTriggerEvent(ActionEvent event) {
        
        DateTrigger dateTrigger = (DateTrigger) lastTrigger;
        dateTrigger.setTargetDate(datePickTrigger.getValue());
        
    }

    @FXML
    private void selectExcPathEvent(ActionEvent event) {
   
        String path = getProgrammPath();
        ExcProgrammAction excProgrammAction = (ExcProgrammAction) lastAction;
        excProgrammAction.setProgramPath(path);        
        
    }

    @FXML
    private void setExcArgsEvent(ActionEvent event) {
        
        ExcProgrammAction excProgrammAction = (ExcProgrammAction) lastAction;
        excProgrammAction.setCommandLineArg(excArgumentsTF.getText());
        
    }

    @FXML
    private void setDayMonthTriggerEvent(ActionEvent event) {
        
        DayMonthTrigger dayMonthTrigger = (DayMonthTrigger) lastTrigger;
        dayMonthTrigger.setTargetMonthDay(choiceBoxMonths.getValue().getValue(), dayMonthSpinner.getValue());
        
    }

    @FXML
    private void checkSizeAction(ActionEvent event) {
        SizeFileTrigger sizef = (SizeFileTrigger) lastTrigger;
        long l = Long.parseLong(sizeField.getText()) ;
        sizeField.clear();
        sizef.setSizeFile(l);
        sizef.setTargetFile(folderPath);
        sizef.setTargetFile(folderPath.getPath(), folderPath.getName());
    }

    @FXML
    private void selectFileEvent(ActionEvent event) {
        
         FileChooser chooser = App.createFC("Open File");
         File file = chooser.showOpenDialog(new Stage());
         folderPath = file;
    }

    @FXML
    private void InsertSleepingRuleSettings(ActionEvent event) {       
       Integer days = spinnerDaySleepingPeriod.getValue();
       Integer hours = spinnerHourSleepingPeriod.getValue();
       Integer minutes = spinnerMinuteSleepingPeriod.getValue();         
       millisecond = TimeConversionUtil.convertToMilliseconds(days, hours, minutes);      
    }

    @FXML
    private void selectProgrammEvent(ActionEvent event) {
        
        String exePath = getProgrammPath();
        ExitStatusTrigger trigger = (ExitStatusTrigger) lastTrigger;
        trigger.setExternalProgram(exePath); 
    
    }        
    
    @FXML
    private void setExitStatusTriggerEvent(ActionEvent event) {
        
        ExitStatusTrigger trigger = (ExitStatusTrigger) lastTrigger;
        trigger.setUserExitStatus(exitStatusPicker.getValue());
    }

    private String getProgrammPath(){
        
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select an executable file");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Executable Files", "*.exe", "*.jar"),
                new FileChooser.ExtensionFilter("All Files", "*.*")
        );

        File selectedFile = fileChooser.showOpenDialog(new Stage());

        return selectedFile.getAbsolutePath(); 
        
    }    
    
}
