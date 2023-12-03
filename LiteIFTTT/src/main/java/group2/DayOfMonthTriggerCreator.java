/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group2;

/**
 *
 * @author patap
 */
public class DayOfMonthTriggerCreator extends TriggerCreator {

    @Override
    public Trigger createTrigger() {
        return new DayMonthTrigger();
    }
    
}
