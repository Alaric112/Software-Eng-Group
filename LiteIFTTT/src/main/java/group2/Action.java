/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package group2;

import java.io.Serializable;

/**
 * The <code>Action</code> interface represents an action that can be executed.
 * Classes implementing this interface should provide specific behavior for the
 * {@link #execute()} method.
 *
 * @author patap
 * @see group2.GenericAction
 */
public interface Action extends Serializable {

    /**
     * Executes the action. Implementing classes should provide specific behavior
     * for this method.
     */    
    void execute();
    
}
