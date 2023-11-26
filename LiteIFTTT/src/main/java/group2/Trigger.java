/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package group2;

/**
 * The <code>Trigger</code> interface represents a condition that can be evaluated
 * to determine whether a specific action or event should be triggered.
 * Implementing classes should provide logic for evaluating the condition.
 *
 * @author patap
 * @see group2.GenericTrigger
 */
public interface Trigger {

    /**
     * Evaluates the condition represented by this trigger.
     *
     * @return {@code true} if the condition is met, {@code false} otherwise.
     */    
    boolean evaluate();
    
}
