/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group2.Model.Rule;

/**
 *
 * @author Lore
 */
public class SleepingRuleDecorator extends RuleDecorator {

    private long minSleepTimeMillis;
    private long lastCheckTimestamp;

    public SleepingRuleDecorator(Rule rule) {
        super(rule);
        this.minSleepTimeMillis = 0;
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

    public void setMinSleepTime(long minSleepTime) {
        this.minSleepTimeMillis = minSleepTime;
    }
}
