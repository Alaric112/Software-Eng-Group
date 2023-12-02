/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group2;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Faust
 */
public class FiredRule {
    
    private static Set<Rule> firedRule = new HashSet<>();

    public FiredRule() {
    
    }
    
    public void addRuleFired(Rule r){
        firedRule.add(r);
    }
    
    public boolean findRuleFired(Rule r){
        return firedRule.contains(r);
    }
    
    public void deleteRuleFired(Rule r){
        firedRule.remove(r);
    }
    
}
