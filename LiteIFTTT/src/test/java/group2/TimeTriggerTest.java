/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package group2;

import group2.Model.Trigger.TimeTrigger;
import static org.junit.Assert.*;
import org.junit.Test;
import java.time.LocalTime;

/**
 * Class representing a trigger based on the time of day.
 * The trigger activates when the current time equals the target time.
 */

public class TimeTriggerTest {
    
    /**
     * Constructs a new instance of the TimeTrigger class.
     *
     * @param hour   The hour of the target time.
     * @param minute The minute of the target time.
     */
    @Test
    public void testEvaluate_True() {

        int currentHour = LocalTime.now().getHour();
        int currentMinute = LocalTime.now().getMinute();

        TimeTrigger timeTrigger = new TimeTrigger(currentHour, currentMinute);

        assertTrue(timeTrigger.evaluate());
    }
    
    /**
     * Evaluates whether the trigger should be activated based on the current time.
     *
     * @return True if the current time is equal to the target time, otherwise false.
     */
    @Test
    public void testEvaluate_False() {
        
        int currentHour = LocalTime.now().getHour();
        int currentMinute = LocalTime.now().getMinute();

        TimeTrigger timeTrigger = new TimeTrigger(currentHour, currentMinute + 1);

        assertFalse(timeTrigger.evaluate());
    }

    /**
     * Gets the target time of the trigger.
     *
     * @return The target time.
     */
    @Test
    public void testGetTargetTime() {
        int hour = 12;
        int minute = 30;

        TimeTrigger timeTrigger = new TimeTrigger(hour, minute);

        assertEquals(LocalTime.of(hour, minute), timeTrigger.getTargetTime());
    }
    
    /**
     * Sets the target time of the trigger.
     *
     * @param hour The new hour of the target time.
     * @param min  The new minute of the target time.
     */
    @Test
    public void testSetTargetTime() {
        TimeTrigger timeTrigger = new TimeTrigger(10, 45);

        int newHour = 14;
        int newMinute = 15;

        timeTrigger.setTargetTime(newHour, newMinute);

        assertEquals(LocalTime.of(newHour, newMinute), timeTrigger.getTargetTime());
    }
}
