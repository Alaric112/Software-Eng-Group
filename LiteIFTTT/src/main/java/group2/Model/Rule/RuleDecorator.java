/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group2.Model.Rule;

import group2.Model.Action.Action;
import group2.Model.Trigger.Trigger;

/**
 *
 * @author Lore
 */
public abstract class RuleDecorator implements Rule{

    
    private Rule rule;

    public RuleDecorator(Rule rule) {
        this.rule = rule;
    }
    
    
    
    @Override
    public void checkRule() {
        rule.checkRule();
    }

    @Override
    public String getName() {
        return rule.getName();
    }

    @Override
    public Trigger getTrigger() {
        return rule.getTrigger();
    }

    @Override
    public Action getAction() {
        return rule.getAction();
    }

    @Override
    public void switchStatus() {
        rule.switchStatus();
    }

    @Override
    public boolean isActive() {
        return rule.isActive();
    }
    
    
}


