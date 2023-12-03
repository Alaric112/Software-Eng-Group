/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package group2;

import java.io.File;
import java.io.IOException;
import static java.lang.Thread.sleep;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;


/**
 *
 * @author Faust
 */


public class SizeFileTriggerTest {
    private FileTrigger fileTrigger;
    private Path tempDirectory;
    private Path tempFile;
    private SizeFileTrigger sizeFileTrigger;
    
    @Before
    public void setUp() throws IOException {
        sizeFileTrigger = new SizeFileTrigger();
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
    public void testEvaluate_FileSizeGreaterThanTarget_ShouldReturnTrue() throws IOException, InterruptedException {
        
        
        File testFile = createTestFile(500); 
        sizeFileTrigger.setTargetFile(tempDirectory.toString(), testFile.toString());
        sizeFileTrigger.setSizeFile(100); 
       
        System.out.println(testFile.length());
        System.out.println(sizeFileTrigger.getSizeFile());
        
        
        boolean result = sizeFileTrigger.evaluate();

       
        assertFalse("Expected true, but got false", result);
    }

    @Test
    public void testEvaluate_FileSizeEqualToTarget_ShouldReturnFalse() throws IOException {
       
        File testFile = createTestFile(1000); // Create a test file with size 1000 bytes
        sizeFileTrigger.setTargetFile(tempDirectory.toString(),testFile.toString());
        sizeFileTrigger.setSizeFile(1000); // Set target size to 1000 bytes

        
        boolean result = sizeFileTrigger.evaluate();

        
        assertFalse("Expected false, but got true", result);
    }

    @Test
    public void testEvaluate_FileSizeLessThanTarget_ShouldReturnFalse() throws IOException {
        
        File testFile = createTestFile(1000); // Create a test file with size 1000 bytes
        sizeFileTrigger.setTargetFile(tempDirectory.toString(),testFile.toString());
        sizeFileTrigger.setSizeFile(1500); // Set target size to 1500 bytes

        
        boolean result = sizeFileTrigger.evaluate();

        
        assertFalse("Expected false, but got true", result);
    }

    private File createTestFile(int size) throws IOException {
        
        File testFile = File.createTempFile("test", ".txt");
        testFile.deleteOnExit();

       
        byte[] data = new byte[size];
        java.nio.file.Files.write(testFile.toPath(), data);

        return testFile;
    }
}



