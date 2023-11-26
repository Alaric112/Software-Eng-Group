/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package group2;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author patap
 */
public class TimeTriggerFactoryTest {
    
    @Test
    public void testCreateTrigger() {    
    
        TimeTriggerFactory timeTriggerFactory = new TimeTriggerFactory();

        Trigger triggerTest = timeTriggerFactory.createTrigger();

        assertTrue(triggerTest instanceof TimeTrigger);
        
    }    
    
}
