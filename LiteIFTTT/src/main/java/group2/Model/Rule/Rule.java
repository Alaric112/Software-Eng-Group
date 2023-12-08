/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group2.Model.Rule;

import group2.Model.Action.Action;
import group2.Model.Trigger.Trigger;
import java.io.Serializable;

/**
 * The <code>Rule</code> interface represents a rule that can be checked,
 * and when a specified condition is met, an associated action is triggered.
 * Implementing classes should provide specific logic for the methods defined in
 * this interface to define the behavior of the rule.
 *
 * <p> This interface follows the decorator pattern, with the {@link BaseRule} class
 * corresponding to the concrete component. </p>
 * 
 * @author Alessandro Accarino
 * @see group2.BaseRule
 * @see group2.Model.Trigger.Trigger
 * @see group2.Model.Action.Action
 */
public interface Rule extends Serializable {
    
    /**
     * Checks the rule's condition and triggers the associated action if the condition is met.
     */
    void checkRule();

    /**
     * Gets the name of the rule.
     *
     * @return The name of the rule.
     */
    String getName();

    /**
     * Gets the trigger associated with the rule.
     *
     * @return The trigger associated with the rule.
     */
    Trigger getTrigger();

    /**
     * Gets the action associated with the rule.
     *
     * @return The action associated with the rule.
     */
    Action getAction();

    /**
     * Switches the status of the rule. Implementing classes should define the logic for
     * changing the status of the rule.
     */
    void switchStatus();
 
    /**
     * Retrieves the activation status of the rule.
     *
     * @return {@code true} if the rule is active, {@code false} otherwise.
     */    
    boolean isActive();
   
}
