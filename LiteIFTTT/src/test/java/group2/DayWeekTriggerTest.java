/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package group2;

import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class DayWeekTriggerTest {

    @Test
    public void testConstructorAndGetTargetDay() {
        DayOfWeek targetDay = DayOfWeek.MONDAY;
        DayWeekTrigger trigger = new DayWeekTrigger();
        trigger.setTargetDay(targetDay);
        assertEquals(targetDay, trigger.getTargetDay());
    }

    @Test
    public void testEvaluateWithCurrentDay() {
        // Assuming the current day is Wednesday when the test is executed
        LocalDateTime currentDateTime = LocalDateTime.now();
        DayOfWeek currentDay = currentDateTime.getDayOfWeek();        
        DayWeekTrigger trigger = new DayWeekTrigger();
        trigger.setTargetDay(currentDay);
        assertTrue(trigger.evaluate());
    }

}