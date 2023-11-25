/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group2;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author patap
 */
public class Ruleset {
    private int timer;
    private String name;
    
    private ObservableList<Rule> rules = FXCollections.observableArrayList();
    public Ruleset(int timer, String name) {
        this.timer = timer;
        this.name = name;
    }
    
    public void addRule(Rule rule){
        
        rules.add(rule);
    }
    
    public void removeRule(int index){
        
        rules.remove(index);
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
}
