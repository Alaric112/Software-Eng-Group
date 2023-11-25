package group2;

import java.io.FileInputStream;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import javafx.scene.image.Image;
import javafx.stage.Modality;

/**
 * JavaFX App
 */
public class App extends Application {
    
    private static Scene scene;
    private static Image appIcon;
    
    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("primary"));
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
     * @throws IOException If an error occurs while loading the FXML file.
     */
    static void createSubWindow(String fxml, String subWindowTitle){
        
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
        subWindow.show();
 
    }
    
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static Image getAppIcon() {
        return appIcon;
    }
    
    public static void main(String[] args) {
        launch();
    }
        
}