/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package group2;

import org.junit.Before;
import org.junit.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 *
 * @author soniabruno
 */

public class FileMoveActionTest {

    private Path sourceFile;
    private Path destinationFolder;
    private Path destinationFile;

    @Before
    public void setUp() throws IOException {
        sourceFile = Files.createTempFile("sourceFile", ".txt");
        
        destinationFolder = Files.createTempDirectory("destinationFolder");
        
        destinationFile = destinationFolder.resolve("destinationFile.txt");
    }

    @Test
    public void testFileMoveAction() throws IOException {
        assertTrue(Files.exists(sourceFile));

        FileMoveAction fileMoveAction = new FileMoveAction();

        fileMoveAction.setSourcePath(sourceFile);
        fileMoveAction.setDestinationPath(destinationFile);

        fileMoveAction.execute();

        assertFalse(Files.exists(sourceFile));  
        assertTrue(Files.exists(destinationFile));
    }
}