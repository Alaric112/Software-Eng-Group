
package group2.Model.Rule;

import group2.Model.Rule.FileManager.RuleList;
import java.util.Observable;
import java.util.concurrent.ForkJoinPool;

/**
 * The `ControlRuleChecker` class represents a singleton object responsible for managing rules and
 * performing periodic checks. It extends the `Observable` class to allow observers to be notified
 * when the rule set is changed.
 */
public final class ControlRuleChecker extends Observable {

    /** The singleton instance of the `ControlRuleChecker`. */
    private static volatile ControlRuleChecker instance;

    /** The set of rules to be checked periodically. */
    private RuleList ruleSet;

    /** The thread responsible for performing periodic rule checks. */
    private Thread periodicCheckThread;

    /**
     * Private constructor to enforce the singleton pattern.
     */
    private ControlRuleChecker() {
        // Private constructor to prevent instantiation.
    }

    /**
     * Retrieves the singleton instance of the `ControlRuleChecker`. If the instance does not
     * exist, it creates a new one using double-checked locking.
     *
     * @return The singleton instance of `ControlRuleChecker`.
     */
    public static ControlRuleChecker getInstance() {
        ControlRuleChecker result = instance;
        if (result != null) {
            return result;
        }
        synchronized (ControlRuleChecker.class) {
            if (instance == null) {
                instance = new ControlRuleChecker();
            }
            return instance;
        }
    }

    /**
     * Starts the periodic rule checking process. The method creates a new thread that checks the
     * rule set at regular intervals.
     */
    public void startPeriodicCheck() {
        // Fetch the timer value from the rules using the getTimer() method
        int timer = ruleSet.getTimer();

        // Utilize an object to notify that the thread has started
        Object threadStarted = new Object();

        if (periodicCheckThread != null && periodicCheckThread.isAlive()) {
            System.out.println("The periodic thread is already running.");
            return;
        }

        periodicCheckThread = new Thread(() -> {
            boolean active = true;
            synchronized (threadStarted) {
                threadStarted.notify(); // Notify that the thread has started
            }
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

        synchronized (threadStarted) {
            try {
                threadStarted.wait(); // Wait for the notification that the thread has started
            } catch (InterruptedException e) {
                System.out.println("Thread start has been interrupted");
                // Handle the interruption if needed
            }
        }
    }

    /**
     * Stops the currently running periodic rule checking thread. It interrupts the thread if it's
     * active.
     *
     * @throws IllegalStateException If the thread is not initialized or not running.
     */
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

    /**
     * Sets a new timer value for the periodic rule checking process.
     *
     * @param newTimer The new timer value in seconds.
     */
    public void setTimer(int newTimer) {
        ruleSet.setTimer(newTimer);
    }

    /**
     * Changes the rule set to the specified `RuleList` and notifies observers about the change.
     *
     * @param ruleSet The new rule set to be used.
     */
    public void changeRuleset(RuleList ruleSet) {
        this.ruleSet = ruleSet;
        setChanged();
        notifyObservers();
    }

    /**
     * Retrieves the current rule set.
     *
     * @return The current rule set.
     */
    public RuleList getRuleSet() {
        return ruleSet;
    }

    /**
     * Retrieves the thread responsible for periodic rule checking.
     *
     * @return The periodic check thread.
     */
    public Thread getPeriodicCheckThread() {
        return periodicCheckThread;
    }

    /**
     * Checks the current rule set by iterating through the rules and invoking `checkRule()` on
     * each rule. The rules are executed on separate threads using the common fork-join pool.
     */
    private void checkRuleSet() {
        ForkJoinPool.commonPool().execute(() -> {
            for (Rule rule : ruleSet.getRules()) {
                // Execute the call to rule.checkRule() on a separate thread if needed
                rule.checkRule();
            }
        });
    }
}
