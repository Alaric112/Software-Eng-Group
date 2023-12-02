/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package group2;

import org.junit.Test;

import java.time.MonthDay;

import static org.junit.Assert.*;

public class DayMonthTriggerTest {

    @Test
    public void testConstructorAndGetTargetMonthDay() {
        int month = 5;
        int dayOfMonth = 15;
        MonthDay targetMonthDay = MonthDay.of(month, dayOfMonth);

        DayMonthTrigger trigger = new DayMonthTrigger(month, dayOfMonth);

        assertEquals(targetMonthDay, trigger.getTargetMonthDay());
    }

    @Test
    public void testSetTargetMonthDay() {
        int initialMonth = 7;
        int initialDayOfMonth = 25;
        int newMonth = 12;
        int newDayOfMonth = 10;

        DayMonthTrigger trigger = new DayMonthTrigger(initialMonth, initialDayOfMonth);
        trigger.setTargetMonthDay(newMonth, newDayOfMonth);

        MonthDay expectedTargetMonthDay = MonthDay.of(newMonth, newDayOfMonth);

        assertEquals(expectedTargetMonthDay, trigger.getTargetMonthDay());
    }

    @Test
    public void testEvaluateWithNonMatchingMonthDay() {
        // Assuming the current date is set to May 15
        int currentMonth = 5;
        int currentDayOfMonth = 20;
        MonthDay currentMonthDay = MonthDay.of(currentMonth, currentDayOfMonth);

        // Setting the target MonthDay to a different day
        int targetMonth = 5;
        int targetDayOfMonth = 10;
        DayMonthTrigger trigger = new DayMonthTrigger(targetMonth, targetDayOfMonth);

        assertFalse(trigger.evaluate());
    }

    // Add more tests based on specific scenarios or edge cases
}
