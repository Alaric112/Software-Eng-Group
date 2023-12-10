/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group2.Model.Rule;

/**
 * The `SleepingRuleDecorator` class is a decorator that adds the functionality
 * of "sleep" before executing the decorated rule check. It extends the
 * `RuleDecorator` class.
 *
 * @author Faust
 */
public class SleepingRuleDecorator extends RuleDecorator {

    private long minSleepTimeMillis;
    private long lastCheckTimestamp;

    /**
     * Constructor that takes a rule to decorate and initializes the "sleep"
     * variables.
     *
     * @param rule The rule to decorate.
     */
    public SleepingRuleDecorator(Rule rule) {
        super(rule);
        this.minSleepTimeMillis = 0;
        this.lastCheckTimestamp = 0;
    }

    /**
     * Executes the check of the decorated rule with a minimum "sleep" interval.
     */
    @Override
    public void checkRule() {
        long currentTime = System.currentTimeMillis();

        if (currentTime - lastCheckTimestamp >= minSleepTimeMillis) {

            super.checkRule();

            lastCheckTimestamp = currentTime;
        }
    }

    /**
     * Sets the minimum "sleep" time in milliseconds.
     *
     * @param minSleepTime The minimum "sleep" time.
     */
    public void setMinSleepTime(long minSleepTime) {
        this.minSleepTimeMillis = minSleepTime;
    }
}
