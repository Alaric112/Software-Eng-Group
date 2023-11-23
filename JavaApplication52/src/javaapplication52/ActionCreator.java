/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication52;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 *
 * @author patap
 */
public class ActionCreator {
    
    private static ActionCreator instance;
    private final Map<String, ActionFactory> actionFactoryMap;

    private ActionCreator() {
        // Costruttore privato per il singleton
        actionFactoryMap = new HashMap<>();
        initializeActionMap();
    }

    private void initializeActionMap() {
        // Associa i tipi di azione alle rispettive implementazioni
        actionFactoryMap.put("message", new MessageActionFactory());
        actionFactoryMap.put("audio", new AudioActionFactory());
        // Aggiungi altri tipi di azione secondo necessità
    }

    public static ActionCreator getInstance() {
        if (instance == null) {
            instance = new ActionCreator();
        }
        return instance;
    }

    public Action createAction(String actionType) {
        // Ottieni il costruttore dell'azione associato al tipo
        ActionFactory actionFactory = actionFactoryMap.get(actionType);
        if (actionFactory  != null) {
            // Crea un'istanza dell'azione utilizzando il costruttore
            return actionFactory.createAction();
        } else {
            throw new IllegalArgumentException("Tipo di azione non supportato: " + actionType);
        }
    }
}