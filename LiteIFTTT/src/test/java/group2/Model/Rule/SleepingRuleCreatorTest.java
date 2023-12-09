/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package group2.Model.Rule;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */


import group2.MockRule;

import static org.junit.Assert.*;
import group2.Model.Rule.Rule;
import org.junit.Before;
import org.junit.Test;
/**
 *
 * @author Faust
 */


public class SleepingRuleCreatorTest {

    private SleepingRuleCreator sleepingRuleCreator;
    private MockRule mockRule;
    

    @Before
    public void setUp() {
        // Initialize the SleepingRuleCreator for testing
        sleepingRuleCreator = new SleepingRuleCreator();
        // Initialize a mock rule for testing
        mockRule = new MockRule("test");
    }

    @Test
    public void testCreateRule() {
        // Create a rule using SleepingRuleCreator
        Rule decoratedRule = sleepingRuleCreator.createRule(mockRule.getName(), mockRule.getTrigger(), mockRule.getAction());

        // Ensure that the created rule is an instance of FireOnlyOnceDecorator
        assertTrue(decoratedRule instanceof SleepingRuleDecorator);
    }

}