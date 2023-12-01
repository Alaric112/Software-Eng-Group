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
public class FileCopyActionCreatorTest {
    
    public FileCopyActionCreatorTest() {
    }
    
    @Before
    public void setUp() {
    }

    /**
     * Test of createAction method, of class FileCopyActionCreator.
     */
    @Test
    public void testCreateAction() {
              
        FileCopyActionCreator fileCopyFactory = new FileCopyActionCreator();

        Action actionTest = fileCopyFactory.createAction();

        assertTrue(actionTest instanceof FileCopyAction);
                      
    }
    
}
