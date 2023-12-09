/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package group2;

import group2.Model.Action.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author soniabruno
 */


/**
 * JUnit test class for testing the functionality of the {@link FileDeleteAction} class.
 */
public class FileDeleteActionTest {

    private Path existingFile;
    private Path nonExistingFile;

    /**
     * Sets up the test environment by creating paths for an existing and a non-existing file.
     *
     * @throws IOException if an I/O error occurs during setup.
     */
    @Before
    public void setUp() throws IOException {
        existingFile = Files.createTempFile("existingFile", ".txt");
        nonExistingFile = Files.createTempFile("nonExistingFile", ".txt");
        Files.delete(nonExistingFile);  
    }

    /**
     * Tests the deletion of an existing file using the {@link FileDeleteAction} class.
     */
    @Test
    public void testDeleteExistingFile() {
        FileDeleteAction fileDeleteAction = new FileDeleteAction();
        fileDeleteAction.setPath(existingFile.toString());        
        assertTrue(Files.exists(existingFile));  
        fileDeleteAction.execute();
        assertFalse(Files.exists(existingFile));  
    }
    
    /**
     * Tests the deletion of a non-existing file using the {@link FileDeleteAction} class.
     */
    @Test
    public void testDeleteNonExistingFile() {
        FileDeleteAction fileDeleteAction = new FileDeleteAction();
        fileDeleteAction.setPath(nonExistingFile.toString());

        assertFalse(Files.exists(nonExistingFile));  
        fileDeleteAction.execute();
        assertFalse(Files.exists(nonExistingFile));  
    }
}