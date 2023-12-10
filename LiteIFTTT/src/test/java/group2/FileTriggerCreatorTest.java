/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package group2;

import group2.Model.Trigger.FileTrigger;
import group2.Model.Trigger.FileTriggerCreator;
import group2.Model.Trigger.Trigger;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Alessandro Accarino
 */
public class FileTriggerCreatorTest {
    
    @Test
    public void testCreateAction() {

        FileTriggerCreator fileTriggerFactory = new FileTriggerCreator();

        Trigger triggerTest = fileTriggerFactory.createTrigger();

        assertTrue(triggerTest instanceof FileTrigger);        
        
    }
    
}
