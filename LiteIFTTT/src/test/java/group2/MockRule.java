 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group2;

import group2.Model.Action.Action;
import group2.Model.Trigger.Trigger;
import group2.Model.Rule.Rule;

/**
 *
 * @author Alessandro Accarino
 */
public class MockRule extends Rule {
    
    private String ruleName;
    private Trigger mockTrigger;
    private Action mockAction;
    private boolean active;
    private boolean checked;
    private boolean fired;
    
    public MockRule(String ruleName) {
        this.ruleName = ruleName;
        this.mockTrigger = new MockTrigger();
        this.mockAction = new MockAction();
        active = true;
        fired = false;
    }  
    
    @Override
    public void checkRule() {
        if(mockTrigger.evaluate() && active){            
            mockAction.execute();
            checked = true;   
            fired = true;
        }
    }

    @Override
    public String getName() {
        return ruleName;
    }

    @Override
    public Trigger getTrigger() {
        return mockTrigger;
    }

    @Override
    public Action getAction() {
        return mockAction;
    }

    @Override
    public void switchStatus() {
        this.active = !this.active;
        fired = false;
    }
    
    public boolean isRuleChecked(){       
        return checked;
    }
    
    @Override
    public boolean isActive(){       
        return active;
    }
    
    public void setActive(boolean active){
        this.active = active;
    }
    
    public boolean isOn(){
        return active;
    }
    
    public boolean isFired() {
        return fired;
    }
    
}
