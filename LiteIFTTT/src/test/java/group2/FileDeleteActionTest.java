/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package group2;

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

public class FileDeleteActionTest {

    private Path existingFile;
    private Path nonExistingFile;

    @Before
    public void setUp() throws IOException {
        existingFile = Files.createTempFile("existingFile", ".txt");
        nonExistingFile = Files.createTempFile("nonExistingFile", ".txt");
        Files.delete(nonExistingFile);  
    }

    @Test
    public void testDeleteExistingFile() {
        FileDeleteAction fileDeleteAction = new FileDeleteAction();
        fileDeleteAction.setPath(existingFile);

        assertTrue(Files.exists(existingFile));  
        fileDeleteAction.execute();
        assertFalse(Files.exists(existingFile));  
    }

    @Test
    public void testDeleteNonExistingFile() {
        FileDeleteAction fileDeleteAction = new FileDeleteAction();
        fileDeleteAction.setPath(nonExistingFile);

        assertFalse(Files.exists(nonExistingFile));  
        fileDeleteAction.execute();
        assertFalse(Files.exists(nonExistingFile));  
    }
}