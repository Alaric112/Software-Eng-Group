/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package group2;

import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * JUnit test class for the {@link Ruleset} class.
 */
public class RulesetTest {

    private Ruleset ruleset;
    
    /**
     * Test implementation of the {@link Rule} interface.
     */
    private static class TestRule implements Rule {
        private String name;
        /**
         * Constructs a TestRule instance with the specified name
         * @param name The name of the test rule.
         */
        public TestRule(String name) {
            this.name = name;
        }

        @Override
        public void checkRule() {
            
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public Trigger getTrigger() {
            return null; 
        }

        @Override
        public Action getAction() {
            return null; 
        }
    }
    
    /**
     * Sets up a new Ruleset instance before each test.
     */
    @Before
    public void setUp() {
        // Inizializza un nuovo Ruleset prima di ogni test
        ruleset = new Ruleset(10, "TestRuleset");
    }
    
    /**
     * Tests the addition of a rule to the Ruleset.
     */
    @Test
    public void testAddRule() {
        // Verifica che la regola sia aggiunta correttamente al Ruleset
        Rule rule = new TestRule("TestRule");
        ruleset.addRule(rule);

        ObservableList<Rule> rules = ruleset.getRules();
        assertTrue(rules.contains(rule));
    }

    /**
     * Tests the removal of a rule from the Ruleset.
     */
    @Test
    public void testRemoveRule() {
        // Verifica che la regola sia rimossa correttamente dal Ruleset
        Rule rule = new TestRule("TestRule");
        ruleset.addRule(rule);

        ruleset.removeRule(0);

        ObservableList<Rule> rules = ruleset.getRules();
        assertFalse(rules.contains(rule));
    }

    /**
     * Tests the calculation of the Ruleset size.
     */
    @Test
    public void testSizeRuleSet() {
        // Verifica che la dimensione del Ruleset sia calcolata correttamente
        assertEquals(0, ruleset.sizeRuleSet());

        Rule rule1 = new TestRule("TestRule1");
        Rule rule2 = new TestRule("TestRule2");

        ruleset.addRule(rule1);
        assertEquals(1, ruleset.sizeRuleSet());

        ruleset.addRule(rule2);
        assertEquals(2, ruleset.sizeRuleSet());

        ruleset.removeRule(0);
        assertEquals(1, ruleset.sizeRuleSet());
    }

    /**
     * Tests the clearing of the Ruleset.
     */
    @Test
    public void testClearRuleSet() {
        // Verifica che il Ruleset venga cancellato correttamente
        Rule rule1 = new TestRule("TestRule1");
        Rule rule2 = new TestRule("TestRule2");

        ruleset.addRule(rule1);
        ruleset.addRule(rule2);

        ruleset.clearRuleSet();

        ObservableList<Rule> rules = ruleset.getRules();
        assertTrue(rules.isEmpty());
    }

    /**
     * Tests the retrieval of the Ruleset name.
     */
    @Test
    public void testGetName() {
        // Verifica che il nome del Ruleset sia restituito correttamente
        assertEquals("TestRuleset", ruleset.getName());
    }

    /**
     * Tests the setting of the Ruleset name.
     */
    @Test
    public void testSetName() {
        // Verifica che il nome del Ruleset sia impostato correttamente
        ruleset.setName("NewTestRuleset");
        assertEquals("NewTestRuleset", ruleset.getName());
    }

    /**
     * Tests the retrieval of the Ruleset timer.
     */
    @Test
    public void testGetTimer() {
        // Verifica che il timer del Ruleset sia restituito correttamente
        assertEquals(10, ruleset.getTimer());
    }

    /**
     * Tests the setting of the Ruleset timer.
     */
    @Test
    public void testSetTimer() {
        // Verifica che il timer del Ruleset sia impostato correttamente
        ruleset.setTimer(20);
        assertEquals(20, ruleset.getTimer());
    }
}