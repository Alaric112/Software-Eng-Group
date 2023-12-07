/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package group2;

import group2.Model.Trigger.ExitStatusTrigger;
import org.junit.Test;
import static org.junit.Assert.*;

public class ExitStatusTriggerTest {
    
    @Test
    public void testEvaluateWithNonMatchingExitStatus() {
        // Assuming the external program exits with status 1
        String externalProgram = "false";
        int userExitStatus = 0;

        ExitStatusTrigger trigger = new ExitStatusTrigger(externalProgram, userExitStatus);
        assertFalse(trigger.evaluate());
    }

    @Test
    public void testSetExternalProgram() {
        ExitStatusTrigger trigger = new ExitStatusTrigger("echo", 0);

        // Set a new external program
        trigger.setExternalProgram("ls");

        // Check if the external program was set correctly
        assertEquals("ls", trigger.getExternalProgram());
    }

    @Test
    public void testSetUserExitStatus() {
        ExitStatusTrigger trigger = new ExitStatusTrigger("echo", 0);

        // Set a new user exit status
        trigger.setUserExitStatus(1);

        // Check if the user exit status was set correctly
        assertEquals(1, trigger.getUserExitStatus());
    }
}
