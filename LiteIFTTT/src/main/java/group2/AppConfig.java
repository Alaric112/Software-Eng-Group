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
 * The AppConfig class provides utility methods for managing application configuration settings
 * and storing/retrieving file paths using Java Properties.
 *
 * <p>It includes methods to save and retrieve key-value pairs in a configuration file,
 * as well as specific methods for handling the last modified file path.</p>
 *
 * <p>The configuration data is stored in a file named "config.properties" in the root
 * directory of the application.</p>
 *
 * <p>The class includes methods for saving and retrieving both general properties and
 * file paths. The properties file is used to store key-value pairs, and it is loaded
 * and saved using the Java Properties API.</p>
 * 
 * @author patap
 */
public class AppConfig {
    
    /* The name of the configuration file. */
    private static final String CONFIG_FILE = "config.properties";
    /* The key used to store the last modified file path in the configuration file. */
    private static final String LAST_FILE = "lastModifiedFile";
    
    /**
     * Saves a key-value pair to the configuration file.
     *
     * @param key   The key to be saved.
     * @param value The value associated with the key.
     */    
    public static void saveProperty(String key, String value) {
        try (OutputStream output = new FileOutputStream(CONFIG_FILE)) {
            Properties prop = new Properties();
            prop.setProperty(key, value);
            prop.store(output, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves the value associated with a specified key from the configuration file.
     *
     * @param key The key for which the value should be retrieved.
     * @return The value associated with the specified key, or null if the key is not found.
     */    
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

    /**
     * Retrieves a file path stored as a property in the configuration file.
     *
     * @param key The key associated with the file path.
     * @return A File object representing the file path, or null if the key is not found.
     */    
    public static File getPropertyAsFile(String key) {
        String filePath = getProperty(key);
        if (filePath != null) {
            return new File(filePath);
        }
        return null;
    }

    /**
     * Saves the path of the last modified file to the configuration file.
     *
     * @param value The file path of the last modified file.
     */    
    public static void savePathOfLastFile( String value){
        
        saveProperty(LAST_FILE, value);
    }

    /**
     * Retrieves the File object representing the last modified file stored in the configuration.
     *
     * @return A File object representing the last modified file, or null if no file path is stored.
     */    
    public static File getLastRuleSet(){
        
        return getPropertyAsFile(LAST_FILE);
    }
    
}
