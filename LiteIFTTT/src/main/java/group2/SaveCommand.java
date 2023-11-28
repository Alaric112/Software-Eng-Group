/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group2;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author patap
 */
public class SaveCommand implements Command {

    private RuleSet ruleSet;
    private List<Rule> rules;
    
    public SaveCommand(RuleSet ruleSet) {
        this.ruleSet = ruleSet;
        this.rules = new ArrayList<>();
        rules.addAll(ruleSet.getRules());
    }    
    
    @Override
    public void execute() {
        
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Save RuleSet");

        // Imposta il filtro per permettere solo i file .dat
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Data Files (*.dat)", "*.dat");
        chooser.getExtensionFilters().add(extFilter);

        // Mostra la finestra di dialogo per salvare il file
        File file = chooser.showSaveDialog(new Stage());

        if (file != null) {
            // Aggiunge l'estensione .dat se l'utente non l'ha inserita
            String path = file.getPath();
            if (!path.toLowerCase().endsWith(".dat")) {
                file = new File(path + ".dat");
            }

            try (ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)))) {                
                out.writeObject(ruleSet);
                out.writeObject(rules);
            } catch (IOException e) {
            // Gestisci l'eccezione in modo appropriato
            System.err.println("Errore durante il salvataggio del file: " + e.getMessage());

                // Mostra un messaggio di errore all'utente
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Errore");
                alert.setHeaderText(null);
                alert.setContentText("Si è verificato un errore durante il salvataggio del file.");
                alert.showAndWait();
            }
        }
     
    }
    
}
