/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package calcolatricegruppo04;

import java.net.URL;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextFormatter.Change;
import javafx.scene.control.cell.PropertyValueFactory;

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
    @FXML
    private Button buttonClear;
    @FXML
    private Button buttonNumberNegate;
    @FXML
    private Button buttonPotenza;
    @FXML
    private Button buttonModulo;
    @FXML
    private Button buttonSeno;
    @FXML
    private Button buttonCoseno;
    @FXML
    private Button buttonTangente;
  
    private ObservableList<MemoryItem> list;
    private Operazione op;
    private double num1, num2, localRes;
    private String expression,operation;
    private boolean operazioneEseguita;
    private Deque<Double> data;
    @FXML
    private Button buttonPotenza1;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        op = new Operazione();
        data = new LinkedList<>();
        num1 = 0.0;
        num2 = 0.0;
        expression = "";
        operazioneEseguita = false;
        
        list = FXCollections.observableArrayList();

        columnMemoryStack.setCellValueFactory(new PropertyValueFactory("value"));

        tableMemory.setItems(list);
        
        //Crea un filtro per consentire solo caratteri numerici
        UnaryOperator<Change> numericFilter = change -> {
            String newText = change.getControlNewText();
            if (Pattern.matches("[-+]?[0-9]*\\.?[0-9]*", newText)) {
                return change;
            }
            return null;
        };
        
        TextFormatter<String> formatter = new TextFormatter<>(numericFilter);
        textDisplayCurrent.setTextFormatter(formatter);
        
        // Disattiva i bottoni Lettura memoria e cancella memoria quando la memoria e' vuota
        BooleanBinding isMemoryEmpty = Bindings.isEmpty(tableMemory.getItems());
        buttonMemRead.disableProperty().bind(isMemoryEmpty);
        buttonMemClear.disableProperty().bind(isMemoryEmpty);
        
        // Disattiva i bottoni quando il text field dispaly e' vuoto
        BooleanBinding isTextFieldEmpty = Bindings.isEmpty(textDisplayCurrent.textProperty());
        buttonMemSave.disableProperty().bind(isTextFieldEmpty);
        
        // controlla se il TextField contiene un punto
        BooleanBinding isDotPresent = Bindings.createBooleanBinding(() ->
                textDisplayCurrent.getText().contains("."), textDisplayCurrent.textProperty());
        
        buttonDecimalNumber.disableProperty().bind(isTextFieldEmpty.or(isDotPresent));
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
        
        if (!textDisplayCurrent.getText().isEmpty()) {  
            num1 = Double.parseDouble(textDisplayCurrent.getText())*(-1);
        }
        
        textDisplayCurrent.setText(Double.toString(num1));
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
            case "^":
                localRes = op.potenza(num1, num2);
                break;
            case "tan":
                localRes = op.tan(num1); 
                break;
            case "sin":
                localRes = op.sin(num1);
                break;
            case "cos":
                localRes = op.cos(num1);
                break;
            case "mod":
                localRes = op.modulo(num1);
                break;
            case "sqrt":
                localRes = op.sqrt(num1);
                break;    
                
                
            default:
                System.out.println("Parameter is unknown");

        };
        aggiornaRisultato();
    }

    private void aggiornaRisultato(){

       expression = Double.toString(localRes);
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
        double value = Double.parseDouble(textDisplayCurrent.getText());
        item.setValue(value);
        list.add(item);
        
    }

    @FXML
    private void handleButtonActionMemRead(ActionEvent event) {
        int s=list.size();
        MemoryItem m = list.remove(s-1);
        double num = m.getValue();
        num2 = num;
        textDisplayCurrent.setText(Double.toString(num));
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
        localRes = 0.0;
    }
    
    private void caricaNumbers(){
    
        if(!expression.isEmpty() && !textDisplayCurrent.getText().isEmpty()){    
            num1 = Double.parseDouble(expression);
            num2 = Double.parseDouble(textDisplayCurrent.getText());
        }
    }

    @FXML
    private void handleButtonActionPotenza(ActionEvent event) {
        operation = "^";
        
            if(!expression.isEmpty() && !operazioneEseguita){
        
        caricaNumbers();
        localRes = op.potenza(num1, num2);
        aggiornaRisultato();
    } else {

        toExpression();
    }

    }

    @FXML
    private void handleButtonActionModulo(ActionEvent event) {
        operation = "mod";
        if (!textDisplayCurrent.getText().isEmpty()) {  
            num1 = op.modulo(Double.parseDouble(textDisplayCurrent.getText()));
        }
        localRes = num1;
        textDisplayCurrent.setText(Double.toString(localRes));
    }

    @FXML
    private void handleButtonActionSeno(ActionEvent event) {
      operation = "sin";  
      if (!textDisplayCurrent.getText().isEmpty()) {  
            num1 = op.sin(Double.parseDouble(textDisplayCurrent.getText()));
        }  
        localRes = num1;
        textDisplayCurrent.setText(Double.toString(localRes));
    }

    @FXML
    private void handleButtonActionCoseno(ActionEvent event) {
        operation = "cos";
        if (!textDisplayCurrent.getText().isEmpty()) {  
            num1 = op.cos(Double.parseDouble(textDisplayCurrent.getText()));
        }  
        localRes = num1;
        textDisplayCurrent.setText(Double.toString(localRes));
    }

    @FXML
    private void handleButtonActionTangente(ActionEvent event) {
        operation = "tan";
        if (!textDisplayCurrent.getText().isEmpty()) {  
            num1 = op.tan(Double.parseDouble(textDisplayCurrent.getText()));
        }  
        localRes = num1;
        textDisplayCurrent.setText(Double.toString(localRes));
    }

    @FXML
    private void handleButtonActionRadice(ActionEvent event) {
        operation = "sqrt";
        if (!textDisplayCurrent.getText().isEmpty()) {  
            num1 = op.sqrt(Double.parseDouble(textDisplayCurrent.getText()));
        }  
        localRes = num1;
        textDisplayCurrent.setText(Double.toString(localRes));
    }
}
