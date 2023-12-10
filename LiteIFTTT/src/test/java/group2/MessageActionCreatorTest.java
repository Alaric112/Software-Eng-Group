/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package group2;

import group2.Model.Action.MessageAction;
import group2.Model.Action.MessageActionCreator;
import group2.Model.Action.Action;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Alessandro Accarino
 */
public class MessageActionCreatorTest {
   
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
