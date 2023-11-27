/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * 
 * @author patap
 */
public class RuleSet {
    private int timer;
    private String name;
    
    private ObservableList<Rule> rules = FXCollections.observableArrayList();
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
    
}
