/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package group2;

import group2.Model.Rule.RuleList;
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
 * @author patap
 */
public class FileIOManagerTest {
    
    private File testFile;
    private RuleList testRuleSet;
    private Rule testRule;
    
    @Before
    public void setUp() throws IOException {
        // Crea un nuovo file temporaneo per i test
        testFile = File.createTempFile("testRuleset", ".dat");
        testRuleSet = new RuleList(5, "TestRuleSet");
        testRule = new MockRule("TestRule");
        
    }
    
    @After
    public void cleaner(){
        
        // Clean up resources if needed
        testRuleSet = null;

        // Delete the temporary file
        if (testFile != null && testFile.exists()) {
            testFile.delete();
        }
    }

//    @Test
//    public void testSaveToFileAsync() throws IOException {
//        // Save the RuleList asynchronously
//        FileIOManager.saveToFileAsync(testFile, testRuleSet);
//        // Add assertions or use some synchronization mechanism to wait for the async operation
//    }    
    
    @Test
    public void testSaveAndLoad() throws IOException  {
        // Crea un oggetto RuleList di test
        testRuleSet.addRule(testRule);
        // Salva il RuleList nel file temporaneo
        FileIOManager.saveToFile(testFile, testRuleSet);
        // Carica il RuleList dal file temporaneo
        FileIOManager.loadFromFile(testFile);
        // Ottieni il RuleList corrente dal RuleChecker
        RuleList loadedRuleSet = ControlRuleChecker.getInstance().getRuleSet();
        // Verifica che il RuleList caricato sia uguale a quello di test
        assertEquals(testRuleSet.getName(), loadedRuleSet.getName());
        assertEquals(testRuleSet.getRules().size(), loadedRuleSet.getRules().size());
    }

    @Test(expected = IOException.class)
    public void testLoadNonExistentFile() throws IOException {
        FileIOManager.loadFromFile(new File("nonexistentfile.dat"));
    }

    @Test(expected = FileNotFoundException.class)
    public void testSaveNullFile() throws IOException {
        FileIOManager.saveToFile(null, testRuleSet);
    }

    @Test
    public void testLoadFromFileAsync() throws IOException, InterruptedException, ExecutionException {

        // Salva il RuleList nel file di test
        FileIOManager.saveToFile(testFile, testRuleSet);

        // Carica il RuleList dal file in modo asincrono
        CompletableFuture<RuleList> future = FileIOManager.loadFromFileAsync(testFile);
        RuleList actualRuleSet = future.get();

        // Confronta il RuleList caricato con quello atteso
        assertEquals(testRuleSet, actualRuleSet);
    }    

    @Test
    public void testSaveAndLoadToFileAsync() throws IOException, InterruptedException, ExecutionException {

        FileIOManager.saveToFileAsync(testFile, testRuleSet);

        // Carica il RuleList dal file di test in modo asincrono
        CompletableFuture<RuleList> loadFuture = FileIOManager.loadFromFileAsync(testFile);

        // Attendi il completamento dell'operazione asincrona di caricamento
        RuleList loadedRuleSet = loadFuture.get();

        // Verifica che il RuleList caricato sia uguale a quello di test
        assertNotNull(loadedRuleSet);
        assertEquals(testRuleSet.getName(), loadedRuleSet.getName());
        assertEquals(testRuleSet.getTimer(), loadedRuleSet.getTimer());
        assertEquals(testRuleSet.getRules().size(), loadedRuleSet.getRules().size());
    }

    
}
