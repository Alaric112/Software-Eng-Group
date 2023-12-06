/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package group2;

import group2.Model.Action.*;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static org.junit.Assert.assertEquals;

/**
 *
 * @author soniabruno
 */


public class FileCopyActionTest {

    private FileCopyAction fileCopyAction;
    private String sourcePath;
    private String destinationPath;
    private Path srcPath;
    private Path dstPath;
    
    @Before
    public void setUp() {
        fileCopyAction = new FileCopyAction();
        sourcePath = "source.txt"; 
        destinationPath = "destination.txt";
        srcPath = Paths.get(sourcePath);
        dstPath = Paths.get(destinationPath);
    }
    
    @After
    public void cleaner() throws IOException{
        // Delete the temporary file
        
        
        if (Files.exists(srcPath)) {
            Files.delete(srcPath);
        }
        
        if (Files.exists(dstPath)) {
            Files.delete(dstPath);
        }
    }
    
    
    @Test
    public void testExecute() {
        
        createSampleFile(srcPath, "this is a test file.");

        fileCopyAction.setSourcePath(sourcePath);
        fileCopyAction.setDestinationPath(destinationPath);

        fileCopyAction.execute();

//        assertTrue(Files.exists(dstPath));

        try {
            String sourceContent = new String(Files.readAllBytes(srcPath));
            String destinationContent = new String(Files.readAllBytes(dstPath));
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