/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package group2;

import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author 39334
 */
public class SleepingRuleDecoratorTest {
    
    public SleepingRuleDecoratorTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of checkRule method, of class SleepingRuleDecorator.
     */
    @Test
    public void testCheckRule() {
        System.out.println("checkRule");
        SleepingRuleDecorator instance = null;
        instance.checkRule();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setMinSleepTime method, of class SleepingRuleDecorator.
     */
    @Test
    public void testSetMinSleepTime() {
        System.out.println("setMinSleepTime");
        long minSleepTime = 0L;
        TimeUnit timeUnit = null;
        SleepingRuleDecorator instance = null;
        instance.setMinSleepTime(minSleepTime, timeUnit);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
