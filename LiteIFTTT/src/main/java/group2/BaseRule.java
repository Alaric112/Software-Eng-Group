/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group2;

/**
 *
 * @author patap
 */
public class BaseRule implements Rule {

    //name of the rule
    
    private String name;
    private Trigger trigger;
    private Action action;
    private boolean active;

    public BaseRule(String name, Trigger trigger, Action action) {
        this.name = name;
        this.trigger = trigger;
        this.action = action;
        this.active = true;
    }
            
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public void switchStatus() {

        this.active = !this.active;
        System.out.println(active);
    }
        
}
