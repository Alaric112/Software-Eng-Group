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

/**
 *
 * 
 * @author patap
 */
public class RuleSet extends Observable implements Serializable {
    
    private int timer;
    private String name;    
    private List<Rule> rules = new ArrayList<>();
    
    public RuleSet(int timer, String name) {
        this.timer = timer;
        this.name = name;     
     
    }
    
    public void addRule(Rule rule){
        
        rules.add(rule);
        changed();        
    }
    
    public void removeRule(Rule rule){
        
        rules.remove(rule);
        changed();        
    }
    
    public List<Rule> getRules(){
        
        return rules;
    }
    
    public int sizeRuleSet(){
        
        return rules.size();
    }
    
    public void clearRuleSet(){
        
        rules.clear();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTimer() {
        return timer;
    }

    public void setTimer(int timer) {
        this.timer = timer;
    }
    
    public void switchRuleStatus(Rule rule){
                
        int i = rules.indexOf(rule);
       
        rule = rules.get(i);
       
        rule.switchStatus();
       
        changed();        
    }

    public void setRules(List<Rule> rules) {
        this.rules = rules;
    }    
    
    // Quando il timer cambia, chiamare questo metodo per notificare gli osservatori
    public void updateTimer() {
        changed();
    }    
    
    @Override
    public void addObserver(Observer o) {
        super.addObserver(o);
    }

    @Override
    public void deleteObserver(Observer o) {
        super.deleteObserver(o);
    }    
    
    private void changed(){
        setChanged();
        notifyObservers();    
    }    

//    @Override
//    public int compareTo(RuleSet o) {
//        return this.name.compareTo(o.name); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + this.timer;
        hash = 97 * hash + Objects.hashCode(this.name);
        hash = 97 * hash + Objects.hashCode(this.rules);
        return hash;
    }

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
        final RuleSet other = (RuleSet) obj;
        if (this.timer != other.timer) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return Objects.equals(this.rules, other.rules);
    }


    
}
