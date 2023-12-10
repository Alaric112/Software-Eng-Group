/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group2.Model.Trigger;

/**
 *
 * @author Faust
 */
public class TimeTriggerFactory extends TriggerCreator{

    private int hour = 0;
    private int min = 0;
    
    @Override
    public Trigger createTrigger() {
        return new TimeTrigger(hour, min);
    }
  
}
