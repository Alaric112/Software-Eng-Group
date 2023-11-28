/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group2;

import java.io.Serializable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * 
 * @author patap
 */
public class RuleSet implements Serializable {
    
    private int timer;
    private String name;    
    
    // Transint is needed because Observable list is NOT serializable
    transient private ObservableList<Rule> rules = FXCollections.observableArrayList();
    
    public RuleSet(int timer, String name) {
        this.timer = timer;
        this.name = name;     
     
    }
    
    public void addRule(Rule rule){
        
        rules.add(rule);
    }
    
    public void removeRule(Rule rule){
        
        rules.remove(rule);
    }
    
    public ObservableList<Rule> getRules(){
        
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
        
    }

    public void setRules(ObservableList<Rule> rules) {
        this.rules = rules;
    }    
    
}
