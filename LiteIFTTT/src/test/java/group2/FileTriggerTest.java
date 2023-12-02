/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package group2;

import java.io.File;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author patap
 */
public class FileTriggerTest {
    
//    @Test
//    public void testEvaluateFileExists() {
//        FileTrigger fileTrigger = new FileTrigger();
//        fileTrigger.setTargetFile("testDirectory", "testFile.txt");
//
//        // Assuming that the file does not exist initially
//        assertFalse(fileTrigger.evaluate());
//
//        // Creating the file for the test
//        assertTrue(createFile(fileTrigger.getTargetFile()));
//
//        // Now the file should exist
//        assertTrue(fileTrigger.evaluate());
//
//        // Cleanup: Deleting the file
//        assertTrue(deleteFile(fileTrigger.getTargetFile()));
//    }

    @Test
    public void testEvaluateFileDoesNotExist() {
        FileTrigger fileTrigger = new FileTrigger();
        fileTrigger.setTargetFile("nonexistentDirectory", "nonexistentFile.txt");

        // The file should not exist
        assertFalse(fileTrigger.evaluate());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEvaluateNullDirectory() {
        FileTrigger fileTrigger = new FileTrigger();
        fileTrigger.setTargetFile(null, "testFile.txt");

        // This should throw an IllegalStateException
        fileTrigger.evaluate();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEvaluateNullFileName() {
        FileTrigger fileTrigger = new FileTrigger();
        fileTrigger.setTargetFile("testDirectory", null);

        // This should throw an IllegalStateException
        fileTrigger.evaluate();
    }

    @Test
    public void testGetTargetFile() {
        FileTrigger fileTrigger = new FileTrigger();
        fileTrigger.setTargetFile("testDirectory", "testFile.txt");

        assertEquals(new File("testDirectory", "testFile.txt"), fileTrigger.getTargetFile());
    }

    @Test
    public void testSetTargetFile() {
        FileTrigger fileTrigger = new FileTrigger();
        fileTrigger.setTargetFile("initialDirectory", "initialFile.txt");

        assertEquals(new File("initialDirectory", "initialFile.txt"), fileTrigger.getTargetFile());

        // Change the target file
        fileTrigger.setTargetFile("newDirectory", "newFile.txt");

        assertEquals(new File("newDirectory", "newFile.txt"), fileTrigger.getTargetFile());
    }

    // Helper methods for file creation and deletion
    private boolean createFile(File file) {
        try {
            return file.createNewFile();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean deleteFile(File file) {
        return file.delete();
    }
}
