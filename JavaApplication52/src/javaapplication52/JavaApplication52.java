/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package javaapplication52;

import java.util.Scanner;

/**
 *
 * @author patap
 */
public class JavaApplication52 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       // Ottieni un'istanza della factory
        ActionCreator createAction = ActionCreator.getInstance();
       
        
        // Creazione di un'istanza di MessageAction
       // Action messageAction = createAction.createAction("message");
     //   messageAction.execute();

        // Creazione di un'istanza di AudioAction
        Action audioAction = createAction.createAction("audio");
        audioAction.execute();

        // Aggiungi un nuovo tipo di azione senza modificare la factory
        //createAction.addActionType("video", VideoAction::new);

        // Creazione di un'istanza di VideoAction
        //Action videoAction = createAction.createAction("video");
        //videoAction.execute();
        
                // Ottieni un'istanza della factory


        // Richiedi l'istanza di MessageAction
        MessageAction messageAction = (MessageAction) createAction.createAction("message");
        MessageAction messageAction2 = (MessageAction) createAction.createAction("message");
        // Interagisci con l'utente per ottenere il messaggio desiderato
        String userMessage = "Ciao Lorenzo";
        
        // Imposta il messaggio sull'istanza di MessageAction
        messageAction.setMessage(userMessage);

        // Esegui l'azione
        messageAction.execute();
        messageAction2.execute();
    }
    
}
