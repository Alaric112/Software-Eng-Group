/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package group2;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author patap
 */
public class FileDeleteActionCreatorTest {
    
    public FileDeleteActionCreatorTest() {
    }
    
    @Before
    public void setUp() {
    }

    /**
     * Test of createAction method, of class FileDeleteActionCreator.
     */
    @Test
    public void testCreateAction() {

        FileDeleteActionCreator fileDeleteFactory = new FileDeleteActionCreator();

        Action actionTest = fileDeleteFactory.createAction();

        assertTrue(actionTest instanceof FileDeleteAction);        
        
    }
    
}
