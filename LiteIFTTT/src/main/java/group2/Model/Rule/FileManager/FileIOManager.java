/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group2.Model.Rule.FileManager;

import group2.AppConfig;
import group2.Model.Rule.ControlRuleChecker;

import group2.Model.Rule.RuleList;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.concurrent.CompletableFuture;

/**
 * The `FileIOManager` class manages the saving and loading of the RuleSet.
 * It uses serialization to persist the RuleSet object to a file.
 * It also provides a method to display error messages through dialog windows.
 * 
 * @author Alessandro Accarino
 */
public class FileIOManager {

    // Instance of the RuleChecker control to handle changes in the RuleList    
    private static ControlRuleChecker checker = ControlRuleChecker.getInstance();

    // Aggiungi un oggetto di blocco per la sincronizzazione
    private static final Object lock = new Object();
    
    /**
     * Loads the RuleList from a specified file.
     * 
     * @param file The file from which to load the RuleList.
     * @throws java.io.IOException
     */    
    public static void loadFromFile(File file) throws IOException {
        if (file == null)
          throw new IOException("File not found");  
          
        RuleList ruleSet = loadRuleSet(file);
        
        if(ruleSet == null)
            throw new IOException("Rule set not loaded");
        
        checker.changeRuleset(ruleSet);            
        
    }

    /**
     * Saves the RuleList to a specified file.
     * 
     * @param file     The file in which to save the RuleList.
     * @param ruleSet  The RuleList to save.
     * @throws java.io.IOException
     */
    public static void saveToFile(File file, RuleList ruleSet) throws IOException {
        
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
     * Asynchronously loads the RuleList from a specified file.
     * 
     * @param file The file from which to load the RuleList.
     * @return 
     */    
    public static CompletableFuture<RuleList> loadFromFileAsync(File file) {
      
        synchronized (lock) {
            
            CompletableFuture<RuleList> completableFuture = new CompletableFuture<>();

            Thread loadThread = new Thread(() -> {
                try {
                    RuleList ruleSet = loadRuleSet(file);

                    if (ruleSet != null) {
                        //checker.changeRuleset(ruleSet);
                        completableFuture.complete(ruleSet);
                    } else {
                        completableFuture.completeExceptionally(new IOException("Rule set not loaded"));
                    }
                } catch (IOException ex) {
                    completableFuture.completeExceptionally(ex);
                }
            });

            loadThread.start();

            return completableFuture;
        }
    }


    /**
     * Asynchronously saves the RuleList to a specified file.
     * 
     * @param file     The file in which to save the RuleList.
     * @param ruleSet  The RuleList to save.
     */    
    public static void saveToFileAsync(File file, RuleList ruleSet) {
        Thread thread = new Thread(() -> {
            synchronized (lock) {
                try {
                    saveToFile(file, ruleSet);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

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
    protected static RuleList loadRuleSet(File file) throws IOException{
        
        RuleList ruleSet = null;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            ruleSet = (RuleList) ois.readObject();
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("File not found: " + file.getPath());
        } catch (IOException | ClassNotFoundException ex) {
            throw new IOException("Error during file loading", ex);
        }

        return ruleSet;          
    }
    
}
