/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package group2.Model.Rule.FileManager;

import group2.App;
import group2.Model.Rule.ControlRuleChecker;
import group2.Model.Rule.RuleList;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author patap
 */
public class LoadCommandTest {
    
    private LoadCommand loadCommand;
    private CompletableFuture<Void> onLoadCompletionMock;
    private File fileMock;
    private RuleList ruleList;
    private ControlRuleChecker checker;
    
    @Before
    public void setUp() throws IOException {
        onLoadCompletionMock = new CompletableFuture<>();
        fileMock = File.createTempFile("test", ".dat");        
        ruleList = new RuleList(5, "TestRule");
        checker = ControlRuleChecker.getInstance();        
        FileIOManager.saveRuleListToFile(fileMock, ruleList);
                
        loadCommand = new LoadCommand(null, fileMock);        
    }

    @Test
    public void testSetOnLoadCompletion() {
        CompletableFuture<Void> newOnLoadCompletionMock = new CompletableFuture<>();
        loadCommand.setOnLoadCompletion(newOnLoadCompletionMock);
        assertEquals(newOnLoadCompletionMock, loadCommand.getOnLoadCompletion());
    }

    @Test
    public void testGetFile() {
        assertEquals(fileMock, loadCommand.getFile());
    }

    @Test
    public void testSetFile() {
        File newFileMock = new File("newTestFile"); // Replace with your actual file or use a mock if needed
        loadCommand.setFile(newFileMock);
        assertEquals(newFileMock, loadCommand.getFile());
    }

    @Test
    public void testExecuteSuccessfully() { 

        loadCommand.setOnLoadCompletion(onLoadCompletionMock);
        loadCommand.execute();

        try {
            onLoadCompletionMock.get();
            assertEquals(ruleList, checker.getRuleSet());
        } catch (Exception e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }   
    
}
