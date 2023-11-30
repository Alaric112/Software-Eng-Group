/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group2;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

public class DayWeekTrigger implements Trigger {

    private DayOfWeek targetDay;
   

    public DayWeekTrigger(DayOfWeek targetDay) {
        this.targetDay = targetDay;
    }

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
