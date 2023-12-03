/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package group2;

/**
 *
 * @author Faust
 */



import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class FireOnlyOnceDecoratorTest {

    private MockRule rule;
    private FireOnlyOnceDecorator fireOnlyOnceDecorator;


    @Before
    public void setUp() {       
        rule = new MockRule("test"); 
        fireOnlyOnceDecorator = new FireOnlyOnceDecorator(rule);
        rule.setActive(true);
    }

//    @Test
//    public void testCheckRule_FirstTimeActive_ShouldExecuteRuleAndSwitchStatus() {
//        // Act
//        
//        fireOnlyOnceDecorator.setOnlyOnce(true);       
//        fireOnlyOnceDecorator.checkRule();
//        fireOnlyOnceDecorator.checkRule();
//        System.out.println(fireOnlyOnceDecorator.isRepeated());
//        System.out.println(fireOnlyOnceDecorator.isOnlyOnce());
//        System.out.println(rule.isActive());
//        assertTrue(fireOnlyOnceDecorator.isRepeated());
//        
//    }

//    @Test
//    public void testCheckRule_SecondTimeActive_ShouldNotExecuteRuleOrSwitchStatus() {
//        // Act
//        fireOnlyOnceDecorator.checkRule(); // Prima esecuzione
//        fireOnlyOnceDecorator.checkRule(); // Seconda esecuzione
//
//        // Assert
//        assertTrue(fakeRule.isCheckRuleCalled());
//        assertTrue(fireOnlyOnceDecorator.onlyOnce);
//        assertFalse(fakeRule.isSwitchStatusCalled());
//    }
//
//    @Test
//    public void testCheckRule_NotActive_ShouldNotExecuteRuleOrSwitchStatus() {
//        // Imposta FakeRule come non attiva
//        fakeRule.switchStatus();
//
//        // Act
//        fireOnlyOnceDecorator.checkRule();
//
//        // Assert
//        assertFalse(fakeRule.isCheckRuleCalled());
//        assertFalse(fireOnlyOnceDecorator.onlyOnce);
//        assertFalse(fakeRule.isSwitchStatusCalled());
//    }
}
