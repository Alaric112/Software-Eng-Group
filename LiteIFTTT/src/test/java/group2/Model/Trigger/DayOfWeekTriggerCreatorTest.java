/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package group2.Model.Trigger;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author soniabruno
 */
public class DayOfWeekTriggerCreatorTest {

    /**
     * Test the {@code createTrigger} method of {@link DayOfWeekTriggerCreator}.
     * It ensures that the created trigger is an instance of {@link DayWeekTrigger}.
     */
    @Test
    public void testCreateTrigger() {
       
        DayOfWeekTriggerCreator triggerCreator = new DayOfWeekTriggerCreator();

        Trigger trigger = triggerCreator.createTrigger();

        assertTrue(trigger instanceof DayWeekTrigger);
    }
}