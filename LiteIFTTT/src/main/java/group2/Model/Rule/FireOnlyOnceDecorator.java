/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group2.Model.Rule;

/**
 *
 * @author Faust
 */

/*
 * This class is a decorator for the provided Rule object in the constructor.
 * It restricts the execution of the decorated rule to only once unless its state is reset
 * through method calls from the base Rule class.
 */
public class FireOnlyOnceDecorator extends RuleDecorator {

    /**
     * Constructor of the class.
     *
     * @param rule Rule to decorate.
     */
    public FireOnlyOnceDecorator(Rule rule) {
        super(rule);
    }

    /**
     * Checks if the rule should be executed, adhering to the "onlyOnce"
     * condition. If the rule has already been executed and is active, it
     * deactivates it and sets the "onlyOnce" flag to false. Otherwise, it sets
     * "onlyOnce" to true.
     */
    @Override
    public void checkRule() {
        
            super.checkRule();
        if (super.isFired() && super.isActive()) {
            
            super.switchStatus();
        }

    }
}
