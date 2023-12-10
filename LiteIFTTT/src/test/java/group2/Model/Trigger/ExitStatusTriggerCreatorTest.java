/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package group2.Model.Trigger;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Alessandro Accarino
 */
public class ExitStatusTriggerCreatorTest {
    
    @Test
    public void testCreateTrigger() {
       
        ExitStatusTriggerCreator triggerCreator = new ExitStatusTriggerCreator();

        Trigger trigger = triggerCreator.createTrigger();

        assertTrue(trigger instanceof ExitStatusTrigger);
    }
    
}
