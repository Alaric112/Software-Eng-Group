package group2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The {@code RuleCreator} class is responsible for creating instances of rules,
 * actions, and triggers in a rule-based system.
 * It follows the Singleton pattern to ensure a single instance of the class.
 *
 * <p>This class allows the creation of rules using specified triggers and actions.
 * It also provides methods to create instances of actions and triggers based on
 * their respective types.</p>
 *
 * Example Usage:
 * 
 * {@code
 * RuleCreator ruleCreator = RuleCreator.getInstance();
 * List<String> availableActionTypes = ruleCreator.getAvailableActionTypes();
 * List<String> availableTriggerTypes = ruleCreator.getAvailableTriggerTypes();
 * Action myAction = ruleCreator.createAction("Message");
 * Trigger myTrigger = ruleCreator.createTrigger("Time");
 * Rule myRule = ruleCreator.createRule("MyRule", myTrigger, myAction);
 * }
 * 
 * @author patap
 */
public final class RuleCreator {
    
 
    private static volatile RuleCreator instance;
    private Map<String, ActionCreator> actionFactoryMap;
    private Map<String, TriggerCreator> triggerFactoryMap;

    /**
     * Private constructor for the singleton pattern.
     * Initializes the action and trigger factory maps.
     */    
   private RuleCreator() {
        
        actionFactoryMap = new HashMap<>();
        triggerFactoryMap = new HashMap<>();
        
        initializeActionMap();
        initializeTriggerMap();
    }
   
    /**
     * Returns the singleton instance of the RuleCreator.
     *
     * @return the singleton instance of RuleCreator
     */   
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
        // Associate action types with their respective implementations
        actionFactoryMap.put("Message", new MessageActionCreator());
        actionFactoryMap.put("Sound", new SoundActionCreator());
        actionFactoryMap.put("Text Append", new TextAppendActionCreator());
        actionFactoryMap.put("File Copy", new FileCopyActionCreator());
        actionFactoryMap.put("File Move", new FileMoveActionCreator());
        actionFactoryMap.put("File Delete", new FileDeleteActionCreator());
        
    }
   
    private void initializeTriggerMap() {
        // Associate trigger types with their respective implementations
        triggerFactoryMap.put("Time", new TimeTriggerFactory());
        triggerFactoryMap.put("Day of Week", new DayOfWeekTriggerCreator());
        triggerFactoryMap.put("File Exist", new FileTriggerCreator());
        triggerFactoryMap.put("Date", new FileTriggerCreator());
                
    }

    /**
     * Creates a rule with the specified name, trigger, and action.
     *
     * @param ruleName the name of the rule
     * @param trigger the trigger for the rule
     * @param action the action associated with the rule
     * @return a new rule instance
     */    
    public Rule createRule(String ruleName, Trigger trigger, Action action){
                                    
        return new BaseRule(ruleName, trigger, action);
    }

    /**
     * Creates an action instance based on the provided action type.
     *
     * @param actionType the type of action to create
     * @return a new action instance
     * @throws IllegalArgumentException if the action type is not supported
     */    
    public Action createAction(String actionType){
        
        // Get the constructor of the action associated with the type
        ActionCreator actionFactory = actionFactoryMap.get(actionType);
        if (actionFactory  != null) {
            // Create an instance of the action using the constructor
            return actionFactory.createAction();
        } else {
            throw new IllegalArgumentException("Tipo di azione non supportato: " + actionType);
        }
        
    }

    /**
     * Creates a trigger instance based on the provided trigger type.
     *
     * @param triggerType the type of trigger to create
     * @return a new trigger instance
     * @throws IllegalArgumentException if the trigger type is not supported
     */    
    public Trigger createTrigger(String triggerType){
        
        // Get the constructor of the trigger associated with the type
        TriggerCreator triggerFactory = triggerFactoryMap.get(triggerType);
        if (triggerFactory  != null) {
            // Create an instance of the trigger using the constructor
            return triggerFactory.createTrigger();
        } else {
            throw new IllegalArgumentException("Tipo di trigger non supportato: " + triggerType);
        }
        
    }

    /**
     * Returns a list of available action types.
     *
     * @return a list of available action types
     */    
    public List<String> getAvailableActionTypes() {
        return new ArrayList<>(actionFactoryMap.keySet());
    }

    /**
     * Returns a list of available trigger types.
     *
     * @return a list of available trigger types
     */    
    public List<String> getAvailableTriggerTypes() {
        return new ArrayList<>(triggerFactoryMap.keySet());
    }
    
}
