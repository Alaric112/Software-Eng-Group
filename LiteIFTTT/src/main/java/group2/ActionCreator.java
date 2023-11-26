/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group2;

/**
 * The <code>TriggerCreator</code> abstract class serves as a base class for creating
 * instances of the {@link Action} interface. Subclasses must implement the
 * {@link #createAction()} method to provide specific logic for creating triggers.
 *
 * @author patap
 * @see group2.Action
 */
public abstract class ActionCreator {

    /**
     * Creates and returns an instance of the {@link Action} interface.
     *
     * @return An instance of the {@link Action} interface.
     */        
    public abstract Action createAction();
    
}
