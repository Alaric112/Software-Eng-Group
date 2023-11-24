/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group2;

import java.time.LocalTime;

/**
 *
 * @author Faust
 */
public class TimeTrigger implements Trigger{

    LocalTime targetTime;
    
    public TimeTrigger(int hour, int minute) {
         this.targetTime = LocalTime.of(hour, minute); 
    }
    
    @Override
    public boolean evaluate() {
        
         LocalTime currentTime = LocalTime.of(LocalTime.now().getHour(), LocalTime.now().getMinute());
            return currentTime.equals(targetTime);
    }
    
   
    
}
