/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package group2;

import java.io.File;
import org.junit.After; 
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Alessandro Accarino
 */
public class AppConfigTest {
    
    // Temporary configuration file for testing
    private static final String TEST_CONFIG_FILE = "test_config.properties";

    @After
    public void tearDown() {
        // Delete the test config file after each test
        File file = new File(TEST_CONFIG_FILE);
        file.delete();
    }

    @Test
    public void testSavePropertyAndGet() {
        // Test saving and retrieving a property
        AppConfig.saveProperty("testKey", "testValue");
        assertEquals("testValue", AppConfig.getProperty("testKey"));
    }

    @Test
    public void testGetPropertyAsFile() {
        // Test saving and retrieving a file path property
        AppConfig.savePathOfLastFile("test\\path\\testFile.txt");
        File file = AppConfig.getPropertyAsFile("lastModifiedFile");
        assertNotNull(file);
        assertEquals("test\\path\\testFile.txt", file.getPath());
    }

    @Test
    public void testGetLastRuleSet() {
        // Test retrieving the last modified file
        AppConfig.savePathOfLastFile("test\\path\\testFile.txt");
        File lastFile = AppConfig.getLastRuleSet();
        assertNotNull(lastFile);
        assertEquals("test\\path\\testFile.txt", lastFile.getPath());
    }
    
}
