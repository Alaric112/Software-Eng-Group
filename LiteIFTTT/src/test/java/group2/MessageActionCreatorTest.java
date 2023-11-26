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
public class MessageActionCreatorTest {
    
    public MessageActionCreatorTest() {
    }


    /**
     * Test of createAction method, of class MessageActionCreator.
     */
    @Test
    public void testCreateAction() {
        
        MessageActionCreator messageActionFactory = new MessageActionCreator();

        Action actionTest = messageActionFactory.createAction();

        assertTrue(actionTest instanceof MessageAction);
        
    }
    
}
