
package group2.Model.Trigger;

/**
 *
 * @author patap
 */
public class ExitStatusTriggerCreator extends TriggerCreator {

    @Override
    public Trigger createTrigger() {
        return new ExitStatusTrigger();
    }
    
}
