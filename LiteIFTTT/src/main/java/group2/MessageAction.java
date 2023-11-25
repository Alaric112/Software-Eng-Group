/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group2;

import javafx.scene.control.Alert;
import javafx.application.Platform;
import javafx.stage.Stage;


/**
 *
 * @author patap
 */
public class MessageAction implements Action {
    
    private String messageInfo;

    public MessageAction() {
        this.messageInfo = "Hello world!";
    }
    
    @Override
    public void execute() {
                 
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Message Action");
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(App.getAppIcon());
            alert.setHeaderText(null);
            alert.setContentText(messageInfo);
            alert.showAndWait();
        });

    }

    public String getMessageInfo() {
        return messageInfo;
    }

    public void setMessageInfo(String messageInfo) {
        this.messageInfo = messageInfo;
    }
        
}
