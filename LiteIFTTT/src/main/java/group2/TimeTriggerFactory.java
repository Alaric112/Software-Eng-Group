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

    @Override
    public List<Control> createParameterControls() {
        List<Control> controls = new ArrayList<>();
        Label labelh = new Label("Insert Hour:");
        TextField textFieldh = new TextField();
        Label labelm = new Label("Insert min:");
        TextField textFieldm = new TextField();
        controls.add(labelh);
        controls.add(textFieldh);
        controls.add(labelm);
        controls.add(textFieldm);
        return controls;
    }
  
}
