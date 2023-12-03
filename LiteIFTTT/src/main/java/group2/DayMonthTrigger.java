/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group2;

/**
 *
 * @author 39334
 */
import java.time.LocalDateTime;
import java.time.MonthDay;

public class DayMonthTrigger implements Trigger {

    private MonthDay targetMonthDay;

    public DayMonthTrigger() {
        this.targetMonthDay = MonthDay.of(1, 1);
    }

    @Override
    public boolean evaluate() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        MonthDay currentMonthDay = MonthDay.from(currentDateTime);

        return currentMonthDay.equals(targetMonthDay);
    }

    public MonthDay getTargetMonthDay() {
        return targetMonthDay;
    }

    public void setTargetMonthDay(int month, int dayOfMonth) {
        this.targetMonthDay = MonthDay.of(month, dayOfMonth);
    }
}

