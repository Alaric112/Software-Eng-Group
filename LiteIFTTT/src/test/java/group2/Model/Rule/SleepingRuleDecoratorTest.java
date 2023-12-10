/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package group2.Model.Rule;



/**
 *
 * @author Faust
 */
import group2.MockRule;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class SleepingRuleDecoratorTest {

    private Rule mockRule;
    private SleepingRuleDecorator sleepingRuleDecorator;

    @Before
    public void setUp() {
        // Initialize a mock Rule for testing
        mockRule = new MockRule("test");
        // Create a SleepingRuleDecorator instance with the mock Rule
        sleepingRuleDecorator = new SleepingRuleDecorator(mockRule);
    }

    @Test
    public void testCheckRuleWithSleep() {

        long minSleepTimeMillis = 2000;
        sleepingRuleDecorator.setMinSleepTime(minSleepTimeMillis);

        sleepingRuleDecorator.checkRule();


        sleep(minSleepTimeMillis);


        sleepingRuleDecorator.checkRule();
        assertTrue(((MockRule) mockRule).isFired());
    }

    @Test
    public void testCheckRuleWithoutSleep() {

        long minSleepTimeMillis = 0;
        sleepingRuleDecorator.setMinSleepTime(minSleepTimeMillis);


        sleepingRuleDecorator.checkRule();
        assertTrue(((MockRule) mockRule).isFired());        


        sleepingRuleDecorator.checkRule();
        assertTrue(((MockRule) mockRule).isFired());
    }


    private void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

}
