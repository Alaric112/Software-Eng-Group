/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group2.Model.Trigger;

import java.time.LocalTime;

/**
 * The <code>TimeTrigger</code> class implements the {@link Trigger} interface
 * and represents a trigger based on a specific target time. It evaluates to
 * true when the current time matches the specified target time.
 *
 * @author Sonia
 * @see group2.Model.Trigger.Trigger
 */
public class TimeTrigger implements Trigger{

    LocalTime targetTime;


    /**
     * Constructs a new instance of the <code>TimeTrigger</code> class with the
     * specified target hour and minute.
     *
     * @param hour   The target hour (0-23).
     * @param minute The target minute (0-59).
     */    
    public TimeTrigger(int hour, int minute) {
         this.targetTime = LocalTime.of(hour, minute); 
    }


    /**
     * Constructs a new instance of the <code>TimeTrigger</code> class with the
     * specified target hour and minute.
     *
     */    
    @Override
    public boolean evaluate() {
                
        LocalTime currentTime = LocalTime.of(LocalTime.now().getHour(), LocalTime.now().getMinute());
        return currentTime.equals(targetTime);
    }

    /**
     * Gets the target time.
     *
     * @return The target time.
     */    
    public LocalTime getTargetTime() {
        return targetTime;
    }

    /**
     * Sets the target time with the specified hour and minute.
     *
     * @param hour The new target hour (0-23).
     * @param min  The new target minute (0-59).
     */    
    public void setTargetTime(int hour, int min) {
        this.targetTime = LocalTime.of(hour, min);
    }
       
        @Override
    public String toString() {
        return "Time selected: "+targetTime;
    }
}
