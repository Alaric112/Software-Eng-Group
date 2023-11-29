/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package group2;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 *
 * @author soniabruno
 */


public class FileCopyActionTest {

    private FileCopyAction fileCopyAction;
    private Path sourceFile;
    private Path destinationFile;

    @Before
    public void setUp() {
        fileCopyAction = new FileCopyAction();
        sourceFile = Paths.get("source.txt"); // Change this to the path of your source file
        destinationFile = Paths.get("destination.txt"); // Change this to the desired path for the destination file
    }
    
    @After
        public void cleaner() throws IOException{
        // Delete the temporary file
        if (Files.exists(sourceFile)) {
            Files.delete(sourceFile);
        }
        
        if (Files.exists(destinationFile)) {
            Files.delete(destinationFile);
        }
    }
    
    
    @Test
    public void testExecute() {
        // Create a sample file with content
        createSampleFile(sourceFile, "Hello, this is a test file.");

        // Set the source and destination paths
        fileCopyAction.setSourcePath(sourceFile);
        fileCopyAction.setDestinationPath(destinationFile);

        // Execute the action
        fileCopyAction.execute();

        // Check if the destination file exists
        assertTrue(Files.exists(destinationFile));

        // Check if the content of the destination file is the same as the source file
        try {
            String sourceContent = new String(Files.readAllBytes(sourceFile));
            String destinationContent = new String(Files.readAllBytes(destinationFile));
            assertEquals(sourceContent, destinationContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createSampleFile(Path filePath, String content) {
        try {
            Files.write(filePath, content.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}