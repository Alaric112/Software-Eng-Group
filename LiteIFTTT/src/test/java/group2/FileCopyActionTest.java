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
    private Path sourcePath;
    private Path destinationPath;

    @Before
    public void setUp() {
        fileCopyAction = new FileCopyAction();
        sourcePath = Paths.get("source.txt"); 
        destinationPath = Paths.get("destination.txt"); 
    }
    
    @After
    public void cleaner() throws IOException{
        // Delete the temporary file
        if (Files.exists(sourcePath)) {
            Files.delete(sourcePath);
        }
        
        if (Files.exists(destinationPath)) {
            Files.delete(destinationPath);
        }
    }
    
    
    @Test
    public void testExecute() {
        
        createSampleFile(sourcePath, "this is a test file.");

        fileCopyAction.setSourcePath(sourcePath);
        fileCopyAction.setDestinationPath(destinationPath);

        fileCopyAction.execute();

        assertTrue(Files.exists(destinationPath));

        try {
            String sourceContent = new String(Files.readAllBytes(sourcePath));
            String destinationContent = new String(Files.readAllBytes(destinationPath));
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