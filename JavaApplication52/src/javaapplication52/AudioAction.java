/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication52;

/**
 *
 * @author patap
 */
public class AudioAction implements Action {
    
    String ms;

    public AudioAction() {
        
        this.ms = "viva la musica";
    }
      
    @Override
    public void execute() {
        System.out.println(ms);
    }
}
