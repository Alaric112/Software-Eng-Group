/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group2.Model.Rule;

import group2.Model.Action.Action;
import group2.Model.Trigger.Trigger;

/**
 *
 * @author Faust
 */
public class BaseRuleCreator {
    
    public Rule createRule(String ruleName, Trigger trigger, Action action){
          Rule rule = new BaseRule(ruleName, trigger, action);
          return rule;
    }
    
}
