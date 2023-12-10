/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group2.Model.Rule;

/**
 *
 * @author Alessandro Accarino
 */
public class TimeConversionUtil {
    
    private TimeConversionUtil(){
        
    }    
    /**
     * Converts the given time values to milliseconds.
     *
     * @param days    The number of days.
     * @param hours   The number of hours.
     * @param minutes The number of minutes.
     * @return The total time in milliseconds.
     */
    public static long convertToMilliseconds(int days, int hours, int minutes) {
        long millisecondsDays = days * 24L * 60 * 60 * 1000;
        long millisecondsHours = hours * 60 * 60 * 1000;
        long millisecondsMinutes = minutes * 60 * 1000;

        return millisecondsDays + millisecondsHours + millisecondsMinutes;
    }    
    
}
