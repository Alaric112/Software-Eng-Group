/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group2;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

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
