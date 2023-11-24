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
 * @author patap
 */
public class GenericTriggerCreator extends TriggerCreator {

    @Override
    public Trigger createTrigger() {
        return new GenericTrigger();
    }
    
    
    @Override
    public List<Control> createParameterControls() {
        List<Control> controls = new ArrayList<>();
        Label label = new Label("Generic Parameter:");
        TextField textField = new TextField();
        controls.add(label);
        controls.add(textField);
        return controls;
    }

}
