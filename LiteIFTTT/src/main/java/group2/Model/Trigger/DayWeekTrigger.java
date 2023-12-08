/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group2.Model.Trigger;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

/**
 * The `DayWeekTrigger` class represents an implementation of the `Trigger` interface
 * for triggering events based on a specific day of the week.
 *
 * @author Lore
 */
public class DayWeekTrigger implements Trigger {

    private DayOfWeek targetDay;

    public DayWeekTrigger() {
        this.targetDay = DayOfWeek.MONDAY;
    }

    /**
     * Evaluates whether the current day matches the target day for triggering an event.
     *
     * @return `true` if the current day matches the target day; otherwise, `false`.
     */    
    @Override
    public boolean evaluate() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DayOfWeek currentDay = currentDateTime.getDayOfWeek();

        return currentDay == targetDay;
    }

    public DayOfWeek getTargetDay() {
        return targetDay;
    }

    public void setTargetDay(DayOfWeek targetDay) {
        this.targetDay = targetDay;
    }
}
