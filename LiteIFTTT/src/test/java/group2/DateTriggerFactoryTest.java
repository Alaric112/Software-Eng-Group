/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package group2;



/**
 *
 * @author Faust
 */
import group2.Model.Trigger.*;
import org.junit.Test;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class DateTriggerFactoryTest {

    @Test
    public void testCreateTrigger_ShouldReturnInstanceOfDateTrigger() {
        // Arrange
        DateTriggerFactory dateTriggerFactory = new DateTriggerFactory();

        // Act
        Trigger trigger = dateTriggerFactory.createTrigger();

        // Assert
        assertNotNull("Trigger is null", trigger);
        assertTrue("Expected instance of DateTrigger", trigger instanceof DateTrigger);
    }
}
