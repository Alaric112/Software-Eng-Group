/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group2;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Platform;
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
            throw new IOException("Rule not loaded");
        
        checker.changeRuleset(ruleSet);            
        
//        if (file != null) {
//           RuleSet ruleSet = loadRuleSet(file);
//           if (ruleSet != null){
//           checker.changeRuleset(ruleSet);
//           } else {
//           throw new IOException("Rule not loaded");
//           }
//        } else {
//        throw new IOException("File not found");
//        }
        
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
                out.writeObject(new ArrayList<>(ruleSet.getRules()));
                out.close();

                AppConfig.savePathOfLastFile(file.getPath());

            } catch (IOException e) {
                // Gestisci l'eccezione in modo appropriato
                System.err.println("Error during file saving: " + e.getMessage());
                showErrorMessage("An error occurred during file saving.");
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

        loadService.setOnFailed(event -> {
            Throwable exception = loadService.getException();
            if (exception != null) {
                // Gestisci l'errore in modo appropriato
                showErrorMessage("An error occurred during file loading: " + exception.getMessage());
            }
        });

        loadService.start(); 
        return loadService;
        
//        Task<Void> task = new Task<>() {
//                @Override
//                protected Void call() throws Exception {           
//                    // Genera un evento di caricamento completato
//                    Platform.runLater(() -> {
//                        try {
//                           loadFromFile(file);
//                        } catch (IOException ex) {
//                            ex.printStackTrace();
//                            }
//                        });
//                    return null;
//                }
//            };
//
//        task.setOnFailed(event -> {
//            Throwable exception = task.getException();
//                if (exception != null) {
//                    // Se si verifica un errore, notifica l'utente attraverso un messaggio di errore
//                        showErrorMessage("An error occurred during file loading: " + exception.getMessage());
//                }
//            });
//
//        Thread thread = new Thread(task);
//        thread.setDaemon(true);
//        thread.start();
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
     * @param message The error message to show.
     */    
    private static void showErrorMessage(String message) {
        // Mostra un messaggio di errore all'utente
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.show();
    }
    
    protected static RuleSet loadRuleSet(File file) throws IOException{
        
        RuleSet ruleSet = null;
        List<Rule> rules = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                        
            ruleSet = (RuleSet) ois.readObject();
            rules = (ArrayList<Rule>) ois.readObject();
            ObservableList<Rule> Obsrules = FXCollections.observableArrayList();
            Obsrules.setAll(rules);
            ruleSet.setRules(Obsrules);
            
        } catch (FileNotFoundException e) {
                throw new FileNotFoundException("File not found");
        } catch (IOException | ClassNotFoundException ex) {
            showErrorMessage("An error occurred during file loading.");
            throw new IOException("Error during file loading", ex);
        }
        
        return ruleSet;
          
    }
    
}
