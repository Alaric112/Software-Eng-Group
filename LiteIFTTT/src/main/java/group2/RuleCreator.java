package group2;

/**
 *
 * @author patap
 */
public final class RuleCreator {
    
    private GenericActionCreator genericActionCreator = new GenericActionCreator();
    private GenericTriggerCreator genericTriggerCreator = new GenericTriggerCreator();
    
    public enum ActionType {
        
        GenericAction
    }
    
    public enum TriggerType {
        
        GenericTrigger
    }

    private RuleCreator() {
    }
    
    
    
    // The field must be declared volatile so that double check lock would work
    // correctly.    
    private static volatile RuleCreator instance;
    
    public static RuleCreator getInstance() {
        // The approach taken here is called double-checked locking (DCL). It
        // exists to prevent race condition between multiple threads that may
        // attempt to get singleton instance at the same time, creating separate
        // instances as a result.
        //
        // It may seem that having the `result` variable here is completely
        // pointless. There is, however, a very important caveat when
        // implementing double-checked locking in Java, which is solved by
        // introducing this local variable.
        //
        // You can read more info DCL issues in Java here:
        // https://refactoring.guru/java-dcl-issue
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
    
    public void printHello(){
        
        System.out.println("Hello world");
    }
    
    public Rule createRule(String ruleName, TriggerType triggerType, ActionType actionType){
    
        Trigger trigger;
        Action action;
        
        switch(triggerType){    
            case GenericTrigger:
                trigger = triggerCreation(genericTriggerCreator);
                break;
            default:
                trigger = triggerCreation(genericTriggerCreator);
          
        }
        switch(actionType){           
            case GenericAction:
                action = actionCreation(genericActionCreator);
                break;
            default:
                action = actionCreation(genericActionCreator);
                         
        }
                        
        return new BaseRule(ruleName, trigger, action);
    }
    
    private Action actionCreation(ActionCreator actionCreator){
        
        return actionCreator.createAction();
    }
    
    private Trigger triggerCreation(TriggerCreator triggerCreator){
        
        return triggerCreator.createTrigger();
    }
}
