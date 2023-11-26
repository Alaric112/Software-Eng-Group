/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group2;

import javafx.scene.control.Alert;
import javafx.application.Platform;
import javafx.stage.Stage;


/**
 * The <code>MessageAction</code> class implements the {@link Action} interface
 * and represents an action that displays a message in a JavaFX alert dialog.
 *
 * @author Faust
 * @see group2.Action
 */
public class MessageAction implements Action {
    
    private String messageInfo;

    /**
     * Constructs a new instance of the <code>MessageAction</code> class with
     * the default message "Hello world!".
     */    
    public MessageAction() {
        this.messageInfo = "Hello world!";
    }

    /**
     * Executes the action by displaying an alert with the specified message.
     * The execution is done on the JavaFX Application Thread.
     */    
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
    
    /**
     * Gets the message information associated with the action.
     *
     * @return The message information.
     */    
    public String getMessageInfo() {
        return messageInfo;
    }

    /**
     * Sets the message information for the action.
     *
     * @param messageInfo The new message information.
     */    
    public void setMessageInfo(String messageInfo) {
        this.messageInfo = messageInfo;
    }
        
}
