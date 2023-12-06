/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package group2;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author patap
 */
public class RuleCreatorTest {
    
    private RuleCreator ruleCreator;

    @Before
    public void setUp() {
        ruleCreator = RuleCreator.getInstance();
    }

    @Test
    public void testCreateRule() {
        String ruleName = "TestRule";
        Trigger mockTrigger = new MockTrigger();
        Action mockAction = new MockAction();

        Rule rule = ruleCreator.createRule(ruleName, mockTrigger, mockAction);

        assertNotNull(rule);
        assertEquals(ruleName, rule.getName());
        assertEquals(mockTrigger, rule.getTrigger());
        assertEquals(mockAction, rule.getAction());
    }

    @Test
    public void testCreateAction() {
        String actionType = "Message";
        Action action = ruleCreator.createAction(actionType);

        assertNotNull(action);
        assertTrue(action instanceof MessageAction);
    }

    @Test
    public void testCreateTrigger() {
        String triggerType = "Time";
        Trigger trigger = ruleCreator.createTrigger(triggerType);

        assertNotNull(trigger);
        assertTrue(trigger instanceof TimeTrigger);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateActionWithUnsupportedType() {
        String unsupportedType = "unsupportedType";
        ruleCreator.createAction(unsupportedType);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateTriggerWithUnsupportedType() {
        String unsupportedType = "unsupportedType";
        ruleCreator.createTrigger(unsupportedType);
    }

    @Test
    public void testGetAvailableActionTypes() {
        List<String> actionTypes = ruleCreator.getAvailableActionTypes();

        assertNotNull(actionTypes);
        assertTrue(actionTypes.contains("Message"));
        assertTrue(actionTypes.contains("Sound"));
    }

    @Test
    public void testGetAvailableTriggerTypes() {
        List<String> triggerTypes = ruleCreator.getAvailableTriggerTypes();

        assertNotNull(triggerTypes);
        assertTrue(triggerTypes.contains("Time"));
    }
    
    @Test
    public void testCreateCompositeAction() {
        Action action1 = new MockAction();
        Action action2 = new MockAction();
        Action action3 = new MockAction();

        Collection<Action> actions = Arrays.asList(action1, action2, action3);

        CompositeAction result = ruleCreator.createCompisteAction(actions);

        assertNotNull(result);

        List<Action> resultActions = result.getActions();
        assertEquals(actions.size(), resultActions.size());
        assertTrue(resultActions.containsAll(actions));
    }
    
}
