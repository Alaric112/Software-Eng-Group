/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package group2;

import java.io.File;
import java.io.IOException;
import org.junit.Before;
import org.junit.After;
import org.junit.rules.TemporaryFolder;
import org.junit.Test;
import static org.junit.Assert.*;


/**
 *
 * @author patap
 */
public class FileTriggerTest {
//    
//    private FileTrigger fileTrigger;
//    private TemporaryFolder temporaryFolder;
//    private File testFile;
//    
//    @Before
//    public void setUp() throws IOException {
//        temporaryFolder = new TemporaryFolder();
//        // Create a temporary directory and file for testing
//        File testDirectory = temporaryFolder.newFolder("testDirectory");
//        File testFile = temporaryFolder.newFile("testDirectory/testFile.txt");
//
//        // Initialize FileTrigger with the temporary directory and file name
//        fileTrigger = new FileTrigger();
//        fileTrigger.setTargetFile(testDirectory.getAbsolutePath(), "testFile.txt");
//    }
//    
//    @Test
//    public void testEvaluateFileExists() {
//        fileTrigger.setTargetFile("testDirectory", "testFile.txt");
//
//        // Now the file should exist
//        assertTrue(fileTrigger.evaluate());
//
//        // Cleanup: Deleting the file
//    }
//
//    @Test
//    public void testEvaluateFileDoesNotExist() {
//        fileTrigger.setTargetFile("nonexistentDirectory", "nonexistentFile.txt");
//
//        // The file should not exist
//        assertFalse(fileTrigger.evaluate());
//    }
//
//    @Test(expected = IllegalArgumentException.class)
//    public void testEvaluateNullDirectory() {
//        fileTrigger.setTargetFile(null, "testFile.txt");
//
//        // This should throw an IllegalStateException
//        fileTrigger.evaluate();
//    }
//
//    @Test(expected = IllegalArgumentException.class)
//    public void testEvaluateNullFileName() {
//        fileTrigger.setTargetFile("testDirectory", null);
//
//        // This should throw an IllegalStateException
//        fileTrigger.evaluate();
//    }
//
//    @Test
//    public void testGetTargetFile() {
//        fileTrigger.setTargetFile("testDirectory", "testFile.txt");
//
//        assertEquals(new File("testDirectory", "testFile.txt"), fileTrigger.getTargetFile());
//    }
//
//    @Test
//    public void testSetTargetFile() {
//        fileTrigger.setTargetFile("initialDirectory", "initialFile.txt");
//
//        assertEquals(new File("initialDirectory", "initialFile.txt"), fileTrigger.getTargetFile());
//
//        // Change the target file
//        fileTrigger.setTargetFile("newDirectory", "newFile.txt");
//
//        assertEquals(new File("newDirectory", "newFile.txt"), fileTrigger.getTargetFile());
//    }

}
