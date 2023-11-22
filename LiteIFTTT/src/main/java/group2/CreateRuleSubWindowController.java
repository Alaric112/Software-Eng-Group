/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package group2;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void confirmRuleCreationEvent(ActionEvent event) {
        
        closeWindowEvent(event);
    }

    @FXML
    private void closeWindowEvent(ActionEvent event) {
        
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

}
