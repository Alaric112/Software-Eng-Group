/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package group2;

import javafx.scene.control.Alert;
import javafx.application.Platform;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author patap
 */
public class MessageActionTest {
    
    @Test
    public void testExecute() {
       
        MessageAction messageAction = new MessageAction();
        String expectedMessage = "Hello world!";

    }

    @Test
    public void testGetMessageInfo() {
        MessageAction messageAction = new MessageAction();

        // Use the getter and assert the default value
        assertEquals("Hello world!", messageAction.getMessageInfo());
    }   
    
    @Test
    public void testSetMessageInfo() {
        MessageAction messageAction = new MessageAction();

        // Use the setter to set a new message
        messageAction.setMessageInfo("New message");

        // Use the getter and assert the new value
        assertEquals("New message", messageAction.getMessageInfo());
    }
    
}
