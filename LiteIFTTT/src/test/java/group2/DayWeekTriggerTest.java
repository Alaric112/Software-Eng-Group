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

//    @Test
//    public void testSetTargetDay() {
//        DayOfWeek initialDay = DayOfWeek.MONDAY;
//        DayOfWeek newDay = DayOfWeek.TUESDAY;
//        DayWeekTrigger trigger = new DayWeekTrigger();
//
//        trigger.setTargetDay(newDay);
//        assertEquals(newDay, trigger.getTargetDay());
//    }

//    @Test
//    public void testEvaluateWithNonMatchingDay() {
//        // Assuming the current day is Monday when the test is executed
//        DayOfWeek currentDay = DayOfWeek.TUESDAY;
//        DayWeekTrigger trigger = new DayWeekTrigger();
//        trigger.setTargetDay(currentDay);
//        assertFalse(trigger.evaluate());
//    }

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