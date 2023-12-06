package group2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * The {@code CompositeAction} class represents a composite action that follows
 * the Composite Design Pattern. It allows grouping multiple {@link Action}s
 * into a single composite action, treating both individual actions and
 * composite actions uniformly.
 * <p>
 * The class provides methods to add individual actions using {@code addAction}
 * and a collection of actions using {@code addActions}. The {@code execute}
 * method invokes the {@code execute} method of each contained action,
 * effectively executing the entire composite action.
 * 
 * The class also provides a method {@code getActions} to retrieve the list of
 * actions contained in the composite action.
 *
 * @author patap
 * @see Action
 * 
 */
public class CompositeAction implements Action {

    private List<Action> actions = new ArrayList<>();

    /**
     * Adds an individual action to the composite action.
     *
     * @param action The action to be added to the composite.
     */    
    public void addAction(Action action) {
        actions.add(action);
    }    

    /**
     * Adds a collection of actions to the composite action.
     *
     * @param actions The collection of actions to be added to the composite.
     */    
    public void addActions(Collection<Action> actions){
        
        this.actions.addAll(actions);
    }

    /**
     * Executes the composite action by invoking the {@code execute} method
     * of each contained action.
     */    
    @Override
    public void execute() {
        System.out.println("Executing composite action");
        for (Action action : actions) {
            action.execute();
        }
    }        

    /**
     * Retrieves the list of actions contained in the composite action.
     *
     * @return The list of actions in the composite.
     */    
    public List<Action> getActions() {
        return actions;
    }    
    
}
