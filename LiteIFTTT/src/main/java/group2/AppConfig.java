/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

/**
 *
 * @author patap
 */
public class AppConfig {
    
    private static final String CONFIG_FILE = "config.properties";
    private static final String LAST_FILE = "lastModifiedFile";
    
    public static void saveProperty(String key, String value) {
        try (OutputStream output = new FileOutputStream(CONFIG_FILE)) {
            Properties prop = new Properties();
            prop.setProperty(key, value);
            prop.store(output, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static String getProperty(String key) {
        try (InputStream input = new FileInputStream(CONFIG_FILE)) {
            Properties prop = new Properties();
            prop.load(input);
            return prop.getProperty(key);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static File getPropertyAsFile(String key) {
        String filePath = getProperty(key);
        if (filePath != null) {
            return new File(filePath);
        }
        return null;
    }

    
    public static void savePathOfLastFile( String value){
        
        saveProperty(LAST_FILE, value);
    }
    
    public static File getLastRuleSet(){
        
        return getPropertyAsFile(LAST_FILE);
    }
    
}
