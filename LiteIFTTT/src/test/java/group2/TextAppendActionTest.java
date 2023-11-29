/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package group2;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import static org.junit.Assert.assertEquals;


/**
 *
 * @author soniabruno
 */
public class TextAppendActionTest {

    private TextAppendAction textAppendAction;
    private File testFile;

    @Before
    public void setUp() throws IOException {
        textAppendAction = new TextAppendAction();
        testFile = File.createTempFile("testAppendAction", ".txt");
    }
    
    @After
    public void cleaner(){
        // Delete the temporary file
        if (testFile != null && testFile.exists()) {
            testFile.delete();
        }
    }
    
    @Test
    public void testExecute() {
        // Set the text to be appended
        textAppendAction.setTextAppend("Appended Text");

        // Set the file to be the test file
        textAppendAction.setFile(testFile);

        // Execute the action
        textAppendAction.execute();

        // Check if the text has been appended
        String finalContent = readFromFile(testFile);
        assertEquals("Appended Text", finalContent);
    }

    private void writeToFile(File file, String content) {
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(file)))) {
            writer.print(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String readFromFile(File file) {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content.toString().trim();
    }
}