/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group2.Model.Rule.FileManager;

import java.io.File;
import javafx.concurrent.Service;

/**
 *
 * @author patap
 */
public class LoadCommand implements Command  {
    
    private Thread onLoadCompletion; 
    private File file;
    
    public LoadCommand(Thread onLoadCompletion, File file) {

       this.onLoadCompletion = onLoadCompletion;
       this.file = file;
       
    }
    
    @Override
    public void execute() {
        
        Service serv = FileIOManager.loadFromFileAsync(file);
        
        if (onLoadCompletion != null){
            serv.setOnSucceeded(event -> {
                onLoadCompletion.start();
            });
        }
        
    }                

    public Runnable getOnLoadCompletion() {
        return onLoadCompletion;
    }

    public void setOnLoadCompletion(Thread onLoadCompletion) {
        this.onLoadCompletion = onLoadCompletion;
    } 

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }   
    
}
