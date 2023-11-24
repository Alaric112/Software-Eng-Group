
package group2;

import java.util.Timer;
import java.util.TimerTask;


public final class ControlRuleChecker {
    // The field must be declared volatile so that double check lock would work
    // correctly.
    private static volatile ControlRuleChecker instance;
    private Ruleset rules;
    private double timer;   
    private Thread periodicCheckThread;



    private ControlRuleChecker() {
    }

    public static ControlRuleChecker getInstance() {
        // The approach taken here is called double-checked locking (DCL). It
        // exists to prevent race condition between multiple threads that may
        // attempt to get singleton instance at the same time, creating separate
        // instances as a result.
        //
        // It may seem that having the `result` variable here is completely
        // pointless. There is, however, a very important caveat when
        // implementing double-checked locking in Java, which is solved by
        // introducing this local variable.
        //
        // You can read more info DCL issues in Java here:
        // https://refactoring.guru/java-dcl-issue
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
    int timer = rules.getTimer();

     periodicCheckThread = new Thread(() -> {
        while (true) {
            try {
                // Iterate through the set of rules and check each rule
                for (Rule rule : rules.getRules()) {
                    rule.checkRule();
                }

                // Sleep for x milliseconds after checking all rules
                Thread.sleep(timer * 1000);
                System.out.println("ciao");

            } catch (InterruptedException e) {
                e.printStackTrace();
                // Handle the interruption if needed
            }
        }
    });

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
       rules.setTimer(newTimer);
        
    }
    public void changeRuleset(Ruleset ruleSet) {
        this.rules= ruleSet; 
       
    }

    public Ruleset getRules() {
        return rules;
    }
    

    private void checkRuleSet() {
        // Implement the logic to check the ruleset periodically
        // This method will be called by the TimerTask
        // Add your code here
    }
}