/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group2.Model.Trigger;

import java.time.LocalDate;

/**
 *
 * @author Faust
 */
public class DateTrigger implements Trigger{

    private LocalDate targetDate;

    public DateTrigger() {
        
    }
    
  
    @Override
    public boolean evaluate() {
        return targetDate.equals(LocalDate.now());
    }

    public LocalDate getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(LocalDate date) {              
        this.targetDate = date;
    }
       
}
