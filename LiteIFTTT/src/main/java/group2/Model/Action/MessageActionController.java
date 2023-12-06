/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group2.Model.Action;

import group2.App;
import java.util.Observable;
import java.util.Observer;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

/**
 *
 * @author patap
 */
public class MessageActionController implements Observer {

    @Override
    public void update(Observable o, Object arg) {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Message Action");
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(App.getAppIcon());
            alert.setHeaderText(null);
            alert.setContentText((String) arg);
            alert.showAndWait();
        });
    }
    
}
