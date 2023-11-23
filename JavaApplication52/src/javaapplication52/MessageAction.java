/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication52;

/**
 *
 * @author patap
 */
public class MessageAction implements Action {
    
    private String message;

    // Costruttore specifico per MessageAction
    public MessageAction() {
        this.message = "Hello world";
    }

    @Override
    public void execute() {
        System.out.println("Messaggio: " + message);
    }

    // Metodo per impostare il messaggio dopo la creazione
    public void setMessage(String message) {
        this.message = message;
    }
}

