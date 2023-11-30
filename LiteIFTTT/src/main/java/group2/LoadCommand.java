/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group2;

import java.io.File;
import javafx.concurrent.Service;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

/**
 *
 * @author patap
 */
public class LoadCommand implements Command  {
    
    private ControlRuleChecker checker;
    private Runnable onLoadCompletion; 
    
    public LoadCommand() {

       checker = ControlRuleChecker.getInstance();
       onLoadCompletion = null;
       
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
        
        Service serv = FileIOManager.loadFromFileAsync(file);
        
        if (onLoadCompletion != null){
            serv.setOnSucceeded(event -> {
                onLoadCompletion.run();
            });
        }
        
    }                

    public Runnable getOnLoadCompletion() {
        return onLoadCompletion;
    }

    public void setOnLoadCompletion(Runnable onLoadCompletion) {
        this.onLoadCompletion = onLoadCompletion;
    } 
    
}
