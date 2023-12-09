
package group2.Model.Rule;

import java.util.Observable;

public final class ControlRuleChecker extends Observable {
    // The field must be declared volatile so that double check lock would work
    // correctly.
    private static volatile ControlRuleChecker instance;
    //private RuleList rules;
    private RuleList ruleSet;
    private Thread periodicCheckThread;

    private ControlRuleChecker() {
        
    }

    public static ControlRuleChecker getInstance() {

        ControlRuleChecker result = instance;
        if (result != null) {
            return result;
        }
        synchronized(ControlRuleChecker.class) {
            if (instance == null) {
                instance = new ControlRuleChecker();                
            }
            return instance;
        }        
    
    }
    
   public void startPeriodicCheck() {
    // Fetch the timer value from the rules using the getTimer() method
    int timer = ruleSet.getTimer();

    if (periodicCheckThread != null && periodicCheckThread.isAlive()) {
        System.out.println("Il thread periodico è già in esecuzione.");
        return; // Esce se il thread è già in esecuzione
    }

    periodicCheckThread = new Thread(() -> {
        boolean active = true;
        try {
            while (active) {

                checkRuleSet();
                // Sleep for x milliseconds after checking all rules
                System.out.println("Finished scanning");
                Thread.sleep(timer * 1000);

            }
        } catch (InterruptedException e) {
            System.out.println("Thread has been interrupted");
            active = false;
            // Handle the interruption if needed
        }
    });

    // Set the thread as a daemon
    periodicCheckThread.setDaemon(true);

    // Start the thread
    periodicCheckThread.start();
}

    public void stopPeriodicCheck() {

        if (periodicCheckThread == null) {
            throw new IllegalStateException("Thread is not initialized.");
        }

        if (!periodicCheckThread.isAlive()) {
            throw new IllegalStateException("Thread is not running.");
        }

        // Interrupt the periodic check thread only if it's running
        periodicCheckThread.interrupt();
    }

    public void setTimer(int newTimer) {
        
        ruleSet.setTimer(newTimer);
    }
    
    public void changeRuleset(RuleList ruleSet) {
        
        this.ruleSet = ruleSet;
        setChanged();
        notifyObservers();

    }

    public RuleList getRuleSet() {
        
        return ruleSet;
    }

    public Thread getPeriodicCheckThread() {
        return periodicCheckThread;
    }

    private void checkRuleSet() {
        
        // Iterate through the set of rules and check each rule
        //ForkJoinPool.commonPool().execute(() -> {
            for (Rule rule : ruleSet.getRules()) {
                // Esegui la chiamata a rule.checkRule() su un thread separato
                rule.checkRule();
            }
        //});
    }
}