/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package group2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;


/**
 *
 * @author patap
 */
public class FileTriggerTest {
    
    private FileTrigger fileTrigger;
    private Path tempDirectory;
    private Path tempFile;
    
    @Before
    public void setUp() throws IOException {
        fileTrigger = new FileTrigger();
        tempDirectory = Files.createTempDirectory("testDirectory");
        tempFile = Files.createTempFile(tempDirectory, "testFile", ".txt");
        
    }

    @After
    public void tearDown() throws IOException {
        // Elimina la cartella e il file temporanei
        Files.deleteIfExists(tempFile);
        Files.deleteIfExists(tempDirectory);
    }    
    
    @Test
    public void testEvaluateFileExists() {
        // Setting up the target file
        fileTrigger.setTargetFile(tempDirectory.toString(), tempFile.getFileName().toString());
    
        // Evaluating the trigger
        assertTrue(fileTrigger.evaluate());
    }

    @Test
    public void testEvaluateFileDoesNotExist() {
        // Setting up the target file
        fileTrigger.setTargetFile("nonexistent/directory", "nonexistentfile.txt");

        // Evaluating the trigger
        assertFalse(fileTrigger.evaluate());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEvaluateNullDirectory() {
        // Setting up the target file with null directory
        fileTrigger.setTargetFile(null, "example.txt");

        // This should throw IllegalStateException
        fileTrigger.evaluate();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEvaluateNullFileName() {
        // Setting up the target file with null file name
        fileTrigger.setTargetFile("path/to/directory", null);

        // This should throw IllegalStateException
        fileTrigger.evaluate();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetTargetFileNullDirectory() {
        // Setting up the target file with null directory
        fileTrigger.setTargetFile(null, "example.txt");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetTargetFileNullFileName() {
        // Setting up the target file with null file name
        fileTrigger.setTargetFile("path/to/directory", null);
    }

}
