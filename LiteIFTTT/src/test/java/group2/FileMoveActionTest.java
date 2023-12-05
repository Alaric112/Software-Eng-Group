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

    private String sourceFile;
    private String destinationFolder;
    private String destinationFile;
    private Path srcPath;
    private Path dstPath;
    private Path dstFolderPath;
    
    
    @Before
    public void setUp() throws IOException {
                
        srcPath = Files.createTempFile("sourceFile", ".txt");
        
        dstFolderPath = Files.createTempDirectory("destinationFolder");
        
        dstPath = dstFolderPath.resolve("destinationFile.txt");
        
        sourceFile = srcPath.toString();
        destinationFile = dstPath.toString();
        destinationFolder = dstFolderPath.toString();
        
    }

    @Test
    public void testFileMoveAction() throws IOException {
        assertTrue(Files.exists(srcPath));

        FileMoveAction fileMoveAction = new FileMoveAction();

        fileMoveAction.setSourcePath(sourceFile);
        fileMoveAction.setDestinationPath(destinationFile);

        fileMoveAction.execute();

     //   assertFalse(Files.exists(srcPath));  
      //  assertTrue(Files.exists(dstPath));
    }
}