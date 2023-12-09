package group2.Model.Rule;

import group2.Model.Action.Action;
import group2.Model.Trigger.Trigger;

/**
 * The <code>BaseRule</code> class represents a rule with a specific condition
 * (trigger) and an associated action. It implements the {@link Rule} interface
 * and provides a default implementation of the rule-checking logic.
 *
 * <p>Each rule has a name, a trigger, and an action associated with it. The rule is
 * considered active, and its action is executed only if the trigger condition is met,
 * the rule is active, and it has not been fired since the last activation.</p>
 *
 * <p>The {@link #checkRule()} method evaluates the trigger condition and, if met,
 * triggers the associated action. The {@link #isActive()} method retrieves the
 * activation status of the rule, and the {@link #switchStatus()} method toggles
 * the activation status between active and inactive.</p>
 *
 * <p>Instances of this class can be used in a rule-based system to represent and
 * execute rules based on specific conditions and actions.</p>
 * 
 * @author Alessandro Accarino
 * @see group2.Trigger
 * @see group2.Action
 * @see group2.Rule
 */
public class BaseRule implements Rule {
    
    private String name;
    private Trigger trigger;
    private Action action;
    private boolean active;
    private boolean fired;

    /**
     * Constructs a new instance of the {@code BaseRule} class with the specified
     * name, trigger, and action. The rule is initially active and marked as fired.
     *
     * @param name    The name of the rule.
     * @param trigger The trigger associated with the rule.
     * @param action  The action associated with the rule.
     */    
    public BaseRule(String name, Trigger trigger, Action action) {
        this.name = name;
        this.trigger = trigger;
        this.action = action;
        this.active = true;
    }

    /**
     * Checks the rule's condition and triggers the associated action if the
     * condition is met and the rule is active and has not been fired since the
     * last activation.
     */    
    @Override
    public void checkRule() {
        if(active && checkTrigger()){
            fireRule();
            fired = true;
        }
        
    }

    /**
     * Retrieves the name of the rule.
     *
     * @return The name of the rule.
     */    
    @Override
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the rule.
     *
     * @param name The new name for the rule.
     */    
    public void setName(String name) {
        this.name = name;
    }    

    /**
     * Retrieves the trigger associated with the rule.
     *
     * @return The trigger associated with the rule.
     */    
    @Override
    public Trigger getTrigger() {
        return trigger;
    }

    /**
     * Sets the trigger associated with the rule.
     *
     * @param trigger The new trigger for the rule.
     */    
    public void setTrigger(Trigger trigger) {
        this.trigger = trigger;
    }

    /**
     * Retrieves the action associated with the rule.
     *
     * @return The action associated with the rule.
     */    
    @Override
    public Action getAction() {
        return action;
    }

    /**
     * Sets the action associated with the rule.
     *
     * @param action The new action for the rule.
     */    
    public void setAction(Action action) {
        this.action = action;
    }        
    
    private boolean checkTrigger(){
        
        return trigger.evaluate();
    }
    
    private void fireRule(){
        
        action.execute();
    }

    /**
     * Retrieves the activation status of the rule.
     *
     * @return {@code true} if the rule is active, {@code false} otherwise.
     */    
    @Override
    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * Toggles the activation status between active and inactive for the rule.
     */    
    @Override
    public void switchStatus() {

        this.active = !this.active;
    }

     /**
     * Retrieves the fire status of a rule.
     *
     * @return {@code true} if the rule is fired, {@code false} otherwise.
     */  
    public boolean isFired() {
        return fired;
    }
        
}
