/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group2;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author patap
 */
public class Ruleset {
    
    private List rules = new ArrayList<Rule>();
    private int timer;
    
    private String name;

    public Ruleset(int timer) {
        this.timer = timer;
        this.name = "ciao";
    }
    
    
    
    public void addRule(Rule rule){
        
        rules.add(rule);
    }
    
    public void removeRule(int index){
        
        rules.remove(index);
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

    public int getTimer() {
        return timer;
    }

    public void setTimer(int timer) {
        this.timer = timer;
    }
}
