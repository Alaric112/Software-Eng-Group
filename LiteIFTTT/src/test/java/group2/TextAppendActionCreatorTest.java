/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package group2;

import group2.Model.Action.TextAppendActionCreator;
import group2.Model.Action.TextAppendAction;
import group2.Model.Action.Action;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author patap
 */
public class TextAppendActionCreatorTest {
    
    public TextAppendActionCreatorTest() {
    }
    
    @Before
    public void setUp() {
    }

    /**
     * Test of createAction method, of class TextAppendActionCreator.
     */
    @Test
    public void testCreateAction() {
        
        TextAppendActionCreator textAppendFactory = new TextAppendActionCreator();

        Action actionTest = textAppendFactory.createAction();

        assertTrue(actionTest instanceof TextAppendAction);         
        
    }
    
}
