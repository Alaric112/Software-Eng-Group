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
public class SleepingRuleCreator extends BaseRuleCreator{
    
    @Override
    public SleepingRuleDecorator createRule(String nameRule,Trigger trigger,Action action){
            return new SleepingRuleDecorator(super.createRule(nameRule,trigger,action));
    }
}