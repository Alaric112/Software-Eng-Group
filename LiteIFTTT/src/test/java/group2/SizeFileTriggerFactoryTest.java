/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package group2;

import group2.Model.Trigger.*;

/**
 *
 * @author Faust
 */
import org.junit.Test;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class SizeFileTriggerFactoryTest {

    @Test
    public void testCreateTrigger_ShouldReturnInstanceOfSizeFileTrigger() {
        
        SizeFileTriggerFactory sizeFileTriggerFactory = new SizeFileTriggerFactory();
        Trigger trigger = sizeFileTriggerFactory.createTrigger();
        assertNotNull("Trigger is null", trigger);
        assertTrue("Expected instance of SizeFileTrigger", trigger instanceof SizeFileTrigger);
    }
}

