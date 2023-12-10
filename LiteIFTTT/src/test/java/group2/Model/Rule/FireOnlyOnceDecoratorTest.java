/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package group2.Model.Rule;

import group2.MockRule;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 *
 * @author Faust
 */




public class FireOnlyOnceDecoratorTest {

    private Rule mockRule;
    private FireOnlyOnceDecorator fireOnlyOnceDecorator;

    @Before
    public void setUp() {
        // Initialize a mock Rule for testing
        mockRule = (Rule) new MockRule("test");
        // Create a FireOnlyOnceDecorator instance with the mock Rule
        fireOnlyOnceDecorator = new FireOnlyOnceDecorator(mockRule);
    }

    @Test
    public void testCheckRuleExecutesOnce() {
      
        assertFalse(fireOnlyOnceDecorator.isFired());
        assertTrue(fireOnlyOnceDecorator.isActive());
        fireOnlyOnceDecorator.checkRule();      
        assertFalse(fireOnlyOnceDecorator.isFired());
        assertFalse(fireOnlyOnceDecorator.isActive());       
        fireOnlyOnceDecorator.checkRule();
        assertFalse(fireOnlyOnceDecorator.isFired());
        assertFalse(fireOnlyOnceDecorator.isActive());  
    }
    
        @Test
    public void testCheckRuleExecutesOnceAfterReactiveRule() {     
        assertFalse(fireOnlyOnceDecorator.isFired());
        assertTrue(fireOnlyOnceDecorator.isActive());
        fireOnlyOnceDecorator.checkRule();      
        assertFalse(fireOnlyOnceDecorator.isFired());
        assertFalse(fireOnlyOnceDecorator.isActive());
        mockRule.switchStatus();
        assertFalse(fireOnlyOnceDecorator.isFired());
        assertTrue(fireOnlyOnceDecorator.isActive()); 
        fireOnlyOnceDecorator.checkRule();
        assertFalse(fireOnlyOnceDecorator.isFired());
        assertFalse(fireOnlyOnceDecorator.isActive()); 
    }
    

}
