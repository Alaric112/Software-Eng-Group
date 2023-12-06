/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group2.Model.Trigger;

/**
 *
 * @author Lore
 */
import group2.Model.Trigger.Trigger;
import java.time.LocalDate;
import java.time.MonthDay;

public class DayMonthTrigger implements Trigger {

    private MonthDay targetMonthDay;

    public DayMonthTrigger() {
        this.targetMonthDay = MonthDay.of(1, 1);
    }
    
    @Override
    public boolean evaluate() {
        LocalDate currentDate = LocalDate.now();
        MonthDay currentMonthDay = MonthDay.of(currentDate.getMonthValue(), currentDate.getDayOfMonth());
        return currentMonthDay.equals(targetMonthDay);
    }
    
    public MonthDay getTargetMonthDay() {
        return targetMonthDay;
    }

    public void setTargetMonthDay(int month, int dayOfMonth) {
        this.targetMonthDay = MonthDay.of(month, dayOfMonth);
    }
}
