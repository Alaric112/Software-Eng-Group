/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group2;

/**
 *
 * @author patap
 */
public class MessageActionCreator extends ActionCreator {

    @Override
    public Action createAction() {
                       
        MessageAction messageAction = new MessageAction();
       
        messageAction.addObserver(new MessageActionController());
        return messageAction;
    }
    
}
