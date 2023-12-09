///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
// */
//package group2.Model.Rule;
//
//import group2.MockRule;
//import org.junit.Before;
//import org.junit.Test;
//
//import static org.junit.Assert.*;
//
///**
// *
// * @author Faust
// */
//
//
//
//
//public class FireOnlyOnceDecoratorTest {
//
//    private Rule mockRule;
//    private FireOnlyOnceDecorator fireOnlyOnceDecorator;
//
//    @Before
//    public void setUp() {
//        // Initialize a mock Rule for testing
//        mockRule = new MockRule("test");
//        // Create a FireOnlyOnceDecorator instance with the mock Rule
//        fireOnlyOnceDecorator = new FireOnlyOnceDecorator(mockRule);
//    }
//
//    @Test
//    public void testCheckRuleExecutesOnce() {
//        // The first call to checkRule should execute the decorated rule
//        fireOnlyOnceDecorator.checkRule();
//
//        
//        // Verify that the decorated rule is fired and onlyOnce is set to false
//        assertTrue(fireOnlyOnceDecorator.isFired());
//
//
//        // Subsequent calls to checkRule should not execute the decorated rule
//        fireOnlyOnceDecorator.checkRule();
//        //assertFalse(false);
//
//    }
//
//    @Test
//    public void testResetState() {
//        // Call checkRule to execute the decorated rule
//        fireOnlyOnceDecorator.checkRule();
//
//        // Verify that the decorated rule is fired and onlyOnce is set to false
//        assertTrue(mockRule.isFired());
//
//
//
//
//        // Subsequent calls to checkRule should execute the decorated rule again
//        fireOnlyOnceDecorator.checkRule();
//        assertTrue(mockRule.isFired());
//
//    }
//
//}
