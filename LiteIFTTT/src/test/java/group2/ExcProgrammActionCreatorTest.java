/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package group2;

import group2.Model.Action.*;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Alessandro Accarino
 */
public class ExcProgrammActionCreatorTest {
    
    @Test
    public void testCreateAction() {

        ExcProgrammActionCreator excProgrammActionFactory = new ExcProgrammActionCreator();

        Action actionTest = excProgrammActionFactory.createAction();

        assertTrue(actionTest instanceof ExcProgrammAction);        
        
    }
    
}
