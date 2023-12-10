/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group2.Model.Trigger;

/**
 * The {@code DayMonthTrigger} class implements the {@code Trigger} interface
 * and represents a trigger based on a specific month and day combination.
 * 
 * @author Lore
 */
import java.time.LocalDate;
import java.time.MonthDay;

public class DayMonthTrigger implements Trigger {

    /** The target month and day for triggering an event. */
    private MonthDay targetMonthDay;

    /**
     * Constructs a new {@code DayMonthTrigger} with the default target month and day set to January 1st.
     */
    public DayMonthTrigger() {
        this.targetMonthDay = MonthDay.of(1, 1);
    }
    
    /**
     * Evaluates whether the current date's month and day match the target month and day.
     * 
     * @return {@code true} if the current date's month and day match the target, {@code false} otherwise.
     */
    @Override
    public boolean evaluate() {
        LocalDate currentDate = LocalDate.now();
        MonthDay currentMonthDay = MonthDay.of(currentDate.getMonthValue(), currentDate.getDayOfMonth());
        return currentMonthDay.equals(targetMonthDay);
    }
    
    /**
     * Gets the target month and day of the trigger.
     * 
     * @return The target month and day.
     */
    public MonthDay getTargetMonthDay() {
        return targetMonthDay;
    }
    
    /**
     * Sets the target month and day for the trigger.
     * 
     * @param month The target month.
     * @param dayOfMonth The target day of the month.
     */
    public void setTargetMonthDay(int month, int dayOfMonth) {
        this.targetMonthDay = MonthDay.of(month, dayOfMonth);
    }

    /**
     * Returns a string representation of the DayMonthTrigger object.
     *
     * @return A string representation of the DayMonthTrigger object.
     */
    @Override
    public String toString() {
        return "Day of Month selected:" + targetMonthDay;
    }
    
}
