/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group2.Model.Rule.FileManager;

import group2.AppConfig;
import group2.Model.Rule.ControlRuleChecker;
import group2.Model.Rule.Rule;
import group2.Model.Rule.RuleSet;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

/**
 * The `FileIOManager` class manages the saving and loading of the RuleSet.
 * It uses serialization to persist the RuleSet object to a file.
 * It also provides a method to display error messages through dialog windows.
 * 
 * @author patap
 */
public class FileIOManager {

    // Instance of the RuleChecker control to handle changes in the RuleSet    
    private static ControlRuleChecker checker = ControlRuleChecker.getInstance();

    /**
     * Loads the RuleSet from a specified file.
     * 
     * @param file The file from which to load the RuleSet.
     * @throws java.io.IOException
     */    
    public static void loadFromFile(File file) throws IOException {
        if (file == null)
          throw new IOException("File not found");  
          
        RuleSet ruleSet = loadRuleSet(file);
        
        if(ruleSet == null)
            throw new IOException("Rule set not loaded");
        
        checker.changeRuleset(ruleSet);            
        
    }

    /**
     * Saves the RuleSet to a specified file.
     * 
     * @param file     The file in which to save the RuleSet.
     * @param ruleSet  The RuleSet to save.
     * @throws java.io.IOException
     */
    public static void saveToFile(File file, RuleSet ruleSet) throws IOException {
        
        if (file != null) {
            // Aggiunge l'estensione .dat se l'utente non l'ha inserita
            String path = file.getPath();
            if (!path.toLowerCase().endsWith(".dat")) {
                file = new File(path + ".dat");
            }

            try (ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)))) {
                out.writeObject(ruleSet);
                //out.writeObject(new ArrayList<>(ruleSet.getRules()));
                out.close();

                AppConfig.savePathOfLastFile(file.getPath());

            } catch (IOException e) {
                // Gestisci l'eccezione in modo appropriato
                System.err.println("Error during file saving: " + e.getMessage());
                throw new IOException("Error during file saving", e);
            }
        } else{ 
            
            throw new FileNotFoundException("The specified file does not exist");
        }
                
    }

    /**
     * Asynchronously loads the RuleSet from a specified file.
     * 
     * @param file The file from which to load the RuleSet.
     * @return 
     */    
    public static Service<RuleSet> loadFromFileAsync(File file) {
        
        LoadRuleSetService loadService = new LoadRuleSetService(file);
        loadService.setOnSucceeded(event -> {            

        });

        loadService.start(); 
        return loadService;
        
    }

    /**
     * Asynchronously saves the RuleSet to a specified file.
     * 
     * @param file     The file in which to save the RuleSet.
     * @param ruleSet  The RuleSet to save.
     */    
    public static void saveToFileAsync(File file, RuleSet ruleSet) {
        Task<Void> task = new Task<>() {
            @Override
            protected Void call() throws Exception {
                saveToFile(file, ruleSet);
                return null;
            }
        };

        Thread thread = new Thread(task);
        thread.setDaemon(true);
        thread.start();
    }   
    
    /**
     * Displays an error message to the user using a dialog window.
     * 
     * @param file
     * @return 
     * @throws java.io.IOException
     */     
    protected static RuleSet loadRuleSet(File file) throws IOException{
        
        RuleSet ruleSet = null;
        List<Rule> rules = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                        
            ruleSet = (RuleSet) ois.readObject();
            
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("File not found");
        } catch (IOException | ClassNotFoundException ex) {
            throw new IOException("Error during file loading", ex);
        }
        
        return ruleSet;
          
    }
    
}
