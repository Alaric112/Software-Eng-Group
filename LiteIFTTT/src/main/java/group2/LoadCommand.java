/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group2;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

/**
 *
 * @author patap
 */
public class LoadCommand implements Command {
    
    private ControlRuleChecker checker;
    
    public LoadCommand() {

       checker = ControlRuleChecker.getInstance();
       
    }
    
    @Override
    public void execute() {
        
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Load RuleSet");

        // Imposta il filtro per permettere solo i file .dat
        ExtensionFilter extFilter = new ExtensionFilter("Data Files (*.dat)", "*.dat");
        chooser.getExtensionFilters().add(extFilter);

        // Mostra la finestra di dialogo per selezionare il file da caricare
        File file = chooser.showOpenDialog(new Stage());

        if (file != null) {
            try (ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)))) {

               // RuleSet ruleSet = checker.getRuleSet();
                RuleSet ruleSet = new RuleSet(5, "");
//                RuleSet ruleSet = new RuleSet(5, "Loading");
                ObservableList<Rule> rules = FXCollections.observableArrayList(); 
//                ObservableList<Rule> rules = ruleSet.getRules();

                ruleSet = (RuleSet) in.readObject();
                List<Rule> loadedRules = (ArrayList<Rule>) in.readObject();

                rules.setAll(loadedRules);
                ruleSet.setRules(rules);
                
                checker.changeRuleset(ruleSet);
                System.out.println(checker.getRuleSet());
                System.out.println(checker.getRuleSet().getRules());
                
            } catch (FileNotFoundException e) {
                
            } catch (IOException | ClassNotFoundException ex) {
                                // Mostra un messaggio di errore all'utente
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Errore");
                alert.setHeaderText(null);
                alert.setContentText("Si è verificato un errore durante il caricamento del file.");
                alert.showAndWait();
            }
        }
    }                
    
}
