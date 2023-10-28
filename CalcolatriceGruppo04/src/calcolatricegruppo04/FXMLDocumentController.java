/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package calcolatricegruppo04;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author patap
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Button buttonNumber7;
    @FXML
    private Button buttonNumber4;
    @FXML
    private Button buttonNumber1;
    @FXML
    private Button buttonNumber9;
    @FXML
    private Button buttonNumber8;
    @FXML
    private Button buttonNumber5;
    @FXML
    private Button buttonNumber2;
    @FXML
    private Button buttonNumber0;
    @FXML
    private Button buttonNumber6;
    @FXML
    private Button buttonNumber3;
    @FXML
    private Button buttonDecimalNumber;
    @FXML
    private Button buttonAdd;
    @FXML
    private Button buttonMulti;
    @FXML
    private Button buttonDiff;
    @FXML
    private Button buttonDiv;
    @FXML
    private Button buttonResult;
    @FXML
    private TextField textDisplayCurrent;
    @FXML
    private TextField textDisplayExpression;
    
    
    private Operazione op;
    private double x, y, localRes, res;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        op = new Operazione();
        x = 0.0;
        y = 0.0;
        
        
    }    

    @FXML
    private void handleButtonActionNumber(ActionEvent event) {
    }

    @FXML
    private void handleButtonActionNegativeNumber(ActionEvent event) {
    }

    @FXML
    private void handleButtonActionAdd(ActionEvent event) {
        
       localRes = op.somma(x, y);
       x = localRes;
       res += localRes;
    }

    @FXML
    private void handleButtonActionMulti(ActionEvent event) {
        
       localRes = op.prodotto(x, y);
       x = localRes;
       res += localRes;
    }

    @FXML
    private void handleButtonActionDiff(ActionEvent event) {
        
       localRes = op.differenza(x, y);
       x = localRes;
       res += localRes;
    }

    @FXML
    private void handleButtonActionDiv(ActionEvent event) {
        
       localRes = op.divisione(x, y);
       x = localRes;
       res += localRes;
    }

    @FXML
    private void handleButtonActionResult(ActionEvent event) {
    }
    
}
