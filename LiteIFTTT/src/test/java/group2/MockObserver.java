/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group2;

import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author Alessandro Accarino
 */
public class MockObserver implements Observer {
    
    private boolean notified = false;

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(Observable o, Object arg) {
        notified = true;
    }

    /**
     * Checks if the observer has been notified.
     *
     * @return true if the observer has been notified, false otherwise.
     */
    public boolean isNotified() {
        return notified;
    }

    /**
     * Resets the notification status to false.
     */
    public void reset() {
        notified = false;
    }
    
    
}
