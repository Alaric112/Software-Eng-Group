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
    public void testDefaultConstructor() {
        ExitStatusTrigger trigger = new ExitStatusTrigger();
        assertEquals("default", trigger.getExternalProgram());
        assertEquals(-1, trigger.getUserExitStatus());
    }

    @Test
    public void testEvaluateWithNonMatchingExitStatus() {
        ExitStatusTrigger trigger = new ExitStatusTrigger();
        // Assuming a valid external program that exits with status 0
        trigger.setExternalProgram("echo hello");
        trigger.setUserExitStatus(1);

        assertFalse(trigger.evaluate());
    }

    @Test
    public void testEvaluateWithInvalidExternalProgram() {
        ExitStatusTrigger trigger = new ExitStatusTrigger();
        // Assuming an invalid external program
        trigger.setExternalProgram("nonexistent_command");
        trigger.setUserExitStatus(0);

        assertFalse(trigger.evaluate());
    }

    @Test
    public void testSetExternalProgram() {
        ExitStatusTrigger trigger = new ExitStatusTrigger();
        trigger.setExternalProgram("new_program");
        assertEquals("new_program", trigger.getExternalProgram());
    }

    @Test
    public void testSetUserExitStatus() {
        ExitStatusTrigger trigger = new ExitStatusTrigger();
        trigger.setUserExitStatus(2);
        assertEquals(2, trigger.getUserExitStatus());
    }
}
