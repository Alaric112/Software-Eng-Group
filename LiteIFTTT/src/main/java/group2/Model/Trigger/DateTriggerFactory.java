/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group2.Model.Trigger;

import group2.Model.Trigger.TriggerCreator;
import group2.Model.Trigger.Trigger;

/**
 *
 * @author Faust
 */
public class DateTriggerFactory extends TriggerCreator{

    @Override
    public Trigger createTrigger() {
        return new DateTrigger();
    }
    
}
