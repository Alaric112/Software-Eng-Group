/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group2.Model.Rule.FileManager;

import group2.Model.Rule.ControlRuleChecker;
import java.io.File;
import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

/**
 *
 * UNUSUED, OLD IMPLEMENTATION
 * 
 * @author Alessandro Accarino
 */
public class LoadRuleSetService extends Service<RuleList> {
    
    private File file;
    private ControlRuleChecker checker;
    
    public LoadRuleSetService(File file) {
        this.file = file;
        checker = ControlRuleChecker.getInstance();
    }

    @Override
    protected Task<RuleList> createTask() {
        return new Task<RuleList>() {
            @Override
            protected RuleList call() throws Exception {               
                    // Esegui l'operazione di caricamento del RuleList da file
               RuleList ruleSet = FileIOManager.loadRuleList(file);
                
                Platform.runLater(() -> {
                    if (ruleSet != null) {
                        checker.changeRuleset(ruleSet);
                    }
                });
                return ruleSet;
            }
        };
    }
    
}
