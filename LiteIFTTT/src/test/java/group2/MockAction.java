/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group2;

import group2.Model.Action.Action;

/**
 *
 * @author patap
 */
public class MockAction implements Action {
    
    private boolean executed;

    @Override
    public void execute() {
        executed = true;
    }

    public boolean isExecuted() {
        return executed;
    }
    
}
