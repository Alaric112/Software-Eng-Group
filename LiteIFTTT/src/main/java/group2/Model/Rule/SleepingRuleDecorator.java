/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group2.Model.Rule;

import group2.Model.Rule.Rule;
import group2.RuleDecorator;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Lore
 */
public class SleepingRuleDecorator extends RuleDecorator{

    private long minSleepTimeMillis;
    private long lastCheckTimestamp;

    public SleepingRuleDecorator(Rule rule, long minSleepTime, TimeUnit timeUnit) {
        super(rule);
        this.minSleepTimeMillis = timeUnit.toMillis(minSleepTime);
        this.lastCheckTimestamp = 0;
    }

    @Override
    public void checkRule() {

        long currentTime = System.currentTimeMillis();

        // Verifica se è trascorso il tempo minimo di "sleep"
        if (currentTime - lastCheckTimestamp >= minSleepTimeMillis) {
            // Esegui il controllo della regola decorata
          super.checkRule();
 
            // Aggiorna il timestamp dell'ultimo controllo
            lastCheckTimestamp = currentTime;
        } 
        
    
    }
    
    // Metodo per impostare un nuovo tempo di "sleep" dinamico
    public void setMinSleepTime(long minSleepTime, TimeUnit timeUnit) {
        
        this.minSleepTimeMillis = timeUnit.toMillis(minSleepTime);
        
    }
}
