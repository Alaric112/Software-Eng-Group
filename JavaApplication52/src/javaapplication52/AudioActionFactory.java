/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication52;

/**
 *
 * @author patap
 */
public class AudioActionFactory implements ActionFactory {
    
    @Override
    public Action createAction() {
        return new AudioAction();
    }

}
