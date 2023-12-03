/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group2;

/**
 *
 * @author Faust
 */
public class FireOnlyOnceDecorator extends RuleDecorator{
    
    private boolean onlyOnce;

    
    public FireOnlyOnceDecorator(Rule rule) {
        super(rule);       
       
    }
    
       @Override
    public void checkRule() {
        if (onlyOnce) {
            super.checkRule();                  
            super.switchStatus();             
        }     
    }

    public boolean isOnlyOnce() {
        return onlyOnce;
    }
    
    public void setOnlyOnce(boolean onlyOnce) {
        this.onlyOnce = onlyOnce;
    }


   
}
