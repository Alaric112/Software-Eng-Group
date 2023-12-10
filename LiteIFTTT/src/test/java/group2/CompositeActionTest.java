/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package group2;

import group2.Model.Action.*;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Alessandro Accarino
 */
public class CompositeActionTest {
    
    
    private CompositeAction compositeAction;
    private Action action1;
    private Action action2;
    private Action action3;
    
    @Before
    public void setUp() {
        // Initialize any objects or perform setup here
        compositeAction = new CompositeAction();
        action1 = new MockAction();
        action2 = new MockAction();
        action3 = new MockAction();
    }   
    
    @Test
    public void testExecute() {

        // Create a CompositeAction and add the mock actions
        compositeAction.addAction(action1);
        compositeAction.addAction(action2);
        compositeAction.addAction(action3);

        // Execute the composite action
        compositeAction.execute();

        // You can add more specific assertions based on the expected behavior of your execute method
        // For now, we'll just check that the execute method of each mock action was called
        assertTrue(((MockAction) action1).isExecuted());
        assertTrue(((MockAction) action2).isExecuted());
        assertTrue(((MockAction) action3).isExecuted());
    }

    @Test
    public void testAddActions() {

        // Create a CompositeAction and add the mock actions using addActions
        compositeAction.addActions(Arrays.asList(action1, action2, action3));

        // Get the actions from the CompositeAction
        List<Action> actions = compositeAction.getActions();

        // Check that the actions list contains the expected actions
        assertTrue(actions.contains(action1));
        assertTrue(actions.contains(action2));
        assertTrue(actions.contains(action3));
    }
    
}
