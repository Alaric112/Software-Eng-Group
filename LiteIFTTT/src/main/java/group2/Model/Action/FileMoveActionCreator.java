/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group2.Model.Action;

/**
 *
 * @author soniabruno
 */

public class FileMoveActionCreator extends ActionCreator {
    
    @Override
    public Action createAction() {
        return new FileMoveAction();
    }
}
