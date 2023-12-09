/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group2.Model.Rule;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Observable;
import java.util.Observer;

/*
 * The RuleList class represents a collection of rules with an associated timer and name.
 * It extends the Observable class to allow observers to be notified when the state of the RuleList changes.
 * This class implements the Serializable interface to support serialization.
 *
 * <p>
 * A RuleList contains a list of Rule objects and provides methods to add, remove, retrieve, and manipulate rules.
 * The timer property represents the time interval associated with the RuleList.
 * Observers can register with the RuleList to receive notifications when the rules or timer change.
 * </p>
 *
 * @author Alessandro Accarino
 */
public class RuleList extends Observable implements Serializable {
    
    private int timer;
    private String name;    
    private List<Rule> rules = new ArrayList<>();

    /**
     * Constructs a new RuleList with the specified timer and name.
     *
     * @param timer The time interval associated with the RuleList.
     * @param name  The name of the RuleList.
     */    
    public RuleList(int timer, String name) {
        this.timer = timer;
        this.name = name;     
     
    }

    /**
     * Adds a rule to the RuleList.
     *
     * @param rule The rule to be added.
     */    
    public void addRule(Rule rule){
        
        rules.add(rule);
        changed();        
    }
 
    /**
     * Removes a rule from the RuleList.
     *
     * @param rule The rule to be removed.
     */    
    public void removeRule(Rule rule){
        
        rules.remove(rule);
        changed();        
    }
 
    /**
     * Retrieves the list of rules in the RuleList.
     *
     * @return The list of rules.
     */    
    public List<Rule> getRules(){
        
        return rules;
    }

    /**
     * Retrieves the number of rules in the RuleList.
     *
     * @return The size of the RuleList.
     */    
    public int sizeRuleSet(){
        
        return rules.size();
    }

    /**
     * Clears all rules from the RuleList.
     */    
    public void clearRuleSet(){
        
        rules.clear();
        changed();
    }

    /**
     * Gets the name of the RuleList.
     *
     * @return The name of the RuleList.
     */    
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the RuleList.
     *
     * @param name The new name for the RuleList.
     */    
    public void setName(String name) {
        this.name = name;
        changed();
    }

    /**
     * Gets the timer associated with the RuleList.
     *
     * @return The timer value.
     */    
    public int getTimer() {
        return timer;
    }

    /**
     * Sets the timer associated with the RuleList.
     *
     * @param timer The new timer value.
     */    
    public void setTimer(int timer) {
        this.timer = timer;
        changed();
    }

    /**
     * Switches the status of a rule in the RuleList.
     *
     * @param rule The rule whose status should be switched.
     */    
    public void switchRuleStatus(Rule rule){
                
        int i = rules.indexOf(rule);
       
        rule = rules.get(i);
       
        rule.switchStatus();
       
        changed();        
    }

    public void setRules(List<Rule> rules) {
        this.rules = rules;
    }   

    /**
     * {@inheritDoc}
     */    
    @Override
    public void addObserver(Observer o) {
        super.addObserver(o);
    }

    /**
     * {@inheritDoc}
     */    
    @Override
    public void deleteObserver(Observer o) {
        super.deleteObserver(o);
    }    
 
    /**
     * Private internal method
     * Notifies observers that the RuleList has changed.
     */    
    private void changed(){
        setChanged();
        notifyObservers();    
    }    

    /**
     * {@inheritDoc}
     */    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + this.timer;
        hash = 97 * hash + Objects.hashCode(this.name);
        hash = 97 * hash + Objects.hashCode(this.rules);
        return hash;
    }

    /**
     * {@inheritDoc}
     */    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final RuleList other = (RuleList) obj;
        if (this.timer != other.timer) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return Objects.equals(this.rules, other.rules);
    }
    
}
