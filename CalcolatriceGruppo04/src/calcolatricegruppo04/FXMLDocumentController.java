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
    private double val,x, y, localRes, res;
    private String expression;
      
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        op = new Operazione();
        x = 0.0;
        y = 0.0;
        
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
        
        String num = ((Button)event.getSource()).getText();
        expression = textDisplayCurrent.getText()+num;
        textDisplayCurrent.setText(expression);
        
        
    }

    @FXML
    private void handleButtonActionNegativeNumber(ActionEvent event) {
    }

    @FXML
    private void handleButtonActionAdd(ActionEvent event) {
    
    if(expression.isEmpty()){    
       localRes = op.somma(x, y);
       System.out.print("prova");
       aggiornaRisultato();     
    }else {
            
        toExpression();      
    }
    
    }

    @FXML
    private void handleButtonActionMulti(ActionEvent event) {
    
    if(expression.isEmpty()){    
       localRes = op.prodotto(x, y);
       aggiornaRisultato();
    } else {
        
        toExpression();
    }  
       
    }

    @FXML
    private void handleButtonActionDiff(ActionEvent event) {
    
    if(expression.isEmpty()){     
       localRes = op.differenza(x, y);
       aggiornaRisultato();
    } else {
        
        toExpression();
    }
    
    }

    @FXML
    private void handleButtonActionDiv(ActionEvent event) {
    
    if(expression.isEmpty()){     
       localRes = op.divisione(x, y);
       aggiornaRisultato();
    } else {
        
        toExpression();
    }
        
    }
    
    @FXML
    private void handleButtonActionResult(ActionEvent event) {
    }
    
    private void aggiornaRisultato(){
        
       x = localRes;
       expression = Double.toString(x);
       res += localRes;
       textDisplayCurrent.setText(expression);
    }
    
    private void toExpression(){
               
       expression = textDisplayCurrent.getText();
       x = Double.parseDouble(expression);
       textDisplayCurrent.clear();
    }

    @FXML
    private void handleButtonActionMemSave(ActionEvent event) {
        
        MemoryItem item = new MemoryItem();
        item.setValue(Double.parseDouble(expression));
        
        list.add(item);
    }

    @FXML
    private void handleButtonActionMemRead(ActionEvent event) {
        
        //list.
    }

    @FXML
    private void handleButtonActionMemClear(ActionEvent event) {
        
        list.clear();
    }
    
}
