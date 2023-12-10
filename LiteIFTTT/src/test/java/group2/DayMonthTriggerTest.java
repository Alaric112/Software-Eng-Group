/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package group2;

import group2.Model.Trigger.*;
import org.junit.Test;
import java.time.MonthDay;
import static org.junit.Assert.*;

public class DayMonthTriggerTest {

    @Test
    public void testConstructorAndGetTargetMonthDay() {
        DayMonthTrigger trigger = new DayMonthTrigger();
        MonthDay defaultTarget = MonthDay.of(1, 1);

        assertEquals(defaultTarget, trigger.getTargetMonthDay());
    }

    @Test
    public void testSetTargetMonthDay() {
        DayMonthTrigger trigger = new DayMonthTrigger();
        int newMonth = 5;
        int newDayOfMonth = 15;

        trigger.setTargetMonthDay(newMonth, newDayOfMonth);
        MonthDay expectedTarget = MonthDay.of(newMonth, newDayOfMonth);

        assertEquals(expectedTarget, trigger.getTargetMonthDay());
    }

  @Test
public void testEvaluateWithMatchingMonthDay() {
    int currentMonth = 1;
    int currentDayOfMonth = 1;

    MonthDay currentMonthDay = MonthDay.of(currentMonth, currentDayOfMonth);
    System.out.println("Current MonthDay: " + currentMonthDay);

    DayMonthTrigger trigger = new DayMonthTrigger();
    trigger.setTargetMonthDay(currentMonth, currentDayOfMonth);

    System.out.println("Target MonthDay: " + trigger.getTargetMonthDay());

    assertTrue(trigger.getTargetMonthDay().equals(currentMonthDay));
}




    @Test
    public void testEvaluateWithNonMatchingMonthDay() {
        // Assuming the current date is set to January 15
        int currentMonth = 1;
        int currentDayOfMonth = 1;
        MonthDay currentMonthDay = MonthDay.of(currentMonth, currentDayOfMonth);

        DayMonthTrigger trigger = new DayMonthTrigger();

        assertFalse(trigger.evaluate());
    }

    // Add more tests based on specific scenarios or edge cases
}