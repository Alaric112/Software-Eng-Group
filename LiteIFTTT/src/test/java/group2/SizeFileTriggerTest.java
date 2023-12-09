///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
// */
//package group2;
//
//import group2.Model.Trigger.SizeFileTrigger;
//import static org.junit.Assert.assertFalse;
//import static org.junit.Assert.assertTrue;
//import static org.junit.Assert.*;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.Rule;
//import org.junit.rules.TemporaryFolder;
//import java.io.File;
//import java.io.IOException;
//
///**
// *
// * @author Faust
// */
//
//
//
//public class SizeFileTriggerTest {
//
//    @Rule
//    public TemporaryFolder tempFolder = new TemporaryFolder();
//
//    private File testFile;
//    private SizeFileTrigger sizeFileTrigger;
//
//    @Before
//    public void setUp() throws IOException {
//        // Create a temporary file for testing
//        testFile = tempFolder.newFile("testFile.txt");
//        // Create a SizeFileTrigger instance with the test file
//        sizeFileTrigger = new SizeFileTrigger();
//        sizeFileTrigger.setTargetFile(testFile);
//    }
//
//    @Test
//    public void testEvaluateFileSizeExceedsTarget() throws IOException {
//        // Set a size target greater than the initial file size
//        long initialFileSize = testFile.length();
//        sizeFileTrigger.setSizeFile(initialFileSize - 1);
//
//        // Evaluate should return true since the file size exceeds the target
//        assertTrue(sizeFileTrigger.evaluate());
//    }
//
//    @Test
//    public void testEvaluateFileSizeDoesNotExceedTarget() throws IOException {
//        // Set a size target greater than the initial file size
//        long initialFileSize = testFile.length();
//        sizeFileTrigger.setSizeFile(initialFileSize + 1);
//
//        // Evaluate should return false since the file size does not exceed the target
//        assertFalse(sizeFileTrigger.evaluate());
//    }
//
//    @Test
//    public void testEvaluateFileSizeEqualsTarget() throws IOException {
//        // Set a size target equal to the initial file size
//        long initialFileSize = testFile.length();
//        sizeFileTrigger.setSizeFile(initialFileSize);
//
//        // Evaluate should return false since the file size equals the target
//        assertFalse(sizeFileTrigger.evaluate());
//    }
//
//    @Test
//    public void testToString() {
//        // Ensure that the toString method returns the expected value
//        assertEquals("SizeTrigger", sizeFileTrigger.toString());
//    }
//}
//
//
//
