/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group2.Model.Rule;

import group2.Model.Rule.Rule;
import group2.RuleDecorator;

/**
 *
 * @author Faust
 */
public class FireOnlyOnceDecorator extends RuleDecorator{
    
    private boolean onlyOnce;

    
    public FireOnlyOnceDecorator(Rule rule) {
        super(rule);     
        onlyOnce = false;
    }
    
       @Override
    public void checkRule() {

            if(super.isActive())
                     onlyOnce = false;
            if(!onlyOnce){  
                super.checkRule();    
                super.switchStatus();
                onlyOnce = true;
                }
            }     
    }


 
