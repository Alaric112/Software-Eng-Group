package group2;

/**
 * The <code>BaseRule</code> class represents a rule with a specific condition
 * (trigger) and an associated action. It implements the {@link Rule} interface
 * and provides a default implementation of the rule-checking logic.
 *
 * @author patap
 * @see group2.Trigger
 * @see group2.Action
 * @see group2.Rule
 */
public class BaseRule implements Rule {
    
    private String name;
    private Trigger trigger;
    private Action action;
    private boolean active;

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
        }
        
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }    

    @Override
    public Trigger getTrigger() {
        return trigger;
    }

    public void setTrigger(Trigger trigger) {
        this.trigger = trigger;
    }

    @Override
    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }        
    
    private boolean checkTrigger(){
        
        return trigger.evaluate();
    }
    
    private void fireRule(){
        
        action.execute();
    }

    @Override
    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public void switchStatus() {

        this.active = !this.active;
    }
        
}
