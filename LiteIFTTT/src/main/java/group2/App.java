package group2;

import group2.Model.Rule.FileManager.*;
import java.io.File;
import java.io.FileInputStream;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.io.InputStream;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Modality;

/**
 * JavaFX App
 */
public class App extends Application {
    
    private static Scene scene;
    private static Image appIcon;
    
    @Override
    public void start(Stage stage) throws IOException {
        
        File lastModifiedFile = AppConfig.getLastRuleSet();
        if(lastModifiedFile != null && lastModifiedFile.exists()){

            try {
                FileIOManager.loadFromFile(lastModifiedFile);
                scene = new Scene(loadFXML("secondary"));
            } catch (IOException ex) {
                scene = new Scene(loadFXML("primary"));
            }           
            
        }else{ scene = new Scene(loadFXML("primary")); }
        
        InputStream stream = new FileInputStream("Asset/auction.png");
        appIcon = new Image(stream);
        stage.setResizable(false);
        stage.getIcons().add(appIcon);
        stage.setScene(scene);
        stage.setTitle("Lite IFTTT group02");
        stage.show();
        
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }
    
    /**
     * Creates a new sub-window with the specified FXML file content.
     * 
     * @param fxml The filename of the FXML file to load into the sub-window.
     * @param subWindowTitle
     */
    public static void createSubWindow(String fxml, String subWindowTitle){
        
        // Create a new Stage for the sub-window
        Stage subWindow = new Stage();
        
        try {
            // Set the scene of the sub-window with the content loaded from the specified FXML file
            subWindow.setScene(new Scene(loadFXML(fxml)));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        // Set the modality of the sub-window to APPLICATION_MODAL , blocking interaction with other windows until it is closed.
        subWindow.initModality(Modality.APPLICATION_MODAL);
        subWindow.setTitle(subWindowTitle);
        subWindow.setResizable(false);
        subWindow.getIcons().add(appIcon);
        subWindow.showAndWait();
    }
    
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static Image getAppIcon() {
        return appIcon;
    }
       
    public static void switchTo(String fxml){
        
        Platform.runLater(() -> {
            try {
                App.setRoot(fxml);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

    } 

    public static Alert createPopUP(AlertType alertType, String title, String contentText){
        
        var alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contentText);
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(getAppIcon());
        
        return alert;
    }
    
    public static void main(String[] args) {
        launch();
    }
    
    public static FileChooser createFC(String title){        
                
        FileChooser chooser = new FileChooser();
        chooser.setTitle(title);
        
        // Mostra la finestra di dialogo per selezionare il file da caricare
        return chooser;
        
    }
    
    public static File createFCLoad(){
        
        FileChooser chooser = createFC("Load RuleSet");
        
        // Imposta il filtro per permettere solo i file .dat
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Data Files (*.dat)", "*.dat");
        chooser.getExtensionFilters().add(extFilter);
        
        File file = chooser.showOpenDialog(new Stage());
        return file;
        
    }
    
    public static File createFCSave(String name){ 
        // Create a file chooser for selecting the save location
        FileChooser chooser = createFC("Save RuleSet");
        
        // Set a filter to allow only .dat files
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Data Files (*.dat)", "*.dat");
        chooser.getExtensionFilters().add(extFilter);

        // Set the initial file name (Automatically sets the file name to the ruleSet's name)
        chooser.setInitialFileName(name);

        // Show the save file dialog and get the selected file
        File file = chooser.showSaveDialog(new Stage());
        return file;
    }    
}