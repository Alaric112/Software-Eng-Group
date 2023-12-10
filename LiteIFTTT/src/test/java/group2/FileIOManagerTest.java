
package group2;

import group2.Model.Rule.FileManager.RuleList;
import group2.Model.Rule.FileManager.FileIOManager;
import group2.Model.Rule.ControlRuleChecker;
import group2.Model.Rule.Rule;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Alessandro Accarino
 */
public class FileIOManagerTest {
    
    private File testFile;
    private RuleList testRuleList;
    private Rule testRule;
    
    @Before
    public void setUp() throws IOException {
        testFile = File.createTempFile("testRuleset", ".dat");
        testRuleList = new RuleList(5, "TestRuleSet");
        testRule = new MockRule("TestRule");
        
    }
    
    @After
    public void cleaner(){
        
        testRuleList = null;

        if (testFile != null && testFile.exists()) {
            testFile.delete();
        }
    }    
    
    @Test
    public void testSaveAndLoad() throws IOException  {
        testRuleList.addRule(testRule);
        FileIOManager.saveRuleListToFile(testFile, testRuleList);
        FileIOManager.loadFromFile(testFile);
        RuleList loadedRuleSet = ControlRuleChecker.getInstance().getRuleSet();
        assertEquals(testRuleList.getName(), loadedRuleSet.getName());
        assertEquals(testRuleList.getRules().size(), loadedRuleSet.getRules().size());
    }

    @Test(expected = IOException.class)
    public void testLoadNonExistentFile() throws IOException {
        FileIOManager.loadFromFile(new File("nonexistentfile.dat"));
    }

    @Test(expected = FileNotFoundException.class)
    public void testSaveNullFile() throws IOException {
        FileIOManager.saveRuleListToFile(null, testRuleList);
    }

    @Test
    public void testLoadFromFileAsync() throws IOException, InterruptedException, ExecutionException {

        FileIOManager.saveRuleListToFile(testFile, testRuleList);

        CompletableFuture<RuleList> future = FileIOManager.loadFromFileAsync(testFile);
        RuleList actualRuleSet = future.get();

        assertEquals(testRuleList, actualRuleSet);
    }    

    @Test
    public void testSaveAndLoadToFileAsync() throws IOException, InterruptedException, ExecutionException {

        FileIOManager.saveToFileAsync(testFile, testRuleList);

        CompletableFuture<RuleList> loadFuture = FileIOManager.loadFromFileAsync(testFile);

        RuleList loadedRuleSet = loadFuture.get();

        assertNotNull(loadedRuleSet);
        assertEquals(testRuleList.getName(), loadedRuleSet.getName());
        assertEquals(testRuleList.getTimer(), loadedRuleSet.getTimer());
        assertEquals(testRuleList.getRules().size(), loadedRuleSet.getRules().size());
    }

    
}
