/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group2;

/**
 *
 * @author patap
 */
public class SoundActionCreator extends ActionCreator {

    @Override
    public Action createAction() {
        
        SoundAction soundAction = new SoundAction();
        soundAction.addObserver(new SoundActionController());
        return soundAction;
    }
      
}
