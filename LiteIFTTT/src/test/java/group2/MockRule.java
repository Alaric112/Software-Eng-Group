/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group2;

/**
 *
 * @author patap
 */
public class MockRule implements Rule {
    
    private String ruleName;
    private Trigger mockTrigger;
    private Action mockAction;
    private boolean active;
    private boolean checked;
    
    public MockRule(String ruleName, Trigger mockTrigger, Action mockAction) {
        this.ruleName = ruleName;
        this.mockTrigger = mockTrigger;
        this.mockAction = mockAction;
    }  
    
    @Override
    public void checkRule() {

        if(mockTrigger.evaluate() && active){
            
            mockAction.execute();
            checked = true;
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
    }
    
    public boolean isRuleChecked(){
        
        return checked;
    }
    
    @Override
    public boolean isActive(){
        
        return checked;
    }
    
}
