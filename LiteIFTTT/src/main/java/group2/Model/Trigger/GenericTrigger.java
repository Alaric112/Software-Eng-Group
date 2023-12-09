/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group2.Model.Trigger;

/**
 * The {@code GenericTrigger} class implements the {@code Trigger} interface
 * and represents a generic trigger with an activation state and an example string.
 * 
 * @author Alessandro Accarino
 */
public class GenericTrigger implements Trigger {

    private boolean active = true;
    
    private String esempio;
    
    @Override
    public boolean evaluate() {
        return active;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getEsempio() {
        return esempio;
    }

    public void setEsempio(String esempio) {
        this.esempio = esempio;
    }
        
}
