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
    private boolean repeat;
    
    public FireOnlyOnceDecorator(Rule rule) {
        super(rule);
        onlyOnce = false; 
        repeat = false;
    }
    
       @Override
    public void checkRule() {
        if (onlyOnce && isActive()) {
            if(!repeat){
                super.checkRule();                  
                super.switchStatus();
                repeat = true;}
        }     
    }

    public boolean isOnlyOnce() {
        return onlyOnce;
    }
    
    public boolean isRepeated() {
        return repeat;
    }

    public void setOnlyOnce(boolean onlyOnce) {
        this.onlyOnce = onlyOnce;
    }


   
}
