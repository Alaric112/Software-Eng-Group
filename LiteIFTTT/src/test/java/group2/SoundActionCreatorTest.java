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
public class SoundActionCreatorTest {
    
    public SoundActionCreatorTest() {
    }
    
    @Before
    public void setUp() {
    }

    /**
     * Test of createAction method, of class SoundActionCreator.
     */
    @Test
    public void testCreateAction() {
        
        SoundActionCreator soundActionFactory = new SoundActionCreator();

        Action actionTest = soundActionFactory.createAction();

        assertTrue(actionTest instanceof SoundAction);
        
    }
    
}
