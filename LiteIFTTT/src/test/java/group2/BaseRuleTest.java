
package group2;

import group2.Model.Rule.*;
import group2.Model.Action.*;
import group2.Model.Trigger.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


/**
 *
 * @author Alessandro Accarino
 */
public class BaseRuleTest {
    
    private class MockTrigger implements Trigger {
        @Override
        public boolean evaluate() {
            return true; // Modifica questo valore a seconda delle tue esigenze di test
        }
    }

    private class MockAction implements Action {
        private boolean executed = false;

        @Override
        public void execute() {
            executed = true;
        }

        public boolean isExecuted() {
            return executed;
        }
    }

    private BaseRule baseRule;
    private MockTrigger mockTrigger;
    private MockAction mockAction;

    @Before
    public void setUp() {
        mockTrigger = new MockTrigger();
        mockAction = new MockAction();
        baseRule = new BaseRule("TestRule", mockTrigger, mockAction);
    }

    @Test
    public void testCheckRuleWhenActiveAndTriggerIsTrue() {
        baseRule.checkRule();
        assertTrue(mockAction.isExecuted());
    }

    @Test
    public void testCheckRuleWhenActiveAndTriggerIsFalse() {
        mockTrigger = new MockTrigger() {
            @Override
            public boolean evaluate() {
                return false;
            }
        };
        baseRule = new BaseRule("TestRule", mockTrigger, mockAction);
        baseRule.checkRule();
        assertFalse(mockAction.isExecuted());
    }

    @Test
    public void testCheckRuleWhenNotActive() {
        baseRule = new BaseRule("TestRule", mockTrigger, mockAction);
        baseRule.setActive(false);

        baseRule.checkRule();
        assertFalse(mockAction.isExecuted());
    }
    
    @Test
    public void testSetName() {
        // Modifica il nome della regola
        baseRule.setName("NewRuleName");

        // Verifica che il nome sia stato cambiato correttamente
        assertEquals("NewRuleName", baseRule.getName());
    }

    @Test
    public void testSetTrigger() {
        Trigger newTrigger = new MockTrigger();
        baseRule.setTrigger(newTrigger);
        assertEquals(newTrigger, baseRule.getTrigger());
    }

    @Test
    public void testSetAction() {
        Action newAction = new MockAction();
        baseRule.setAction(newAction);
        assertEquals(newAction, baseRule.getAction());
    }
    
}
