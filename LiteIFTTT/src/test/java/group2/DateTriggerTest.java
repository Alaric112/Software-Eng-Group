/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package group2;

import group2.Model.Trigger.*;
import java.time.LocalDate;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertThrows;

public class DateTriggerTest {

    @Test
    public void testEvaluate_TargetDateEqualsCurrentDate_ShouldReturnTrue() {
        // Arrange
        DateTrigger dateTrigger = new DateTrigger();
        LocalDate currentDate = LocalDate.now();
        dateTrigger.setTargetDate(currentDate);

        // Act
        boolean result = dateTrigger.evaluate();

        // Assert
        assertTrue("Expected true, but got false", result);
    }

    @Test
    public void testEvaluate_TargetDateNotEqualsCurrentDate_ShouldReturnFalse() {
        // Arrange
        DateTrigger dateTrigger = new DateTrigger();
        LocalDate currentDate = LocalDate.now().minusDays(1); // Set a date one day before today
        dateTrigger.setTargetDate(currentDate);

        // Act
        boolean result = dateTrigger.evaluate();

        // Assert
        assertFalse("Expected false, but got true", result);
    }

    @Test
    public void testEvaluate_NullTargetDate_ShouldThrowNullPointerException() {
        // Arrange
        DateTrigger dateTrigger = new DateTrigger();

        // Act & Assert
        assertThrows("NullPointerException expected", NullPointerException.class, () -> dateTrigger.evaluate());
    }

    @Test
    public void testSetTargetDate_ShouldSetTargetDate() {
        // Arrange
        DateTrigger dateTrigger = new DateTrigger();
        LocalDate newDate = LocalDate.of(2023, 1, 1);

        // Act
        dateTrigger.setTargetDate(newDate);

        // Assert
        assertEquals("Target date not set correctly", newDate, dateTrigger.getTargetDate());
    }
}


