/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package group2;

import group2.Model.Action.FileMoveAction;
import java.io.File;
import org.junit.Before;
import org.junit.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.After;
import static org.junit.Assert.*;

/**
 *
 * @author soniabruno
 */

public class FileMoveActionTest {
    private FileMoveAction fileMoveAction;
    private String destinationFolder;
    private File srcPath;
    private Path dstPath;
    
    @Before
    public void setUp() throws IOException {
        fileMoveAction = new FileMoveAction();
        srcPath = File.createTempFile("sourceFile", ".txt");   
        dstPath = Files.createTempDirectory("destinationFolder");
    }
    
    /**
     * Cleans up resources used during the test, including deleting the temporary source file
     * and clearing the temporary destination directory.
     *
     * @throws IOException if an I/O error occurs during cleanup.
     */
    @After
    public void cleaner() throws IOException{
        // Delete the temporary file
        if (srcPath.exists()) {
            srcPath.delete();
        }
        
        // Delete the destination directory and its contents
        if (Files.exists(dstPath)) {
            Files.walk(dstPath)
                .sorted((p1, p2) -> -p1.compareTo(p2)) 
                .forEach(path -> {
                    try {
                        Files.delete(path);
                    } catch (IOException e) {
                    }
                });
        }
    }
    
    @Test
    public void testSetSourcePath() {
        String sourcePath = "source.txt";
        fileMoveAction.setSourcePath(sourcePath);
        assertEquals(sourcePath, fileMoveAction.getSourcePath());
    }

    @Test
    public void testSetDestinationPath() {
        String destinationPath = "destination.txt";
        fileMoveAction.setDestinationPath(destinationPath);
        assertEquals(destinationPath, fileMoveAction.getDestinationPath());
    }
   
    /**
     * Tests the {@link FileMoveAction#execute()} method by moving a file
     * from the source path to the destination path and checking if the file exists.
     *
     * @throws IOException if an I/O error occurs during the test.
     */
    @Test
    public void testFileMoveActionExecute() throws IOException {

        fileMoveAction.setSourcePath(srcPath.getAbsolutePath());
        fileMoveAction.setDestinationPath(dstPath.toString());

        fileMoveAction.execute();
        
        Path destinationFilePath = dstPath.resolve(srcPath.getName());
        assertTrue(Files.exists(destinationFilePath));
    }
}