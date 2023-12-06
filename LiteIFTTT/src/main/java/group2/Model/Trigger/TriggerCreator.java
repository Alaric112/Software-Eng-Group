/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group2.Model.Trigger;

/**
 * The <code>TriggerCreator</code> abstract class serves as a base class for creating
 * instances of the {@link Trigger} interface. Subclasses must implement the
 * {@link #createTrigger()} method to provide specific logic for creating triggers.
 *
 * @author patap
 * @see group2.Model.Trigger.Trigger
 */
public abstract class TriggerCreator {

    /**
     * Creates and returns an instance of the {@link Trigger} interface.
     *
     * @return An instance of the {@link Trigger} interface.
     */    
    public abstract Trigger createTrigger();
        
}
