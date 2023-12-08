/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group2.Model.Rule.FileManager;

import group2.Model.Rule.ControlRuleChecker;
import group2.Model.Rule.RuleSet;
import java.io.File;
import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

/**
 *
 * @author patap
 */
public class LoadRuleSetService extends Service<RuleSet> {
    
    private File file;
    private ControlRuleChecker checker;
    
    public LoadRuleSetService(File file) {
        this.file = file;
        checker = ControlRuleChecker.getInstance();
    }

    @Override
    protected Task<RuleSet> createTask() {
        return new Task<RuleSet>() {
            @Override
            protected RuleSet call() throws Exception {               
                    // Esegui l'operazione di caricamento del RuleSet da file
               RuleSet ruleSet = FileIOManager.loadRuleSet(file);
                
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
