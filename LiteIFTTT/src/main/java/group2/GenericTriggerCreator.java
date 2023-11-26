/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group2;

/**
 *
 * @author patap
 */
public class GenericTriggerCreator extends TriggerCreator {

    @Override
    public Trigger createTrigger() {
        return new GenericTrigger();
    }
    
    
//    @Override
//    public List<Control> createParameterControls() {
//        List<Control> controls = new ArrayList<>();
//        Label label = new Label("Generic Parameter:");
//        TextField textField = new TextField();
//        controls.add(label);
//        controls.add(textField);
//        return controls;
//    }

}
