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
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
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
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        triggerChoiceBox.getItems().addAll(ruleCreator.getAvailableTriggerTypes());
        actionChoiceBox.getItems().addAll(ruleCreator.getAvailableActionTypes());

        timeTriggerBox.setVisible(false);
        messageActionBox.setVisible(false);
        playAudioBox.setVisible(false);

        // TO REFACTOR
        SpinnerValueFactory<Integer> hourValues = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23, 0);
        this.spinnerHourTimeTrigger.setValueFactory(hourValues);
        SpinnerValueFactory<Integer> minuteValues = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59, 0);
        this.spinnerMinuteTimeTrigger.setValueFactory(minuteValues);

        // Disattiva bottone conferma quando il text field del nome della regola e' vuoto
        BooleanBinding isTextFieldEmpty = Bindings.isEmpty(ruleNameTF.textProperty());

        // Disattiva quando il TreeView e' vuoto, ovvero NON ha una radice
        BooleanProperty triggerTreeViewHasRoot = new SimpleBooleanProperty();

        triggerTreeViewHasRoot.bind(Bindings.createBooleanBinding(()
                -> triggerTreeView.getRoot() != null, triggerTreeView.rootProperty()));

        BooleanProperty actionTreeViewHasRoot = new SimpleBooleanProperty();

        actionTreeViewHasRoot.bind(Bindings.createBooleanBinding(()
                -> actionTreeView.getRoot() != null, actionTreeView.rootProperty()));

        confirmButton.disableProperty().bind(isTextFieldEmpty.or(triggerTreeViewHasRoot.not()).or(actionTreeViewHasRoot.not()));

    }

    @FXML
    private void confirmRuleCreationEvent(ActionEvent event) {

        String ruleName = ruleNameTF.getText();

        Rule rule = ruleCreator.createRule(ruleName, trigger, action);

        RuleSet ruleSet = checker.getRuleSet();
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

        TreeItem<String> item = new TreeItem<>(triggerChoiceBox.getValue());
        triggerTreeView.setRoot(item);
        trigger = ruleCreator.createTrigger(item.getValue());

    }

    @FXML
    private void addActionEvent(ActionEvent event) {

        TreeItem<String> item = new TreeItem<>(actionChoiceBox.getValue());
        actionTreeView.setRoot(item);
        action = ruleCreator.createAction(item.getValue());

    }

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

    @FXML
    private void setTimeEvent(ActionEvent event) {

        TimeTrigger t = (TimeTrigger) trigger;

        t.setTargetTime(spinnerHourTimeTrigger.getValue(), spinnerMinuteTimeTrigger.getValue());

        System.out.println(t.getTargetTime());

    }

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

    @FXML
    private void insertMessageAction(ActionEvent event) {

        String messageInfo = textMessageArea.getText();
        textMessageArea.clear();

        MessageAction m = (MessageAction) action;
        m.setMessageInfo(messageInfo);

    }

    @FXML
    private void selectPathEvent(ActionEvent event) {

        FileChooser chooser = new FileChooser();
        chooser.setTitle("Open File");
        File file = chooser.showOpenDialog(new Stage());
        String path = file.getPath();
        pathSound.setText(path);
        SoundAction s = (SoundAction) action;
        s.setPath(path);

    }

}
