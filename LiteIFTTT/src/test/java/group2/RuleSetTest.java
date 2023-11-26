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
 * JUnit test class for the {@link RuleSet} class.
 */
public class RuleSetTest {

    private RuleSet ruleset;
    
    /**
     * Test implementation of the {@link Rule} interface.
     */
    private static class TestRule implements Rule {
        private String name;
        private boolean active;
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
        
        @Override
        public void switchStatus(){
            
            this.active = !this.active;
        }
    }
    
    /**
     * Sets up a new RuleSet instance before each test.
     */
    @Before
    public void setUp() {
        // Inizializza un nuovo RuleSet prima di ogni test
        ruleset = new RuleSet(10, "TestRuleset");
    }
    
    /**
     * Tests the addition of a rule to the RuleSet.
     */
    @Test
    public void testAddRule() {
        // Verifica che la regola sia aggiunta correttamente al RuleSet
        Rule rule = new TestRule("TestRule");
        ruleset.addRule(rule);

        ObservableList<Rule> rules = ruleset.getRules();
        assertTrue(rules.contains(rule));
    }

    /**
     * Tests the removal of a rule from the RuleSet.
     */
    @Test
    public void testRemoveRule() {
        // Verifica che la regola sia rimossa correttamente dal RuleSet
        Rule rule = new TestRule("TestRule");
        ruleset.addRule(rule);

        ruleset.removeRule(rule);

        ObservableList<Rule> rules = ruleset.getRules();
        assertFalse(rules.contains(rule));
    }

    /**
     * Tests the calculation of the RuleSet size.
     */
    @Test
    public void testSizeRuleSet() {
        // Verifica che la dimensione del RuleSet sia calcolata correttamente
        assertEquals(0, ruleset.sizeRuleSet());

        Rule rule1 = new TestRule("TestRule1");
        Rule rule2 = new TestRule("TestRule2");

        ruleset.addRule(rule1);
        assertEquals(1, ruleset.sizeRuleSet());

        ruleset.addRule(rule2);
        assertEquals(2, ruleset.sizeRuleSet());

        ruleset.removeRule(rule1);
        assertEquals(1, ruleset.sizeRuleSet());
    }

    /**
     * Tests the clearing of the RuleSet.
     */
    @Test
    public void testClearRuleSet() {
        // Verifica che il RuleSet venga cancellato correttamente
        Rule rule1 = new TestRule("TestRule1");
        Rule rule2 = new TestRule("TestRule2");

        ruleset.addRule(rule1);
        ruleset.addRule(rule2);

        ruleset.clearRuleSet();

        ObservableList<Rule> rules = ruleset.getRules();
        assertTrue(rules.isEmpty());
    }

    /**
     * Tests the retrieval of the RuleSet name.
     */
    @Test
    public void testGetName() {
        // Verifica che il nome del RuleSet sia restituito correttamente
        assertEquals("TestRuleset", ruleset.getName());
    }

    /**
     * Tests the setting of the RuleSet name.
     */
    @Test
    public void testSetName() {
        // Verifica che il nome del RuleSet sia impostato correttamente
        ruleset.setName("NewTestRuleset");
        assertEquals("NewTestRuleset", ruleset.getName());
    }

    /**
     * Tests the retrieval of the RuleSet timer.
     */
    @Test
    public void testGetTimer() {
        // Verifica che il timer del RuleSet sia restituito correttamente
        assertEquals(10, ruleset.getTimer());
    }

    /**
     * Tests the setting of the RuleSet timer.
     */
    @Test
    public void testSetTimer() {
        // Verifica che il timer del RuleSet sia impostato correttamente
        ruleset.setTimer(20);
        assertEquals(20, ruleset.getTimer());
    }
}