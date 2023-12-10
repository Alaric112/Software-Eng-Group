/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package group2;

import group2.Model.Action.MessageAction;
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

/**
 *
 * @author Alessandro Accarino
 */
public class MessageActionTest {
    
    private MessageAction messageAction;
    
    @Before
    public void setUp() {
       
        messageAction = new MessageAction();
    }

    @Test
    public void testExecute() {
        // Verifies that executing the action triggers the controller's update method
        MockObserver mockObserver = new MockObserver();
        messageAction.addObserver(mockObserver);
        messageAction.execute();
        // Verifica che l'osservatore sia stato notificato
        assertTrue(mockObserver.isNotified());
    }    
    
    @Test
    public void testGetMessageInfo() {
        // Use the getter and assert the default value
        assertEquals("Hello world!", messageAction.getMessageInfo());
    }   
    
    @Test
    public void testSetMessageInfo() {

        // Use the setter to set a new message
        messageAction.setMessageInfo("New message");

        // Use the getter and assert the new value
        assertEquals("New message", messageAction.getMessageInfo());
    }
    
}
