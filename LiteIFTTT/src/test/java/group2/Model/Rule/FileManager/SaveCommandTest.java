/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package group2.Model.Rule.FileManager;

import group2.Model.Rule.RuleList;
import java.io.File;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class SaveCommandTest {
    
    private RuleList ruleList;
    private File file;
    private SaveCommand saveCommand;
    private File fileDst; 
    
    @Before
    public void setUp() {
        ruleList = new RuleList(5, "Test");  
        String nameFile = "testFile";
        file = new File(nameFile);  
        fileDst = new File(nameFile + ".dat");
        saveCommand = new SaveCommand(ruleList, file);

    }

    @After
    public void cleaner(){
        
        if (file.exists()) {
            file.delete();
        }
    }     
    
    @Test
    public void testExecute() {
        // Call the execute method
        RuleList loadedRuleSet = new RuleList(5, "SecondTest");
        saveCommand.execute();
        
        try {
            CompletableFuture<RuleList> future = FileIOManager.loadFromFileAsync(fileDst);
            loadedRuleSet = future.get();
            assertEquals(ruleList, loadedRuleSet);
        } catch (InterruptedException | ExecutionException ex) {
            // Lancia un'eccezione personalizzata per far fallire il test
            fail("Il test ha generato un'eccezione: " + ex.getMessage());
        }
        assertEquals(ruleList, loadedRuleSet);
    }

    @Test
    public void testGetRuleSet() {
        // Ensure that getRuleSet returns the correct RuleList
        assertEquals(ruleList, saveCommand.getRuleSet());
    }

    @Test
    public void testSetRuleSet() {
        // Create a new RuleList for testing
        RuleList newRuleSet = new RuleList(5, "Testing");

        // Set the new RuleList using setRuleSet
        saveCommand.setRuleSet(newRuleSet);

        // Ensure that getRuleSet returns the newly set RuleList
        assertEquals(newRuleSet, saveCommand.getRuleSet());
    }

    @Test
    public void testGetFile() {
        // Ensure that getFile returns the correct File
        assertEquals(file, saveCommand.getFile());
    }

    @Test
    public void testSetFile() {
        // Create a new File for testing
        File newFile = new File("newTestFile.txt");  // Provide a valid file path

        // Set the new File using setFile
        saveCommand.setFile(newFile);

        // Ensure that getFile returns the newly set File
        assertEquals(newFile, saveCommand.getFile());
    }
    
}
