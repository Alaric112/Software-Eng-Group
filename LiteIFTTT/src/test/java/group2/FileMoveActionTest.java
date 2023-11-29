/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package group2;

import org.junit.Before;
import org.junit.Test;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 *
 * @author soniabruno
 */


public class FileMoveActionTest {

    private FileMoveAction fileMoveAction;

    @Before
    public void setUp() {
        fileMoveAction = new FileMoveAction();
    }

// @Test
//public void testExecuteSuccessfulFileMove() throws IOException {
//    // Create temporary source and destination paths
//    Path sourcePath = Files.createTempFile("source", ".txt");
//    Path destinationPath = Files.createTempFile("prova\\destination", ".txt");
//
//    // Set paths in the FileMoveAction instance
//    fileMoveAction.setSourcePath(sourcePath);
//    fileMoveAction.setDestinationPath(destinationPath);
//    System.out.println(sourcePath);
//    // Execute the action
//    fileMoveAction.execute();
//System.out.println(sourcePath);
//    // Assert that the source file no longer exists if it originally existed
//    if (Files.exists(sourcePath)) {
//        assertFalse("Source file still exists", Files.exists(sourcePath));
//    }
//
//    // Assert that the destination file now exists
//    assertTrue("Destination file does not exist", Files.exists(destinationPath));
//
//    // Clean up temporary files
//    Files.deleteIfExists(destinationPath);
//}


    @Test
    public void testExecuteWithError() {
        // Set non-existing source and destination paths
        Path sourcePath = Paths.get("not_existing_source.txt");
        Path destinationPath = Paths.get("not_existing_destination.txt");

        fileMoveAction.setSourcePath(sourcePath);
        fileMoveAction.setDestinationPath(destinationPath);

        // Execute the action and expect an error, but no exception to be thrown
        fileMoveAction.execute();
    }

    @Test
    public void testSetSourcePath() {
        // Set a new source path
        Path sourcePath = Paths.get("new_source.txt");
        fileMoveAction.setSourcePath(sourcePath);

        // Assert that the source path has been set correctly
        assertTrue(sourcePath.equals(fileMoveAction.setSourcePath(sourcePath)));
    }

    @Test
    public void testSetDestinationPath() {
        // Set a new destination path
        Path destinationPath = Paths.get("new_destination.txt");
        fileMoveAction.setDestinationPath(destinationPath);

        // Assert that the destination path has been set correctly
        assertTrue(destinationPath.equals(fileMoveAction.setDestinationPath(destinationPath)));
    }
}