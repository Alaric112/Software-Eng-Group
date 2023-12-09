package group2.Model.Rule;

import group2.Model.Trigger.*;
import group2.Model.Action.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The {@code RuleCreator} class is responsible for creating instances of rules,
 * actions, and triggers in a rule-based system. It follows the Singleton
 * pattern to ensure a single instance of the class.
 *
 * <p>
 * This class allows the creation of rules using specified triggers and actions.
 * It also provides methods to create instances of actions and triggers based on
 * their respective types.</p>
 *
 * @author Alessandro Accarino
 */
public final class RuleCreator {

    private static volatile RuleCreator instance;
    private Map<String, ActionCreator> actionFactoryMap;
    private Map<String, TriggerCreator> triggerFactoryMap;
    private Map<String, BaseRuleCreator> ruleFactoryMap;
    private ControlRuleChecker checker = ControlRuleChecker.getInstance();
    
    private Map<String, Action> actionMacroMap;
    private Map<String, Trigger> triggerMacroMap;
    private Set<String> uniqueActionKey;
    
    /**
     * Private constructor for the singleton pattern. Initializes the action and
     * trigger factory maps.
     */
    private RuleCreator() {

        actionFactoryMap = new HashMap<>();
        triggerFactoryMap = new HashMap<>();
        ruleFactoryMap = new HashMap<>();
        actionMacroMap = new HashMap<>();
        triggerMacroMap = new HashMap<>();
        uniqueActionKey = new HashSet<>();
        
        initializeActionMap();
        initializeTriggerMap();
        initializeRuleMap();
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
        synchronized (RuleCreator.class) {
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
        actionFactoryMap.put("Execute Programm", new ExcProgrammActionCreator());

    }

    private void initializeTriggerMap() {
        // Associate trigger types with their respective implementations
        triggerFactoryMap.put("Time", new TimeTriggerFactory());
        triggerFactoryMap.put("Day of Week", new DayOfWeekTriggerCreator());
        triggerFactoryMap.put("Day of Month", new DayOfMonthTriggerCreator());
        triggerFactoryMap.put("File Exist", new FileTriggerCreator());
        triggerFactoryMap.put("File Size", new SizeFileTriggerFactory());
        triggerFactoryMap.put("Date", new DateTriggerFactory());
//        triggerFactoryMap.put("Exit Status", new Exit());

    }

    private void initializeRuleMap() {
        ruleFactoryMap.put("OnlyOnce", new FireOnlyOnceCreator());
        ruleFactoryMap.put("SleepingTime", new SleepingRuleCreator());
    }

    /**
     * Creates a rule instance based on the provided name,trigger,action type.
     *
     * @param ruleName
     * @param trigger
     * @param action
     * @param ruleType the type of rule to create
     * @return a new rule instance
     * @throws IllegalArgumentException if the rule type is not supported
     */
    public Rule createRule(String ruleName, Trigger trigger, Action action, String ruleType) {

        // Get the constructor of the action associated with the type
        BaseRuleCreator RuleFactory = ruleFactoryMap.get(ruleType);
        if (RuleFactory != null) {
            // Create an instance of the rule using the constructor          
            Rule r = RuleFactory.createRule(ruleName, trigger, action);
            addRuleTORuleSet(r);
            return r;
        } else {
            throw new IllegalArgumentException("Tipo di rule non supportato: " + ruleType);
        }

    }
    
    public CompositeAction createCompositeAction(Collection<Action> actions){
        
        CompositeAction compAction = new CompositeAction();
        compAction.addActions(actions);
        return compAction;
    }

    /**
     * Creates a rule with the specified name, trigger, and action.
     *
     * @param ruleName the name of the rule
     * @param trigger the trigger for the rule
     * @param action the action associated with the rule
     * @return 
     */    
    public Rule createRule(String ruleName, Trigger trigger, Action action){
                                    
        Rule rule = new BaseRule(ruleName, trigger, action);
        addRuleTORuleSet(rule);
        return rule;

    }

    private void addRuleTORuleSet(Rule rule) {

        RuleList ruleSet = checker.getRuleSet();
        if (ruleSet != null) {
            ruleSet.addRule(rule);
        }

    }

    /**
     * Creates an action instance based on the provided action type.
     *
     * @param actionType the type of action to create
     * @return a new action instance
     * @throws IllegalArgumentException if the action type is not supported
     */
    public Action createAction(String actionType) {

        // Get the constructor of the action associated with the type
        ActionCreator actionFactory = actionFactoryMap.get(actionType);
        if (actionFactory != null) {
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
    public Trigger createTrigger(String triggerType) {

        // Get the constructor of the trigger associated with the type
        TriggerCreator triggerFactory = triggerFactoryMap.get(triggerType);
        if (triggerFactory != null) {
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

    public List<String> getAvailableMacroAction() {
        return new ArrayList<>(actionMacroMap.keySet());
    } 

    public List<String> getAvailableMacroTrigger() {
        return new ArrayList<>(triggerMacroMap.keySet());
    }
    
    public boolean macroActionEmpty(){
        
        return actionMacroMap.isEmpty();
        
    }
    
    public boolean macroTriggerEmpty(){
        
       return triggerMacroMap.isEmpty(); 
    }    
    
    public Action getMacroAction(String key){
        
        return actionMacroMap.get(key);
    }

    public void registerActionMacro(String nameKey, Action action){
        
        if(uniqueActionKey.add(nameKey)){
            
            actionMacroMap.put(nameKey, action);
            System.out.println(actionMacroMap);
            //save operation 
        }
    }
    
}
