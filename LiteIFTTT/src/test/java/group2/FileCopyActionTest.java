/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package group2;

import group2.Model.Action.*;
import java.io.File;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import static org.junit.Assert.*;

/**
 *
 * @author soniabruno
 */


public class FileCopyActionTest {

    private FileCopyAction fileCopyAction;
    private String destinationPath;
    private File srcPath;
    private Path dstPath;
    
    @Before
    public void setUp() throws IOException {
        fileCopyAction = new FileCopyAction(); 
        srcPath = File.createTempFile("source", ".txt");
        dstPath= Files.createTempDirectory(destinationPath);
    }
    
    /**
     * Cleans up resources used during the test, including deleting the temporary source file
     * and clearing the temporary destination directory.
     *
     * @throws IOException if an I/O error occurs during cleanup.
     */
    @After
    public void cleaner() throws IOException{
        if (srcPath.exists()) {
            srcPath.delete();
        }
        
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
    
    /**
     * Tests the {@link FileCopyAction#setSourcePath(String)} method.
     */
    @Test
    public void testSetSourcePath() {
        String sourcePath = "source.txt";
        fileCopyAction.setSourcePath(sourcePath);
        assertEquals(sourcePath, fileCopyAction.getSourcePath());
    }

    /**
     * Tests the {@link FileCopyAction#setDestinationPath(String)} method.
     */
    @Test
    public void testSetDestinationPath() {
        destinationPath = "destination.txt";
        fileCopyAction.setDestinationPath(destinationPath);
        assertEquals(destinationPath, fileCopyAction.getDestinationPath());
    }
    
    /**
     * Tests the {@link FileCopyAction#execute()} method by copying a file
     * from the source path to the destination path and checking if the file exists.
     *
     * @throws IOException if an I/O error occurs during the test.
     */
    @Test
    public void testFileCopyActionExecute() throws IOException {

        fileCopyAction.setSourcePath(srcPath.getAbsolutePath());

        fileCopyAction.setDestinationPath(dstPath.toString());

        fileCopyAction.execute();

        Path destinationFilePath = dstPath.resolve(srcPath.getName());
        assertTrue(Files.exists(destinationFilePath));
    }
}
