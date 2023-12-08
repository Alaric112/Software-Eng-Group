/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group2.Model.Trigger;

/**
 *
 * @author Alessandro Accarino
 */
public class FileTriggerCreator extends TriggerCreator {

    @Override
    public Trigger createTrigger() {
        return new FileTrigger();
    }
    
}
