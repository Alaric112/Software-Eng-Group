/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group2.Model.Rule;

import group2.Model.Action.Action;
import group2.Model.Trigger.Trigger;

/**
 * The {@code RuleDecorator} class is an abstract class that extends the {@code Rule} class.
 * It serves as a base class for rule decorators, allowing additional functionality to be added
 * to existing rules without modifying their structure.
 * 
 * @author Lore
 */
public abstract class RuleDecorator extends Rule {
    
    /** The rule being decorated. */
    private Rule rule;

    /**
     * Constructs a new RuleDecorator with the specified rule to decorate.
     * 
     * @param rule The rule to decorate.
     */
    public RuleDecorator(Rule rule) {
        this.rule = rule;
    }    
        
    /**
     * Checks the decorated rule.
     */
    @Override
    public void checkRule() {
        rule.checkRule();
    }

    /**
     * Gets the name of the decorated rule.
     * 
     * @return The name of the decorated rule.
     */
    @Override
    public String getName() {
        return rule.getName();
    }

    /**
     * Gets the trigger of the decorated rule.
     * 
     * @return The trigger of the decorated rule.
     */
    @Override
    public Trigger getTrigger() {
        return rule.getTrigger();
    }

    /**
     * Gets the action of the decorated rule.
     * 
     * @return The action of the decorated rule.
     */
    @Override
    public Action getAction() {
        return rule.getAction();
    }

    /**
     * Switches the status of the decorated rule and notifies observers.
     */
    @Override
    public synchronized void switchStatus() {
        rule.switchStatus();
        setChanged();
        notifyObservers();
    }

    /**
     * Checks if the decorated rule is active.
     * 
     * @return {@code true} if the decorated rule is active, {@code false} otherwise.
     */
    @Override
    public boolean isActive() {
        return rule.isActive();
    }
    
    /**
     * Checks if the decorated rule has been fired.
     * 
     * @return {@code true} if the decorated rule has been fired, {@code false} otherwise.
     */
    @Override
    public boolean isFired() {
        return rule.isFired();
    }
    
}
