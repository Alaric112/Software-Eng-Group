/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group2.Model.Rule;

import group2.Model.Rule.Rule;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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
        setChanged();
        notifyObservers();        
    }
    
    public void removeRule(Rule rule){
        
        rules.remove(rule);
        System.out.println("OHI");
        setChanged();
        notifyObservers();        
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
       
        setChanged();
        notifyObservers();        
    }

    public void setRules(List<Rule> rules) {
        this.rules = rules;
    }    
    
    // Quando il timer cambia, chiamare questo metodo per notificare gli osservatori
    public void updateTimer() {
        setChanged();
        notifyObservers();
    }    
    
    @Override
    public void addObserver(Observer o) {
        super.addObserver(o);
    }

    @Override
    public void deleteObserver(Observer o) {
        super.deleteObserver(o);
    }    
    
}
