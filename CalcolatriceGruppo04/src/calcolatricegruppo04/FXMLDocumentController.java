/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package calcolatricegruppo04;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

/**
 *
 * @author patap
 */
public class FXMLDocumentController implements Initializable {

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
    private Button buttonMemSave;
    @FXML
    private Button buttonMemRead;
    @FXML
    private Button buttonMemClear;
    @FXML
    private TableView<MemoryItem> tableMemory;
    @FXML
    private TableColumn<MemoryItem, Double> columnMemoryStack;
    @FXML
    private Button buttonNumber;

    private ObservableList<MemoryItem> list;

    private Operazione op;
    private double num1, num2, localRes, res;
    private String expression,operation;
    private boolean operazioneEseguita;
    
    @FXML
    private Button buttonNumber9;
    @FXML
    private Button buttonClear;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        op = new Operazione();
        
        num1 = 0.0;
        num2 = 0.0;
        expression = "";
        operazioneEseguita = false;
        
        list = FXCollections.observableArrayList();

        columnMemoryStack.setCellFactory(new PropertyValueFactory("value"));

        tableMemory.setItems(list);

        // Disattiva i bottoni Lettura memoria e cancella memoria quando la memoria e' vuota
        BooleanBinding xx = Bindings.isEmpty(tableMemory.getItems());
        buttonMemRead.disableProperty().bind(xx);
        buttonMemClear.disableProperty().bind(xx);

    }


    @FXML
    private void handleButtonActionNumber(ActionEvent event) {
    
    if(operazioneEseguita) {
        
        textDisplayCurrent.clear();
        operazioneEseguita = false;
    }
    
        String num = ((Button)event.getSource()).getText();
        textDisplayCurrent.setText(textDisplayCurrent.getText()+num);

    }



    @FXML
    private void handleButtonActionNegativeNumber(ActionEvent event) {
    }

    @FXML
    private void handleButtonActionAdd(ActionEvent event) {

    operation = "+";
    
    if(!expression.isEmpty() && !operazioneEseguita){
        
            caricaNumbers();
            localRes = op.somma(num1, num2);
            aggiornaRisultato();
            
    } else {

        toExpression();
    }

    }

    @FXML
    private void handleButtonActionMulti(ActionEvent event) {

    operation = "*";    
        
    if(!expression.isEmpty() && !operazioneEseguita){
        
        caricaNumbers();
        localRes = op.prodotto(num1, num2);
        aggiornaRisultato();
        
    } else {

        toExpression();
    }

    }

    @FXML
    private void handleButtonActionDiff(ActionEvent event) {

    operation = "-";    
        
    if(!expression.isEmpty() && !operazioneEseguita){
        
        caricaNumbers();
        localRes = op.differenza(num1, num2);
        aggiornaRisultato();
    } else {

        toExpression();
    }

    }

    @FXML
    private void handleButtonActionDiv(ActionEvent event) {

    operation = "/";    
        
    if(!expression.isEmpty() && !operazioneEseguita){
         
        caricaNumbers();
        localRes = op.divisione(num1, num2);
        aggiornaRisultato();
    } else {

        toExpression();
    }

    }

    @FXML
    private void handleButtonActionResult(ActionEvent event) {

        caricaNumbers();

        switch (operation) {
            case "+":
                localRes = op.somma(num1, num2);
                break;
            case "-":
                localRes = op.differenza(num1, num2);
                break;
            case "*":
                localRes = op.prodotto(num1, num2);
                break;
            case "/":
                localRes = op.divisione(num1, num2);
                break;

            default:
                System.out.println("Parameter is unknown");

        };
        aggiornaRisultato();
    }

    private void aggiornaRisultato(){

       expression = Double.toString(localRes);
       res += localRes;
       textDisplayCurrent.setText(expression);
       operazioneEseguita = true;
    }

    private void toExpression(){

       expression = textDisplayCurrent.getText();
       textDisplayCurrent.clear();
       operazioneEseguita = false;
    }

    @FXML
    private void handleButtonActionMemSave(ActionEvent event) {

        MemoryItem item = new MemoryItem();
        item.setValue(Double.parseDouble(expression));

        list.add(item);
    }

    @FXML
    private void handleButtonActionMemRead(ActionEvent event) {


    }

    @FXML
    private void handleButtonActionMemClear(ActionEvent event) {

        list.clear();
    }

    @FXML
    private void handleButtonActionClear(ActionEvent event) {

        expression = "";
        textDisplayCurrent.clear();
        num1 = 0.0;
        num2 = 0.0;
    }
    
    private void caricaNumbers(){
        
        num1 = Double.parseDouble(expression);
        num2 = Double.parseDouble(textDisplayCurrent.getText());
    }
}
