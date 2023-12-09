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
import java.nio.file.Paths;
import static org.junit.Assert.*;

/**
 *
 * @author soniabruno
 */


public class FileCopyActionTest {

    private FileCopyAction fileCopyAction;
    private String sourcePath;
    private String destinationPath;
    private File srcPath;
    private Path dstPath;
    
    @Before
    public void setUp() throws IOException {
        fileCopyAction = new FileCopyAction();
        sourcePath = "source.txt"; 
        srcPath = File.createTempFile("source", ".txt");
        dstPath= Files.createTempDirectory(destinationPath);
    }
    
    @After
    public void cleaner() throws IOException{
        // Delete the temporary file
        if (srcPath.exists()) {
            srcPath.delete();
        }
        
//        if (Files.exists(dstPath)) {
//            Files.delete(dstPath);
//        }
    }
    
    
    @Test

    public void testExecute() throws IOException {

        fileCopyAction.setSourcePath(srcPath.getAbsolutePath());

        fileCopyAction.setDestinationPath(dstPath.toString());

        System.out.println("Absolute destination path: " + dstPath.toAbsolutePath());

        fileCopyAction.execute();

        Path destinationFilePath = dstPath.resolve(srcPath.getName());
        assertTrue(Files.exists(destinationFilePath));
    }
}
