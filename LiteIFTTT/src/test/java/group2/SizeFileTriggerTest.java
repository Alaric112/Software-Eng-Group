/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package group2;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;


/**
 *
 * @author Faust
 */


//public class SizeFileTriggerTest {



//    private FileTrigger fileTrigger;
//    private Path tempDirectory;
//    private Path tempFile;
//    private SizeFileTrigger sizeFileTrigger;
//    
//    @Before
//    public void setUp() throws IOException {
//        sizeFileTrigger =  = new SizeFileTrigger();
//        fileTrigger = new FileTrigger();
//        tempDirectory = Files.createTempDirectory("testDirectory");
//        tempFile = Files.createTempFile(tempDirectory, "testFile", ".txt");
//        
//    }
//
//
//
//    @Test
//    public void testEvaluate_FileSizeGreaterThanTarget_ShouldReturnTrue() throws IOException {
//        // Arrange
//        
//        File testFile = createTestFile(1000); // Create a test file with size 1000 bytes
//        sizeFileTrigger.setTargetFile(tempDirectory.toString(),testFile.toString());
//        sizeFileTrigger.setSizeFile(500); // Set target size to 500 bytes
//        
//        // Act
//        boolean result = sizeFileTrigger.evaluate();
//
//        // Assert
//        assertTrue("Expected true, but got false", result);
//    }
//
//    @Test
//    public void testEvaluate_FileSizeEqualToTarget_ShouldReturnFalse() throws IOException {
//        // Arrange
//        SizeFileTrigger sizeFileTrigger = new SizeFileTrigger();
//        File testFile = createTestFile(1000); // Create a test file with size 1000 bytes
//        sizeFileTrigger.setTargetFile(testFile);
//        sizeFileTrigger.setSizeFile(1000); // Set target size to 1000 bytes
//
//        // Act
//        boolean result = sizeFileTrigger.evaluate();
//
//        // Assert
//        assertFalse("Expected false, but got true", result);
//    }
//
//    @Test
//    public void testEvaluate_FileSizeLessThanTarget_ShouldReturnFalse() throws IOException {
//        // Arrange
//        SizeFileTrigger sizeFileTrigger = new SizeFileTrigger();
//        File testFile = createTestFile(1000); // Create a test file with size 1000 bytes
//        sizeFileTrigger.setTargetFile(testFile);
//        sizeFileTrigger.setSizeFile(1500); // Set target size to 1500 bytes
//
//        // Act
//        boolean result = sizeFileTrigger.evaluate();
//
//        // Assert
//        assertFalse("Expected false, but got true", result);
//    }
//
//    private File createTestFile(int size) throws IOException {
//        // Create a test file with specified size
//        File testFile = File.createTempFile("test", ".txt");
//        testFile.deleteOnExit();
//
//        // Write some data to the file to reach the specified size
//        byte[] data = new byte[size];
//        java.nio.file.Files.write(testFile.toPath(), data);
//
//        return testFile;
//    }
//}



