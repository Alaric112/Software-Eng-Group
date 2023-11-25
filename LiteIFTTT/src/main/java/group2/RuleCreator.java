package group2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.scene.control.Control;

/**
 *
 * @author patap
 */
public final class RuleCreator {
    
    private static volatile RuleCreator instance;
    private Map<String, ActionCreator> actionFactoryMap;
    private Map<String, TriggerCreator> triggerFactoryMap;
    private String lastSelectedType;
    
   private RuleCreator() {
        // Costruttore privato per il singleton
        actionFactoryMap = new HashMap<>();
        triggerFactoryMap = new HashMap<>();
        
        initializeActionMap();
        initializeTriggerMap();
    }
    
    public static RuleCreator getInstance() {

        RuleCreator result = instance;
        if (result != null) {
            return result;
        }
        synchronized(RuleCreator.class) {
            if (instance == null) {
                instance = new RuleCreator();
            }
            return instance;
        }
    }

    private void initializeActionMap() {
        // Associa i tipi di azione alle rispettive implementazioni
        actionFactoryMap.put("message", new GenericActionCreator());
        actionFactoryMap.put("sound", new GenericActionCreator());

    }
   
    private void initializeTriggerMap() {
        // Associa i tipi di azione alle rispettive implementazioni
        triggerFactoryMap.put("Time", new TimeTriggerFactory());
    }
    
    public Rule createRule(String ruleName, Trigger trigger, Action action){
                                    
        return new BaseRule(ruleName, trigger, action);
    }
    
    public Action createAction(String actionType){
        
        // Ottieni il costruttore dell'azione associato al tipo
        ActionCreator actionFactory = actionFactoryMap.get(actionType);
        if (actionFactory  != null) {
            // Crea un'istanza dell'azione utilizzando il costruttore
            return actionFactory.createAction();
        } else {
            throw new IllegalArgumentException("Tipo di azione non supportato: " + actionType);
        }
        
    }
    
    public Trigger createTrigger(String triggerType){
        
        // Ottieni il costruttore del trigger associato al tipo
        TriggerCreator triggerFactory = triggerFactoryMap.get(triggerType);
        if (triggerFactory  != null) {
            // Crea un'istanza del trigger utilizzando il costruttore
            return triggerFactory.createTrigger();
        } else {
            throw new IllegalArgumentException("Tipo di trigger non supportato: " + triggerType);
        }
        
    }
    
//    public List<Control> createTriggerControl(String triggerType){
//        
//        // Ottieni il costruttore del trigger associato al tipo
//        TriggerCreator triggerFactory = triggerFactoryMap.get(triggerType);
//        if (triggerFactory  != null) {
//            // Crea un'istanza del trigger utilizzando il costruttore
//            return triggerFactory.createParameterControls();
//        } else {
//            throw new IllegalArgumentException("Tipo di trigger non supportato: " + triggerType);
//        }
//        
//    }    
    
    public List<String> getAvailableActionTypes() {
        return new ArrayList<>(actionFactoryMap.keySet());
    }

    public List<String> getAvailableTriggerTypes() {
        return new ArrayList<>(triggerFactoryMap.keySet());
    }

    public String getLastSelectedType() {
        return lastSelectedType;
    }

    public void setLastSelectedType(String lastSelectedType) {
        this.lastSelectedType = lastSelectedType;
    }   
    
}
