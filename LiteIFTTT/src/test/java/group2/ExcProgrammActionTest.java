/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package group2;

import group2.Model.Action.*;
import java.io.PrintStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Alessandro Accarino
 */
public class ExcProgrammActionTest {
    
    private ExcProgrammAction action;
    
    @Before
    public void setUp() throws IOException {
        action = new ExcProgrammAction(); 
    }
    
    @Test
    public void testExecute() {
        action.setProgramPath("java");
        action.setCommandLineArg("-version");

        // Redirect System.out to capture output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Execute the action
        action.execute();

        System.setOut(System.out);
        // Verify the output
        assertNotNull(outContent.toString());
        assertFalse(outContent.toString().toLowerCase().contains("error"));

    }

    @Test
    public void testExecuteWithInvalidProgramPath() {
        action.setProgramPath("nonexistent_program");
        action.setCommandLineArg("testArg");

        // Redirect System.err to capture error output
        ByteArrayOutputStream errContent = new ByteArrayOutputStream();
        System.setErr(new PrintStream(errContent));

        // Execute the action
        action.execute();

        // Reset System.err
        System.setErr(System.err);

        // Verify the error output
        assertTrue(errContent.toString().contains("java.io.IOException"));
    }

    @Test
    public void testGetSetProgramPath() {
        action.setProgramPath("testProgramPath");
        assertEquals("testProgramPath", action.getProgramPath());
    }

    @Test
    public void testGetSetCommandLineArg() {
        action.setCommandLineArg("testCommandLineArg");
        assertEquals("testCommandLineArg", action.getCommandLineArg());
    }
}
