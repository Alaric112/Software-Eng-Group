/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group2.Model.Rule.FileManager;

import group2.Model.Rule.ControlRuleChecker;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;

/**
 * The {@code LoadCommand} class represents a command to asynchronously load a RuleList from a specified file.
 * It implements the {@link Command} interface, allowing it to be used in a command pattern.
 *
 * <p>This class takes a {@link CompletableFuture} as a parameter to execute on the completion of the load operation.
 * The load operation is performed asynchronously using Java's {@link CompletableFuture}, allowing for non-blocking execution.</p>
 *
 * @author Alessandro Accarino
 */
public class LoadCommand implements Command  {
    
    private CompletableFuture<Void> onLoadCompletion; 
    private File file;
    private ControlRuleChecker checker = ControlRuleChecker.getInstance();
   
    /**
     * Constructs a new LoadCommand with the specified {@link Thread} for execution on completion and the target file to load.
     *
     * @param onLoadCompletion The {@link CompletableFuture} to execute upon completion of the load operation.
     * @param file, The file from which to load the RuleList.
     */    
    public LoadCommand(CompletableFuture<Void> onLoadCompletion, File file) {

       this.onLoadCompletion = onLoadCompletion;
       this.file = file;
       
    }

    /**
     * Executes the LoadCommand by asynchronously loading a RuleSet from the specified file.
     * If the onLoadCompletion thread is set, it is started on successful completion of the load operation.
     */    
    @Override
    public void execute(){
        
        CompletableFuture<RuleList> completableFuture = FileIOManager.loadFromFileAsync(file);

        completableFuture.thenAcceptAsync(ruleSet -> {
            checker.changeRuleset(ruleSet);
            // Esegui l'azione definita dall'utente qui
            if (onLoadCompletion != null) {
                onLoadCompletion.complete(null);
            }
        }).exceptionally(ex ->{
            Throwable cause = ex.getCause();
            if (cause instanceof IOException) {
                System.err.println("Error during file loading: " + cause.getMessage());
            } else {
                // Altri tipi di eccezioni
                ex.printStackTrace();
            }
            return null;
        });
        
    }                

    /**
     * Gets the {@link CompletableFuture} to execute on completion of the load operation.
     *
     * @return The {@link CompletableFuture} to execute on completion.
     */    
    public CompletableFuture<Void> getOnLoadCompletion() {
        return onLoadCompletion;
    }

    /**
     * Sets the {@link CompletableFuture} to execute on completion of the load operation.
     *
     * @param onLoadCompletion The {@link CompletableFuture} to set for execution on completion.
     */    
    public void setOnLoadCompletion(CompletableFuture<Void> onLoadCompletion) {
        this.onLoadCompletion = onLoadCompletion;
    } 

    /**
     * Gets the file from which to load the RuleList.
     *
     * @return The file from which to load the RuleList.
     */    
    public File getFile() {
        return file;
    }

    /**
     * Sets the file from which to load the RuleList.
     *
     * @param file The file to set for loading the RuleList.
     */    
    public void setFile(File file) {
        this.file = file;
    }   
    
}
