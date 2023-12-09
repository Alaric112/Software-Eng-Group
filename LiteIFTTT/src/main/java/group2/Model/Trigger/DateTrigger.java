/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group2.Model.Trigger;

import java.time.LocalDate;

/**
 * The {@code DateTrigger} class implements the {@code Trigger} interface
 * and represents a trigger based on a specific target date.
 * 
 * @author Faust
 */
public class DateTrigger implements Trigger{

    private LocalDate targetDate;

    public DateTrigger() {
        
    }
    
    /**
     * Evaluates whether the current date is equal to the target date.
     * 
     * @return {@code true} if the current date is equal to the target date, {@code false} otherwise.
     */
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
