package group2.Model.Rule;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */


import group2.MockRule;
import group2.Model.Rule.FireOnlyOnceCreator;
import group2.Model.Rule.FireOnlyOnceDecorator;
import static org.junit.Assert.*;
import group2.Model.Rule.Rule;
import org.junit.Before;
import org.junit.Test;
/**
 *
 * @author Faust
 */


public class FireOnlyOnceCreatorTest {

    private FireOnlyOnceCreator fireOnlyOnceCreator;
    private MockRule mockRule;
    

    @Before
    public void setUp() {
        // Initialize the FireOnlyOnceCreator for testing
        fireOnlyOnceCreator = new FireOnlyOnceCreator();
        // Initialize a mock rule for testing
        mockRule = new MockRule("test");
    }

    @Test
    public void testCreateRule() {
        // Create a rule using FireOnlyOnceCreator
        Rule decoratedRule = fireOnlyOnceCreator.createRule(mockRule.getName(), mockRule.getTrigger(), mockRule.getAction());

        // Ensure that the created rule is an instance of FireOnlyOnceDecorator
        assertTrue(decoratedRule instanceof FireOnlyOnceDecorator);
    }

}