/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group2;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author Faust
 */
public class TimeTriggerFactory extends TriggerCreator{

    private int hour = 0;
    private int min = 0;
    
    @Override
    public Trigger createTrigger() {
        return new TimeTrigger(hour, min);
    }
  
}
