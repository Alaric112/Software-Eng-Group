/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package group2;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author patap
 */
public class SubWindowsCreationRuleSetController implements Initializable {

    @FXML
    private Button closeButton;
    @FXML
    private Button confirmButton;
    @FXML
    private Spinner<Integer> spinnerControlTime;
    @FXML
    private TextField ruleSetNameTextField;
    private ControlRuleChecker checker =ControlRuleChecker.getInstance();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       confirmButton.disableProperty().bind(ruleSetNameTextField.textProperty().isEmpty()); 
       SpinnerValueFactory<Integer> gradesValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(5, 60, 5);
       this.spinnerControlTime.setValueFactory(gradesValueFactory);
    }    

    @FXML
    private void closeWindowAction(ActionEvent event) {
        
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();       
    }

    @FXML
    private void creationRuleSetConfirmationAction(ActionEvent event) {

        Ruleset rules=new Ruleset(spinnerControlTime.getValue(),ruleSetNameTextField.getText());
        checker.changeRuleset(rules);

        switchTo("secondary");
        closeWindowAction(event);
        
    }
    
    private void switchTo(String fxml){
        
        try {
            App.setRoot("secondary");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }  
    
}
